package com.sir.dbms_mini.Profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.sir.dbms_mini.Utils.BottomNavigationHelper;
import com.sir.dbms_mini.R;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    private Context mcontext3 = ProfileActivity.this;
    private static final int ACTIVITY_NUM = 2;

    TextView pt1,pt2,pt3,pt4,pt5;
    TextView resetemail;
    Button sendresetlink;
    FirebaseFirestore fprofile;
    FirebaseAuth fauthpro;
    String uid_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        pt1 = (TextView)findViewById(R.id.profile_inputname);
        pt2 = (TextView)findViewById(R.id.profile_inputemail);
        pt3 = (TextView)findViewById(R.id.profile_inputphone);
        pt4 = (TextView) findViewById(R.id.profile_inputpwd);
        pt5 = (TextView) findViewById(R.id.profile_inputdept);
        resetemail = (TextView)findViewById(R.id.resetemaillink);

        sendresetlink = (Button)findViewById(R.id.buttonsendreset);


        fprofile = FirebaseFirestore.getInstance();
        fauthpro = FirebaseAuth.getInstance();
        uid_profile =  fauthpro.getCurrentUser().getUid();
        DocumentReference drpro = fprofile.collection("Users").document(uid_profile);
        drpro.addSnapshotListener(this,new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value_pro, FirebaseFirestoreException error) {

                pt1.setText(value_pro.getString("name"));
                pt2.setText(value_pro.getString("email"));
                pt3.setText(value_pro.getString("phone"));
                pt4.setText(value_pro.getString("password"));
                pt5.setText(value_pro.getString("department"));

                resetemail.setText(value_pro.getString("email"));
            }
        });

        sendresetlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_reset = resetemail.getText().toString().trim();
                fauthpro.getInstance().sendPasswordResetEmail(input_reset)
                        .addOnCompleteListener(ProfileActivity.this, new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Reset Link Sent to Your Email", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        setupBottomNavigation();
    }

    //Bottom Navigation Function
    private void setupBottomNavigation(){
        Log.d(TAG, "setupBottomNavigation : setting up Bottom Navigation View");
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);

        //BMV helper
        BottomNavigationHelper.setupBottomNavigation(bottomNavigationView);

        //enabling BMV
        BottomNavigationHelper.enableNavigation(mcontext3, bottomNavigationView);

        //call getMenu for anim
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}