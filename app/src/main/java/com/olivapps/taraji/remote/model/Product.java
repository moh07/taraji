package com.olivapps.taraji.remote.model;

public class Product {
    int id;
    String titre;
    String image;
    String pack;
    String currency = "DT";
    int prix;
    String type;

    public Product(int id, String titre, String image, String pack, int prix, String type) {
        this.id = id;
        this.titre = titre;
        this.image = image;
        this.pack = pack;
        this.prix = prix;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", image='" + image + '\'' +
                ", pack='" + pack + '\'' +
                ", currency='" + currency + '\'' +
                ", prix=" + prix +
                ", type='" + type + '\'' +
                '}';
    }
}
