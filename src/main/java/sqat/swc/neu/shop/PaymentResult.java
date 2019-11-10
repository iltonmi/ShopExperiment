package sqat.swc.neu.shop;

public class PaymentResult {

    private boolean success;

    private String message;

    private int change;

    public PaymentResult(boolean success, int change, String message) {
        this.success = success;
        this.change = change;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    @Override
    public String toString() {
        return "Payment Success? " + success + "\n" +
               "Change: " + change + "\n" +
               "Message: " + message + "\n";
    }
}
