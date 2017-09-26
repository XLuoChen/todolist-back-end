package com.tw.josaber.todolist.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(new Date());
    }
}
