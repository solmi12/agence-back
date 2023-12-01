package com.example.employe.management.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;



public class KontaktDto {


    @NotNull
    private String title;

    @NotNull
    private String nachname;
    @NotNull
    private String description;

    @NotNull
    private String numTlp;
    @NotNull
    @Email
    private String mail;

    @NotNull
    private Boolean isShow;





    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getShow() {
        return isShow;
    }

    public void setShow(Boolean show) {
        isShow = show;
    }

    @NotNull
    private Boolean isSow;
    public KontaktDto() {
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getNumTlp() {
        return numTlp;
    }

    public void setNumTlp(String numTlp) {
        this.numTlp = numTlp;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMail() {

        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }




}
