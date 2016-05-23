package com.vny.alfiandari.pesleague;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by 40622214 on 5/16/2016.
 */
public class MainActivity extends AppCompatActivity {

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, MenuUtama.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
