package roman.boichenko.ivrit.fragments.Learning;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import java.sql.Timestamp;


import roman.boichenko.ivrit.DTO.wordsBD.Word;
import roman.boichenko.ivrit.DTO.wordsBD.WordDB;
import roman.boichenko.ivrit.MainActivity;
import roman.boichenko.ivrit.R;

public class LearningWords extends Fragment {
    Context context;

    Button button_show_answer;
    Button button_10;
    Button button_11;
    Button button_12;
    Button button_13;
    LinearLayout LL_show_answer;
    LinearLayout LL_time_for_words;
    LinearLayout LL_answer;
    TextView text_hebrew;
    TextView text_russian;
    TextView text_transcription;
    TextView text_info_1;
    TextView text_info_2;

    private static final String TAG = "MY_TAG LearningWords";
    WordDB db;
    private Word word;
/*
    public static LearningWords newInstance(WordDB db) {
        Bundle args = new Bundle();
        LearningWords fragment = new LearningWords();
        fragment.db = db;
        fragment.setArguments(args);
        return fragment;
    }
*/

    // waiting_time в секундах
    int[] waiting_time = {
            180,  //3 мин                         [0]
            3600, // 60минут  1 час
            86400,//  +24 часа = 1 день
            2 * 86400, //   172800
            5 * 86400, //
            10 * 86400, // 10 дней
            3 * 604800,  //  3 недели
            6 * 604800,  //
            3 * 2629743, //  3 месяца
            6 * 2629743, //
            31556926, //   год                    [10]
            47335389,   //  1,5* 31556926  = 1.5 год
            2 * 31556926,   //   63113852mc
            3 * 31556926};    //             //      [13]

    String[] waiting_time_string = {"3 мин", "1 час", "1 день", "2 дня", "5 дней", "10 дней", "3 недели", "6 недель", "3 мес",
            "6 мес", "1 год", "1.5 года", "2 года", "3 года"};

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
        //   Log.d(TAG, "Fragment1 onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = MainActivity.db;
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
        Log.d(TAG, "onResume: ");
        Log.d(TAG, "onResponse   11111: " + word.toString());


        if (MainActivity.admin) {
            String string_info = " ";
            string_info += "id " + word.id + "  ";
            //   string_info += "count  " + count + "  ";
            string_info += "waiting_time  " + word.waiting_time + "  ";
            //   text_info_2.setText(String.valueOf(timestamp));
            text_info_1.setText(string_info);
        }

        text_hebrew.setText(String.valueOf(word.hebrew));
        text_russian.setText(String.valueOf(word.russian));
        text_transcription.setText(String.valueOf(word.transcription));


        if (word.waiting_time >= 11) {
            word.waiting_time = 10;
        }
        Log.d(TAG, "onResume: word.waiting_time " + word.waiting_time);

        button_10.setText(Html.fromHtml("СНОВА <br/><small> " + waiting_time_string[0] + "</small>"));
        button_11.setText(Html.fromHtml("ТРУДНО <br/><small>" + waiting_time_string[word.waiting_time + 1] + "</small>"));
        button_12.setText(Html.fromHtml("ХОРОШО <br/><small>" + waiting_time_string[word.waiting_time + 2] + "</small>"));
        button_13.setText(Html.fromHtml("ЛЕГКО <br/><small>" + waiting_time_string[word.waiting_time + 3] + "</small>"));


        button_10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Toast.makeText(context, "СНОВА", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onClick: СНОВА ");
                setTimeStamp(waiting_time[0] * 1000L, 0);
            }
        });
        button_11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Toast.makeText(context, "ТРУДНО", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onClick: ТРУДНО ");
                setTimeStamp(waiting_time[word.waiting_time + 1] * 1000L, word.waiting_time + 1);
            }
        });
        button_12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //  Toast.makeText(context, "ХОРОШО", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onClick: ХОРОШО ");
                setTimeStamp(waiting_time[word.waiting_time + 2] * 1000L, word.waiting_time + 2);
            }
        });
        button_13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //    Toast.makeText(context, "ЛЕГКО", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onClick: ЛЕГКО ");
                setTimeStamp(waiting_time[word.waiting_time + 3] * 1000L, word.waiting_time + 3);
            }
        });
    }

    private void setTimeStamp(long time, int waiting_time) {
        long timestamp = Learning.other.getCurrentTimeStamp();
        // записываем новый timestamp    и   waiting_time    для даного слова
        db.getWordDAO().updateWord(word.id, timestamp + time, waiting_time);


        Fragment fragment = getFragmentManager().findFragmentByTag("LearningWords");

        //   fragment.run_LearningWords();


        if (fragment != null) {
            getFragmentManager().
                    beginTransaction()
                    //  .addToBackStack(null)
                    .remove(fragment)
                    .commit();
        }


        fragment = getFragmentManager().findFragmentByTag("Learning");

        if (fragment != null) {
            Log.d(TAG, "fragment   Learning  есть  attach ");
            getFragmentManager().
                    beginTransaction()
                    //  .addToBackStack(null)
                    .attach(fragment)
                    .commit();
        }


    }


}
