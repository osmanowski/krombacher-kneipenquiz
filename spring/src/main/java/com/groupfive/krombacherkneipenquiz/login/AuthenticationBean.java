package com.groupfive.krombacherkneipenquiz.login;

public class AuthenticationBean {

    private String message;
    private String role;

    public AuthenticationBean(String message, String role) {
        this.message = message;
        this.role = role;
    }

    public String getRole() {return role; }

    public void setRole(String role) { this.role = role; }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("HelloWorldBean [message=%s]","Author", message,"Bach");
    }
}