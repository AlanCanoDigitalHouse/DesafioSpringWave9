package challenge1springboot.socialmeli.utils;

import java.time.LocalDate;

public class DateValidator {

    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String DELIMITER = "-";

    public static boolean validDate(String date) {

        String[] newDate = date.split(DELIMITER);
        try {
            LocalDate.of(Integer.parseInt(newDate[2]), Integer.parseInt(newDate[1]), Integer.parseInt(newDate[0]));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static LocalDate dateValidFormat(String date) {
        String[] newDate = date.split(DELIMITER);
        try {
            return LocalDate.of(Integer.parseInt(newDate[2]), Integer.parseInt(newDate[1]), Integer.parseInt(newDate[0]));
        } catch (Exception e) {
            return LocalDate.now();
        }
    }
}
