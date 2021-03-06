package models;

import com.fasterxml.jackson.core.SerializableString;

import java.io.Serializable;
import java.util.Date;

/**@author Teta Butera Nelly*/

public class Company implements Serializable {

    private Integer TIN;
    private String name;
    private String email;
    private String type;
    private Integer phone;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Company(){}

    public Company(Integer TIN, String name, String email, String type, Integer phone,String description){
        this.TIN = TIN;
        this.name = name;
        this.email = email;
        this.type = type;
        this.phone = phone;
        this.description = description;
    }

    public Integer getTIN() {
        return TIN;
    }

    public void setTIN(Integer TIN) {
        this.TIN = TIN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

}
