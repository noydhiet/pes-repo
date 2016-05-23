package com.vny.alfiandari.pesleague;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.vny.alfiandari.pesleague.ListItem.ListEventActivity;
import com.vny.alfiandari.pesleague.ListItem.ProfileActivity;
import com.vny.alfiandari.pesleague.ListItem.RangkingActivity;

import com.vny.alfiandari.pesleague.pendaftaran.SignInActivity;
import com.vny.alfiandari.pesleague.pendaftaran.SignUpActivity;

/**
 * Created by DELL on 5/18/2016.
 */
public class BaseActivityMenu extends AppCompatActivity {
    protected NavigationView navigationView;
    private DrawerLayout mDrawerLayout;
    protected FrameLayout fRaamelayout;
    private boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_navigationview);
        fRaamelayout = (FrameLayout) findViewById(R.id.fRaame_layout);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                Intent i = new Intent();
                switch (menuItem.getItemId()){
                    case R.id.home:
                        i = new Intent(BaseActivityMenu.this, MenuUtama.class);
                        finish();
                        break;
                    case R.id.listevent:
                        i = new Intent(BaseActivityMenu.this, ListEventActivity.class);
                        finish();
                        break;
                    case R.id.ranking:
                        i = new Intent(BaseActivityMenu.this, RangkingActivity.class);
                        finish();
                        break;
                    case R.id.profile:
                        i = new Intent(BaseActivityMenu.this, ProfileActivity.class);
                        finish();
                        break;
                    case R.id.login:
                        i = new Intent(BaseActivityMenu.this, SignInActivity.class);
                        finish();
                        break;
                    case R.id.sign_up:
                        i = new Intent(BaseActivityMenu.this, SignUpActivity.class);
                        finish();
                        break;
                }
                startActivity(i);

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
//            case R.id.action_settings:
//                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        Toast.makeText(this,"Please click BACK again to exit",Toast.LENGTH_SHORT).show();


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
