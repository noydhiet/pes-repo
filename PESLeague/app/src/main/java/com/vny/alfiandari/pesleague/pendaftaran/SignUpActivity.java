package com.vny.alfiandari.pesleague.pendaftaran;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vny.alfiandari.pesleague.BaseActivityMenu;
import com.vny.alfiandari.pesleague.R;

/**
 * Created by DELL on 5/18/2016.
 */
public class SignUpActivity extends BaseActivityMenu {
    private static final String TAG = "SignupActivity";
    private EditText ed_email;
    private EditText ed_username;
    private EditText ed_fullname;
    private EditText ed_city;
    private EditText ed_password;
    private Button btn_signUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_signup, fRaamelayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setTitle("SIGN UP");
        actionBar.setDisplayHomeAsUpEnabled(true);

        ed_email = (EditText) findViewById(R.id.ed_email);
        ed_username = (EditText) findViewById(R.id.ed_username_signup);
        ed_fullname = (EditText) findViewById(R.id.ed_fullname);
        ed_city = (EditText) findViewById(R.id.ed_city);
        ed_password = (EditText) findViewById(R.id.ed_pass_signup);
        btn_signUp = (Button) findViewById(R.id.btn_signup);

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signUp(){
        Log.d(TAG, "SignUp");

        if(!isValid()){
            Onloginvalid();
            return;
        }
        btn_signUp.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this,
                R.style.AppTheme_NoActionBar);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onSignUpSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    private void onSignUpSuccess(){
        btn_signUp.setEnabled(true);
        setResult(RESULT_OK,null);
        finish();
    }

    private boolean isValid(){
        boolean valid=true;

        String str_email = ed_email.getText().toString();
        String str_username = ed_username.getText().toString();
        String str_fullname = ed_fullname.getText().toString();
        String str_city = ed_city.getText().toString();
        String str_pass = ed_password.getText().toString();

        if(str_email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(str_email).matches()){
            ed_email.setError("enter a valid email");
            valid=false;
        }else{
            ed_email.setError(null);
        }

        if(str_username.isEmpty() || str_username.length() > 10){
            ed_username.setError("enter a valid username between 0 and 10 characters");
            valid=false;
        }else{
            ed_username.setError(null);
        }

        if(str_fullname.isEmpty()){
            ed_fullname.setError("enter a valid fullname");
            valid=false;
        }else{
            ed_fullname.setError(null);
        }

        if(str_city.isEmpty()){
            ed_city.setError("enter a valid city");
            valid=false;
        }else{
            ed_city.setError(null);
        }

        if(str_pass.isEmpty() || str_pass.length() < 4 || str_pass.length() > 8 ){
            ed_password.setError("between 4 and 8 alphanumeric characters");
            valid=false;
        }else{
            ed_password.setError(null);
        }
        return true;
    }

    private void Onloginvalid(){
        Toast.makeText(getBaseContext(), "Logout failed", Toast.LENGTH_LONG).show();
//        txt_link_signup.setTextColor(Color.BLUE);
        btn_signUp.setEnabled(true);

    }
}
