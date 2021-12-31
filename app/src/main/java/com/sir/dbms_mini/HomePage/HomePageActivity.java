package com.sir.dbms_mini.HomePage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.sir.dbms_mini.Utils.BottomNavigationHelper;
import com.sir.dbms_mini.ExtraActivities.About;
import com.sir.dbms_mini.ExtraActivities.ContactEmail;
import com.sir.dbms_mini.ExtraActivities.IntroPage;
import com.sir.dbms_mini.FirebaseProduct.AddProductActivity;
import com.sir.dbms_mini.FirebaseProduct.ScanProductActivity;
import com.sir.dbms_mini.FirebaseProduct.ViewProductActivity;
import com.sir.dbms_mini.Profile.ProfileActivity;
import com.sir.dbms_mini.R;
import com.sir.dbms_mini.SQLProduct.AddExportActivity;
import com.sir.dbms_mini.SQLProduct.AddSQLActivity;
import com.sir.dbms_mini.SQLProduct.ViewSQLDataExportActivity;
import com.sir.dbms_mini.Utils.tp;

public class HomePageActivity extends AppCompatActivity {
    //Bottom Navigation
    private static final String TAG = "HomePageActivity";
    private Context mcontext = HomePageActivity.this;
    private static final int ACTIVITY_NUM = 0;

    //Navigation Drawer
    NavigationView navMenu;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    Button btnCheck, btnAdd, btnExport, btnScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navMenu = (NavigationView)findViewById(R.id.navigationMenu);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        btnCheck = (Button) findViewById(R.id.btn_check_inven);
        btnAdd = (Button)findViewById(R.id.btn_add_inven);
        btnExport = (Button)findViewById(R.id.btn_export_inven);
        btnScan = (Button)findViewById(R.id.btn_add_scan_inven);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ViewProductActivity.class));
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddProductActivity.class));
            }
        });

        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddExportActivity.class));
            }
        });

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScanProductActivity.class));
            }
        });

        setupBottomNavigation();
        setUpNavigationMenu();
    }

    //Navigation Function
    private void setUpNavigationMenu() {
        navMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.navMenu_import:
                        startActivity(new Intent(getApplicationContext(), AddProductActivity.class));
                        Toast.makeText(getApplicationContext(),"New Import Order",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navMenu_view_import:
                        startActivity(new Intent(getApplicationContext(), ViewProductActivity.class));
                        Toast.makeText(getApplicationContext(),"View Order",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navMenu_export:
                        startActivity(new Intent(getApplicationContext(), AddExportActivity.class));
                        Toast.makeText(getApplicationContext(),"New Export Order",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navMenu_view_export:
                        startActivity(new Intent(getApplicationContext(), ViewSQLDataExportActivity.class));
                        Toast.makeText(getApplicationContext(),"View Order",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navMenu_scan:
                        startActivity(new Intent(getApplicationContext(), ScanProductActivity.class));
                        Toast.makeText(getApplicationContext(),"Scan",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navMenu_aboutus:
                        startActivity(new Intent(getApplicationContext(), About.class));
                        Toast.makeText(getApplicationContext(),"About the App",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navMenu_email:
                        startActivity(new Intent(getApplicationContext(), ContactEmail.class));
                        Toast.makeText(getApplicationContext(),"Send Email",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navMenu_profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        Toast.makeText(getApplicationContext(),"Profile Details",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navMenu_signout:
                        startActivity(new Intent(getApplicationContext(), IntroPage.class));
                        Toast.makeText(getApplicationContext(),"Signed-Out Successfully",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });
    }

    //Bottom Navigation Function
    private void setupBottomNavigation(){
        Log.d(TAG, "setupBottomNavigation : setting up Bottom Navigation View");
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);

        //BMV helper
        BottomNavigationHelper.setupBottomNavigation(bottomNavigationView);

        //enabling BMV
        BottomNavigationHelper.enableNavigation(mcontext, bottomNavigationView);

        //call getMenu for anim
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}