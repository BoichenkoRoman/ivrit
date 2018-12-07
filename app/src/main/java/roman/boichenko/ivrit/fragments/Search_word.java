package roman.boichenko.ivrit.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import roman.boichenko.ivrit.DTO.BD.Word;
import roman.boichenko.ivrit.DTO.BD.WordDB;
import roman.boichenko.ivrit.MainActivity;
import roman.boichenko.ivrit.R;

public class Search_word extends Fragment {
    private View view;
    private EditText editText;
    private Button button;
    Context context;
    private static final String TAG = "MY_TAG Search_word";
    private List<Word> words_search;
    private List<Word> words_temp;
    WordDB db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        words_search = new ArrayList<>();
        words_temp = new ArrayList<>();
        db = MainActivity.bd_word;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.search_word, container, false);

        button = view.findViewById(R.id.button);
        editText = view.findViewById(R.id.editText);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "Поиск", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onClick: СНОВА ");

                String search = editText.getText().toString();
                // search = "%" + search + "%";
                Log.d(TAG, "onClick: search " + search);
                words_search = db.getWordDAO().getWordByNameRussian(search);
                words_temp = db.getWordDAO().getWordByNameHebrew(search, search + " %", "% " + search);
                words_search.addAll(words_temp);

                words_temp.clear();
                words_temp = db.getWordDAO().getWordByNameHebrewPlural(search,search + " %", "% " + search);

                words_search.addAll(words_temp);

                for (Word word : words_search) {
                    Log.d(TAG, "1 : " + word.toString());
                }


            }
        });


    }
}

