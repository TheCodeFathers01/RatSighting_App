package edu.gatech.tcf.ratsighting_app.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import edu.gatech.tcf.ratsighting_app.R;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button reg = (Button) findViewById(R.id.reg_register);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button cancel = (Button) findViewById(R.id.reg_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHomeScreen();
            }
        });

        Button login = (Button) findViewById(R.id.reg_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchLoginActivity();
            }
        });
    }

//    mAuth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//        @Override
//        public void onComplete(@NonNull Task<AuthResult> task) {
//            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
//
//            // If sign in fails, display a message to the user. If sign in succeeds
//            // the auth state listener will be notified and logic to handle the
//            // signed in user can be handled in the listener.
//            if (!task.isSuccessful()) {
//                Toast.makeText(EmailPasswordActivity.this, R.string.auth_failed,
//                        Toast.LENGTH_SHORT).show();
//            }
//
//            // ...
//        }
//    });

    private void goToHomeScreen() {
        Intent goHome = new Intent(this, WelcomeActivity.class);
        startActivity(goHome);
    }

    public void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
