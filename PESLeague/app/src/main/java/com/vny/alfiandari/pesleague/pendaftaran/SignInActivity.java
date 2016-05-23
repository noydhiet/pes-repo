package com.vny.alfiandari.pesleague.pendaftaran;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vny.alfiandari.pesleague.BaseActivityMenu;
import com.vny.alfiandari.pesleague.R;

/**
 * Created by DELL on 5/18/2016.
 */
public class SignInActivity extends BaseActivityMenu {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private EditText ed_username;
    private EditText ed_pass;
    private Button btn_login;
    private TextView txt_link_signup;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
        getLayoutInflater().inflate(R.layout.activity_login, fRaamelayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setTitle("LOGIN");
        actionBar.setDisplayHomeAsUpEnabled(true);

        ed_username = (EditText) findViewById(R.id.ed_username);
        ed_pass = (EditText) findViewById(R.id.ed_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        txt_link_signup = (TextView) findViewById(R.id.txtlink_signup);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mtd_Login();
            }
        });
        txt_link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(SignInActivity.this, SignUpActivity.class) );
            }
        });
    }

    private void Mtd_Login(){
        Log.d(TAG, "LOGIN");

        if(!isValidate()){
            Onloginvalid();
            return;
        }

        btn_login.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(SignInActivity.this,
                R.style.AppTheme_NoActionBar);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void onLoginSuccess() {
        btn_login.setEnabled(true);
//        finish();
    }

    private boolean isValidate(){
        boolean valid = true;
        String username =  ed_username.getText().toString().trim();
        String pass = ed_pass.getText().toString();

        if(username.isEmpty()){
            ed_username.setError("enter a valid username");
            valid = false;
        }else{
            ed_username.setError(null);
        }

        if(pass.isEmpty() || pass.length() < 4 || pass.length() > 10){
            ed_pass.setError("beetwen 4 and 10 alphanumeric characters");
            valid = false;
        }else{
            ed_pass.setError(null);
        }
        return valid;
    }
    private void Onloginvalid(){
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        txt_link_signup.setTextColor(Color.BLUE);
        btn_login.setEnabled(true);

    }
}
