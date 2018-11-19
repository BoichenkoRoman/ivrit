package roman.boichenko.ivrit.External_storage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import roman.boichenko.ivrit.Constant;
import roman.boichenko.ivrit.DTO.wordsBD.Word;
import roman.boichenko.ivrit.MainActivity;

public class HttpProvider {

    private static final String TAG = "MY_TAG HttpProvider";
    private Api api;

    public static HttpProvider getInstance() {
        return new HttpProvider();
    }

    public HttpProvider() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://android.ua-service.com.ua/hebrew/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);

    }

    public void getListWords(Callback<List<Word>> callback) {
        String accountName = MainActivity.sharedPref.getPreferencesString(Constant.EMAIL);
        Call<List<Word>> call = api.getListWords(accountName);
        call.enqueue(callback);
    }

/*
    public String updateQuantity(String sklad, int id, String name, int quantity) throws Exception {
        Log.d(TAG, "update: ");
        Call<String> call = api.updateQuantity("updateQuantity", sklad, id, name, quantity);
        Response<String> response = null;
        try {
            response = call.execute();
            //  Log.d(TAG, "response 1:   response " + response);
            if (response.code() == 204 || response.code() == 404 || response.code() == 206 || response.code() == 406) {
                String str = response.message();
                Log.d(TAG, "response 2:   str " + str);
                return str;
            } else {
                String str = response.message();
                Log.d(TAG, "response 3:   str " + str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new Exception("Server error!");
    }
*/
/*
    public void getListTovarov(Callback<List<Tovar>> callback) {
        Call<List<Tovar>> call = api.getTovar();
        call.enqueue(callback);
    }
*/
/*
    public String AddNewMazgan(Mazgan mazgan, String action) throws Exception {
      //  Log.d(TAG, "AddNewMazgan: "  + mazgan.getID());
        Call<String> call = api.AddNEwMazgan(action, mazgan.getId(), mazgan.getName(), mazgan.getBrend(), mazgan.getPower(), mazgan.getEnerguClass(),
                mazgan.getRazmerNog(), mazgan.getRazmerDvX(), mazgan.getRazmerDvY(), mazgan.getRazmerGolovX(), mazgan.getRazmerGolovY(), mazgan.getCabel(), mazgan.getTrybu(), mazgan.getDiametr_trub(),
                mazgan.getDescriptions(), mazgan.getURL(), mazgan.getURL_img());

        Response<String> response = null;
        try {
            response = call.execute();
            //  Log.d(TAG, "response 1:   response " + response);
            if (response.code() == 204 || response.code() == 404 || response.code() == 206 || response.code() == 406) {
                String str = response.message();
            //    Log.d(TAG, "response 2:   str " + str);
                return str;
            } else {
                String str = response.message();
              //  Log.d(TAG, "response 3:   str " + str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new Exception("Server error!");
    }
*/
/*
    public String AddNewTovar(Tovar tovar) throws Exception {

        Log.d(TAG, "AddNewMazgan: ");
        Call<String> call = api.AddNewTovar("AddNewTovar",  tovar.getQuantity(), tovar.getName(), tovar.getType(), tovar.getDescription());

        Response<String> response = null;
        try {
            response = call.execute();
            //  Log.d(TAG, "response 1:   response " + response);
            if (response.code() == 204 || response.code() == 404 || response.code() == 206 || response.code() == 406) {
                String str = response.message();
                Log.d(TAG, "response 2:   str " + str);
                return str;
            } else {
                String str = response.message();
                Log.d(TAG, "response 3:   str " + str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new Exception("Server error!");
    }
*/
/*
    public void getMazganimSklad(Callback<List<Quantity>> callback, String sklad) {
        Call<List<Quantity>> call = api.getMazganimSklad("getQuantity", sklad);
        call.enqueue(callback);
    }
*/

}
