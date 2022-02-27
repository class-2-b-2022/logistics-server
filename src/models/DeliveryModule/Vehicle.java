package models.DeliveryModule;

import java.sql.Date;
import java.time.LocalDate;

public class Vehicle {
    private String model;
    private String plateNbr;
    private String brand;
    private String owner;
    private String Description;
    private Date createdAt;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlateNbr() {
        return plateNbr;
    }

    public void setPlateNbr(String plateNbr) {
        this.plateNbr = plateNbr;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
