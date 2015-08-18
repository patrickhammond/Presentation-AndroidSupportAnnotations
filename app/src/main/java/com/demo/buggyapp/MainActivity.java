package com.demo.buggyapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends BaseActivity {

    /**
     * @param username Optional username to show in the title.
     */
    public static Intent createIntent(Context context, String username) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("username", username);
        return intent;
    }

    public static Intent createIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = getIntent().getStringExtra("username");

        // Bug #2 - Passing in a color ref vs color value
        getWindow().getDecorView().setBackgroundColor(android.R.color.black);
    }

    @Override
    protected void doSomeSetup() {
        // Bug #4a - Not calling base class implementation like the documentation states

        setTitle(username);  // Bug #1 - Username is optional, might be null.

        try {
            // Bug #3 - app does not have network permissions in the manifest
            checkinWithHome();

            // Bug #4b - app doesn't handle when a file is not deleted
            deleteOldFile();
        } catch (Exception ex) {
            Log.e("DEBUG", "don't do this in production", ex);
        }
    }

    private boolean deleteOldFile() throws Exception {
        File file = new File("myfile");
        return file.delete();
    }

    public void checkinWithHome() throws IOException {
        // Copied from http://developer.android.com/reference/java/net/HttpURLConnection.html
        URL url = new URL("http://www.home.com/checkin");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            //readStream(in);
        } finally {
            urlConnection.disconnect();
        }
    }

    // Bug #5 - not private, visible for testing, not sure why it isn't private
    int calculateUsernameLength(String username) {
        return username.length();
    }
}
