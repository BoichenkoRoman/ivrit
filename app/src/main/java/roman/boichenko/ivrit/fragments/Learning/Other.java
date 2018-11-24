package roman.boichenko.ivrit.fragments.Learning;

import java.sql.Timestamp;

public class Other {

    public static String[] waiting_time_string = {"3 мин", "1 час", "1 день", "2 дня", "5 дней", "10 дней", "3 недели", "6 недель", "3 мес",
            "6 мес", "1 год", "1.5 года", "2 года", "3 года"};

    public static int[] waiting_time = {
            180,  //3 мин                         [0]
            3600, // 60минут  1 час
            86400,//  +24 часа = 1 день
            2 * 86400, //   172800
            5 * 86400, //
            10 * 86400, // 10 дней
            3 * 604800,  //  3 недели
            6 * 604800,  //
            3 * 2629743, //  3 месяца
            6 * 2629743, //
            31556926, //   год                    [10]
            47335389,   //  1,5* 31556926  = 1.5 год
            2 * 31556926,   //   63113852mc
            3 * 31556926};    //             //      [13]


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
