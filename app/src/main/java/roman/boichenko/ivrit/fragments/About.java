package roman.boichenko.ivrit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import roman.boichenko.ivrit.R;

public class About extends Fragment {
    private View view;

    public static About getInstanse() {
        Bundle args = new Bundle();
        About fragment = new About();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.about, container, false);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
