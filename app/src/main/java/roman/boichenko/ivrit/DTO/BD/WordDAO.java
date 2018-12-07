package roman.boichenko.ivrit.DTO.BD;


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

    // Добавление всех слов в базу данных
    @Insert
    void insertAll(List<Word> employees);


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


    //   russian      hebrew   hebrewPlural
    // WHERE UniversityName LIKE '%State%'
    @Query("SELECT * FROM wordsTable  WHERE  russian LIKE:russian")
    List<Word> getWordByNameRussian(String russian);

    @Query("SELECT * FROM wordsTable  WHERE  hebrewWithoutNikudot LIKE :hebrew  or hebrewWithoutNikudot LIKE :hebrew1  or hebrewWithoutNikudot LIKE:hebrew2")
    List<Word> getWordByNameHebrew(String hebrew, String hebrew1, String hebrew2);

    @Query("SELECT * FROM wordsTable  WHERE hebrewPluralWithoutNikudot  LIKE:hebrew  or hebrewPluralWithoutNikudot  LIKE:hebrew1  or hebrewPluralWithoutNikudot LIKE:hebrew2")
    List<Word> getWordByNameHebrewPlural(String hebrew, String hebrew1, String hebrew2);


    // Получение кота по идентификатору
    @Query("SELECT * FROM wordsTable WHERE id = :catId")
    Word getCatById(int catId);
}