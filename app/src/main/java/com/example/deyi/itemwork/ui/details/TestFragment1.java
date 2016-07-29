package com.example.deyi.itemwork.ui.details;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

import com.example.deyi.itemwork.ui.frame.ContentActivity;
import com.example.deyi.itemwork.bean.FragmentBean;
import com.example.deyi.itemwork.MyApplication;
import com.example.deyi.itemwork.R;

/**
 * Created by lerendan on 2015/7/29.
 * 测试窗口界面1
 */
public class TestFragment1 extends BaseFragment {

    private Button button1;
    private View contentPanel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_text1, container, false);

        contentPanel = rootview.findViewById(R.id.contentPanel);
        contentPanel.setDrawingCacheEnabled(true);
        button1 = (Button) rootview.findViewById(R.id.button1);

        //X按钮点击事件
        button1.setOnClickListener(new View.OnClickListener() {
            public boolean added = false;

            @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            @Override
            public void onClick(View view) {
                String data = "测试数据1";
//                Bitmap bmp = contentPanel.getDrawingCache();     这种方法出错不显示图片
                Bitmap bmp = Bitmap.createBitmap(contentPanel.getDrawingCache());

                if (bmp != null) {
                    FragmentBean bean = new FragmentBean();
                    bean.setKey(data);
                    bean.setFragment(TestFragment1.this);

                    for (FragmentBean temp : MyApplication.fragmentBeans) {
                        if (temp.getKey().equals(data)) {
                            added = true;
                            break;
                        }
                    }
                    if (!added) {
                        MyApplication.bitmapLruCache.put(data, bmp);
                        MyApplication.fragmentBeans.add(bean);
                        ((ContentActivity) getActivity()).notifyGirdView();
                    }

                }

                smallAnim(contentPanel);
//                view.animate().
            }

        });

        return rootview;
    }

    /**
     * 点击后缩小然后到gridview中
     */
    void smallAnim(View view) {
        Animation animation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.ZORDER_BOTTOM, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ((ContentActivity) getActivity()).showGridFragment();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);

    }

    @Override
    public Fragment newInstance() {
        return new TestFragment1();
    }
}
