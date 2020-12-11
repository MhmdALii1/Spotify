package com.example.spotify.Models;

import java.io.Serializable;

//the object that contains all the necessary details for an artist related to a specific album

public class AlbumArtists implements Serializable {

    External_urls external_urls;
    String href;
    String id;
    String name;
    String type;
    String uri;

    public AlbumArtists() {

    }

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

    public String getAAId() {
        return id;
    }

    public void setAAId(String id) {
        this.id = id;
    }

    public String getAAName() {
        return name;
    }

    public void setAAName(String name) {
        this.name = name;
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
