package com.example.spotify.DataObjects;

import java.io.Serializable;

public class External_urls implements Serializable {

    String spotify;

    public String getSpotify() {
        return spotify;
    }

    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }

    @Override
    public String toString() {
        return "External_urls{" +
                "spotify='" + spotify + '\'' +
                '}';
    }
}
