package com.example.notitas.model;

public class Usuario {
    private int id;
    private String user;
    private String email;
    private String password;
    public Usuario(int id,String user,String email,String password){
        this.id = id;
        this.email = email;
        this.password= password;
        this.user = user;
    }
    public int getId(){
        return id;
    }
    public String getUser(){
        return user;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }

    public void setUser(String user){
        this.user = user;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
}

