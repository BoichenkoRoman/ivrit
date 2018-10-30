package roman.boichenko.ivrit.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;


import roman.boichenko.ivrit.MainActivity;
import roman.boichenko.ivrit.R;

import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import static roman.boichenko.ivrit.R.style.CustomTextView;
// import roman.boichenko.remindme.MainActivity;

public class Words extends Fragment {
    Context context;
    //  WebView WV_about1;
    //  WebView WV_about2;
    TextView textView;
    TextView textView2;

    GridLayout gridLayout;
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
        gridLayout = view.findViewById(R.id.gridLayout);
        //    gridLayout.setColumnCount(10);

        gridLayout.setPadding(10, 10, 10, 10);


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
        int slovo_length = slovo2.length();
        Log.d(TAG, "slovo2: " + slovo2 + "  " + slovo_length);
        textView2.setText(slovo2);


        //  GridLayout.setColumnCount(10);
        // GridLayout.setRowCount(5);


        for (int i = 0; i < slovo_length; i++) {

            final TextView txt = new TextView(context, null, R.style.CustomTextView);
            txt.setId(slovo_length - 1 - i);

            //   Log.d(TAG, "onStart: " + slovo2.charAt(i));

            //    txt.setText(String.valueOf(i));
            txt.setText(String.valueOf(slovo2.charAt(slovo_length - 1 - i)));
            txt.setTextSize(25);

            //      txt.setBackgroundResource(R.color.my_color);
            //   LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            LayoutParams layoutParams = new LayoutParams(130, LayoutParams.WRAP_CONTENT);

            //    layoutParams.gravity = Gravity.CENTER;
            //  layoutParams.setMargins(10, 10, 10, 10);




/*
            txt.setShadowLayer(
                    10f,   //float radius
                    15f,  //float dx
                    15f,  //float dy
                    0xFFFFFFFF //int color
            );
*/

//                txt.setTextAppearance(R.style.CustomTextView);


            // layoutParams.setWidth(LayoutParams.WRAP_CONTENT);
            txt.setGravity(Gravity.CENTER);
            txt.setLayoutParams(layoutParams);
            //     txt.setShadowLayer(10, 10, 10, 9999);


            txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View viewIn) {
                    Log.d(TAG, "1 " + txt.getId());
                    Toast.makeText(context, txt.getText() + "  " + txt.getId(), Toast.LENGTH_SHORT).show();
                }
            });

            gridLayout.addView(txt);

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
}
