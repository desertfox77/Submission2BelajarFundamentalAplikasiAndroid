package com.example.subdua.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.example.subdua.R;
import com.example.subdua.fragment.FollowersFragment;
import com.example.subdua.fragment.FollowingFragment;

import java.util.Objects;

public class PageAdapter extends FragmentPagerAdapter {
    private final Context context;
    public PageAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new FollowersFragment();
                break;
            case 1:
                fragment = new FollowingFragment();
                break;
        }
        return Objects.requireNonNull(fragment);
    }


    private final int[] TAB_NAME = new int[]{
            R.string.fragmentfollowers,
            R.string.fragmentfollowing
    };
    @Override
    public int getCount() {
        return 2;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_NAME[position]);
    }
}

