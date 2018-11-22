package roman.boichenko.ivrit.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import roman.boichenko.ivrit.MainActivity;
import roman.boichenko.ivrit.R;
import roman.boichenko.ivrit.SharedPref;

import static roman.boichenko.ivrit.Constant.*;
import static roman.boichenko.ivrit.MainActivity.*;

public class Settings extends Fragment implements SeekBar.OnSeekBarChangeListener {
    //  SeekBar seekBar;
    Context context;
    CheckBox checkBox;
    Button button_minus;
    Button button_plus;
    private TextView distance;
    private TextView textView;
    private TextView settings;
    private TextView settings_1;
    private TextView settings_2;
    private TextView textView4;
    int int_settings_2;
    private static final String TAG = "MY_TAG Settings";
    private TextView[] textView_arr;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
        //   Log.d(TAG, "Fragment1 onAttach");
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int_settings_2 = sharedPref.getPreferencesInteger(SETTINGS_2);
        Log.d(TAG, "onCreate: settings_2 " + int_settings_2);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.settings, container, false);

        final SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        button_minus = view.findViewById(R.id.button_minus);
        button_plus = view.findViewById(R.id.button_plus);

        distance = (TextView) view.findViewById(R.id.distance);
        textView = (TextView) view.findViewById(R.id.textView3);

        settings = (TextView) view.findViewById(R.id.settings);
        settings_1 = (TextView) view.findViewById(R.id.settings_1);
        settings_2 = (TextView) view.findViewById(R.id.settings_2);
        textView4 = (TextView) view.findViewById(R.id.textView4);
        textView_arr = new TextView[]{distance, textView, settings, settings_1, settings_2, textView4};

        checkBox = (CheckBox) view.findViewById(R.id.checkBox);

        if (sharedPref.getPreferencesBoolean(NIGHT_MODE)) {
            set_night_mode();
        } else {
            set_day_mode();
        }


        seekBar.setProgress(20);
        textView.setText("20");

        textView.setText(String.valueOf(int_settings_2));


        button_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: button_minus ");
                SharedPref.savePreferenceInteger(SETTINGS_2, --int_settings_2);
                textView.setText(String.valueOf(int_settings_2));

            }
        });

        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: button_plus ");
                SharedPref.savePreferenceInteger(SETTINGS_2, ++int_settings_2);
                textView.setText(String.valueOf(int_settings_2));
            }
        });

        seekBar.setOnSeekBarChangeListener(this);
        return view;
    }

    //   ночной режим
    public void set_night_mode() {
        checkBox.setChecked(true);
        MainActivity.fragment_container.setBackgroundColor(getResources().getColor(R.color.gray));

        for (TextView textView : textView_arr) {
            textView.setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    //   дневной режим
    public void set_day_mode() {
        checkBox.setChecked(false);
        MainActivity.fragment_container.setBackgroundColor(getResources().getColor(R.color.colorWhite));

        for (TextView textView : textView_arr) {
            textView.setTextColor(getResources().getColor(R.color.black));
        }
    }


    @Override
    public void onStart() {
        super.onStart();

        //установка ночного режима
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                 //   Log.d(TAG, "onCheckedChanged:  выбран  ночного режима ");
                    SharedPref.savePreferencesBoolean(NIGHT_MODE, true);

                    set_night_mode();
                    // Night_mode
                } else {
                  //  Log.d(TAG, "onCheckedChanged:   не выбран  дневной  режима  ");
                    SharedPref.savePreferencesBoolean(NIGHT_MODE, false);

                    set_day_mode();
                }
            }
        });


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        distance.setText("" + i);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
