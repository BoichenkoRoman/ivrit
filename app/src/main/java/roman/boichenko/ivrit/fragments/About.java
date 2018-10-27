package roman.boichenko.ivrit.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;


import roman.boichenko.ivrit.R;

// import roman.boichenko.remindme.MainActivity;

public class About extends Fragment {
    Context context;
  //  WebView WV_about1;
  //  WebView WV_about2;

    private static final String TAG = "MY_TAG About";

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

        View view = inflater.inflate(R.layout.about, container, false);
       // ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        //   actionBar.show();   // Показать ActionBar
//        actionBar.setTitle("About");

    //    WV_about1 = view.findViewById(R.id.WV_about1);
    //    WV_about2 = view.findViewById(R.id.WV_about2);

        setHasOptionsMenu(true);  // добавляем меню из фрагмента  в наше активити
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
/*
        String htmlText = getString(R.string.about1);
        WV_about1.loadData(htmlText, "text/html; charset=UTF-8", null);

        String htmlText2 = getString(R.string.about2) + getString(R.string.about3);
        WV_about2.loadData(htmlText2, "text/html; charset=UTF-8", null);

        WV_about2.setBackgroundColor(Color.parseColor("#7c7edc"));
        //  WV_about.loadUrl("file:///android_asset/1.html");
        //  WV_about.loadUrl("file:///android_asset/Android_ Spinner.html");

        //    TV_about.setText(Html.fromHtml(getString(R.string.about)));
*/

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        //  menu.findItem(R.id.menu_refresh).setVisible(false); // можем скрыть некоторые пункты
    }
}
