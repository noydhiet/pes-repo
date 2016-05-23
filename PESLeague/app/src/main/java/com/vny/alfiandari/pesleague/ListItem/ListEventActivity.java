package com.vny.alfiandari.pesleague.ListItem;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.vny.alfiandari.pesleague.BaseActivityMenu;
import com.vny.alfiandari.pesleague.R;

import com.vny.alfiandari.pesleague.pendaftaran.SignInActivity;
import com.vny.alfiandari.pesleague.pendaftaran.SignUpActivity;

/**
 * Created by DELL on 5/17/2016.
 */
public class ListEventActivity extends BaseActivityMenu {
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.list_event_activity);
        getLayoutInflater().inflate(R.layout.list_event_activity,fRaamelayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setTitle("LIST EVENT");
        actionBar.setDisplayHomeAsUpEnabled(true);


    }
}
