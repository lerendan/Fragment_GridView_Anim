package com.example.deyi.itemwork.ui.details;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.deyi.itemwork.ui.frame.ContentActivity;
import com.example.deyi.itemwork.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lerendan on 2015/7/29.
 * 列表ListFragment
 */
public class MyListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView myListView;
    private List<String> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main, container, false);
        myListView = (ListView) view.findViewById(R.id.listView);
        data = getData();
        myListView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, data));
        myListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 设置Listview数据
     * @return 数据列表
     */
    private List<String> getData() {
        List<String> data = new ArrayList<String>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");
        return data;
    }

    /**
     * 设置各个Item点击事件
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Adapter adapter = adapterView.getAdapter();
        String data = (String) adapter.getItem(i);

        Fragment fragment = null;

        if (data.equals("测试数据1")) {
            fragment = new TestFragment1();
        }
        if (data.equals("测试数据2")) {
            fragment = new TestFragment2();
        }
        if (data.equals("测试数据3")) {
            fragment = new TestFragment3();
        }
        if (data.equals("测试数据4")) {
            fragment = new TestFragment4();
        }

        try {
            ((ContentActivity) getActivity()).showDetailFragment(data, fragment.getClass());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }

    }

}
