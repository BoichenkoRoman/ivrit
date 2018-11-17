package roman.boichenko.ivrit.fragments.Learning;

import java.sql.Timestamp;

public class Other {


    public long getCurrentTimeStamp() {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            long timestamp3 = timestamp.getTime();

            return timestamp3;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}
