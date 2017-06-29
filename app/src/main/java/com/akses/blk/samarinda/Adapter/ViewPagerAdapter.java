package com.akses.blk.samarinda.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.akses.blk.samarinda.Fragments.FragmentFour;
import com.akses.blk.samarinda.Fragments.FragmentOne;
import com.akses.blk.samarinda.Fragments.FragmentThree;
import com.akses.blk.samarinda.Fragments.FragmentTwo;

/**
 * Created by ASUS on 09/05/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[]{"Berita", "Pelatihan", "Pencari", "Perusahaan"};
    private Context context;

    public ViewPagerAdapter(FragmentManager fragmentManager, Context context){
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public int getCount(){
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position){
        if (position == 0){
            return new FragmentOne();
        } else if (position == 1){
            return new FragmentTwo();
        } else if (position == 2){
            return new FragmentThree();
        } else {
            return new FragmentFour();
        }
    }

    @Override
    public CharSequence getPageTitle(int position){
        return tabTitles[position];
    }
}
