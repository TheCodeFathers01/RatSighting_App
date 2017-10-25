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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import edu.gatech.tcf.ratsighting_app.Model.SightingListContainer;
import edu.gatech.tcf.ratsighting_app.Model.User;
import edu.gatech.tcf.ratsighting_app.Model.UserListContainer;
import edu.gatech.tcf.ratsighting_app.Model.UserType;
import edu.gatech.tcf.ratsighting_app.R;

public class PostLogin extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView addInfo = (TextView) findViewById(R.id.userInfo);
        auth = FirebaseAuth.getInstance();
        String temp = "You are signed in as " + auth.getCurrentUser().getEmail();
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

        Button listButton = (Button) findViewById(R.id.sightingListButton);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSightingList();
            }
        });

        Button addButton = (Button) findViewById(R.id.NewRatSighting);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAddSighting();
            }
        });

        Button adminButton = (Button) findViewById(R.id.adminButton);
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchAdminPage();
            }
        });

        Button mapButton = (Button) findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMap();
            }
        });

        Button loadButton = (Button) findViewById(R.id.loadButton);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }
    /**
     *
     * launches the Welcome Screen
     *
     */
    private void launchWelcome() {
        auth.signOut();
        Toast.makeText(this, "Signed Out", Toast.LENGTH_SHORT).show();
        Intent goHome = new Intent(this, WelcomeActivity.class);
        startActivity(goHome);
    }

    private void launchSightingList() {
        Intent goList = new Intent(this, SightingList.class);
        startActivity(goList);
    }

    private void launchAddSighting() {
        Intent addActivity = new Intent(this, AddNewRatSightingReport.class);
        startActivity(addActivity);
    }

    private void launchAdminPage() {
        FirebaseUser firebaseUser = auth.getCurrentUser();
        String userEmail = firebaseUser.getEmail();
        boolean isAdmin = false;
        for (User user : UserListContainer.list) {
            if (user.getEmail().equals(userEmail) && user.getuT() == UserType.ADMIN) {
                isAdmin = true;
            }
        }
        if (isAdmin) {
            Intent goAdmin = new Intent(this, CSVReaderActivity.class);
            startActivity(goAdmin);
        } else {
            Toast.makeText(this, "Thats not for you", Toast.LENGTH_SHORT).show();
        }
    }

    private void launchMap() {
        Intent map = new Intent(this, MapsActivity.class);
        startActivity(map);
    }

    private void saveData() {
        try {
            File userFile = new File(this.getFilesDir(), "Users");
            File sightingFile = new File(this.getFilesDir(), "Sightings");
            ObjectOutputStream outUser = new ObjectOutputStream(new FileOutputStream(userFile));
            ObjectOutputStream outSighting = new ObjectOutputStream(new FileOutputStream(sightingFile));
            outUser.writeObject(UserListContainer.list);
            outSighting.writeObject(SightingListContainer.list);
            outUser.close();
            outSighting.close();
            Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData() {
        try {
            File userFile = new File(this.getFilesDir(), "Users");
            File sightingFile = new File(this.getFilesDir(), "Sightings");
            ObjectInputStream inUser = new ObjectInputStream(new FileInputStream(userFile));
            ObjectInputStream inSighting = new ObjectInputStream(new FileInputStream(sightingFile));
            UserListContainer.list = (List) inUser.readObject();
            SightingListContainer.list = (ArrayList) inSighting.readObject();
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
