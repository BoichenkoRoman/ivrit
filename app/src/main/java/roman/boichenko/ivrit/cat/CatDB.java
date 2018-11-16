package roman.boichenko.ivrit.cat;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {Cat.class}, version = 2)
public abstract class CatDB extends RoomDatabase {
    public abstract CatDAO getCatDAO();
}
