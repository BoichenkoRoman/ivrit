package roman.boichenko.ivrit.External_storage;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import roman.boichenko.ivrit.DTO.wordsBD.Word;
import roman.boichenko.ivrit.MainActivity;


public class GetBDwords {
    private static final String TAG = "MY_TAG GetBDwords";
    private static ArrayList<Word> listWords = new ArrayList<>();

    public GetBDwords() {
        //getListWords();
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

                    for (Word w : listWords) {
                      //  Log.d(TAG, "onResponse: " + w.toString());
                    }

                    Log.d(TAG, "onResponse: listWords.size() " + listWords.size());

              MainActivity.db.getWordDAO().insertAll(listWords);
             //   LearningWords.textView_setText(listWords.size());



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


    private void showError(String s) {
        Log.d(TAG, "showError: ERROR " + s);
        // Toast.makeText(, "", Toast.LENGTH_SHORT).show();


    }

}
