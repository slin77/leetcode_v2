package com.linsizhe.doordash;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FiveMinInterval {


    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm aa");

        Date date = format.parse("8:00 am");

        System.out.println(date.getHours());
    }
}
