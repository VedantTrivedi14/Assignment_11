package com.tatvasoftassignment.assignment_11.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.tatvasoftassignment.assignment_11.Fragment.AudioFragment;
import com.tatvasoftassignment.assignment_11.Fragment.ContactsFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    Context ctx;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, Context ctx) {
        super(fragmentActivity);
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new AudioFragment(ctx);
        }
        return new ContactsFragment(ctx);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
