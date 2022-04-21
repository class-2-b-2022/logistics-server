public class CompanyModel {

    private Integer TIN;
    private String name;
    private String email;
    private String type;
    private Integer phone;

    public CompanyModel(){}

    public CompanyModel(Integer TIN,String name, String email, String type, Integer phone){
        this.TIN = TIN;
        this.name = name;
        this.email = email;
        this.type = type;
        this.phone = phone;
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
