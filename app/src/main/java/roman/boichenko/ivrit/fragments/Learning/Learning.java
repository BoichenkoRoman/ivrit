package roman.boichenko.ivrit.fragments.Learning;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import roman.boichenko.ivrit.DTO.BD.Word;
import roman.boichenko.ivrit.DTO.BD.WordDB;
import roman.boichenko.ivrit.MainActivity;
import roman.boichenko.ivrit.R;

public class Learning extends Fragment {
    Context context;
    private static final String TAG = "MY_TAG Learning";
    protected static Other other;
    WordDB db;
    int number = 0;

    List<Word> words_arr;
    public static ArrayList<Word> words_arr_random;
    TextView textView5;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
        //   Log.d(TAG, "Fragment1 onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = MainActivity.bd_word;

        words_arr = new ArrayList<>();
        words_arr_random = new ArrayList<>();

        other = new Other();
        long timestamp = Learning.other.getCurrentTimeStamp();
        // масив всех слов   до  текущего  timestamp
        words_arr = db.getWordDAO().getWordByTimeStamp(timestamp);

        Random random = new Random();
        for (int i = 0; i < 25; i++) {
            int rand = random.nextInt(words_arr.size());
            // выбираем из всех    25   рандомных
            words_arr_random.add(words_arr.get(rand));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.learning, container, false);
        MainActivity.toolbar.setTitle("Запоминание  слов");
        setHasOptionsMenu(true);  // добавляем меню из фрагмента  в наше активити

        textView5 = view.findViewById(R.id.textView5);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume:  number 333   " + number);

        //сортируем по   времени    вперед самые первые
        Collections.sort(words_arr_random, new Comparator<Word>() {
            public int compare(Word w1, Word w2) {
                return Long.valueOf(w1.timeStamp).compareTo(Long.valueOf(w2.timeStamp));
            }
        });


        Log.d(TAG, " words_arr_random размер  " + words_arr_random.size());
        Log.d(TAG, "----------------------- ");

        for (Word word3 : words_arr_random) {

            Log.d(TAG, "id  " + word3.id + " " + "timeStamp " + word3.timeStamp + " " + word3.russian);
            //   Log.d(TAG, "onResume: " + word3.timeStamp);
        }
        Log.d(TAG, "----------------------- ");

        if (words_arr_random.size() != 0) {
            Word word = words_arr_random.remove(0);

            getFragmentManager().
                    beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.fragment_container, LearningWords.newInstance(word), "LearningWords")
                    .commit();


            Fragment fragment = getFragmentManager().findFragmentByTag("Learning");
            if (fragment != null) {
                Log.d(TAG, "fragment   Learning  есть   detach ");
                getFragmentManager().
                        beginTransaction()
                        //  .addToBackStack(null)
                        .detach(fragment)
                        .commit();
            }
            number++;
        } else {

            textView5.setText("на сегоднешний день все слова закончились ");
            Log.d(TAG, "onResume:  слова закончились ");
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }
}
