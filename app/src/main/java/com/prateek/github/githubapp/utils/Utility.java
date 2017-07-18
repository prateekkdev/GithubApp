package com.prateek.github.githubapp.utils;

import java.text.SimpleDateFormat;

/**
 * Created by prateek.kesarwani on 18/07/17.
 */

public class Utility {

    public static long getTimestampFromDate(String str) {

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss'Z'");
            long time = dateFormat.parse(str).getTime();
            return time;
        } catch (Exception e) {
            // Format failed.
            return -1;
        }
    }
}