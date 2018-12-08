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
import roman.boichenko.ivrit.R;

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
                if (response.isSuccessful()) {
                    // TODO
                    listWords.addAll(response.body());
/*
                    for (Word w : listWords) {
                        Log.d(TAG, "onResponse: " + w.toString());
                    }
*/

                    Log.d(TAG, "onResponse: база загружена размер  " + listWords.size());

                    MainActivity.bd_word.getWordDAO().insertAll(listWords);
                    //  Toast.makeText(context, "Загружено слов: " + listWords.size(), Toast.LENGTH_LONG).show();
                    CustomToast.makeText(context, "Загружено слов: " + listWords.size(), Toast.LENGTH_LONG, R.drawable.ok).show();
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

        Abc abc = new Abc(1, "Алеф", "א", "@drawable/alef", "Буква «алеф» состоит из двух штрихов. Начинаем писать с правого штриха, он очень похож на русскую «с» — пишем сверху вниз. Затем переходим к левому — обратите внимание насколько он выступает за верхнюю границу строки.");
        //   Abc abc = new Abc(1, "Алеф", "א", "@drawable/alef", "Буква «алеф» состоит из двух штрихов. Начинаем писать с правого штриха, он очень похож на русскую «с» — пишем сверху вниз. Затем переходим к левому — обратите внимание насколько он выступает за верхнюю границу строкиБуква «алеф» состоит из двух штрихов. Начинаем писать с правого штриха, он очень похож на русскую «с» — пишем сверху вниз. Затем переходим к левому — обратите внимание насколько он выступает за верхнюю границу строки.");
        abcd_arr.add(abc);
        abc = new Abc(2, "Бэт/Вэт", "ב", "@drawable/bet", "«Бэт» представляет из себя полукруг с загнутым внизу хвостиком. Начало письма сверху вниз. Если поставить точку внутри то это буква «б»(бэт), а если без точки, то «в»(вэт).");
        abcd_arr.add(abc);
        abc = new Abc(3, "Гимель", "ג", "@drawable/gimel", "«Гимель» пишется сверху вниз. Верхний элемент выступает за верхнюю границу строки. Напоминает перевёрнутую пятерку без хвостика.");
        abcd_arr.add(abc);
        abc = new Abc(4, "Далет", "ד", "@drawable/dalet", "«Далет» нужно выводить сверху вниз, немного не доводя до нижней границы строки. Иногда посередине буквы может образовываться небольшая петля. Напоминает недописанную тройку.");
        abcd_arr.add(abc);
        abc = new Abc(5, "Hэй", "ה", "@drawable/hei", "Состоит из двух штрихов. Сперва начинаем выводить верхний штрих в виде полукруга, затем второй более маленький чуть ниже.");
        abcd_arr.add(abc);
        abc = new Abc(6, "Вав", "ו", "@drawable/vav", "«Вав» одна из самых простых по написанию — обыкновенная вертикальная палочка. По длине составляет высоту строки.");
        abcd_arr.add(abc);
        abc = new Abc(7, "Заин", "ז", "@drawable/zain", "«Заин» это фактически развернутая «гимель».");
        abcd_arr.add(abc);
        abc = new Abc(8, "Хэт", "ח", "@drawable/het", "«Хэт» состоит из двух штрихов, первым пишется правый в виде полукруглой дуги, затем левый — маленькая палочка. Визуально можно сравнить с английской «n».");
        abcd_arr.add(abc);
        abc = new Abc(9, "Тэт", "ט", "@drawable/tet", "«Тэт» напоминает большой незамкнутый овал. Пишется снизу вверх, выходит за пределы верхней границы строки.");
        abcd_arr.add(abc);
        abc = new Abc(10, "Йуд", "י", "@drawable/iyd", "«Йуд» это по сути простая запятая, прижатая к верхней границе строчки.");
        abcd_arr.add(abc);
        abc = new Abc(11, "Каф/Хаф", "כ", "@drawable/kaf", "«Каф»(«к») это перевёрнутая русская «с» с точкой внутри. Без точки это уже другая буква — «хаф»(звук «х»)");
        abcd_arr.add(abc);
        abc = new Abc(12, "Каф/Хаф софит (конечная)", "ך", "@drawable/kaf_sofit", "«Каф/хаф софит пишется тогда, когда является последней в слове. Визуально отличается добавлением опущенного вниз, длинного штриха.");
        abcd_arr.add(abc);
        abc = new Abc(13, "Ламэд", "ל", "@drawable/lamed", "Прописная «ламэд» отличается своим длинным хвостиком, сильно выступающим за верхнюю границу строки.");
        abcd_arr.add(abc);
        abc = new Abc(14, "Мэм", "מ", "@drawable/mem", "«Мэм» очень напоминает английскую «N» с небольшим наклоном вправо. Правый штрих может немного выходить за верхнюю границу строчки.");
        abcd_arr.add(abc);
        abc = new Abc(15, "Мэм софит (конечная)", "ם", "@drawable/mem_sofit", "«Мэм софит» состоит из круга и небольшой палочки, которая примыкает к нему с левой стороны. Кончик может незначительно выступать за пределы строчки.");
        abcd_arr.add(abc);
        abc = new Abc(16, "Нун", "נ", "@drawable/nun", "Прописная «нун» пишется сверху вниз, чем-то напоминает клюшку.");
        abcd_arr.add(abc);
        abc = new Abc(17, "Нун софит (конечная)", "ן", "@drawable/nun_sofit", "«Нун софит» — длинная вертикальная палочка заметно выступающая за пределы строки.");
        abcd_arr.add(abc);
        abc = new Abc(18, "Самэх", "ס", "@drawable/sameh", "Прописная «самэх» проста в написании — обыкновенный нолик.");
        abcd_arr.add(abc);
        abc = new Abc(19, "Аин", "ע", "@drawable/ain", "«Аин» представляет собой петлю, схожую с незамкнутой сверху цифрой восемь.");
        abcd_arr.add(abc);
        abc = new Abc(20, "Пэй/Фэй", "פ", "@drawable/pei", "Ивритская «Пэй» это по сути дела спираль с точкой внутри. Без точки превращается в букву «фэй», звук «ф».");
        abcd_arr.add(abc);
        abc = new Abc(21, "Пэй/Фэй софит (конечная)", "ף", "@drawable/pei_sofit", "«Пэй/фэй софит» по своему написанию напоминает «ламэд», но в отличии от нее верхний штрих изгибается петлёй и возвращается к основанию.");
        abcd_arr.add(abc);
        abc = new Abc(22, "Цадик", "צ", "@drawable/cadik", "Буква «цадик» очень похожа на цифру три. Её особенностью является выступ за верхнюю границу строки.");
        abcd_arr.add(abc);
        abc = new Abc(23, "Цадик софит (конечная)", "ץ", "@drawable/cadik_sofit", "«Цадик софит» легко спутать с «пэй софит». Единственное отличие — хвостик не загибается к основанию, а наоборот взмывает в высь.");
        abcd_arr.add(abc);
        abc = new Abc(24, "Куф", "ק", "@drawable/kuf", "«Куф» прописью схожа с русской «р», но в отличие от неё оба штриха не соприкасаются друг с другом.");
        abcd_arr.add(abc);
        abc = new Abc(25, "Рэйш", "ר", "@drawable/reish", "«Рэйш» — довольна простая в написании буква, напоминает полукруг.");
        abcd_arr.add(abc);
        abc = new Abc(26, "Шин/Син", "ש", "@drawable/shin", "«Шин» очень похожа на русскую «е». Точка справа превращает её в «син» со звуком «с».");
        abcd_arr.add(abc);
        abc = new Abc(27, "Тав", "ת", "@drawable/tav", "Последняя буква алфавита иврита «тав» состоит из двух штрихов. Сначала выводим правый штрих, напоминающий «рэйш», затем присоединяем к нему ещё один, немного выступающий за нижнюю границу строки.");
        abcd_arr.add(abc);

        MainActivity.bd_abc.getAbcDAO().insertAll(abcd_arr);
        CustomToast.makeText(context, "загружено " + abcd_arr.size() + " букв.", Toast.LENGTH_SHORT, R.drawable.ok).show();
    }


    private void showError(String s) {
        Log.d(TAG, "showError: ERROR " + s);
        CustomToast.makeText(context, "showError: ERROR", Toast.LENGTH_SHORT).show();
    }

}
