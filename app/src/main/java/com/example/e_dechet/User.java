package com.example.e_dechet;

public class User {
    private String nom_complet;
    private String email;
    private String password;
    private String confpassword;


    public User() {
    }

    public User(String nom_complet, String email, String password, String confpassword) {
        this.nom_complet = nom_complet;
        this.email = email;
        this.password = password;
        this.confpassword = confpassword;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getNom_complet() {
        return nom_complet;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfpassword() {
        return confpassword;
    }

    public void setNom_complet(String nom_complet) {
        this.nom_complet = nom_complet;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfpassword(String confpassword) {
        this.confpassword = confpassword;
    }
}

