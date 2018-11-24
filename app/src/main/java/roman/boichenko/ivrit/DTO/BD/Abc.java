package roman.boichenko.ivrit.DTO.BD;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "abcTable")
public class Abc {
    @PrimaryKey
    int id;
    public String russian;
    public String img;
    public String hebrew;
    public String description;
    public long timeStamp;
    public int waiting_time;

    public Abc(int id, String russian, String hebrew, String img, String description) {
        this.id = id;
        this.russian = russian;
        this.img = img;
        this.hebrew = hebrew;
        this.description = description;

        this.timeStamp = 0;
        this.waiting_time = 0;
    }

    @Override
    public String toString() {
        return "Abc{" +
                "id=" + id +
                ", russian='" + russian + '\'' +
                ", img='" + img + '\'' +
                ", hebrew='" + hebrew + '\'' +
                ", description='" + description + '\'' +
                ", timeStamp=" + timeStamp +
                ", waiting_time=" + waiting_time +
                '}';
    }
}
