package roman.boichenko.ivrit.DTO;

public class Words {

    int id;
    String russian;
    String hebrew;
    String hebrewPlural;
    String transcription;
    String transcriptionPlural;
    String gender;
    String theme;
    String example;

    public Words() {
    }

    public Words(int id, String russian, String hebrew, String hebrewPlural, String transcription, String transcriptionPlural, String gender, String theme, String example) {
        this.id = id;
        this.russian = russian;
        this.hebrew = hebrew;
        this.hebrewPlural = hebrewPlural;
        this.transcription = transcription;
        this.transcriptionPlural = transcriptionPlural;
        this.gender = gender;
        this.theme = theme;
        this.example = example;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public String getHebrew() {
        return hebrew;
    }

    public void setHebrew(String hebrew) {
        this.hebrew = hebrew;
    }

    public String getHebrewPlural() {
        return hebrewPlural;
    }

    public void setHebrewPlural(String hebrewPlural) {
        this.hebrewPlural = hebrewPlural;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getTranscriptionPlural() {
        return transcriptionPlural;
    }

    public void setTranscriptionPlural(String transcriptionPlural) {
        this.transcriptionPlural = transcriptionPlural;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }


    @Override
    public String toString() {
        return "Words{" +
                "id=" + id +
                ", russian='" + russian + '\'' +
                ", hebrew='" + hebrew + '\'' +
                ", hebrewPlural='" + hebrewPlural + '\'' +
                ", transcription='" + transcription + '\'' +
                ", transcriptionPlural='" + transcriptionPlural + '\'' +
                ", gender='" + gender + '\'' +
                ", theme='" + theme + '\'' +
                ", example='" + example + '\'' +
                '}';
    }
}
