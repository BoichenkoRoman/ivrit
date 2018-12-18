package roman.boichenko.ivrit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import roman.boichenko.ivrit.R;

public class About extends Fragment {
    private View view;
    private TextView textView1;
    private TextView textView2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.about, container, false);
        textView1 = view.findViewById(R.id.textView1);
        textView2 = view.findViewById(R.id.textView2);


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        /*
        String str = "<p align=right> <b> "
                + "<br>" +"Hi!" + "  </br> "
                + "<br> How are you "+" </br>"
                + "<br>I am fine" + " </br> </b> </p>";
         */
        String str = "О программе v7.00 18.12.2018";


        textView1.setText(Html.fromHtml(str));

        str = "Учите что угодно, где угодно и когда угодно. Эффективно используйте время, которое вы тратите в транспорте, в очередях и в любой другой ситуации, когда вам нечем заняться.";

        textView2.setText(Html.fromHtml(str));


    }
}
