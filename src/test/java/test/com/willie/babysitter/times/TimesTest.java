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
        Times times = new Times();
        
        // Get first 3: 12AM 1AM 2AM
        List<String> options = times.getHoursList(0, 2);
        System.out.println(options.toString());
        assertNotNull(options);
        assertEquals(3, options.size());
        assertEquals("12AM", options.get(0));
        assertEquals("2AM", options.get(2));
        
        // Get the last 3: 9PM 10PM 11PM
        options = times.getHoursList(21, 23);
        System.out.println(options.toString());
        assertNotNull(options);
        assertEquals(3, options.size());
        assertEquals("9PM", options.get(0));
        assertEquals("11PM", options.get(2));
        
        // Get only midnight
        options = times.getHoursList(0, 0);
        System.out.println(options.toString());
        assertNotNull(options);
        assertEquals(1, options.size());
        assertEquals("12AM", options.get(0));
    }
    
    @Test
    public void testGetHoursAcrossMidnight() {
        Times times = new Times();
        
        // Get where start > end 5PM to 4AM
        List<String> options = times.getHoursList(23, 0);
        System.out.println(options.toString());
        assertNotNull(options);
        assertEquals(2, options.size());
        assertEquals("11PM", options.get(0));
        assertEquals("12AM", options.get(1));
        
        // Get where start > end 5PM to 4AM
        options = times.getHoursList(17, 4);
        System.out.println(options.toString());
        assertNotNull(options);
        assertEquals(12, options.size());
        assertEquals("5PM", options.get(0));
        assertEquals("12AM", options.get(7));
        assertEquals("4AM", options.get(11));        
    }

    @Test
    public void testBadTimes() {
        Times times = new Times();
        List<String> badOptions = times.getHoursList(-1, 12);
        assertEquals(0, badOptions.size());
        
        badOptions = times.getHoursList(0, 24);
        assertEquals(0, badOptions.size());    }
    
}
