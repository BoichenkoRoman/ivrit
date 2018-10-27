package roman.boichenko.remindme.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import roman.boichenko.remindme.fragments.ExampleFragment;

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
                return ExampleFragment.getInstanse();
            case 1:
                return ExampleFragment.getInstanse();
            case 2:
                return ExampleFragment.getInstanse();
            case 3:
                return ExampleFragment.getInstanse();
        }

        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
