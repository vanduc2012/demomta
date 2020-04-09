package com.example.mta.demo.dto;

import lombok.*;

import java.io.Serializable;

@Data
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = -1L;
    private String username;
    private String password;
    //default constructor for JSON Parsing
    public JwtRequest()
    {
    }

    public JwtRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
