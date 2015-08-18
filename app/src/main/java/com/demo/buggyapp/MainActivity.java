package com.demo.buggyapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.widget.Toast;

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

    @Nullable private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = getIntent().getStringExtra("username");

        // Bug #2 - Passing in a color ref vs color value
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(android.R.color.black));
    }

    @Override
    protected void doSomeSetup() {
        // Bug #4a - Not calling base class implementation like the documentation states
        super.doSomeSetup();

        String message = username == null ? "No username" : String.format("The username is %d long", calculateUsernameLength(username));
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        try {
            // Bug #3 - app does not have network permissions in the manifest
            checkinWithHome();

            // Bug #4b - app doesn't handle when a file is not deleted
            boolean deleted = deleteOldFile();
            if (!deleted) {
                Toast.makeText(this, "Doh! File not deleted", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Log.e("DEBUG", "don't do this in production", ex);
        }
    }

    @CheckResult
    private boolean deleteOldFile() throws Exception {
        File file = new File("myfile");
        return file.delete();
    }

    @RequiresPermission(Manifest.permission.INTERNET)
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
    @VisibleForTesting
    int calculateUsernameLength(@NonNull String username) {
        return username.length();
    }
}
