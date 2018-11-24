package roman.boichenko.ivrit.fragments.Learning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import roman.boichenko.ivrit.DTO.BD.Abc;
import roman.boichenko.ivrit.R;

public class Alphabet extends Fragment {
    private View view;
  //  private static ArrayList<Abc> abcd_arr_random;

    public static Alphabet getInstanse() {
        Bundle args = new Bundle();
        Alphabet fragment = new Alphabet();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





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
