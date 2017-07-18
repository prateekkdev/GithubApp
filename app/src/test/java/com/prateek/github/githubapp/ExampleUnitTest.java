package com.prateek.github.githubapp;

import com.prateek.github.githubapp.network.dto.CrashlyticsDto;
import com.prateek.github.githubapp.utils.Utility;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void check_date_converter1() throws Exception {
        assertEquals(1484675807000l, Utility.getTimestampFromDate("2017-04-17T23:26:47Z"));
    }

    @Test
    public void check_date_converter2() throws Exception {
        assertEquals(1327730005000l, Utility.getTimestampFromDate("2012-03-28T11:23:25Z"));
    }

    @Mock
    CrashlyticsDto crashlyticsModel1;

    @Mock
    CrashlyticsDto crashlyticsModel2;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void check_crashlytis_compareto() throws Exception {

        when(crashlyticsModel1.getUpdatedAt()).thenReturn("2012-03-28T11:23:25Z");
        when(crashlyticsModel2.getUpdatedAt()).thenReturn("2012-03-29T13:00:22Z");

//        long t1 = Utility.getTimestampFromDate("2012-03-28T11:23:25Z");
//
//        long t2 = Utility.getTimestampFromDate("2012-03-29T13:00:22Z");
//
//        System.out.println("1. t1: " + t1);
//        System.out.println("1. t2: " + t2);


        String value1 = crashlyticsModel1.getUpdatedAt();
        String value2 = crashlyticsModel2.getUpdatedAt();

//        long l1 = crashlyticsModel1.getTimestampFromDate(value1);
//        long l2 = crashlyticsModel2.getTimestampFromDate(value2);

        assertEquals(-1, crashlyticsModel1.compareTo(crashlyticsModel2));

        // assertEquals(1, crashlyticsModel2.compareTo(crashlyticsModel1));
    }
}