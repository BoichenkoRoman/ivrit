package roman.boichenko.ivrit.fragments;

import android.app.Activity;
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
import android.widget.TextView;


import roman.boichenko.ivrit.MainActivity;
import roman.boichenko.ivrit.R;

// import roman.boichenko.remindme.MainActivity;

public class Words extends Fragment {
    Context context;
    //  WebView WV_about1;
    //  WebView WV_about2;
    TextView textView;
    TextView textView2;
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
        // ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        //   actionBar.show();   // Показать ActionBar
//        actionBar.setTitle("Words");

        //    WV_about1 = view.findViewById(R.id.WV_about1);
        //    WV_about2 = view.findViewById(R.id.WV_about2);
        //  Toolbar  toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        MainActivity.toolbar.setTitle("Повторение слов");

        textView = view.findViewById(R.id.textView);
        textView2 = view.findViewById(R.id.textView2);

        setHasOptionsMenu(true);  // добавляем меню из фрагмента  в наше активити
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        String slovo1 = "לֶקסִיקוֹן";

        textView.setText(slovo1);
        Log.d(TAG, "slovo1: " + slovo1 + "  " + slovo1.length());


        String slovo2 = removeМocalization(slovo1);
        Log.d(TAG, "slovo2: " + slovo2 + "  " + slovo2.length());
        textView2.setText(slovo2);
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
}
