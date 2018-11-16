package roman.boichenko.ivrit.fragments;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import roman.boichenko.ivrit.DTO.wordsBD.Word;
import roman.boichenko.ivrit.DTO.wordsBD.WordDB;

import roman.boichenko.ivrit.MainActivity;
import roman.boichenko.ivrit.R;

public class LearningWords extends Fragment {
    Context context;
    Button button;
    Button button_show_answer;
    Button button_10;
    Button button_11;
    Button button_12;
    Button button_13;
    LinearLayout LL_show_answer;
    LinearLayout LL_time_for_words;
    static TextView textView;
    EditText editText;
    private static final String TAG = "MY_TAG LearningWords";

    WordDB db;


    public static LearningWords newInstance(WordDB db) {
        Bundle args = new Bundle();
        LearningWords fragment = new LearningWords();
        fragment.db = db;
        fragment.setArguments(args);
        return fragment;
    }


    // waiting_time в секундах
    int[] waiting_time = {
            180,  //3 мин
            3600, // 60минут  1 час
            86400,//  +24 часа
            2 * 86400, //
            5 * 86400, //
            10 * 86400, // 10 дней
            3 * 604800,  //  3 недели
            6 * 604800,  //
            3 * 2629743, //  3 месяца
            6 * 2629743, //
            31556926, //   год
            1, 5 * 31556926,
            2 * 31556926,
            3 * 31556926};


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
        Log.d(TAG, "Fragment1 onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //  db = Room.databaseBuilder(context, CatDB.class, "mydatabase")
        //        .allowMainThreadQueries()  //получить доступ к базе данных в основном потоке с помощью
        //        .build();


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.learning_words, container, false);

        LL_show_answer = view.findViewById(R.id.LL_show_answer);
        LL_time_for_words = view.findViewById(R.id.LL_time_for_words);

        textView = view.findViewById(R.id.text_1);
        editText = view.findViewById(R.id.editText);
        button = view.findViewById(R.id.buttom_test3);
        button_show_answer = view.findViewById(R.id.button_show_answer);
        button_show_answer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //  Toast.makeText(context, "button_show_answer", Toast.LENGTH_SHORT).show();
                LL_show_answer.setVisibility(View.INVISIBLE);
                LL_time_for_words.setVisibility(View.VISIBLE);
                //TODO
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "отправить", Toast.LENGTH_SHORT).show();

            }
        });

        button_10 = view.findViewById(R.id.button_10);
        button_11 = view.findViewById(R.id.button_11);
        button_12 = view.findViewById(R.id.button_12);
        button_13 = view.findViewById(R.id.button_13);

        button_10.setText(Html.fromHtml("СНОВА <br/><small>3 мин </small>"));
        button_11.setText(Html.fromHtml("ТРУДНО <br/><small>3 мин </small>"));
        button_12.setText(Html.fromHtml("ХОРОШО <br/><small>3 мин </small>"));
        button_13.setText(Html.fromHtml("ЛЕГКО <br/><small>3 мин </small>"));

        button_10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "СНОВА", Toast.LENGTH_SHORT).show();

            }
        });
        button_11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "ТРУДНО", Toast.LENGTH_SHORT).show();

            }
        });
        button_12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "ХОРОШО", Toast.LENGTH_SHORT).show();

            }
        });
        button_13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "ЛЕГКО", Toast.LENGTH_SHORT).show();

            }
        });


        setHasOptionsMenu(true);  // добавляем меню из фрагмента  в наше активити
        return view;
    }


    @Override
    public void onResume() {
        //  sqlScoutServer.resume();
        super.onResume();





/*
        Cat rec = db.getCatDAO().getCatById(33); // Асинхронно считаем данные из базы. Получаем запись с идентификатором ноль.
        if (rec != null) {
            rec.name = "ВАськин 55 ";                                  // Заносим новые данные в поле адреса.
            db.getCatDAO().update(rec);     // Обновляем запись в базе.
        }

*/

       MainActivity.toolbar.setTitle("Изучение  слов ");


        List<Word> words;

        words = db.getWordDAO().getAllWords();

        //    Cat cat5 = db.getCatDAO().getCatById(30);
        //    if (cat5 != null) {
        //        Log.d(TAG, "cat5  " + cat5.toString());
        //     }


        String str = " ";


        for (Word word : words) {
            Log.d(TAG, "Word: " + word.name + " " + word.id);
            str += word.name + " ";
        }


           textView.setText(str);
      //  textView.setText(getCurrentTimeStamp());
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


    public static void textView_setText(int number) {
        textView.setText(String.valueOf(number));
    }

    public String getCurrentTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        try {

            //   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //   String currentDateTime = dateFormat.format(new Date()); // Find todays date

            //  Long tsLong = System.currentTimeMillis()/1000;
            //   String currentDateTime = tsLong.toString();

            Date date = new Date();
            Log.d(TAG, "currentDateTime 2: " + new Timestamp(date.getTime()));


            //method 1
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Log.d(TAG, "currentDateTime 1: " + timestamp);
            //method 2 - via Date


            long timestamp3 = timestamp.getTime();
            //return number of milliseconds since January 1, 1970, 00:00:00 GMT
            Log.d(TAG, "currentDateTime 3: " + timestamp3);


            //format timestamp
            Log.d(TAG, "currentDateTime 4: " + sdf.format(timestamp));

            getDate(timestamp3);

            //   преобразовать дату 13-09-2011  в  timestamp  1315861200000
            String str_date = "13-09-2011";
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date date4 = (Date) formatter.parse(str_date);
            Log.d(TAG, "Today is " + date4.getTime());


            return " ";
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    private String getDate(long timeStamp) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date date = (new Date(timeStamp));

            Log.d(TAG, "netDate  getDate : " + date);
            Log.d(TAG, "netDate  getTime : " + date.getTime());
            Log.d(TAG, "netDate  getHours : " + date.getHours());
            Log.d(TAG, "netDate  getTime : " + date.getTime());


            return sdf.format(date);
        } catch (Exception ex) {
            return "xx";
        }
    }

}
