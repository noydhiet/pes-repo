package com.vny.alfiandari.pesleague.ListItem;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.vny.alfiandari.pesleague.BaseActivityMenu;
import com.vny.alfiandari.pesleague.R;

/**
 * Created by DELL on 5/17/2016.
 */
public class ProfileActivity extends BaseActivityMenu {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_profile, fRaamelayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setTitle("PROFILE");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
