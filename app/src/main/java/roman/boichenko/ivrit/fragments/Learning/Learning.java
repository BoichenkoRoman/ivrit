package roman.boichenko.ivrit.fragments.Learning;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import roman.boichenko.ivrit.MainActivity;
import roman.boichenko.ivrit.R;

public class Learning extends Fragment {
    Context context;
    private static final String TAG = "MY_TAG Learning";
    protected static Other other;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
        //   Log.d(TAG, "Fragment1 onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.learning, container, false);


        MainActivity.toolbar.setTitle("Запоминание  слов");


        setHasOptionsMenu(true);  // добавляем меню из фрагмента  в наше активити
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        other = new Other();

        Log.d(TAG, "onResume: ");

        getFragmentManager().
                beginTransaction()
                //  .addToBackStack(null)
                .replace(R.id.fragment_container, new LearningWords(), "LearningWords")
                .commit();





        /*
        for (int n: waiting_time             ) {
            Log.d(TAG, "onResume: waiting_time " + n);
        }
*/

/*
        Cat rec = db.getCatDAO().getCatById(33); // Асинхронно считаем данные из базы. Получаем запись с идентификатором ноль.
        if (rec != null) {
            rec.name = "ВАськин 55 ";                                  // Заносим новые данные в поле адреса.
            db.getCatDAO().update(rec);     // Обновляем запись в базе.
        }

*/


    }
}
