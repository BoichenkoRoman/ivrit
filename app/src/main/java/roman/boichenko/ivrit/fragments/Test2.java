package roman.boichenko.ivrit.fragments;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.TextView;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.AccountPicker;


import roman.boichenko.ivrit.MainActivity;
import roman.boichenko.ivrit.R;

import static android.app.Activity.RESULT_OK;


public class Test2 extends Fragment {
    Context context;
    Button button;
    TextView textView;
    int PICK_ACCOUNT_REQUEST = 1;
    private static final String TAG = "MY_TAG Test2";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;

        Log.d(TAG, "Fragment1 onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.test2, container, false);
        //    ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        //   actionBar.show();   // Показать ActionBar
//        actionBar.setTitle("Words");


        textView = view.findViewById(R.id.text_test2);




        setHasOptionsMenu(true);  // добавляем меню из фрагмента  в наше активити
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

    }





    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}
