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
    public static ArrayList<Abc> abcd_arr_random;

    public static Alphabet getInstanse() {
        Bundle args = new Bundle();
        Alphabet fragment = new Alphabet();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        abcd_arr_random = new ArrayList<>();

        Abc abc = new Abc(1, "Алеф", "א", "@drawable/ain", "Буква «алеф» состоит из двух штрихов. Начинаем писать с правого штриха, он очень похож на русскую «с» — пишем сверху вниз. Затем переходим к левому — обратите внимание насколько он выступает за верхнюю границу строки.");
        abcd_arr_random.add(abc);
        abc = new Abc(2, "Бэт/Вэт", "ב", "@drawable/ain", "«Бэт» представляет из себя полукруг с загнутым внизу хвостиком. Начало письма сверху вниз. Если поставить точку внутри то это буква «б»(бэт), а если без точки, то «в»(вэт).");
        abcd_arr_random.add(abc);
        abc = new Abc(3, "Гимель", "ג", "@drawable/gimel", "«Гимель» пишется сверху вниз. Верхний элемент выступает за верхнюю границу строки. Напоминает перевёрнутую пятерку без хвостика.");
        abcd_arr_random.add(abc);
        abc = new Abc(4, "Гимель", "ג", "@drawable/gimel", "«Гимель» пишется сверху вниз. Верхний элемент выступает за верхнюю границу строки. Напоминает перевёрнутую пятерку без хвостика.");
        abcd_arr_random.add(abc);




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
