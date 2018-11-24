package roman.boichenko.ivrit.DTO.BD;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {Abc.class}, version = 2)
public abstract class AbcDB extends RoomDatabase {
    public abstract AbcDAO getAbcDAO();
}
