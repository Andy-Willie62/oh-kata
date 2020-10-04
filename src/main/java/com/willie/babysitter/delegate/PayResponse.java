/*
 * PayResponse - Response object for PayDelegate.
 */
package Delegate;

/**
 *
 * @author Andy
 */
public class PayResponse {
    private int payStatus;
    private String payMessage;

    public PayResponse() {
        payStatus = 0;
        payMessage = "";
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
