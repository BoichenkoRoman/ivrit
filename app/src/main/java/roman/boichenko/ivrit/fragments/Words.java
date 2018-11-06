package roman.boichenko.ivrit.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import roman.boichenko.ivrit.MainActivity;
import roman.boichenko.ivrit.R;

import android.widget.LinearLayout.LayoutParams;

public class Words extends Fragment {
    Context context;

    TextView textView;
    TextView textView2;
    TextView textEl;
    Button button;
    GridLayout gridLayout;
    int error;

    TextView[] arr_TextView;

    EditText editText;

    private static final String TAG = "MY_TAG Words";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
        //   Log.d(TAG, "Fragment1 onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
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
        textEl = view.findViewById(R.id.textView1);
        button = view.findViewById(R.id.button_words);


        setHasOptionsMenu(true);  // добавляем меню из фрагмента  в наше активити
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        //Log.d(TAG, "onStart: ");
        final String slovo1 = "לֶקסִיקוֹן";

        textView.setText("лексикон");
        Log.d(TAG, "slovo1: " + slovo1 + "  " + slovo1.length());


        final String slovo2 = removeМocalization(slovo1);

        final int slovo_length = slovo2.length();
        Log.d(TAG, "slovo2: " + slovo2 + "  " + slovo_length);
        textView2.setText(slovo2);


        // показать ответ
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                wordVerification(editText.getText().toString());
            }
        });


        int width_txt = 100;  // ширина текста   поля буквы

        gridLayout.setColumnCount(slovo_length);

        gridLayout.removeAllViews();

        arr_TextView = new TextView[slovo_length];

        // длина  поля для  ввода == длина слова
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(slovo_length)});


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged: " + s.toString());
                // действия перед тем, как что то введено
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged: " + s.toString());
                // действия, когда вводится какой то текст
                // s - то, что вводится, для преобразования в строку - s.toString()
                textEl.setText("");
                button.setVisibility(View.VISIBLE);
                TextVievInvisible();
            }


            @Override
            public void afterTextChanged(Editable editable) {
                Log.d(TAG, "afterTextChanged: " + editable.toString());
                // действия после того, как что то введено
                // editable - то, что введено. В строку - editable.toString()
                if (editable.length() == slovo_length) {
                    // проверяем слово веденное
                    wordVerification(editable.toString());

                    //  скрываем клавиатуру
                    hideKeyboard();
                }
            }
        });


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


    private void hideKeyboard() {
        Activity activity = getActivity();
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        try {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
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
        //  Log.d(TAG, "buttononClick: 111");
        wordVerification("");
    }


    private void TextVievInvisible() {
        for (TextView txt : arr_TextView) {
            txt.setVisibility(View.INVISIBLE);
        }
    }


    public void wordVerification(String word) {
        hideKeyboard();
        error = 0;


        //проверка  слова которое ввел пользователь  и  вывод  на экран результата
        //    Log.d(TAG, "rr2:    word   " + word);

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
                error++;
                arr_TextView[arr_TextView.length - 1 - i].setVisibility(View.VISIBLE);
                arr_TextView[arr_TextView.length - 1 - i].setBackgroundResource(R.color.my_color);
            }
        }


        if (error != 0) {
            textEl.setText("Вы допустили ошибок: " + error);

        } else {
            button.setVisibility(View.INVISIBLE);
            textEl.setText(Html.fromHtml("<p><font color=\"#fff\">Поздравляю! Так держать.</font>"));
        }
    }
}
