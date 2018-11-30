package roman.boichenko.ivrit.fragments.Learning;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import roman.boichenko.ivrit.DTO.BD.Abc;
import roman.boichenko.ivrit.DTO.BD.AbcDB;

import roman.boichenko.ivrit.MainActivity;
import roman.boichenko.ivrit.R;

import static roman.boichenko.ivrit.fragments.Learning.Other.*;


public class Alphabet extends Fragment {
    private View view;
    private static final String TAG = "MY_TAG Alphabet";
    AbcDB db;
    List<Abc> abc_arr;
    private Abc abc;

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
    private TextView text_description;

    private ImageView imageView;
    private TextView text_info_1;
    private TextView text_info_2;
    private long timestamp;
    private Other other;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = MainActivity.bd_abc;
        abc_arr = new ArrayList<>();
        other = new Other();
        timestamp = other.getCurrentTimeStamp();

        //  abc_arr = db.getAbcDAO().getAllAbc();    //   запрос на все буквы
        abc_arr = db.getAbcDAO().getAbcByTimeStamp(timestamp);    //   запрос на  буквы  который меньше сейчас времени


    }


    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.alphabet, container, false);


        LL_show_answer = view.findViewById(R.id.LL_show_answer);
        LL_time_for_words = view.findViewById(R.id.LL_time_for_words);
        LL_answer = view.findViewById(R.id.LL_answer);

        text_hebrew = view.findViewById(R.id.text_hebrew);
        text_russian = view.findViewById(R.id.text_russian);
        text_description = view.findViewById(R.id.text_description);
        imageView = view.findViewById(R.id.imageView);
        //  text_transcription = view.findViewById(R.id.text_transcription);

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


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        run();
    }


    private void run() {
        Log.d(TAG, "run: ");

        LL_show_answer.setVisibility(View.VISIBLE);
        LL_time_for_words.setVisibility(View.INVISIBLE);
        LL_answer.setVisibility(View.INVISIBLE);


        if (abc_arr.size() != 0) {
            // берем всегда первый по индексу 0
            abc = abc_arr.remove(0);

            ///   Закрываем для  всех
            if (MainActivity.admin) {
                data_for_admin(abc);
            }


            text_hebrew.setText(String.valueOf(abc.hebrew));
            text_russian.setText(String.valueOf(abc.russian));
            text_description.setText(String.valueOf(abc.description));

            int id = getContext().getResources().getIdentifier(abc.img, "drawable", getContext().getPackageName());
            imageView.setImageResource(id);

            //  imageView.setImageDrawable(Drawable.createFromPath(abc.img));
            //   text_transcription.setText(String.valueOf(abc.transcription));

            if (abc.waiting_time >= 11) {
                abc.waiting_time = 10;
            }
            Log.d(TAG, "onResume: word.waiting_time " + abc.waiting_time);

            button_10.setText(Html.fromHtml("СНОВА <br/><small> " + waiting_time_string[0] + "</small>"));
            button_11.setText(Html.fromHtml("ТРУДНО <br/><small>" + waiting_time_string[abc.waiting_time + 1] + "</small>"));
            button_12.setText(Html.fromHtml("ХОРОШО <br/><small>" + waiting_time_string[abc.waiting_time + 2] + "</small>"));
            button_13.setText(Html.fromHtml("ЛЕГКО <br/><small>" + waiting_time_string[abc.waiting_time + 3] + "</small>"));


            button_10.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Toast.makeText(context, "СНОВА", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onClick: СНОВА ");
                    setTimeStamp(waiting_time[0] * 1000L, 0);
                    abc_arr.add(abc);   //  добавляем если нажали снова первая кнопка
                }
            });
            button_11.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Toast.makeText(context, "ТРУДНО", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onClick: ТРУДНО ");
                    setTimeStamp(waiting_time[abc.waiting_time + 1] * 1000L, abc.waiting_time + 1);
                }
            });
            button_12.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //  Toast.makeText(context, "ХОРОШО", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onClick: ХОРОШО ");
                    setTimeStamp(waiting_time[abc.waiting_time + 2] * 1000L, abc.waiting_time + 2);
                }
            });
            button_13.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //    Toast.makeText(context, "ЛЕГКО", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onClick: ЛЕГКО ");
                    setTimeStamp(waiting_time[abc.waiting_time + 3] * 1000L, abc.waiting_time + 3);
                }
            });
        } else {
            text_hebrew.setTextSize(20);
            text_hebrew.setText("на сегодня все буквы пройдены");
        }
    }

    private void data_for_admin(Abc abc) {
        //   Log.d(TAG, "onResume:  ADMIN");
        String string_info = " ";
        string_info += "id " + abc.id + "  ";
        //   string_info += "count  " + count + "  ";
        string_info += "waiting_time  " + abc.waiting_time + "  ";
        //   text_info_2.setText(String.valueOf(timestamp));
        string_info += "size " + abc_arr.size() + "  ";
//        text_info_1.setText(string_info);


    }

    private void setTimeStamp(long time, int waiting_time) {

        timestamp = other.getCurrentTimeStamp();
        // записываем новый timestamp    и   waiting_time    для даного слова
        db.getAbcDAO().updateAbc(abc.id, timestamp + time, waiting_time);

        run();

    }


}
