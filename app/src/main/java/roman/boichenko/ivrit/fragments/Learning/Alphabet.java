package roman.boichenko.ivrit.fragments.Learning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import roman.boichenko.ivrit.R;

public class Alphabet extends Fragment {
    private View view;

    public static Alphabet getInstanse() {
        Bundle args = new Bundle();
        Alphabet fragment = new Alphabet();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.alphabet, container, false);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
