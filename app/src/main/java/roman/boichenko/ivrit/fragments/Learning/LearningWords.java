package roman.boichenko.ivrit.fragments.Learning;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

import roman.boichenko.ivrit.DTO.BD.Word;
import roman.boichenko.ivrit.DTO.BD.WordDB;
import roman.boichenko.ivrit.MainActivity;
import roman.boichenko.ivrit.R;

import static roman.boichenko.ivrit.fragments.Learning.Other.*;

// класс  принимает слово   и отображает его на дисплей ,  сохраняет в  БД   время следующего показа слова
public class LearningWords extends Fragment {
    Context context;

    private Button button_show_answer;
    private Button button_10;
    private Button button_11;
    private Button button_12;
    private Button button_13;
    private LinearLayout LL_show_answer;
    private LinearLayout LL_time_for_words;
    private LinearLayout LL_answer;
    private TextView text_hebrew;
    private TextView text_russian;
    private TextView text_transcription;
    private TextView text_info_1;
    private TextView text_info_2;
    private HashSet <Integer> rus_to_hebrew  ;   //    показывать на оборот вопрос руское слово



    private static final String TAG = "MY_TAG LearningWords";
    WordDB db;
    private Word word;

    public static LearningWords newInstance(Word word) {
        Bundle args = new Bundle();
        LearningWords fragment = new LearningWords();
        fragment.word = word;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = MainActivity.bd_word;

        // масив временных рамок когда показывать сначала рускую версиию слова
        rus_to_hebrew= new HashSet();
        rus_to_hebrew.add(0);
        rus_to_hebrew.add(1);
        rus_to_hebrew.add(2);      
        rus_to_hebrew.add(5);
        rus_to_hebrew.add(7);
        rus_to_hebrew.add(9);

    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.learning_words, container, false);

        LL_show_answer = view.findViewById(R.id.LL_show_answer);
        LL_time_for_words = view.findViewById(R.id.LL_time_for_words);
        LL_answer = view.findViewById(R.id.LL_answer);

        text_hebrew = view.findViewById(R.id.text_hebrew);
        text_russian = view.findViewById(R.id.text_russian);
        text_transcription = view.findViewById(R.id.text_transcription);
        text_info_1 = view.findViewById(R.id.text_info_1);
        text_info_2 = view.findViewById(R.id.text_info_2);

        button_show_answer = view.findViewById(R.id.button_show_answer);
        button_show_answer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {  //  кнопка показать ответ
                //  Toast.makeText(context, "button_show_answer", Toast.LENGTH_SHORT).show();
                LL_show_answer.setVisibility(View.INVISIBLE);
                LL_time_for_words.setVisibility(View.VISIBLE);
                LL_answer.setVisibility(View.VISIBLE);
                //TODO
                //      Log.d(TAG, "onClick: обработка нажатия \"показать ответ\" ");

            }
        });

        button_10 = view.findViewById(R.id.button_10);
        button_11 = view.findViewById(R.id.button_11);
        button_12 = view.findViewById(R.id.button_12);
        button_13 = view.findViewById(R.id.button_13);


        //    setHasOptionsMenu(true);  // добавляем меню из фрагмента  в наше активити
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        //  Log.d(TAG, "onResume   11111: " + word.toString());

        ///   Закрываем для  всех
        if (MainActivity.admin) {
            data_for_admin();
        }

      //  Log.d(TAG, "onResume: " +rus_to_hebrew.contains(5));
      if(rus_to_hebrew.contains(word.waiting_time)) {
       //   Log.d(TAG, "onResume: 1");
          text_hebrew.setText(String.valueOf(word.russian));
          text_russian.setText(String.valueOf(word.hebrew));
          text_transcription.setText(String.valueOf(word.transcription));
      }
      else {
        //  Log.d(TAG, "onResume: 2");
          text_hebrew.setText(String.valueOf(word.hebrew));
          text_russian.setText(String.valueOf(word.russian));
          text_transcription.setText(String.valueOf(word.transcription));
      }

     //   Log.d(TAG, "onResume: word.waiting_time " + word.waiting_time);




      //  Log.d(TAG, "onResume: " + waiting_time.length);

        //  чтоб не выйти  за конец   массива с временыыми метками
        if (word.waiting_time >= waiting_time.length - 3) {
            word.waiting_time = waiting_time.length - 4;
        }
        //  Log.d(TAG, "onResume: word.waiting_time " + word.waiting_time);

        button_10.setText(Html.fromHtml("СНОВА <br/><small> " + waiting_time_string[0] + "</small>"));
        button_11.setText(Html.fromHtml("ТРУДНО <br/><small>" + waiting_time_string[word.waiting_time + 1] + "</small>"));
        button_12.setText(Html.fromHtml("ХОРОШО <br/><small>" + waiting_time_string[word.waiting_time + 2] + "</small>"));
        button_13.setText(Html.fromHtml("ЛЕГКО <br/><small>" + waiting_time_string[word.waiting_time + 3] + "</small>"));


        button_10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Toast.makeText(context, "СНОВА", Toast.LENGTH_SHORT).show();
                //  Log.d(TAG, "onClick: СНОВА ");
                setTimeStamp(waiting_time[0] * 1000L, 0);
                Learning.words_arr_learning.add(word);   //  добавляем если нажали снова первая кнопка
            }
        });
        button_11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Toast.makeText(context, "ТРУДНО", Toast.LENGTH_SHORT).show();
                //   Log.d(TAG, "onClick: ТРУДНО ");
                setTimeStamp(waiting_time[word.waiting_time + 1] * 1000L, word.waiting_time + 1);
            }
        });
        button_12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //  Toast.makeText(context, "ХОРОШО", Toast.LENGTH_SHORT).show();
                //  Log.d(TAG, "onClick: ХОРОШО ");
                setTimeStamp(waiting_time[word.waiting_time + 2] * 1000L, word.waiting_time + 2);
            }
        });
        button_13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //    Toast.makeText(context, "ЛЕГКО", Toast.LENGTH_SHORT).show();
                //  Log.d(TAG, "onClick: ЛЕГКО ");
                setTimeStamp(waiting_time[word.waiting_time + 3] * 1000L, word.waiting_time + 3);
            }
        });
    }


    private void data_for_admin() {
        //   Log.d(TAG, "onResume:  ADMIN");
        String string_info = " ";
        string_info += "id " + word.id ;
        //   string_info += "count  " + count + "  ";
        string_info += "  " + word.waiting_time ;
        //   text_info_2.setText(String.valueOf(timestamp));
        string_info += "  " + Learning.words_arr_learning.size() + "  ";
        text_info_1.setText(string_info);


    }

    private void setTimeStamp(long time, int waiting_time) {
        long timestamp = Learning.other.getCurrentTimeStamp();
        // записываем новый timestamp    и   waiting_time    для даного слова
        db.getWordDAO().updateWord(word.id, timestamp + time, waiting_time);


        Fragment fragment;
        fragment = getFragmentManager().findFragmentByTag("Learning");
        if (fragment != null) {
            //     Log.d(TAG, "fragment   Learning  есть  attach ");
            getFragmentManager().
                    beginTransaction()
                    //  .addToBackStack(null)
                    .attach(fragment)
                    .commit();
        }


        fragment = getFragmentManager().findFragmentByTag("LearningWords");
        if (fragment != null) {
            getFragmentManager().
                    beginTransaction()
                    //  .addToBackStack(null)
                    .remove(fragment)
                    .commit();
        }
    }
}
