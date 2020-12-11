package com.example.spotify.Interfaces;

import android.widget.ArrayAdapter;
import com.example.spotify.Models.Artist;
import java.util.ArrayList;

public interface ViewSearch {

    public void AdapterSet(ArrayAdapter<String> adapter);
    public void SendArtists(ArrayList<Artist> artists,String query);
}
