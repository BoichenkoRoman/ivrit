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
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import roman.boichenko.ivrit.DTO.BD.Word;
import roman.boichenko.ivrit.DTO.BD.WordDB;
import roman.boichenko.ivrit.MainActivity;
import roman.boichenko.ivrit.R;
import roman.boichenko.ivrit.SharedPref;

import static roman.boichenko.ivrit.Constant.*;

public class Learning extends Fragment {
    private Context context;
    private static final String TAG = "MY_TAG Learning";
    protected static Other other;
    WordDB db;
    int number = 0;
    //  private static SharedPref sharedPref;

    private List<Word> words_arr_bd;  //   колекция всех слов  из бд
    //  private List<Word> words_arr_;  //   колекция всех слов  из бд
    public static ArrayList<Word> words_arr_learning;
    TextView textView5;
    private HashSet<Integer> spelling;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //   sharedPref = new SharedPref(context);
        MainActivity.sharedPref.savePreferenceInteger(WINDOWS, 2);

        // когда показывать провописание
        spelling = new HashSet();
        //   spelling.add(0);
        //    spelling.add(6);
        //   spelling.add(8);

        super.onCreate(savedInstanceState);
        db = MainActivity.bd_word;

        words_arr_bd = new ArrayList<>();
        words_arr_learning = new ArrayList<>();

        other = new Other();
        long timestamp = Learning.other.getCurrentTimeStamp();
        // масив всех слов   до  текущего  timestamp
        words_arr_bd = db.getWordDAO().getWordByTimeStamp(timestamp);

   /*
        Collections.sort(words_arr_bd, new Comparator<Word>() {
            public int compare(Word w1, Word w2) {
                return Long.valueOf(w2.timeStamp).compareTo(Long.valueOf(w1.timeStamp));
            }
        });

     */
        //   Log.d(TAG, "onCreate:  отсортированный входящий лист  words_arr_bd " + words_arr_bd.size());
        for (Word word : words_arr_bd) {
            if (word.timeStamp != 0) {
                words_arr_learning.add(word);
            }
        }


        //   Log.d(TAG, "onCreate:   входящий лист 1  words_arr_bd " + words_arr_bd.size() + " ------------------------------------------------------------");
        //  for (Word word : words_arr_learning) {
        //      Log.d(TAG, "onCreate:  " + word.toString());
        //   }
        //   Log.d(TAG, "onCreate:   входящий лист 1 end  words_arr_bd " + words_arr_bd.size() + " ------------------------------------------------------------");


        Random random = new Random();

        int words_arr_bd_size = words_arr_bd.size() > 25 ? 25 : words_arr_bd.size();
        //  Log.d(TAG, "onCreate: words_arr_bd_size = " + words_arr_bd_size);
        for (int i = 0; i < words_arr_bd_size; i++) {
            int rand = random.nextInt(words_arr_bd.size());
            // выбираем из всех    25   рандомных
            words_arr_learning.add(words_arr_bd.get(rand));
        }

/*
        for (Word word : words_arr_learning) {
            Log.d(TAG, "onCreate:  " + word.toString());
        }
        Log.d(TAG, "onCreate:   входящий лист  2  words_arr_bd " + words_arr_learning.size() + " ------------------------------------------------------------");
*/

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
        //  Log.d(TAG, "onResume:  number 333   " + number);

        //сортируем по   времени    вперед самые первые
        Collections.sort(words_arr_learning, new Comparator<Word>() {
            public int compare(Word w1, Word w2) {
                return Long.valueOf(w1.timeStamp).compareTo(Long.valueOf(w2.timeStamp));
            }
        });

/*
        Log.d(TAG, " words_arr_learning размер  " + words_arr_learning.size());
        Log.d(TAG, "----------------------- ");

        for (Word word3 : words_arr_learning) {
            Log.d(TAG, "id  " + word3.id + " " + "timeStamp " + word3.timeStamp + " " + word3.russian);
            //   Log.d(TAG, "onResume: " + word3.timeStamp);
        }

        Log.d(TAG, "----------------------- ");
*/
        if (words_arr_learning.size() != 0) {
            Word word = words_arr_learning.remove(0);


            if (spelling.contains(word.waiting_time)) {
                getFragmentManager().
                        beginTransaction()
                        .addToBackStack(null)
                        .add(R.id.fragment_container, SpellingOfWords.newInstance(word), "SpellingOfWords")
                        .commit();

            } else {
                getFragmentManager().
                        beginTransaction()
                        //  .addToBackStack(null)
                        .add(R.id.fragment_container, LearningWords.newInstance(word), "LearningWords")
                        .commit();
            }

            Fragment fragment = getFragmentManager().findFragmentByTag("Learning");
            if (fragment != null) {
                //  Log.d(TAG, "fragment   Learning  есть   detach ");
                getFragmentManager().
                        beginTransaction()
                        //  .addToBackStack(null)
                        .detach(fragment)
                        .commit();
            }
            number++;
        } else {

            textView5.setText("на текущий урок все слова закончились попробуйте  позже ");
            //   Log.d(TAG, "onResume:  слова закончились ");
        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
