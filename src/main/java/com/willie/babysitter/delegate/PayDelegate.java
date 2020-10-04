/*
 * PayDelegate is where business logic would go in a three tier application.
 *   Also this is where a service would be defined in Angular.
 */
package com.willie.babysitter.delegate;

import com.willie.babysitter.constants.Constants;
import com.willie.babysitter.times.Times;
import java.util.List;

public class PayDelegate {
    
    public PayResponse calculatePay(PayRequest request) {
        PayResponse response;
        
        // Validate the Request
        response = validatePayRequest(request);
        if (response.getPayStatus() != PayResponse.OK) {
            return response;
        }
        
        // Calculate the pay
        int start = request.getStartHour();
        int bedtime = request.getBedtimeHour();
        int end = request.getEndHour();
        
        // Get the hours worked
        int startToBedtime = bedtime - start;
        int bedTimeToMidnight = (end >= 24 ? 24 - bedtime : end - bedtime);
        int midnightToEnd = (end >= 24 ? end - 24 : 0);
        //System.out.println("startToBedtime=" + startToBedtime);
        //System.out.println("bedTimeToMidnight=" + bedTimeToMidnight);
        //System.out.println("midnightToEnd=" + midnightToEnd);
        
        response.setPay(
                startToBedtime * Constants.START_TO_BEDTIME_RATE +
                bedTimeToMidnight * Constants.BEDTIME_TO_MIDNIGHT_RATE +
                midnightToEnd * Constants.MIDNIGHT_TO_END_RATE
        );
        
        return response;
    }
    
    protected PayResponse validatePayRequest(PayRequest request) {
        // Get time strings
        List<String> hours = Times.getHoursList(0, 23);
        
        // Check for invalid times
        if (request.getStartHour() == -1) {
            return PayResponse.ErrorResponse("Please select a Start Time.");
        }
        if (request.getBedtimeHour() == -1) {
            return PayResponse.ErrorResponse("Please select a Bed Time.");
        }
        if (request.getEndHour() == -1) {
            return PayResponse.ErrorResponse("Please select an End Time.");
        }
        
        // Check startTime
        if (request.getStartHour() < Constants.MINIMUM_START_HOUR) {
            return PayResponse.ErrorResponse("Invalid Start Time before " + hours.get(Constants.MINIMUM_BEDTIME_HOUR));
        }
        
        // Check endTime
        if (request.getEndHour() < Constants.MINIMUM_START_HOUR && request.getEndHour() > Constants.MAXIMUM_END_HOUR) {
            return PayResponse.ErrorResponse("Invalid End Time after " + hours.get(Constants.MAXIMUM_END_HOUR));
        }
        
        // Normalize the hours for straightforward math
        normalizeTimes(request);
        
        // Check that startTime <= bedTime
        if (request.getStartHour() > request.getBedtimeHour()) {
            return PayResponse.ErrorResponse("Invalid for Start Time to come after Bed Time.");
        }
        
        // Check that bedTime <= endTime
        if (request.getBedtimeHour() > request.getEndHour()) {
            return PayResponse.ErrorResponse("Invalid for Bed Time to come after End Time.");
        }
        
        return new PayResponse();
    }
    
    protected void normalizeTimes(PayRequest request) {
        if (request.getStartHour() == 0) {
            request.setStartHour(24);
        }
        
        if (request.getBedtimeHour() == 0) {
            request.setBedtimeHour(24);
        }
        
        if (request.getEndHour() == 0) {
            request.setEndHour(24);
        }
        
        int end = request.getEndHour();
        if (end < Constants.MINIMUM_START_HOUR) {
            request.setEndHour(end + 24);
        }
    }
}
