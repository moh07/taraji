package com.olivapps.taraji.remote.model;

public class Player {
    int id;
    String first_name;
    String last_name;
    String picture;

    public Player(int id, String first_name, String last_name, String picture) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
