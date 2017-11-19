package com._520it.wms.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date getBeginTime(Date date){
        Calendar c=Calendar.getInstance();
        c.setTime(date);//date-calender
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        return c.getTime();//calender->date
    }
    public static Date getEndTime(Date date){
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY,23);
        c.set(Calendar.MINUTE,59);
        c.set(Calendar.SECOND,59);
        return c.getTime();
    }
}
