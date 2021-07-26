package com.mercadolibre.socialmeli.logic;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class PostUtils {

    public Date dateNow() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateString = sdf.format(date);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        return endDate;
    }

    public Date dateMinius15() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -15);  // number of days to add
        String tomorrow = (String) (sdf.format(c.getTime()));
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(tomorrow);
        return startDate;

    }

}
