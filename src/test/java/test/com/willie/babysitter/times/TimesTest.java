/*
 * Tests for the times class
 */
package test.com.willie.babysitter.times;

import com.willie.babysitter.times.Times;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author Andy
 */
public class TimesTest extends TestCase {
    
    @Test
    public void testGetHours() {
        
        // Get first 3: 12AM 1AM 2AM
        List<String> options = Times.getHoursList(0, 2);
        System.out.println(options.toString());
        assertNotNull(options);
        assertEquals(3, options.size());
        assertEquals("12AM", options.get(0));
        assertEquals("2AM", options.get(2));
        
        // Get the last 3: 9PM 10PM 11PM
        options = Times.getHoursList(21, 23);
        System.out.println(options.toString());
        assertNotNull(options);
        assertEquals(3, options.size());
        assertEquals("9PM", options.get(0));
        assertEquals("11PM", options.get(2));
        
        // Get only midnight
        options = Times.getHoursList(0, 0);
        System.out.println(options.toString());
        assertNotNull(options);
        assertEquals(1, options.size());
        assertEquals("12AM", options.get(0));
    }
    
    @Test
    public void testGetHoursAcrossMidnight() {
        
        // Get where start > end 5PM to 4AM
        List<String> options = Times.getHoursList(23, 0);
        System.out.println(options.toString());
        assertNotNull(options);
        assertEquals(2, options.size());
        assertEquals("11PM", options.get(0));
        assertEquals("12AM", options.get(1));
        
        // Get where start > end 5PM to 4AM
        options = Times.getHoursList(17, 4);
        System.out.println(options.toString());
        assertNotNull(options);
        assertEquals(12, options.size());
        assertEquals("5PM", options.get(0));
        assertEquals("12AM", options.get(7));
        assertEquals("4AM", options.get(11));        
    }

    @Test
    public void testBadTimes() {
        List<String> badOptions = Times.getHoursList(-1, 12);
        assertEquals("-1 start is invalid", 0, badOptions.size());
        
        badOptions = Times.getHoursList(24, 12);
        assertEquals("24 start is invalid", 0, badOptions.size());
        
        badOptions = Times.getHoursList(0, -1);
        assertEquals("-1 end is invalid", 0, badOptions.size());
        
        badOptions = Times.getHoursList(0, 24);
        assertEquals("24 end is invalid", 0, badOptions.size());
    }
    
    @Test
    public void testGetHour() {
        assertEquals(0, Times.getHour("12AM"));
        assertEquals(1, Times.getHour("1AM"));
        assertEquals(2, Times.getHour("2AM"));
        assertEquals(3, Times.getHour("3AM"));
        assertEquals(4, Times.getHour("4AM"));
        assertEquals(5, Times.getHour("5AM"));
        assertEquals(6, Times.getHour("6AM"));
        assertEquals(7, Times.getHour("7AM"));
        assertEquals(8, Times.getHour("8AM"));
        assertEquals(9, Times.getHour("9AM"));
        assertEquals(10, Times.getHour("10AM"));
        assertEquals(11, Times.getHour("11AM"));
        assertEquals(12, Times.getHour("12PM"));
        assertEquals(13, Times.getHour("1PM"));
        assertEquals(14, Times.getHour("2PM"));
        assertEquals(15, Times.getHour("3PM"));
        assertEquals(16, Times.getHour("4PM"));
        assertEquals(17, Times.getHour("5PM"));
        assertEquals(18, Times.getHour("6PM"));
        assertEquals(19, Times.getHour("7PM"));
        assertEquals(20, Times.getHour("8PM"));
        assertEquals(21, Times.getHour("9PM"));
        assertEquals(22, Times.getHour("10PM"));
        assertEquals(23, Times.getHour("11PM"));
    }

    @Test
    public void testBadGetHour() {
        assertEquals("-1 indicates an invaid hour", -1, Times.getHour(null));
        assertEquals("-1 indicates an invaid hour", -1, Times.getHour(""));
        assertEquals("-1 indicates an invaid hour", -1, Times.getHour("12 AM"));
        assertEquals("-1 indicates an invaid hour", -1, Times.getHour("1P"));
        assertEquals("-1 indicates an invaid hour", -1, Times.getHour("OH"));
    }
}
