package com.example.deyi.itemwork;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 但超 on 2015/7/29.
 */
public class MyApplication extends Application {

    public static BitmapLruCache bitmapLruCache ;
    public static List<FragmentBean> fragmentBeans;


    @Override
    public void onCreate() {
        super.onCreate();
        long parent = Runtime.getRuntime().maxMemory();
        bitmapLruCache = new BitmapLruCache((int)(parent*0.3));
        fragmentBeans = new ArrayList<FragmentBean>();

    }


}
