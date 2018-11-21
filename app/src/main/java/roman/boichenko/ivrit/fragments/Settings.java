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
    int settings_2;
    private static final String TAG = "MY_TAG Settings";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
        //   Log.d(TAG, "Fragment1 onAttach");
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings_2 = sharedPref.getPreferencesInteger(SETTINGS_2);
        Log.d(TAG, "onCreate: settings_2 " + settings_2);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.settings, container, false);

        final SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        distance = (TextView) view.findViewById(R.id.distance);
        textView = (TextView) view.findViewById(R.id.textView3);
        checkBox = (CheckBox) view.findViewById(R.id.checkBox);

        seekBar.setProgress(20);
        textView.setText("20");

        textView.setText(String.valueOf(settings_2));

        button_minus = view.findViewById(R.id.button_minus);
        button_plus = view.findViewById(R.id.button_plus);
        button_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: button_minus ");
                SharedPref.savePreferenceInteger(SETTINGS_2, --settings_2);
                textView.setText(String.valueOf(settings_2));

            }
        });
        /*
        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: button_plus ");
                SharedPref.savePreferenceInteger(SETTINGS_2, ++settings_2);
                textView.setText(String.valueOf(settings_2));
            }
        });
*/

        View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onClick: button_plus " + settings_2);
                SharedPref.savePreferenceInteger(SETTINGS_2, ++settings_2);
                textView.setText(String.valueOf(settings_2));
                return true;
            }
        };


        button_plus.setOnTouchListener(mOnTouchListener);

        seekBar.setOnSeekBarChangeListener(this);


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

    /*    button_plus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d(TAG, "onClick: button_plus ");
                return true;
            }
        }
*/

        if (sharedPref.getPreferencesBoolean(NIGHT_MODE)) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }

        //установка ночного режима
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //  textView.setText("Флажок выбран");
                    Log.d(TAG, "onCheckedChanged: Флажок выбран ");
                    MainActivity.fragment_container.setBackgroundColor(getResources().getColor(R.color.gray));
                    SharedPref.savePreferencesBoolean(NIGHT_MODE, true);
                    // Night_mode
                } else {
                    //  textView.setText("Флажок не выбран");
                    Log.d(TAG, "onCheckedChanged: Флажок  не выбран ");
                    MainActivity.fragment_container.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    SharedPref.savePreferencesBoolean(NIGHT_MODE, false);
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
