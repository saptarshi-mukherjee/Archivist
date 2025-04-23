package com.archivist.reading_platform.Strategies.DateNormalisation;

import java.time.LocalDate;

public class BasicStrategy implements IndianFormatStrategy {
    @Override
    public LocalDate normaliseDate(String date_str) {
        String[] date_arr=date_str.split("/");
        int day=Integer.parseInt(date_arr[0]);
        int month=Integer.parseInt(date_arr[1]);
        int year=Integer.parseInt(date_arr[2]);
        LocalDate date=LocalDate.of(year,month,day);
        return date;
    }
}
