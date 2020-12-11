package com.example.spotify.Views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.spotify.Models.Artist;
import com.example.spotify.Presenters.SearchPresenter;
import com.example.spotify.R;
import com.example.spotify.Interfaces.ViewSearch;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements ViewSearch {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ListView listView = findViewById(R.id.list);
        android.widget.SearchView searchview = findViewById(R.id.search1);
        SearchPresenter searchPresenter=new SearchPresenter(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                String selected = (listView.getItemAtPosition(position).toString());
                                                searchview.setQuery(selected,false);
                                                listView.setVisibility(View.GONE);


                                            }
                                        });

                searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {

                        listView.setVisibility(View.GONE);
                        if (!(query.equals(""))) {

                            searchPresenter.SearchResultsSubmit(query);

                        }

                        searchview.clearFocus();
                        return true;
                    }
                    @Override
                    public boolean onQueryTextChange(String newText) {


                        if(newText.equals("")) {

                            listView.setVisibility(View.GONE);


                        }else{

                            searchPresenter.SearchResultsSuggestions(newText);

                        }

                        return true;
                    }
                });
    }

    @Override
    public void AdapterSet(ArrayAdapter<String> adapter) {

                ListView listView = findViewById(R.id.list);
                listView.setVisibility(View.VISIBLE);
                listView.setBackgroundColor(Color.WHITE);
                listView.setAdapter(adapter);

        }

    @Override
    public void SendArtists(ArrayList<Artist> artists,String query){

        if (artists!= null && artists.size() > 0) {

            Intent intent = new Intent(SearchActivity.this, ArtistsActivity.class);
            intent.putExtra("querytext", query);
            intent.putExtra("Artists", artists);
            startActivity(intent);

        } else {
            Toast.makeText(SearchActivity.this, "Sorry, this artist doesn't exist!", Toast.LENGTH_LONG).show();

        }

    }

}



















