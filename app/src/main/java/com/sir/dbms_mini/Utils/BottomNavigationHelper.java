package com.sir.dbms_mini.Utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sir.dbms_mini.HomePage.HomePageActivity;
import com.sir.dbms_mini.Profile.ProfileActivity;
import com.sir.dbms_mini.R;
import com.sir.dbms_mini.Search.SearchActivity;

public class BottomNavigationHelper {
    private static final String TAG = "BottomNavigationHelper";

    public static void setupBottomNavigation(BottomNavigationView bottomNavigationView){
        Log.d(TAG, "setupBottomNavigationView : Setting up BMV");
    }
    public static void enableNavigation(final Context context, BottomNavigationView view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Intent intent0 = new Intent(context, HomePageActivity.class);// Activity_num = 0
                        context.startActivity(intent0);
                        break;

                    case R.id.search:
                        Intent intent1 = new Intent(context, SearchActivity.class);// Activity_num = 1
                        context.startActivity(intent1);
                        break;

                    case R.id.profile:
                        Intent intent3 = new Intent(context, ProfileActivity.class);// Activity_num = 2
                        context.startActivity(intent3);
                        break;
                }
                return false;
            }
        });
    }
}
