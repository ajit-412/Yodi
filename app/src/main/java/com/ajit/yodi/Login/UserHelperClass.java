package com.ajit.yodi.Login;

public class UserHelperClass {

    String mobileno,firstname,lastname,email,profile_img;

    public UserHelperClass() {
    }

    public UserHelperClass(String mobileno, String firstname, String lastname, String email, String profile_img) {
        this.mobileno = mobileno;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.profile_img = profile_img;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }
}
