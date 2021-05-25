package com.test.twoappfragment;

import androidx.fragment.app.Fragment;

public class MainActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new UserListFragment();
    }
}