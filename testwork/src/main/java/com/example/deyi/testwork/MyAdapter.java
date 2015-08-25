package com.example.deyi.testwork;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 20150519 on 2015/8/21.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private List<String> datas;

    public MyAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_re,viewGroup,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.tv.setText(datas.get(i));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


}

class MyViewHolder extends RecyclerView.ViewHolder {

    TextView tv ;

    public MyViewHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.id_tv);
    }
}
