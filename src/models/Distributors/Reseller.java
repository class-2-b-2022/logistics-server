package models.Distributors;

import java.sql.Date;

public class Reseller {
    private String first_name;
    private String last_name;
    private Integer telephone;
    private String email;
    private String business_name;
    private Date createdAt;

    public Reseller() {
    }

    public Reseller(String first_name, String last_name, Integer telephone, String email, String business_name, Date createdAt) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.telephone = telephone;
        this.email = email;
        this.business_name = business_name;
        this.createdAt = createdAt;
    }
    public String getFirst_name() {   return first_name;   }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name= last_name;
    }

    public Integer getTelephone() { return telephone; };

    public void setTelephone(Integer telephone) { this.telephone = telephone;  }

    public String getEmail() {   return email;    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) { this.business_name = business_name; }

    public Date getCreatedAt() { return createdAt;    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
