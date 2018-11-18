package roman.boichenko.ivrit.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import roman.boichenko.ivrit.fragments.About;

public class TabsPagerFragmentAdapter extends FragmentPagerAdapter {

    private String[] tabs;

    public TabsPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[]{"Tab 1", "Напоминание", "tab 3", "tab 4"};


    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return About.getInstanse();
            case 1:
                return About.getInstanse();
            case 2:
                return About.getInstanse();
            case 3:
                return About.getInstanse();
        }

        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
