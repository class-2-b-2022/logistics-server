package models.Distributors;

import java.sql.Date;

public class Reseller {
    private Integer id;
    private String first_name;
    private String last_name;
    private Integer telephone;
    private String email;
    private String business_name;
    private Date createdAt;

    public Reseller() {
    }

    public Reseller(Integer id, String first_name, String last_name, Integer telephone, String email, String business_name, Date createdAt) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.telephone = telephone;
        this.email = email;
        this.business_name = business_name;
        this.createdAt = createdAt;
    }


}
