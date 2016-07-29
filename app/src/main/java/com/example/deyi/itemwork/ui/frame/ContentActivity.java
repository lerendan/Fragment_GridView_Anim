package com.example.deyi.itemwork.ui.frame;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.example.deyi.itemwork.R;
import com.example.deyi.itemwork.ui.details.MyGridFragment;
import com.example.deyi.itemwork.ui.details.MyListFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 但超 on 2015/8/7.
 * 主Activity
 */
public class ContentActivity extends AppCompatActivity {

    private MyListFragment myListFragment;
    private MyGridFragment gridFragment;

    Map<String, Fragment> fragmentMap = new HashMap<>();

    public Map<String, Fragment> getFragmentList() {
        return fragmentMap;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        setDefaultFragment();
    }

    /**
     * 设置首页Fragment
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        myListFragment = new MyListFragment();
        transaction.add(R.id.mycontent, myListFragment);
        gridFragment = new MyGridFragment();
        transaction.add(R.id.mycontent, gridFragment);
        transaction.hide(gridFragment);
        transaction.show(myListFragment);
        transaction.commitAllowingStateLoss();
    }


    /**
     * 更新GridView
     */
    public void notifyGirdView() {
        gridFragment.notifyGirdView();
    }


    /**
     * 显示窗口管理GridFragment
     */
    public void showGridFragment() {
        gridFragment.notifyGirdView();
        showFragment(gridFragment);
    }


    /**
     * 显示列表管理ListFragment
     */
    public void showListFragment() {
        showFragment(myListFragment);
    }

    public void showDetailFragment(String key, Class clazz) throws IllegalAccessException, InstantiationException {
        Fragment fragment = null;
        if (fragmentMap.containsKey(key)) {
            fragment = fragmentMap.get(key);
        } else {
            fragment = (Fragment) clazz.newInstance();
            fragmentMap.put(key, fragment);
        }
        showFragment(fragment);
    }

    /**
     * 设置返回键跳转
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showListFragment();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 显示指定Fragment
     */
    private void showFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        List<Fragment> list = fm.getFragments();
        boolean IsfragmentInMem = false;
        for (Fragment f : list) {
            if (f != null) {
                transaction.hide(f);
                if (f.equals(fragment)) IsfragmentInMem = true;
            }

        }
        if (!IsfragmentInMem) {
            transaction.add(R.id.mycontent, fragment);
        }
        transaction.show(fragment);
        transaction.commitAllowingStateLoss();
    }

}
