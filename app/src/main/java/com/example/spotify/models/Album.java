package com.example.spotify.models;

import com.example.spotify.DataObjects.AlbumArtists;
import com.example.spotify.DataObjects.External_urls;
import com.example.spotify.DataObjects.Image;

import java.io.Serializable;
import java.util.ArrayList;

//the object that contains all the necessary details for an artist album

public class Album implements Serializable {

    String album_group;
    String album_type;
    ArrayList<AlbumArtists> artists;
    ArrayList<String> available_markets;
    External_urls external_urls;
    String href;
    String id;
    ArrayList<Image> images;
    String name;
    String release_date;
    String release_date_precision;
    int total_tracks;
    String type;
    String uri;

    public Album() { }

    public String getAlbum_group() {
        return album_group;
    }

    public void setAlbum_group(String album_group) {
        this.album_group = album_group;
    }

    public String getAlbum_type() {
        return album_type;
    }

    public void setAlbum_type(String album_type) {
        this.album_type = album_type;
    }

    public ArrayList<AlbumArtists> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<AlbumArtists> artists) {
        this.artists = artists;
    }

    public ArrayList<String> getAvailable_markets() {
        return available_markets;
    }

    public void setAvailable_markets(ArrayList<String> available_markets) { this.available_markets = available_markets; }

    public External_urls getExternal_urls() {
        return external_urls;
    }

    public void setExternal_urls(External_urls external_urls) { this.external_urls = external_urls; }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getAId() {
        return id;
    }

    public void setAId(String id) {
        this.id = id;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public String getAName() {
        return name;
    }

    public void setAName(String name) {
        this.name = name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getRelease_date_precision() {
        return release_date_precision;
    }

    public void setRelease_date_precision(String release_date_precision) {
        this.release_date_precision = release_date_precision;
    }

    public int getTotal_tracks() {
        return total_tracks;
    }

    public void setTotal_tracks(int total_tracks) {
        this.total_tracks = total_tracks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
