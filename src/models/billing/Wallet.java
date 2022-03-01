package models.billing;

public class Wallet {
    private int id;
    private int userId;
    private String dateOfCreation;
    private double amount;

    public Wallet(int id, int userId, String doc,double amount) {
        this.id = id;
        this.userId = userId;
        this.dateOfCreation = doc;
        this.amount = amount;
    };

    public Wallet() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public double getAmount() {
        return amount;
    }
}
