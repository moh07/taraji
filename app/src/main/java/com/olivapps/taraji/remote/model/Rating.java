package com.olivapps.taraji.remote.model;

public class Rating {
    int id;
    Player player;
    int rating;

    public Rating(int id, Player player, int rating) {
        this.id = id;
        this.player = player;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", player=" + player +
                ", rating=" + rating +
                '}';
    }
}
