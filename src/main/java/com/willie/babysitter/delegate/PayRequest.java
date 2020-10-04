/*
 * PayRequest - Request object for PayDelegate.
 */
package Delegate;

/**
 *
 * @author Andy
 */
public class PayRequest {
    private int startHour;
    private int bedtimeHour;
    private int endHour;

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getBedtimeHour() {
        return bedtimeHour;
    }

    public void setBedtimeHour(int bedtimeHour) {
        this.bedtimeHour = bedtimeHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }
    
}
