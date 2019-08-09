package com.omega_r.omegaintentbuilder;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

//import com.omega_r.libs.omegafragmentbuilder.AppOmegaFragmentBuilder;
import com.omega_r.omegaintentbuilder.fragments.FirstFragment;
import com.omega_r.omegaintentbuilder.fragments.SecondFragment;

import omega.com.annotations.OmegaActivity;

@OmegaActivity
public class TabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

//        private FirstFragment firstFragment = AppOmegaFragmentBuilder.firstFragment()
//                                                                     .value("First fragment")
//                                                                     .createFragment();
//
//        private SecondFragment secondFragment = AppOmegaFragmentBuilder.secondFragment()
//                                                                       .value("Second fragment")
//                                                                       .createFragment();

        private SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return null;
//            return (position == 0) ? firstFragment : secondFragment;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return (position == 0) ? "First" : "Second";
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
