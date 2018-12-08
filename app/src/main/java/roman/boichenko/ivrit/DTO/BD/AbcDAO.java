package roman.boichenko.ivrit.DTO.BD;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import java.util.List;

@Dao
public interface AbcDAO {

    // Добавление букв в базу данных
    @Insert
    void add(Abc abc);

    // Добавление всех слов в базу данных
    @Insert
    void insertAll(List<Abc> employees);



    @Query("UPDATE abcTable SET timeStamp=:newtimeStamp, waiting_time=:waiting_time  WHERE ID=:id")
    void updateAbc(int id, long newtimeStamp, int waiting_time);


    @Query("SELECT * FROM abcTable  WHERE  timeStamp <=:timeStamp")
    List<Abc> getAbcByTimeStamp(long timeStamp);


}