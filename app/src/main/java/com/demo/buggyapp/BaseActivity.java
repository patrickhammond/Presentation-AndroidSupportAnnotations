package com.demo.buggyapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        doSomeSetup();
    }

    /**
     * Implementations that override this method should also always call into this implementation
     */
    protected void doSomeSetup() {
        // Does something incredibly important
    }
}
