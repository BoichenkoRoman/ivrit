package roman.boichenko.ivrit;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.idescout.sql.SqlScoutServer;

import roman.boichenko.ivrit.DTO.BD.AbcDB;
import roman.boichenko.ivrit.DTO.BD.WordDB;
import roman.boichenko.ivrit.External_storage.write_BD;
import roman.boichenko.ivrit.fragments.About;
import roman.boichenko.ivrit.fragments.Learning.Alphabet;
import roman.boichenko.ivrit.fragments.Learning.Learning;

import roman.boichenko.ivrit.fragments.Settings;

import static roman.boichenko.ivrit.Constant.*;

public class MainActivity extends AppCompatActivity {

    public static Toolbar toolbar;
    private DrawerLayout drawerLayout;
    //  FragmentManager fragmentManager;
    //  private ViewPager viewPager;
    public static FrameLayout fragment_container;
    public static TextView textView_navigation_header;
    //  int PICK_ACCOUNT_REQUEST = 1;
    private static final String TAG = "MY_TAG MainActivity";

    private SqlScoutServer sqlScoutServer;
    public static WordDB bd_word;
    public static AbcDB bd_abc;

    // SharedPreferences sPref;
    public static SharedPref sharedPref;
    //   private String accountName = " ";
    public static boolean admin = true;

    // private HashMap mapAdmin;
    //  private Map<Integer, String> mapAdmin = new HashMap<Integer, String>();
    //  private HashSet<String> hashSet_Admin = new HashSet<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //  setTheme(R.style.Night); //  указали какая тема по умолчанию
        //  setTheme(R.style.Day); //  указали какая тема по умолчанию
        //   hashSet_Admin.add("boichenko.roman@gmail.com");
        //     hashSet_Admin.add("jra220576@gmail.com");


        sqlScoutServer = SqlScoutServer.create(this, getPackageName());
        sharedPref = new SharedPref(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment_container = findViewById(R.id.fragment_container);

        //  установка в контейнере фрагмента    заднего фона  (ночной режим )
        if (sharedPref.getPreferencesBoolean(NIGHT_MODE)) {
            fragment_container.setBackgroundColor(getResources().getColor(R.color.gray));
            //TODO    добавить цвет текста
        } else {
            fragment_container.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            //TODO     добавить цвет текста
        }


        initToolbar();
        initNavigationView();

        bd_word = Room.databaseBuilder(this, WordDB.class, "words_db")
                .allowMainThreadQueries()  //получить доступ к базе данных в основном потоке
                .build();

        bd_abc = Room.databaseBuilder(this, AbcDB.class, "abc_db")
                .allowMainThreadQueries()  //получить доступ к базе данных в основном потоке
                .build();

        if (!sharedPref.getPreferencesBoolean(Constant.first_call_to_database)) {  //   первый раз запускаем апликацию
            Log.d(TAG, "onResume:    первый раз запускаем апликацию");

            // запрос слов с сервера
            write_BD getBDwords = new write_BD(this);
            getBDwords.getListWords();
            getBDwords.getListAbc();

            // для   выбора акаунта  google
            //      Intent googlePickerIntent = AccountPicker.newChooseAccountIntent(null, null, new String[]{"com.google"}, false, null, null, null, null);
            //    startActivityForResult(googlePickerIntent, PICK_ACCOUNT_REQUEST);

            // About
            getSupportFragmentManager()
                    .beginTransaction()
                    //    .addToBackStack(null)
                    .replace(R.id.fragment_container, new About(), "About")
                    .commit();

        } else {
            //  accountName = sharedPref.getPreferencesString(Constant.EMAIL);
            //  textView_navigation_header.setText(accountName);
            getSupportFragmentManager()
                    .beginTransaction()
                    // .addToBackStack(null)
                    .add(R.id.fragment_container, new Learning(), "Learning")
                    .commit();
        }

        //   check_admin();

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Toast.makeText(MainActivity.this, "Поиск слова", Toast.LENGTH_SHORT).show();
                //TODO
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.view_navigation_open, R.string.view_navigation_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        View header = navigationView.getHeaderView(0);
        textView_navigation_header = (TextView) header.findViewById(R.id.textView_navigation_header);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();
                //  переход по меню из  NavigationView
                switch (menuItem.getItemId()) {
                    case R.id.nav_learning_words:  // запоминание слов
                        getSupportFragmentManager()
                                .beginTransaction()
                                //  .addToBackStack(null)
                                .replace(R.id.fragment_container, new Learning(), "Learning")
                                .commit();
                        break;
                    case R.id.nav_alphabet:  // запоминание слов
                        getSupportFragmentManager()
                                .beginTransaction()
                                //  .addToBackStack(null)
                                .replace(R.id.fragment_container, new Alphabet(), "Learning")
                                .commit();
                        break;
/*
                    case R.id.nav_spelling_of_words:
                        getSupportFragmentManager()
                                .beginTransaction()
                                //    .addToBackStack(null)
                                .replace(R.id.fragment_container, new SpellingOfWords(), "SpellingOfWords")
                                .commit();
                        break;
*/

                    case R.id.nav_settings:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.fragment_container, new Settings(), "Settings")
                                .commit();
                        break;
                }
                return true;
            }
        });
    }

/*
    @Override
    // для   выбора акаунта  google
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        Log.d(TAG, "onActivityResult: ");

        if (requestCode == PICK_ACCOUNT_REQUEST && resultCode == RESULT_OK) {
            accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);

            textView_navigation_header.setText(accountName);
            sharedPref.savePreferenceString(Constant.EMAIL, accountName);
            check_admin();
            // запрос слов с сервера
            write_BD getBDwords = new write_BD(this);
            getBDwords.getListWords();


        }
    }

*/

    /*
        private void check_admin() {
            //   Log.d(TAG, "check_admin: accountName "+ accountName);

            if (hashSet_Admin.contains(accountName)) {
                //  Log.d(TAG, "check_admin: true");
                admin = true;

            } else {
                // Log.d(TAG, "check_admin: false");
            }

        }
    */
    public void showError(String s) {
        Log.d(TAG, "showError: ERROR" + s);
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(s)
                .setPositiveButton("Ok", null)
                .setCancelable(false)
                .create()
                .show();
    }


    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        sqlScoutServer.resume();
        super.onResume();

    }

    @Override
    protected void onPause() {
        sqlScoutServer.destroy();
        super.onPause();
    }

    @Override
    protected void onStop() {
        sqlScoutServer.destroy();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        sqlScoutServer.destroy();
        super.onDestroy();
    }


}
