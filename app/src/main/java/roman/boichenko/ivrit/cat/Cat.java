package roman.boichenko.ivrit.cat;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "cats")
public class Cat {
    @PrimaryKey
    public  //( autoGenerate = true)
            int id;

    public String name;

    @ColumnInfo(name = "age")
    int age;

    String breed;

    @Ignore
    private String gender;

    public Cat(int id, String name, int age, String breed) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", breed='" + breed + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}


