package com.example.spotify.models;

import com.example.spotify.DataObjects.External_urls;
import com.example.spotify.DataObjects.Followers;
import com.example.spotify.DataObjects.Image;
import java.io.Serializable;
import java.util.ArrayList;

//the object that contains all the necessary details about a specific artist

public class Artist implements Serializable {

    External_urls external_urls;
    Followers followers;
    ArrayList<String> genres;
    String href;
    String id;
    ArrayList<Image> images;
    String name;
    int popularity;
    String type;
    String uri;

    public Artist(){ }


    public External_urls getExternal_urls() {
        return external_urls;
    }

    public void setExternal_urls(External_urls external_urls) { this.external_urls = external_urls; }

    public Followers getFollowers() {
        return followers;
    }

    public void setFollowers(Followers followers) {
        this.followers = followers;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
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

    @Override
    public String toString() {
        return "Artist{\n" +
                "\nexternal_urls=" + external_urls +
                ", \nfollowers=" + followers +
                ", \ngenres=" + genres +
                ", \nhref='" + href + '\'' +
                ", \nid='" + id + '\'' +
                ", \nimages=" + images +
                ", \nname='" + name + '\'' +
                ", \npopularity=" + popularity +
                ", \ntype='" + type + '\'' +
                ", \nuri='" + uri + '\'' +"\n\n"+
                '}';
    }

}
