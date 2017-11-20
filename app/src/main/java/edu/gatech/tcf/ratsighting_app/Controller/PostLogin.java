package edu.gatech.tcf.ratsighting_app.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.apache.commons.lang3.ObjectUtils;

import edu.gatech.tcf.ratsighting_app.Model.User;
import edu.gatech.tcf.ratsighting_app.Model.UserListContainer;
import edu.gatech.tcf.ratsighting_app.Model.UserType;
import edu.gatech.tcf.ratsighting_app.R;

public class PostLogin extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView addInfo = (TextView) findViewById(R.id.userInfo);
        auth = FirebaseAuth.getInstance();
        String temp = "You are signed in as ";
        try {
            temp += auth.getCurrentUser().getEmail();
        } catch (NullPointerException e) {
            temp = null;
        }

        addInfo.setText(temp);


        Button logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(this);

        Button listButton = (Button) findViewById(R.id.sightingListButton);
        listButton.setOnClickListener(this);

        Button addButton = (Button) findViewById(R.id.NewRatSighting);
        addButton.setOnClickListener(this);

        Button adminButton = (Button) findViewById(R.id.adminButton);
        adminButton.setOnClickListener(this);

        Button mapButton = (Button) findViewById(R.id.mapButton);
        mapButton.setOnClickListener(this);

        Button filtered = (Button) findViewById(R.id.filterButton);
        filtered.setOnClickListener(this);
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
        map.putExtra("Filtered", false);
        startActivity(map);
    }

    private void launchFilteredSearch() {
        Intent intent = new Intent(this, FilteredListGeneratorActivity.class);
        startActivity(intent);
    }
    public void onClick(View v) {
        String temp = getResources().getResourceEntryName(v.getId());
        if(temp.equals("logoutButton") ){
            launchWelcome();
        }
        else if(temp.equals("sightingListButton") ){
            launchSightingList();
        }
        else if(temp.equals("NewRatSighting") ){
            launchAddSighting();
        }
        else if(temp.equals("adminButton") ){
            launchAdminPage();
        }
        else if(temp.equals("mapButton") ){
            launchMap();
        }
        else if(temp.equals("filterButton") ){
            launchFilteredSearch();
        }
        else {
            Toast.makeText(this, "Please select a valid button", Toast.LENGTH_SHORT).show();
        }
    }
}
