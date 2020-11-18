package com.example.spotify.DataObjects;

import com.example.spotify.models.Artists;

import java.io.Serializable;

// this object is used to fill the data fetched after the request for the search artist, its an object of artists

public class Data implements Serializable {

    public Artists artists=new Artists();;

    public Data(){ }

}
