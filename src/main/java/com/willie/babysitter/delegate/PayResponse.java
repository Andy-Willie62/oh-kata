/*
 * PayResponse - Response object for PayDelegate.
 */
package com.willie.babysitter.delegate;

/**
 *
 * @author Andy
 */
public class PayResponse {
    public static final int OK = 0;
    public static final int WARNING = 1;
    public static final int ERROR = 2;
    
    private int pay;
    private int payStatus;
    private String payMessage;

    public PayResponse() {
        pay = 0;
        payStatus = 0;
        payMessage = "";
    }
    
    public static PayResponse ErrorResponse(String message) {
        PayResponse response = new PayResponse();
        response.setPayStatus(ERROR);
        response.setPayMessage(message);
        return response;
    }
    
    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayMessage() {
        return payMessage;
    }

    public void setPayMessage(String payMessage) {
        this.payMessage = payMessage;
    }
    
    public String toString() {
        StringBuilder value = new StringBuilder();
        value.append("PayResponse:");
        value.append("\n  PayStatus=" + payStatus);
        value.append("\n  PayMessage=" + payMessage);
        return value.toString();
    }
}
