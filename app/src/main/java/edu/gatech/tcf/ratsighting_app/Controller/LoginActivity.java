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
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.gatech.tcf.ratsighting_app.Model.DefaultUser;
import edu.gatech.tcf.ratsighting_app.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private EditText email;
    private EditText pass;
    private Button loginButton;
    private Button cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        email = (EditText) findViewById(R.id.userText);

        pass = (EditText) findViewById(R.id.passText);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            goToPostLogin();
        }
    }

    private void goToPostLogin() {
        Intent goHome = new Intent(this, PostLogin.class);
        startActivity(goHome);
    }

    private void goToHomeScreen() {
        Intent goHome = new Intent(this, WelcomeActivity.class);
        startActivity(goHome);
    }

    @Override
    public void onClick(View v) {
        if(v == loginButton){
            loginUser();
        }
    }
    /**
     *
     * logs in the user
     *
     */
    private void loginUser() {
        String emailAddress = email.getText().toString().trim();
        String password = pass.getText().toString().trim();
        if(TextUtils.isEmpty(emailAddress)){
            Toast.makeText(this, "Login Failed, Please enter a email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Login Failed, Please enter a password", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(emailAddress, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    goToPostLogin();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed, Email and Password were not recognized", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
