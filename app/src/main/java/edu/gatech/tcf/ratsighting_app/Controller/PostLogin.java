package edu.gatech.tcf.ratsighting_app.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;

import edu.gatech.tcf.ratsighting_app.R;

public class PostLogin extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView addInfo = (TextView) findViewById(R.id.userInfo);
        firebaseAuth = FirebaseAuth.getInstance();
        String temp = "You are signed in as" + firebaseAuth.getCurrentUser().getEmail();
        addInfo.setText(temp);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchWelcome();
            }
        });
    }
    /**
     *
     * launches the Welcome Screen
     *
     */
    private void launchWelcome() {
        firebaseAuth.signOut();
        Toast.makeText(this, "Signed Out", Toast.LENGTH_SHORT).show();
        Intent goHome = new Intent(this, WelcomeActivity.class);
        startActivity(goHome);
    }

}
