package roman.boichenko.ivrit.DTO.wordsBD;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface WordDAO {

    // Добавление кота в базу данных
    @Insert
    void add(Word word);

    // Добавление котов в базу данных
    @Insert
    void insertAll(Word... words);

    // Меняем кота
    @Update
    void update(Word word);

    // Удаление кота из базы данных
    @Delete
    void delete(Word word);

    // Получение всех котов из базы данных
    @Query("SELECT * FROM wordsTable")
    List<Word> getAllWords();

    // Получение всех котов из базы данных с нужным условием
  //  @Query("SELECT * FROM Word WHERE breed LIKE '%' || :breed || '%'")
  //  List<Word> getAllCatsWithThatBreed(String breed);

    // Получение кота по идентификатору
    @Query("SELECT * FROM wordsTable WHERE id = :catId")
    Word getCatById(int catId);
}