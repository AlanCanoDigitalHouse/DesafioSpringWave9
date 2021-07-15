package com.example.demo.Handlers;

import com.example.demo.Exceptions.CustomExceptionHandler;
import com.example.demo.Exceptions.InvalidDateCustomException;
import java.util.Calendar;
import java.util.Date;

public class GetDate {

    public static Date GetDate(Date date, int daysBefore) throws CustomExceptionHandler {
        if(date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, daysBefore * -1);
            return calendar.getTime();
        }
        throw new InvalidDateCustomException();
    }

}
