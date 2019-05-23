package com.wposs.buc.gpstracker;

public class CreateUser {

    public String name, email, pass, code, compartir, lat, lng, imageUrl;

    public CreateUser() {
    }

    public CreateUser(String name, String email, String pass, String code, String compartir, String lat, String lng, String imageUrl) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.code = code;
        this.compartir = compartir;
        this.lat = lat;
        this.lng = lng;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompartir() {
        return compartir;
    }

    public void setCompartir(String compartir) {
        this.compartir = compartir;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
