/*
 * PayDelegate is where business logic would go in a three tier application.
 *   Also this is where a service would be defined in Angular.
 */
package com.willie.babysitter.delegate;

import com.willie.babysitter.constants.Constants;

public class PayDelegate {
    
    public PayResponse calculatePay(PayRequest request) {
        PayResponse response;
        
        // Validate the Request
        response = validatePayRequest(request);
        if (response.getPayStatus() != PayResponse.OK) {
            return response;
        }
        
        // Calculate the pay
        
        
        return response;
    }
    
    protected PayResponse validatePayRequest(PayRequest request) {
        // Normalize the hours for straightforward math
        normalizeTimes(request);
        
        // Check that startTime >= bedTime
        if (request.getStartHour() > request.getBedtimeHour()) {
            return PayResponse.ErrorResponse("Invalid for Start Time to come after Bed Time.");
        }
        
        // Check that bedTime >= endTime
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
