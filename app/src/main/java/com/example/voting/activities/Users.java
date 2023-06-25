package com.example.voting.activities;

public class Users {

    String name,cni,email,password;
    public Users() {
    }

    public Users(String name, String cni, String email, String password) {
        this.name = name;
        this.cni=cni;
        this.email = email;
        this.password = password;
    }

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
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

    public String getCni() {return cni;}

    public void setCni(String cni) {
        this.cni = cni;
    }
}
