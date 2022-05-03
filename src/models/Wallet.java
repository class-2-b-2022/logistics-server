package models;


import java.io.Serializable;

/**
 * @author : Gasaro leila
 */
public class Wallet implements Serializable {
    private int id;
    private int userId;
    private String dateOfCreation;
    private double amount;
    private String status;



    public Wallet(int id, int userId, String doc, double amount, String status) {
        this.id = id;
        this.userId = userId;
        this.dateOfCreation = doc;
        this.amount = amount;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
