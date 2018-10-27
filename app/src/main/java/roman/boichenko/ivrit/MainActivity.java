package roman.boichenko.ivrit;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;


import roman.boichenko.ivrit.adapter.TabsPagerFragmentAdapter;
import roman.boichenko.ivrit.fragments.About;
import roman.boichenko.ivrit.fragments.ExampleFragment;
import roman.boichenko.ivrit.fragments.Test2;

public class MainActivity extends AppCompatActivity {
    private static final int LAYOUT = R.layout.activity_main;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    FragmentManager fragmentManager;
    private ViewPager viewPager;
    public static FrameLayout fragment_container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault2); //  указали какая тема по умолчанию

        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        fragment_container = findViewById(R.id.fragment_container);
        /* */


        initToolbar();
        initNavigationView();
        //   initTabs();


        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .add(R.id.fragment_container, new About(), "AddNewMazgan")
                .commit();


    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //  drawerLayout.setScrimColor(3333);
        // drawerLayout.setDrawerElevation(0f);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.view_navigation_open, R.string.view_navigation_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();
                //  переход по меню из  NavigationView
                switch (menuItem.getItemId()) {
                    case R.id.test2:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.fragment_container, new Test2(), "Test2")
                                .commit();

                        break;
                    case R.id.about:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.fragment_container, new About(), "About")
                                .commit();

                        break;
                        case R.id.ExampleFragment:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.fragment_container, ExampleFragment.getInstanse(), "About")
                                .commit();

                        break;

                }


                return true;
            }
        });
    }

    private void initTabs() {
        /*
        viewPager = findViewById(R.id.viewPager);


        TabsPagerFragmentAdapter adapter = new TabsPagerFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager); // связываем  tabLayout   с  viewPager
        */
    }


    private void showNotificationTab() {
        viewPager.setCurrentItem(Constant.TAB_TWO);
    }
}
