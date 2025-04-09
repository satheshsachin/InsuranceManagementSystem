package entity;

public class Payment {
	private int paymentId;
    private String paymentDate;
    private double paymentAmount;
    private String client;
    
    public Payment() {
    }
    
    public Payment(int paymentId, String paymentDate, double paymentAmount, String client) {
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.client = client;
    }

    public int getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    public String getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
    public double getPaymentAmount() {
        return paymentAmount;
    }
    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    public String getClient() {
        return client;
    }
    public void setClient(String client) {
        this.client = client;
    }
    @Override
    public String toString() {
        return "Payment [paymentId="+paymentId+", paymentDate="+paymentDate+", paymentAmount="+paymentAmount+", client="+client+"]";
    }

}
