package roman.boichenko.ivrit.DTO.wordsBD;

public class Abc {

    int id;

    public String russian;
    public String img;

    public long timeStamp;

    public Abc(int id, String russian, String img, long timeStamp) {
        this.id = id;
        this.russian = russian;
        this.img = img;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Abc{" +
                "id=" + id +
                ", russian='" + russian + '\'' +
                ", img='" + img + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
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

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
