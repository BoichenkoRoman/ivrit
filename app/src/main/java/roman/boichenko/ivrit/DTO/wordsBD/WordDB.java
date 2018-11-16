package roman.boichenko.ivrit.DTO.wordsBD;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {Word.class}, version = 2)
public abstract class WordDB extends RoomDatabase {
    public abstract WordDAO getWordDAO();
}
