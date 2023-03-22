package com.christiancrespo.caso8_christiancrespo.models;

public class Users {
    String idU;
    String nombreU;
    String emailU;
    String generoU;
    String statusU;

    public Users() {
    }

    public Users(String idU, String nombreU, String emailU, String generoU, String statusU) {
        this.idU = idU;
        this.nombreU = nombreU;
        this.emailU = emailU;
        this.generoU = generoU;
        this.statusU = statusU;
    }

    public String getIdU() {
        return idU;
    }

    public void setIdU(String idU) {
        this.idU = idU;
    }

    public String getNombreU() {
        return nombreU;
    }

    public void setNombreU(String nombreU) {
        this.nombreU = nombreU;
    }

    public String getEmailU() {
        return emailU;
    }

    public void setEmailU(String emailU) {
        this.emailU = emailU;
    }

    public String getGeneroU() {
        return generoU;
    }

    public void setGeneroU(String generoU) {
        this.generoU = generoU;
    }

    public String getStatusU() {
        return statusU;
    }

    public void setStatusU(String statusU) {
        this.statusU = statusU;
    }
}
