package roman.boichenko.ivrit.DTO.BD;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface AbcDAO {

    // Добавление кота в базу данных
  @Insert
   void add(Abc abc);

    // Добавление всехслов в базу данных
    @Insert
    void insertAll(List<Abc> employees);


    @Update
    void update(Abc abc);


    // Удаление кота из базы данных
    @Delete
    void delete(Abc abc);


    @Query("UPDATE abcTable SET timeStamp=:newtimeStamp, waiting_time=:waiting_time  WHERE ID=:id")
    void updateAbc(int id, long newtimeStamp, int waiting_time);

    // Получение всех котов из базы данных
    @Query("SELECT * FROM abcTable")
    List<Abc> getAllAbc();


    @Query("SELECT COUNT(*) FROM abcTable WHERE  timeStamp <=:timeStamp ")
    int count(long timeStamp);

    /*
        // получение   записей по   timeStamp    меньше текушей   и ограничение по лимиту
        @Query("SELECT * FROM wordsTable WHERE  timeStamp <=:timeStamp LIMIT :limit")
        List<Word> getWordsByTimeStamp(long timeStamp, int limit);
    */
//SELECT * FROM `table` ORDER BY RAND() LIMIT 5
    // получение   записей по   timeStamp    меньше текушей   и ограничение по лимиту
    @Query("SELECT * FROM abcTable  WHERE  timeStamp <=:timeStamp  LIMIT :limit")
    Abc getAbcByTimeStampLimit(long timeStamp, int limit);

    @Query("SELECT * FROM abcTable  WHERE  timeStamp <=:timeStamp")
    List<Abc> getAbcByTimeStamp(long timeStamp);


    // Получение Abc по идентификатору
    @Query("SELECT * FROM abcTable WHERE id = :catId")
    Abc getAbcById(int catId);
}