package models.user_model;

public class User {
    private String names;
    private String email;
    private String password;
    private String roleAsString;
    private int phone;
    private int role;
    public User() {}
    
//    public User(String) {
//    	
//    }
    
    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }


    public String getRoleAsString() {
        return roleAsString;
    }

    public void setRoleAsString(String roleAsString) {
        this.roleAsString = roleAsString;
    }
}
