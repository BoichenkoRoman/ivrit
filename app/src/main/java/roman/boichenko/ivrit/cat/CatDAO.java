package roman.boichenko.ivrit.cat;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CatDAO {

    // Добавление кота в базу данных
    @Insert
    void add(Cat cat);
    @Insert
    void insert(Cat cat);



    // Добавление котов в базу данных
    @Insert
    void insertAll(Cat... cats);

    // Меняем кота
    @Update
    void update(Cat cat);

    // Удаление кота из базы данных
    @Delete
    void delete(Cat cat);

    // Получение всех котов из базы данных
    @Query("SELECT * FROM cats")
    List<Cat> getAllCats();

    // Получение всех котов из базы данных с нужным условием
  //  @Query("SELECT * FROM cats WHERE breed LIKE '%' || :breed || '%'")
  //  List<Cat> getAllCatsWithThatBreed(String breed);

    // Получение кота по идентификатору
    @Query("SELECT * FROM cats WHERE id = :catId")
    Cat getCatById(int catId);
}