package com.example.spotify.Views;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.example.spotify.Models.Album;
import com.example.spotify.Presenters.ArtistsPresenter;
import com.example.spotify.R;
import com.example.spotify.Models.Artist;
import com.example.spotify.Adapters.ArtistAdapter;
import java.util.ArrayList;

//ArtistsActivity activity where I dispalyed the artists after the search submit and the results(Artists) where fetched through
// the intent from the search activity and displayed in a gridview (Each item has a cardview artistprofile)
// I also fetched the albums of the artist clicked here and gave them to the AlbumsActivity activity to display


public class ArtistsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);
        ArtistsPresenter artistsPresenter=new ArtistsPresenter(this);
        android.widget.SearchView searchview=findViewById(R.id.searchvw);
        GridView gridView=findViewById(R.id.grid);
        searchview.setQueryHint(getIntent().getStringExtra("querytext"));
        searchview.setIconified(false);
        searchview.setFocusable(false);
        searchview.setInputType(InputType.TYPE_NULL);
        EnableSearchView(searchview, false);
        gridView.setDrawSelectorOnTop(true);
        ArrayList<Artist> artistss=(ArrayList<Artist>)getIntent().getSerializableExtra("Artists");
        ArtistAdapter adapter = new ArtistAdapter(this,artistss);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ArtistsActivity.this, AlbumsActivity.class);
                Artist ChoseenArtist = artistss.get(position);
                intent.putExtra("artistname", ChoseenArtist.getName());

                ArrayList<Album> albums=artistsPresenter.FetchArtitAlbums(ChoseenArtist);

                if (albums!= null && albums.size() > 0) {

                    intent.putExtra("AlbumsActivity",albums);
                    startActivity(intent);

                }
            }
        });

    }
    private void EnableSearchView(View view, boolean enabled) {
        view.setEnabled(enabled);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                EnableSearchView(child, enabled);
            }
        }
    }
}