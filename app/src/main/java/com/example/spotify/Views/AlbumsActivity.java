package com.example.spotify.Views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import com.example.spotify.R;
import com.example.spotify.Models.Album;
import com.example.spotify.Adapters.AlbumAdapter;
import java.util.ArrayList;

//AlbumsActivity activity where I dispalyed the albums of an artist using a gridview and fetching the arraylist of albums by intent

public class AlbumsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        GridView gridView=findViewById(R.id.grid2);
        TextView ArtistName=findViewById(R.id.namear);
        ArtistName.setText(getIntent().getStringExtra("artistname"));
        gridView.setDrawSelectorOnTop(true);
        AlbumAdapter adapter = new AlbumAdapter(this,(ArrayList<Album>)getIntent().getSerializableExtra("AlbumsActivity"));
        gridView.setAdapter(adapter);

    }

}