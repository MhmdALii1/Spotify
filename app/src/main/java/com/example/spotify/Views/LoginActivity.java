package com.example.spotify.Views;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.spotify.Presenters.LoginPresenter;
import com.example.spotify.R;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

// the activity where the login and authentication using spotify api is done, I made connection
// with the spotify api to view a login interface inorder to login to my account and be redirected
// to the ArtistsActivity activity, the data of user is fetched using json and gson objects as
// albums and artists, and of course by volley requests

public class LoginActivity extends AppCompatActivity {

    SharedPreferences SharedPreferencess;
    LoginPresenter loginPresenter;
    int REQUEST_CODE=1337;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ProgressBar progressbar=findViewById(R.id.progressBar);
        Button loginbutton=findViewById(R.id.login);
        progressbar.setVisibility(View.INVISIBLE);

        loginPresenter=new LoginPresenter(this);

        loginbutton.setOnClickListener(v -> {

            progressbar.setVisibility(View.VISIBLE);
            loginPresenter.authenticateSpotify();
            SharedPreferencess = this.getSharedPreferences("SPOTIFY", 0);
            queue = Volley.newRequestQueue(this);

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);

            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
                    SharedPreferences.Editor editor = getSharedPreferences("SPOTIFY", 0).edit();
                    editor.putString("token", response.getAccessToken());
                    Log.d("STARTING", "GOT AUTH TOKEN");
                    editor.apply();
                    ProgressBar progressbar=findViewById(R.id.progressBar);
                    progressbar.setVisibility(View.INVISIBLE);
                    loginPresenter.waitForUserInfo(queue,SharedPreferencess);
                    break;

                // Auth flow returned an error
                case ERROR:
                    Toast.makeText(this,"Sorry, Failed To Sign In !\nTry Again :)",Toast.LENGTH_LONG).show();
                    Intent intent1=new Intent(this,LoginActivity.class);
                    startActivity(intent1);
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases
            }
        }
    }
}