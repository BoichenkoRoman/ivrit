package roman.boichenko.ivrit.External_storage;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import roman.boichenko.ivrit.Constant;
import roman.boichenko.ivrit.DTO.BD.Abc;
import roman.boichenko.ivrit.DTO.BD.Word;
import roman.boichenko.ivrit.MainActivity;
import roman.boichenko.ivrit.Other.CustomToast;


public class write_BD {
    private static final String TAG = "MY_TAG write_BD";
    private static ArrayList<Word> listWords = new ArrayList<>();
    private Context context;
    //  SharedPreferences sPref;
    //  static final String SharedPreferences_BD = "SharedPreferences_BD";
    private static ArrayList<Abc> abcd_arr;


    public write_BD(Context context) {
        this.context = context;
    }


    public void getListWords() {
        HttpProvider.getInstance().getListWords(new Callback<List<Word>>() {
            @Override
            public void onResponse(Call<List<Word>> call, Response<List<Word>> response) {
                //    Log.d(TAG, "onResponse:  response.body" + response.body());
                //   Log.d(TAG, "onResponse:  response.message" + response.message());
                //   Log.d(TAG, "onResponse:  response" + response);
                //   Log.d(TAG, "onResponse:  errorBody" + response.errorBody());

                if (response.isSuccessful()) {
                    // TODO
                    listWords.addAll(response.body());

//                    for (Word w : listWords) {
//                        //  Log.d(TAG, "onResponse: " + w.toString());
//                    }

                    Log.d(TAG, "onResponse: база загружена размер  " + listWords.size());

                    MainActivity.bd_word.getWordDAO().insertAll(listWords);
                    //  Toast.makeText(context, "Загружено слов: " + listWords.size(), Toast.LENGTH_LONG).show();
                    CustomToast.makeText(context, "Загружено слов: " + listWords.size(), Toast.LENGTH_LONG).show();
                    MainActivity.sharedPref.savePreferencesBoolean(Constant.first_call_to_database, true);


                } else {
                    showError("Server error");
                    try {
                        Log.d(TAG, "onResponse: server error: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Word>> call, Throwable t) {
                showError(" Connection error!");
            }
        });
    }


    public void getListAbc() {
        //TODO    загружаем список букв в базу данных
        abcd_arr = new ArrayList<>();

        Abc abc = new Abc(1, "Алеф", "א", "@drawable/ain", "Буква «алеф» состоит из двух штрихов. Начинаем писать с правого штриха, он очень похож на русскую «с» — пишем сверху вниз. Затем переходим к левому — обратите внимание насколько он выступает за верхнюю границу строки.");
        abcd_arr.add(abc);
        abc = new Abc(2, "Бэт/Вэт", "ב", "@drawable/ain", "«Бэт» представляет из себя полукруг с загнутым внизу хвостиком. Начало письма сверху вниз. Если поставить точку внутри то это буква «б»(бэт), а если без точки, то «в»(вэт).");
        abcd_arr.add(abc);
        abc = new Abc(3, "Гимель", "ג", "@drawable/gimel", "«Гимель» пишется сверху вниз. Верхний элемент выступает за верхнюю границу строки. Напоминает перевёрнутую пятерку без хвостика.");
        abcd_arr.add(abc);

        abc = new Abc(4, "Далет", "ד", "@drawable/dalet", "««Далет» нужно выводить сверху вниз, немного не доводя до нижней границы строки. Иногда посередине буквы может образовываться небольшая петля. Напоминает недописанную тройку.");
        abcd_arr.add(abc);

        MainActivity.bd_abc.getAbcDAO().insertAll(abcd_arr);


    }


    private void showError(String s) {
        Log.d(TAG, "showError: ERROR " + s);
        CustomToast.makeText(context, "showError: ERROR", Toast.LENGTH_SHORT).show();


    }

}
