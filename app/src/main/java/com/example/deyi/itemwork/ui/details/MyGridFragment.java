package com.example.deyi.itemwork.ui.details;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.deyi.itemwork.ui.frame.ContentActivity;
import com.example.deyi.itemwork.bean.FragmentBean;
import com.example.deyi.itemwork.MyApplication;
import com.example.deyi.itemwork.adapter.MyGridAdapter;
import com.example.deyi.itemwork.R;

import java.util.List;

/**
 * Created by lerendan on 2015/7/29.
 * 窗口GridFragment
 */
public class MyGridFragment extends Fragment {

    private GridView gridView;
    private MyGridAdapter myGridAdapter;

    //push_left_in右边的Item向左平移  push_right_in左边的Item向右上移动
    private Animation push_left_in, push_right_in;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_grid, container, false);


        push_left_in = AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_in);
        push_right_in = AnimationUtils.loadAnimation(getActivity(), R.anim.push_right_in);

        gridView = (GridView) view.findViewById(R.id.gridView);
        myGridAdapter = new MyGridAdapter(getActivity(), MyApplication.fragmentBeans, new MyGridAdapter.GridviewClickListener() {
            @Override
            public void onDeleteBtnClick(int position, ViewGroup viewGroup, Fragment fragment) {

                List<FragmentBean> mList = MyApplication.fragmentBeans;

                //position是删除位置，i是删除位置的后一个位置，从i所在的Item开始动画
                int i = position + 1;
                //左边Item向右上移动，右边Item向左平移
                for (; i < mList.size(); i++) {
                    if (i % 2 == 0) {
                        viewGroup.getChildAt(i - 1).startAnimation(push_right_in);
                    } else {
                        viewGroup.getChildAt(i - 1).startAnimation(push_left_in);
                    }

                }

                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.remove(fragment);
                transaction.commitAllowingStateLoss();
            }

        });

        gridView.setAdapter(myGridAdapter);


        //GridView中Item点击事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String data = myGridAdapter.getItemName(i);
//                FragmentBean bean = (FragmentBean) myGridAdapter.getItem(i);

//                try {
//                    ((ContentActivity) getActivity()).showDetailFragment(data, bean.getFragment().getClass());
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (java.lang.InstantiationException e) {
//                    e.printStackTrace();
//                }
                bigItemAnim(view, i);

            }
        });


        return view;
    }

    /**
     * Item点击后放大动画
     */
    public void bigItemAnim(View view, int i) {
        final int position = i;
//
//        Rect rect = new Rect();
//        view.getGlobalVisibleRect(rect);
//        int left = rect.left;
//        int right = rect.right;
//        int top = rect.top;
//        int bottom = rect.bottom;
//        int height = gridView.getMeasuredHeight();
//        int width = gridView.getMeasuredWidth();


        Animation animation;
        if (position % 2 == 0) {
            animation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f, Animation.ABSOLUTE, 0.5f, Animation.ABSOLUTE, 0.5f);
        } else {
            animation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.ABSOLUTE, 0.5f);
        }
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setDuration(500);


        animation.setAnimationListener(new Animation.AnimationListener() {

            String data = myGridAdapter.getItemName(position);
            FragmentBean bean = (FragmentBean) myGridAdapter.getItem(position);

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                try {
                    ((ContentActivity) getActivity()).showDetailFragment(data, bean.getFragment().getClass());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        view.startAnimation(animation);

    }



    public void notifyGirdView() {
        myGridAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

}