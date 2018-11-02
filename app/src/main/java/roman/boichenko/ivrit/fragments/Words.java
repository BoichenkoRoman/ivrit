package roman.boichenko.ivrit.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;


import roman.boichenko.ivrit.MainActivity;
import roman.boichenko.ivrit.R;

import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


import static roman.boichenko.ivrit.R.style.CustomTextView;
// import roman.boichenko.remindme.MainActivity;

public class Words extends Fragment {
    Context context;
    //  WebView WV_about1;
    //  WebView WV_about2;
    TextView textView;
    TextView textView2;

    Button button;
    GridLayout gridLayout;

    int number_letter = 0; //   порядоку букв
    TextView[] arr_TextView;
    //  HashMap<Integer, TextView> text_33;
    EditText editText;

    private static final String TAG = "MY_TAG Words";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;

        Log.d(TAG, "Fragment1 onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        //  MainActivity.viewPager.setVisibility(View.INVISIBLE);
        //  MainActivity.fragment_container.setVisibility(View.VISIBLE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.words, container, false);
        MainActivity.toolbar.setTitle("Правописание слов");
        gridLayout = view.findViewById(R.id.gridLayout);

        editText = view.findViewById(R.id.EditText1);
        gridLayout.setPadding(10, 10, 10, 10);

        textView = view.findViewById(R.id.textView);
        textView2 = view.findViewById(R.id.textView2);

        button = view.findViewById(R.id.button_words);


        setHasOptionsMenu(true);  // добавляем меню из фрагмента  в наше активити
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        final String slovo1 = "לֶקסִיקוֹן";

        textView.setText(slovo1);
        Log.d(TAG, "slovo1: " + slovo1 + "  " + slovo1.length());


        final String slovo2 = removeМocalization(slovo1);

        int slovo_length = slovo2.length();
        Log.d(TAG, "slovo2: " + slovo2 + "  " + slovo_length);
        textView2.setText(slovo2);


        // показать готовое слово
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                wordVerification(slovo2);
            }
        });


        int width_txt = 100;  // ширина текста   поля буквы

        gridLayout.setColumnCount(slovo_length);

        gridLayout.removeAllViews();

        arr_TextView = new TextView[slovo_length];

        // размер  поля для  ввода
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(slovo_length)});

        editText.setOnKeyListener(new View.OnKeyListener() {
                                      @Override
                                      public boolean onKey(View v, int keyCode, KeyEvent event) {
                                          if (event.getAction() == KeyEvent.ACTION_DOWN &&
                                                  (keyCode == KeyEvent.KEYCODE_ENTER)) {
                                              // сохраняем текст, введенный до нажатия Enter в переменную
                                              String strWord = editText.getText().toString();
                                              //    Toast.makeText(context, "введеное слово - " + strWord, Toast.LENGTH_SHORT).show();

                                              wordVerification(strWord);
                                              return true;
                                          }
                                          return false;
                                      }
                                  }
        );

        arr_TextView = new TextView[slovo_length];


        for (int i = 0; i < slovo_length; i++) {
            TextView txt = new TextView(context, null, R.style.CustomTextView);
            txt.setId(i);
            txt.setTextSize(20);
            txt.setText(String.valueOf(slovo2.charAt(slovo_length - 1 - i)));
            txt.setGravity(Gravity.CENTER);
            txt.setVisibility(View.INVISIBLE);

            LayoutParams layoutParams1 = new LayoutParams(width_txt, LayoutParams.WRAP_CONTENT);
            txt.setLayoutParams(layoutParams1);

            arr_TextView[i] = txt;
            gridLayout.addView(txt);
        }
    }


    private static void shuffleArray(TextView[] arr_TextView) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = new Random();
        for (int i = arr_TextView.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            TextView a = arr_TextView[index];
            arr_TextView[index] = arr_TextView[i];
            arr_TextView[i] = a;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //  menu.findItem(R.id.menu_refresh).setVisible(false); // можем скрыть некоторые пункты
    }


    public String removeМocalization(String str) {
        // удаление  огласовок  из слова возврашаем новое слово
        char[] myCharArray = str.toCharArray();
        String slovoNew = "";
        for (char ch : myCharArray) {
            if (Character.isLetter(ch)) {
                slovoNew += String.valueOf(ch);
            }
        }        //  Log.d(TAG, "slovoNew  : " + slovoNew);
        return slovoNew;
    }

    public void buttononClick() {
        //   Toast.makeText(context, "111", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "buttononClick: 111");
        wordVerification("");
    }

    public void wordVerification(String word) {
        //проверка  слова которое ввел пользователь  и  вывод  на экран результата
        Log.d(TAG, "rr2:    word   " + word);

        //  скрываем все  буквы   и делаем фон  TextView  как  родитеяля
        for (TextView txt : arr_TextView) {
            txt.setVisibility(View.INVISIBLE);
            txt.setBackgroundResource(R.color.colorPrimary);
        }


        int r;
        if (word.length() >= arr_TextView.length) {
            r = arr_TextView.length;
            //  Log.d(TAG, "rr2: веденное слово больше или равно чем задумагое " + r);
        } else {
            r = word.length();
            //  Log.d(TAG, "rr2: веденное слово меньше чем задумагое " + r);
        }

        for (int i = 0; i < arr_TextView.length; i++) {
            String sumbol = "";
            if (i < r) {
                sumbol = String.valueOf(word.charAt(i));
                //    Log.d(TAG, "rr2:    id   " + i + "  sumbol word  " + sumbol);
                //    Log.d(TAG, "rr2:    arr_TextView[i].getText()  " + arr_TextView[arr_TextView.length - 1 - i].getText());
            }

            if (sumbol.equals(arr_TextView[arr_TextView.length - 1 - i].getText())) {
                arr_TextView[arr_TextView.length - 1 - i].setBackgroundResource(R.color.my_color2);
                arr_TextView[arr_TextView.length - 1 - i].setVisibility(View.VISIBLE);
            } else {
                arr_TextView[arr_TextView.length - 1 - i].setVisibility(View.VISIBLE);
                arr_TextView[arr_TextView.length - 1 - i].setBackgroundResource(R.color.my_color);
            }
        }
    }
}
