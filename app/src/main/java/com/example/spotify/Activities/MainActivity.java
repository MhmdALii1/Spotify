package com.example.spotify.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.spotify.R;
import com.example.spotify.models.User;
import com.example.spotify.models.UserService;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

// the activity where the login and authentication using spotify api is done, I made connection
// with the spotify api to view a login interface inorder to login to my account and be redirected
// to the Artistss activity, the data of user is fetched using json and gson objects as the
// albums and artists, and of course by volley and requests

public class MainActivity extends AppCompatActivity {
    Button login;
    ProgressBar progressbar;
    Intent intent;
    public static User user = null;
    private SharedPreferences.Editor editor;
    private SharedPreferences msharedPreferences;

    private RequestQueue queue;

    static final String  CLIENT_ID = "ef3bd01982014562b84ded84b50df3c2",
                         REDIRECT_URI="https://com.example.spotify/callback/";
    static final int     REQUEST_CODE=1337;
    static final String  SCOPES = "user-library-modify,user-read-email,user-read-private,streaming";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressbar=findViewById(R.id.progressBar);
        login=findViewById(R.id.login);
        progressbar.setVisibility(View.INVISIBLE);

        login.setOnClickListener(v -> {

            progressbar.setVisibility(View.VISIBLE);
            authenticateSpotify();
            msharedPreferences = this.getSharedPreferences("SPOTIFY", 0);
            queue = Volley.newRequestQueue(this);

        });

    }

    private void authenticateSpotify() {
        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);
        builder.setScopes(new String[]{SCOPES});
        AuthenticationRequest request = builder.build();
        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
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
                    editor = getSharedPreferences("SPOTIFY", 0).edit();
                    editor.putString("token", response.getAccessToken());


                    Log.d("STARTING", "GOT AUTH TOKEN");
                    editor.apply();
                    progressbar.setVisibility(View.INVISIBLE);
                    waitForUserInfo();
                    break;

                // Auth flow returned an error
                case ERROR:
                    // Handle error response
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases
            }
        }
    }

    private void waitForUserInfo() {
        UserService userService = new UserService(queue, msharedPreferences);
        userService.get(() -> {
            user = userService.getUser();
            editor = getSharedPreferences("SPOTIFY", 0).edit();
            editor.putString("userid", user.id);
            Log.d("STARTING", "GOT USER INFORMATION");
            // We use commit instead of apply because we need the information stored immediately
            editor.commit();
            startMainActivity();
        });
    }

    private void startMainActivity() {
        intent=new Intent(this, Search.class);
        intent.putExtra("em",user.email);
        startActivity(intent);
    }

}