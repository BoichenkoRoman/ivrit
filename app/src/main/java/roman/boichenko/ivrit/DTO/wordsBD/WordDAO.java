package roman.boichenko.ivrit.DTO.wordsBD;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface WordDAO {

    // Добавление кота в базу данных
    @Insert
    void add(Word word);

    // Добавление котов в базу данных
    @Insert
    void insertAll(List<Word> employees);

    // Меняем кота
    @Update
    void update(Word word);


    // Удаление кота из базы данных
    @Delete
    void delete(Word word);


    @Query("UPDATE wordsTable SET timeStamp=:newtimeStamp, waiting_time=:waiting_time  WHERE ID=:id")
    void updateWord(int id, long newtimeStamp, int waiting_time);

    // Получение всех котов из базы данных
    @Query("SELECT * FROM wordsTable")
    List<Word> getAllWords();


    @Query("SELECT COUNT(*) FROM wordsTable WHERE  timeStamp <=:timeStamp ")
    int count(long timeStamp);

    /*
        // получение   записей по   timeStamp    меньше текушей   и ограничение по лимиту
        @Query("SELECT * FROM wordsTable WHERE  timeStamp <=:timeStamp LIMIT :limit")
        List<Word> getWordsByTimeStamp(long timeStamp, int limit);
    */
//SELECT * FROM `table` ORDER BY RAND() LIMIT 5
    // получение   записей по   timeStamp    меньше текушей   и ограничение по лимиту
    @Query("SELECT * FROM wordsTable  WHERE  timeStamp <=:timeStamp  LIMIT :limit")
    Word getWordByTimeStampLimit(long timeStamp, int limit);

    @Query("SELECT * FROM wordsTable  WHERE  timeStamp <=:timeStamp")
    List<Word> getWordByTimeStamp(long timeStamp);


    // Получение кота по идентификатору
    @Query("SELECT * FROM wordsTable WHERE id = :catId")
    Word getCatById(int catId);
}