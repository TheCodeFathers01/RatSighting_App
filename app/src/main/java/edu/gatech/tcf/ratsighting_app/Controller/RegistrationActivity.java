package edu.gatech.tcf.ratsighting_app.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.tcf.ratsighting_app.Model.Admin;
import edu.gatech.tcf.ratsighting_app.Model.DefaultUser;
import edu.gatech.tcf.ratsighting_app.Model.User;
import edu.gatech.tcf.ratsighting_app.Model.UserType;
import edu.gatech.tcf.ratsighting_app.R;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText user;
    private EditText email;
    private EditText pass;
    private Spinner userType;
    private FirebaseAuth firebaseAuth;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference("server/saving-data/userData");
    private String emailAddress;
    private String password;
    private String username;
    private UserType tempUT;
    private final List<User> tempU = new ArrayList<User>();
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ref = database.getReference("server/saving-data/userData");
        firebaseAuth = FirebaseAuth.getInstance();
        user = (EditText) findViewById(R.id.reg_username);
        email = (EditText) findViewById(R.id.reg_email);
        pass = (EditText) findViewById(R.id.reg_password);

        Button reg = (Button) findViewById(R.id.reg_register);
        reg.setOnClickListener(this);

        Button cancel = (Button) findViewById(R.id.reg_cancel);
        cancel.setOnClickListener(this);

        TextView login = (TextView) findViewById(R.id.reg_login);
        login.setOnClickListener(this);


        userType = (Spinner) findViewById(R.id.reg_selectUserType);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, UserType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userType.setAdapter(adapter);


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
    /**
     *
     *This one registers a user to the firebase database.
     * it uses firebase Auth and firebase Database
     *
     */
    private void registerUser() {
        emailAddress = email.getText().toString().trim();
        password = pass.getText().toString().trim();
        username = user.getText().toString().trim();
        if(TextUtils.isEmpty(emailAddress)){
            Toast.makeText(this, "Registration was Unsuccessful, enter a email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Registration was Unsuccessful, enter a password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this, "Registration was Unsuccessful, enter a username", Toast.LENGTH_SHORT).show();
            return;
        }
        tempUT = (UserType) userType.getSelectedItem(); //Get type from spinner
        usersRef = ref.child("User:" + username);
        User temp = new User();
        if (tempUT == UserType.USER) {
            temp = new DefaultUser(username, password, emailAddress, tempUT);
        } else if (tempUT == UserType.ADMIN) {
            temp = new Admin(username, password, emailAddress, tempUT);
        }
        usersRef.setValue(temp);
        firebaseAuth.createUserWithEmailAndPassword(emailAddress, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                boolean temp = task.isSuccessful();
                if (task.isSuccessful()) {
                    launchPostLoginActivity();
                } else {
                    String tempS = task.getException().toString();
                    Toast.makeText(RegistrationActivity.this, "Registration was Unsuccessful because " + tempS + ", please check your email or try a different password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /**
     *
     * sends user to Home Screen
     *
     */
    private void goToHomeScreen() {
        Intent goHome = new Intent(this, WelcomeActivity.class);
        startActivity(goHome);
    }
    /**
     *
     * sends user to Login Screen
     *
     */
    private void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    /**
     *
     * sends user to Post Login Screen
     *
     */
    private void launchPostLoginActivity() {
        firebaseAuth.signInWithEmailAndPassword(emailAddress, password);
        Intent intent = new Intent(this, PostLogin.class);
        startActivity(intent);
    }

    @Override
    /**
     *
     * determines what button was pressed and what to do
     *
     */
    public void onClick(View v) {
        String temp = getResources().getResourceEntryName(v.getId());
        if(temp.equals("reg_register") ){
            launchPostLoginActivity();
        }
        else if(temp.equals("reg_cancel") ){
            goToHomeScreen();
        }
        else if(temp.equals("reg_login") ){
            launchLoginActivity();
        }
        else {
            Toast.makeText(this, "Please select a valid button", Toast.LENGTH_SHORT).show();
        }
    }
}
