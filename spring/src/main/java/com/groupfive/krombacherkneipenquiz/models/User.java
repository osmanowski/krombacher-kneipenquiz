package com.groupfive.krombacherkneipenquiz.models;

import com.groupfive.krombacherkneipenquiz.ÜbergangsklassefuerMethoden;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Entity
@Inheritance
@Table(name="user")
public abstract class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long benutzerId;
    @NotNull
    private String benutzername;
    @NotNull
    private String eMail;
    @NotNull
    private String Pw;
    @NotNull
    private String Role;
    @Transient
    private String passwordConfirm;
    private String ip;
    private boolean enable;


     public User(String benutzername, String eMail, String pw) {
        this.benutzername = benutzername;
        this.eMail = eMail;
        this.Pw = pw;

    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPw() {
        return Pw;
    }

    public void setPw(String pw) {
        Pw = pw;
    }

    public long getBenutzerId() {
        return benutzerId;
    }

    public void setBenutzerId(long benutzerId) {
        this.benutzerId = benutzerId;
    }

    public User() {

    }

    public User(String benutzername, String pw) {
         this.benutzername = benutzername;
         this.Pw = pw;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }


    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

}
