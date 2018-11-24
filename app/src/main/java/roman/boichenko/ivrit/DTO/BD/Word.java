package roman.boichenko.ivrit.DTO.BD;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "wordsTable")
public class Word {
    @PrimaryKey
    public  //( autoGenerate = true)
            int id;

    public String russian;
    public String hebrew;
    public String hebrewPlural;
    public String transcription;
    public String transcriptionPlural;
    public String gender;
    public String theme;
    public String example;
    public long timeStamp;
    public int waiting_time;


    public Word(int id, String russian, String hebrew, String hebrewPlural, String transcription, String transcriptionPlural, String gender, String theme, String example, long timeStamp, int waiting_time) {
        this.id = id;
        this.russian = russian;
        this.hebrew = hebrew;
        this.hebrewPlural = hebrewPlural;
        this.transcription = transcription;
        this.transcriptionPlural = transcriptionPlural;
        this.gender = gender;
        this.theme = theme;
        this.example = example;
        this.timeStamp = timeStamp;
        this.waiting_time = waiting_time;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", russian='" + russian + '\'' +
                ", hebrew='" + hebrew + '\'' +
                ", hebrewPlural='" + hebrewPlural + '\'' +
                ", transcription='" + transcription + '\'' +
                ", transcriptionPlural='" + transcriptionPlural + '\'' +
                ", gender='" + gender + '\'' +
                ", theme='" + theme + '\'' +
                ", example='" + example + '\'' +
                ", timeStamp=" + timeStamp +
                ", waiting_time=" + waiting_time +
                '}';
    }
}


