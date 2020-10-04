/*
 * Tests for the PayDelegate class
 */
package com.willie.babysitter.delegate;

import com.willie.babysitter.constants.Constants;
import static junit.framework.TestCase.assertEquals;
import org.junit.Test;

public class PayDelegateTest {
    
    @Test
    public void testNormalizeRequest() {
        PayDelegate payDelegate = new PayDelegate();
        PayRequest request;
        
        request = new PayRequest(0, 0, 0);
        payDelegate.normalizeTimes(request);
        //System.out.println("request=" + request.toString());
        assertEquals(24, request.getStartHour());
        assertEquals(24, request.getBedtimeHour());
        assertEquals(24, request.getEndHour());

        request = new PayRequest(17, 0, 4);
        payDelegate.normalizeTimes(request);
        //System.out.println("request=" + request.toString());
        assertEquals(17, request.getStartHour());
        assertEquals(24, request.getBedtimeHour());
        assertEquals(28, request.getEndHour());
    }
    
    @Test
    public void testValidatePayRequest() {
        PayDelegate payDelegate = new PayDelegate();
        PayRequest request;
        PayResponse response;

        request = new PayRequest(17, 18, 19);
        response = payDelegate.validatePayRequest(request);
        //System.out.println("Response=" + response);
        assertEquals(PayResponse.OK, response.getPayStatus());
        
        request = new PayRequest(17, 0, 4);
        response = payDelegate.validatePayRequest(request);
        //System.out.println("Response=" + response);
        assertEquals(PayResponse.OK, response.getPayStatus());
        
        request = new PayRequest(18, 21, 1);
        response = payDelegate.validatePayRequest(request);
        //System.out.println("Response=" + response);
        assertEquals(PayResponse.OK, response.getPayStatus());
    }
    
    @Test
    public void testInvalidatePayRequest() {
        PayDelegate payDelegate = new PayDelegate();
        PayRequest request;
        PayResponse response;
        
        // Invalid start time
        request = new PayRequest(15, 21, 1);
        response = payDelegate.validatePayRequest(request);
        //System.out.println("Response=" + response);
        assertEquals(PayResponse.ERROR, response.getPayStatus());

        // Invalid end time
        request = new PayRequest(17, 21, 5);
        response = payDelegate.validatePayRequest(request);
        //System.out.println("Response=" + response);
        assertEquals(PayResponse.ERROR, response.getPayStatus());

    }
    
    @Test
    public void testPay() {
        PayDelegate payDelegate = new PayDelegate();
        PayRequest request;
        PayResponse response;

        request = new PayRequest(17, 18, 19);
        response = payDelegate.calculatePay(request);
        assertEquals(PayResponse.OK, response.getPayStatus());
        assertEquals("Incorrect Pay", (1*Constants.START_TO_BEDTIME_RATE + 1*Constants.BEDTIME_TO_MIDNIGHT_RATE), response.getPay());

        request = new PayRequest(17, 0, 4);
        response = payDelegate.calculatePay(request);
        assertEquals(PayResponse.OK, response.getPayStatus());
        assertEquals("Incorrect Pay", (7*Constants.START_TO_BEDTIME_RATE + 4*Constants.MIDNIGHT_TO_END_RATE), response.getPay());

        request = new PayRequest(18, 21, 1);
        response = payDelegate.calculatePay(request);
        assertEquals(PayResponse.OK, response.getPayStatus());
        assertEquals("Incorrect Pay", (3*Constants.START_TO_BEDTIME_RATE + 3*Constants.BEDTIME_TO_MIDNIGHT_RATE + 1*Constants.MIDNIGHT_TO_END_RATE), response.getPay());

        request = new PayRequest(23, 23, 2);
        response = payDelegate.calculatePay(request);
        assertEquals(PayResponse.OK, response.getPayStatus());
        assertEquals("Incorrect Pay", (1*Constants.BEDTIME_TO_MIDNIGHT_RATE + 2*Constants.MIDNIGHT_TO_END_RATE), response.getPay());

        request = new PayRequest(21, 23, 23);
        response = payDelegate.calculatePay(request);
        assertEquals(PayResponse.OK, response.getPayStatus());
        assertEquals("Incorrect Pay", (2*Constants.START_TO_BEDTIME_RATE), response.getPay());
    }
}
