package com.test.twoappfragment;

import androidx.fragment.app.Fragment;

public class UserActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        Fragment fragment = new UserFragment();
        return fragment;
    }
}