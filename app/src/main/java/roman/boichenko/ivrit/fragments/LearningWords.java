package roman.boichenko.ivrit.fragments;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import roman.boichenko.ivrit.DTO.wordsBD.Word;
import roman.boichenko.ivrit.DTO.wordsBD.WordDB;

import roman.boichenko.ivrit.R;
import roman.boichenko.ivrit.cat.Cat;
import roman.boichenko.ivrit.cat.CatDB;


public class LearningWords extends Fragment {
    Context context;
    Button button;
    static TextView textView;
    EditText editText;
    private static final String TAG = "MY_TAG LearningWords";
    CatDB db;
    WordDB db1;
//    private SqlScoutServer sqlScoutServer;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
        Log.d(TAG, "Fragment1 onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        db = Room.databaseBuilder(context, CatDB.class, "mydatabase")
                .allowMainThreadQueries()  //получить доступ к базе данных в основном потоке с помощью
                .build();


        db1 = Room.databaseBuilder(context, WordDB.class, "mydatabase2")
                .allowMainThreadQueries()
                .build();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.learningWords, container, false);
        //    ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        //   actionBar.show();   // Показать ActionBar
        //   actionBar.setTitle("SpellingOfWords");


        textView = view.findViewById(R.id.text_test2);
        editText = view.findViewById(R.id.editText);
        button = view.findViewById(R.id.buttom_test3);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "отправить", Toast.LENGTH_SHORT).show();

            }
        });


        setHasOptionsMenu(true);  // добавляем меню из фрагмента  в наше активити
        return view;
    }


    @Override
    public void onResume() {
        //  sqlScoutServer.resume();
        super.onResume();


        // запрос слов с сервера
        // GetBDwords getBDwords = new GetBDwords();
        // getBDwords.getListWords();

        //   db.getCatDAO().add(new Cat(1, "Барсик", 7, "Рэгдолл"));
        //   db.getCatDAO().add(new Cat(2, "Васька", 5, "Мейнкун"));
        // db.getCatDAO().add(new Cat(30, "Мурзик", 2, "Бирма"));
        //    db.getCatDAO().insert(new Cat(33, "Мурзик2", 22, "Бирма"));


        Cat rec = db.getCatDAO().getCatById(33); // Асинхронно считаем данные из базы. Получаем запись с идентификатором ноль.
        if (rec != null) {
            rec.name = "ВАськин 55 ";                                  // Заносим новые данные в поле адреса.
            db.getCatDAO().update(rec);     // Обновляем запись в базе.
        }


//     db1.getWordDAO().add(new Word(1, "слово 1 ", 7));
        //    db1.getWordDAO().add(new Word(2, "слово 2", 7));
        //  db1.getWordDAO().add(new Word(77, "слово 3", 7));


        List<Cat> cats;
        List<Word> words;
        //  cats = db.getCatDAO().getAllCatsWithThatBreed("ирма");
        cats = db.getCatDAO().getAllCats();
        words = db1.getWordDAO().getAllWords();

        Cat cat5 = db.getCatDAO().getCatById(30);
        Log.d(TAG, "cat5  " + cat5.toString());

        String str = " ";
        for (Cat cat : cats) {
            Log.d(TAG, "onClick: " + cat.name + " " + cat.id);
            str += cat.name + " ";
        }


        for (Word word : words) {
            Log.d(TAG, "Word: " + word.name + " " + word.id);
            str += word.name + " ";
        }


        textView.setText(str);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


    public static void textView_setText(int number) {
        textView.setText(String.valueOf(number));
    }


}
