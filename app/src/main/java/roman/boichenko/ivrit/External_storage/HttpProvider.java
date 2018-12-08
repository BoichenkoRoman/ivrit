package roman.boichenko.ivrit.External_storage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import roman.boichenko.ivrit.Constant;
import roman.boichenko.ivrit.DTO.BD.Word;
import roman.boichenko.ivrit.MainActivity;

public class HttpProvider {

    private static final String TAG = "MY_TAG HttpProvider";
    private Api api;

    public static HttpProvider getInstance() {
        return new HttpProvider();
    }

    public HttpProvider() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://android.ua-service.com.ua/hebrew/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);

    }

    public void getListWords(Callback<List<Word>> callback) {

        Call<List<Word>> call = api.getListWords();
        call.enqueue(callback);
    }



}
