package models;

import java.io.Serializable;

/**
 * @author : Niyigena-Yves
 */
public class Wallet implements Serializable {
    private int id;
    private int userId;
    private String dateOfCreation;
    private double amount;


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
