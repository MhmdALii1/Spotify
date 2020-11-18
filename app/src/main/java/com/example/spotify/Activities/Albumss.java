package com.example.spotify.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spotify.R;
import com.example.spotify.models.Album;
import com.example.spotify.models.AlbumAdapter;
import com.example.spotify.models.Artist;
import com.example.spotify.models.ArtistAdapter;

import java.util.ArrayList;

//Albumss activity where I dispalyed the albums of an artist using a gridview and fetching the arraylist of albums by intent

public class Albumss extends AppCompatActivity {

    public static ArrayList<Album> albumss = new ArrayList<>();

    GridView gridView1;
    AlbumAdapter adapterr;
    SharedPreferences sharedPreferencess;
    Intent intent1;
    TextView nameart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        sharedPreferencess = this.getSharedPreferences("SPOTIFY", 0);
        gridView1=findViewById(R.id.grid2);
        nameart=findViewById(R.id.namear);
        intent1=getIntent();
        nameart.setText(intent1.getStringExtra("artistname"));
        albumss=(ArrayList<Album>)intent1.getSerializableExtra("Albumss");
        gridView1.setDrawSelectorOnTop(true);
        adapterr = new AlbumAdapter(this,(ArrayList<Album>)intent1.getSerializableExtra("Albumss"));
        gridView1.setAdapter(adapterr);

    }

}