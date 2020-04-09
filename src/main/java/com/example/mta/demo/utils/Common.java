package com.example.mta.demo.utils;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class Common {
    public  static Timestamp getTimestamp(){
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        return  ts;
    }
}
