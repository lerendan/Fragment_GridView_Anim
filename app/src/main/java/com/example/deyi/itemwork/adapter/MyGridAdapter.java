package com.example.deyi.itemwork.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.deyi.itemwork.MyApplication;
import com.example.deyi.itemwork.R;
import com.example.deyi.itemwork.bean.FragmentBean;

import java.util.List;

/**
 * Created by lerendan on 2015/7/29.
 * GridView的适配器
 */
public class MyGridAdapter extends BaseAdapter {

    int seletedId = -1;

    private List<FragmentBean> mList;
    private LayoutInflater minflater;
    private final GridviewClickListener listener;

    Animation testAnim;

    public interface GridviewClickListener {
        void onDeleteBtnClick(int position, ViewGroup viewGroup, Fragment fragment);
    }

    public MyGridAdapter(Context context, List<FragmentBean> data, GridviewClickListener l) {
        mList = data;
        minflater = LayoutInflater.from(context);
        listener = l;

        testAnim = AnimationUtils.loadAnimation(context, R.anim.slide_top_to_bottom);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(final int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 获取Item带的Key
     */
    public String getItemName(int position) {
        return mList.get(position).getKey();
    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = minflater.inflate(R.layout.item_grid, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView2);
            viewHolder.close = (ImageView) convertView.findViewById(R.id.close);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        //viewHolder.imageView.setImageResource(getItem(position));
        final FragmentBean bean = (FragmentBean) getItem(position);

        Bitmap bitmap = MyApplication.bitmapLruCache.get(bean.getKey());

        viewHolder.imageView.setImageBitmap(bitmap);
        viewHolder.close.setImageResource(R.mipmap.red);


        /**
         * GridView中删除位移动画
         */
        viewHolder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onDeleteBtnClick(position, viewGroup, bean.getFragment());

                mList.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    class ViewHolder {
        public ImageView imageView, close;
    }

}


