package roman.boichenko.ivrit;


import android.accounts.AccountManager;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
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


import com.google.android.gms.common.AccountPicker;
import com.idescout.sql.SqlScoutServer;

import roman.boichenko.ivrit.DTO.wordsBD.WordDB;
import roman.boichenko.ivrit.External_storage.GetBDwords;
import roman.boichenko.ivrit.fragments.About;
import roman.boichenko.ivrit.fragments.Learning.Learning;

import roman.boichenko.ivrit.fragments.Learning.SpellingOfWords;


public class MainActivity extends AppCompatActivity {
    private static final int LAYOUT = R.layout.activity_main;
    public static Toolbar toolbar;
    private DrawerLayout drawerLayout;
    FragmentManager fragmentManager;
    private ViewPager viewPager;
    public static FrameLayout fragment_container;
    public static TextView textView_navigation_header;
    int PICK_ACCOUNT_REQUEST = 1;
    private static final String TAG = "MY_TAG MainActivity";

    private SqlScoutServer sqlScoutServer;
    public static WordDB db;

    SharedPreferences sPref;
    static final String SharedPreferences_BD = "SharedPreferences_BD";
    static final String EMAIL = "EMAIL";

    public static String accountName = " ";
    public static boolean admin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault2); //  указали какая тема по умолчанию

        sqlScoutServer = SqlScoutServer.create(this, getPackageName());
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        fragment_container = findViewById(R.id.fragment_container);


        initToolbar();
        initNavigationView();

        db = Room.databaseBuilder(this, WordDB.class, "words_db")
                .allowMainThreadQueries()  //получить доступ к базе данных в основном потоке с помощью
                .build();


        if (!read_SP_BD()) {  //   первый раз запускаем апликацию
            Log.d(TAG, "onResume:    первый раз запускаем апликацию");

            // для   выбора акаунта  google
            Intent googlePickerIntent = AccountPicker.newChooseAccountIntent(null, null, new String[]{"com.google"}, false, null, null, null, null);
            startActivityForResult(googlePickerIntent, PICK_ACCOUNT_REQUEST);

        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    // .addToBackStack(null)
                    .replace(R.id.fragment_container, new Learning(), "Learning")
                    .commit();
        }
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

                    case R.id.nav_spelling_of_words:
                        getSupportFragmentManager()
                                .beginTransaction()
                                //    .addToBackStack(null)
                                .replace(R.id.fragment_container, new SpellingOfWords(), "SpellingOfWords")
                                .commit();
                        break;
                }
                return true;
            }
        });
    }


    @Override
    // для   выбора акаунта  google
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        Log.d(TAG, "onActivityResult: ");

        if (requestCode == PICK_ACCOUNT_REQUEST && resultCode == RESULT_OK) {
            accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
            //  TextView infoTextView = (TextView) findViewById(R.id.textView);
            textView_navigation_header.setText(accountName);
            //     Log.d(TAG, "onActivityResult: " + data.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE));
            //    Log.d(TAG, "onActivityResult: " + data.getStringExtra(AccountManager.KEY_AUTHTOKEN));
            //     Log.d(TAG, "onActivityResult: " + data.getStringExtra(AccountManager.KEY_USERDATA));
            textView_navigation_header.setText(accountName);
            save_SP_email(EMAIL, accountName);


            getWordsfromBD();


            if (accountName.equals("boichenko.roman@gmail.com")) {
                admin = true;
            }
        }
    }


    private void getWordsfromBD() {
     //   Log.d(TAG, "onResume: запись в базу данных новых слов ");
     //   Log.d(TAG, "onResume: accountName  " + accountName);

        // запрос слов с сервера
        GetBDwords getBDwords = new GetBDwords(this);
        getBDwords.getListWords();

        save_SP_BD(); // сохраняем в  SharedPreferences_BD   что было обращение к БД

        accountName = read_SP_email();
        textView_navigation_header.setText(accountName);


    }


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



        // About


        getSupportFragmentManager()
                .beginTransaction()
                //    .addToBackStack(null)
                .replace(R.id.fragment_container, new About(), "SpellingOfWords")
                .commit();






    }

    private boolean read_SP_BD() {
        // читаем информ из SharedPreferences было ли перове обрашение к
        sPref = getPreferences(MODE_PRIVATE);
        boolean saved_db = sPref.getBoolean(SharedPreferences_BD, false);
        return saved_db;
    }

    public void save_SP_BD() {
        // сохраняем в  SharedPreferences  что было первое обращение к БД
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putBoolean(SharedPreferences_BD, true);
        ed.commit();
    }

    public void save_SP_email(String name, String value) {
        // сохраняем в  SharedPreferences email пользователя
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(name, value);
        ed.commit();
    }

    private String read_SP_email() {
        sPref = getPreferences(MODE_PRIVATE);
        return sPref.getString(EMAIL, " ");
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
