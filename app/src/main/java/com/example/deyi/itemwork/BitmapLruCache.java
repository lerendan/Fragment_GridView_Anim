package com.example.deyi.itemwork;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by lerendan on 2015/7/29.
 */
public class BitmapLruCache extends LruCache<String,Bitmap> {

    public BitmapLruCache(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return  value.getHeight() * value.getRowBytes();
    }



}
