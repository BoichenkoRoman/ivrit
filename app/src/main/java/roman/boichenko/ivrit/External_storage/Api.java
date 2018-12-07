package roman.boichenko.ivrit.External_storage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import roman.boichenko.ivrit.DTO.BD.Word;


public interface Api {
    @GET("showBDwords.php")
        // общий список   из  таблицы mazganim
    Call<List<Word>> getListWords();    // Call - Эта обертка нужна для работы Retrofit.   //@Query("email") String email
    // В ней мы указываем, какой тип данных ожидаем получить из messages1.json - т.е. List<Mazgan>.
    // <то что мы ждем от сервера >

/*
    @GET("showBDtovar.php")
    Call<List<Tovar>> getTovar();


    @GET("mazganim.php")
        // обновление  количества остатков  на складе
    Call<String> updateQuantity(@Query("action") String action, @Query("sklad") String sklad, @Query("id") int id, @Query("name") String name, @Query("quantity") int quantity);


    @GET("mazganim.php")
        // получение остатка на складах
    Call<List<Quantity>> getMazganimSklad(@Query("action") String action, @Query("sklad") String sklad);


    @GET("mazganim.php")
        // добавление нового мазгана в общий список
    Call<String> AddNEwMazgan(@Query("action") String action, @Query("id") int id, @Query("name") String name, @Query("brend") String brend, @Query("LS") double LS, @Query("energy") String energy, @Query("Razmer_nog") float Razmer_nog,
                              @Query("DV_x") float DV_x, @Query("DV_y") float DV_y, @Query("GL_x") float GL_x, @Query("GL_y") float GL_Y, @Query("cabel") String cabel, @Query("trub") String trub,
                              @Query("Razmer_trub") String Razmer_trub, @Query("Description") String Description, @Query("URL") String URL, @Query("URL_img") String URL_img);


    @GET("mazganim.php")
        // добавление нового товара  в общий список
    Call<String> AddNewTovar(@Query("action") String action, @Query("quantity") Integer quantity, @Query("name") String name, @Query("type") String type, @Query("Description") String description);
*/
}
