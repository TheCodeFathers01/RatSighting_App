package edu.gatech.tcf.ratsighting_app.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.gatech.tcf.ratsighting_app.Model.DefaultUser;
import edu.gatech.tcf.ratsighting_app.R;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText name = (EditText) findViewById(R.id.userText);

        EditText pass = (EditText) findViewById(R.id.passText);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.userText);

                EditText pass = (EditText) findViewById(R.id.passText);
                if(validateUserAndPass(name, pass)) {
                    goToPostLogin();
                } else{
                    Snackbar.make(v, "Login Credentials are not recognized. Please check username and password", Snackbar.LENGTH_LONG)
                       .setAction("Action", null).show();
                }
            }
        });

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHomeScreen();
            }
        });

        mAuth = FirebaseAuth.getInstance();


    }

    private void goToPostLogin() {
        Intent goHome = new Intent(this, PostLogin.class);
        startActivity(goHome);
    }

    private void goToHomeScreen() {
        //
        Intent goHome = new Intent(this, WelcomeActivity.class);
        startActivity(goHome);
    }
    private boolean validateUserAndPass(EditText name, EditText pass){
        String nameUser = name.getText().toString();
        String namePass = pass.getText().toString();
        if(nameUser.equals("user") && namePass.equals("pass")){
            return true;
        }        return false;
    }

}
