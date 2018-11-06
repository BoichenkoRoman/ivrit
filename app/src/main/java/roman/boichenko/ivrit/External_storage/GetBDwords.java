package roman.boichenko.ivrit.External_storage;

import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import roman.boichenko.ivrit.DTO.Words;
import roman.boichenko.ivrit.fragments.Test2;


public class GetBDwords {
    private static final String TAG = "MY_TAG GetBDwords";
    public static ArrayList<Words> listWords = new ArrayList<>();

    public GetBDwords() {
        //getListWords();
    }


    public void getListWords() {
        HttpProvider.getInstance().getListWords(new Callback<List<Words>>() {
            @Override
            public void onResponse(Call<List<Words>> call, Response<List<Words>> response) {
                Log.d(TAG, "onResponse:  response.body" +response.body());
                Log.d(TAG, "onResponse:  response.message" +response.message());
                Log.d(TAG, "onResponse:  response" +response);
                Log.d(TAG, "onResponse:  errorBody" +response.errorBody());



                if (response.isSuccessful()) {
                    // TODO
                    listWords.addAll(response.body());

                    for (Words w : listWords
                         ) {
                        Log.d(TAG, "onResponse: "+ w.toString());

                    }
                    Test2.textView_setText(listWords.size());


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
            public void onFailure(Call<List<Words>> call, Throwable t) {
                showError(" Connection error!");
            }
        });
    }


    private void showError(String s) {
        Log.d(TAG, "showError: ERROR " + s);
        // Toast.makeText(, "", Toast.LENGTH_SHORT).show();


    }

}
