package roman.boichenko.ivrit.DTO.wordsBD;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "wordsTable")
public class Word {
    @PrimaryKey
    public  //( autoGenerate = true)
            int id;

    public String name;

    @ColumnInfo(name = "number")
    int number;

//    String breed;

  //  @Ignore
 //   private String gender;

    public Word(int id, String name, int number) {

        this.id = id;
        this.name = name;
        this.number = number;
       // this.breed = breed;
    }

}


