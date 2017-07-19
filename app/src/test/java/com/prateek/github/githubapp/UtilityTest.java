package com.prateek.github.githubapp;

import com.prateek.github.githubapp.utils.Utility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UtilityTest {

    @Test
    public void check_date_converter1() throws Exception {
        assertEquals(1484675807000l, Utility.getTimestampFromDate("2017-04-17T23:26:47Z"));
    }

    @Test
    public void check_date_converter2() throws Exception {
        assertEquals(1327730005000l, Utility.getTimestampFromDate("2012-03-28T11:23:25Z"));
    }
}