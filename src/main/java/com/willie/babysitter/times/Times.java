/*
 * Times object
 */
package com.willie.babysitter.times;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andy
 */
public class Times {
    /**
     * Hours as string representation of military hours 0-23
     */
    private static final String[] hours = new String[] 
    {"12AM", "1AM", "2AM", "3AM", "4AM", "5AM", "6AM", "7AM", "8AM", "9AM", "10AM", "11AM", "12PM", "1PM", "2PM", "3PM", "4PM", "5PM", "6PM", "7PM", "8PM", "9PM", "10PM", "11PM"};
    
    /**
     * Return a list of hours in the requested range.
     * 
     * @param start - time represented as military time 0=midnight, 1=1AM, ... 23=11PM
     * @param end - time represented as military time 0=midnight, 1=1AM, ... 23=11PM
     * @return ArrayList<String>
     */
    public static List<String> getHoursList(final int start, final int end) {
        List<String> results = new ArrayList<>();
        
        // Check for invalid parmeters
        if (start < 0 || start > 23 ||
            end < 0 || end > 23 ) {
            return results;
        }
        
        int hoursPointer = start;
        int endLoop = (end < start ? end + 24 : end);
        do {
            results.add(hours[(hoursPointer % 24)]);
            hoursPointer++;
        } while(hoursPointer <= endLoop);
        
        return results;
    }
}
