package com.example.sqlitep2.data.model;

public class User {
    private String id;
    private String username;
    private String email;
    private String imageUrl;
    private String apellido;
    private String barrio;
    private String id_cargo;
    private int edad;

    public User() {

    }

    public User(String id, int edad, String id_cargo, String barrio, String apellido, String imageUrl, String email, String username) {
        this.id = id;
        this.edad = edad;
        this.id_cargo = id_cargo;
        this.barrio = barrio;
        this.apellido = apellido;
        this.imageUrl = imageUrl;
        this.email = email;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(String id_cargo) {
        this.id_cargo = id_cargo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
