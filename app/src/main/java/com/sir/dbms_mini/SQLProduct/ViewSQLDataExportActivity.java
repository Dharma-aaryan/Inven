package com.sir.dbms_mini.SQLProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.sir.dbms_mini.HomePage.HomePageActivity;
import com.sir.dbms_mini.R;

import java.util.ArrayList;

public class ViewSQLDataExportActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ModelSQL> dataholder;
    ImageView backHome;
    ProgressBar progBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sqldata_export);

        progBar = findViewById(R.id.progressBar_view);
        backHome = (ImageView) findViewById(R.id.backArrow_view_sql);
        recyclerView=(RecyclerView)findViewById(R.id.ViewSQL);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor=new dbmanager(this).readalldata();
        dataholder=new ArrayList<>();

        while(cursor.moveToNext())
        {
            ModelSQL obj=new ModelSQL(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
            dataholder.add(obj);
            progBar.setVisibility(View.GONE);
        }

        SQLAdapter adapter = new SQLAdapter(dataholder);
        recyclerView.setAdapter(adapter);

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
            }
        });

    }
}