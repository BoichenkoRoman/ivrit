package roman.boichenko.ivrit.DTO.BD;

public class Abc {

    int id;
    public String russian;
    public String img;
    public String hebrew;
    public String description;
    public long timeStamp;
    public int waiting_time;

    public Abc(int id, String russian, String hebrew, String img, String description, long timeStamp, int waiting_time) {
        this.id = id;
        this.russian = russian;
        this.img = img;
        this.hebrew = hebrew;
        this.description = description;
        this.timeStamp = timeStamp;
        this.waiting_time = waiting_time;
    }

    public Abc(int id, String russian, String hebrew, String img, String description) {
        this.id = id;
        this.russian = russian;
        this.img = img;
        this.hebrew = hebrew;
        this.description = description;
        this.timeStamp = timeStamp;
        this.waiting_time = waiting_time;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHebrew() {
        return hebrew;
    }

    public void setHebrew(String hebrew) {
        this.hebrew = hebrew;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getWaiting_time() {
        return waiting_time;
    }

    public void setWaiting_time(int waiting_time) {
        this.waiting_time = waiting_time;
    }
}
