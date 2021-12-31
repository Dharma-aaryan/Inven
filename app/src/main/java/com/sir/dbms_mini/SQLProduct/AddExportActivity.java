package com.sir.dbms_mini.SQLProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.sir.dbms_mini.R;

public class AddExportActivity extends AppCompatActivity {
    EditText eName, eCategory, eWidth, eHeight, eDesc, eCost;
    Button saveData, viewData;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_export);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        eName = (EditText) findViewById(R.id.object_name_export);
        eCategory = (EditText) findViewById(R.id.object_category_export);
        eWidth = (EditText) findViewById(R.id.object_width_export);
        eHeight = (EditText) findViewById(R.id.object_height_export);
        eDesc = (EditText) findViewById(R.id.object_desc_export);
        eCost = (EditText) findViewById(R.id.object_cost_export);

        img = (ImageView)findViewById(R.id.backArrow_export);

        saveData = (Button) findViewById(R.id.btn_save_object_export);
        viewData = (Button) findViewById(R.id.btn_view_object_export);

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processInsert(eName.getText().toString(),
                        eCategory.getText().toString(),
                        eWidth.getText().toString(),
                        eHeight.getText().toString(),
                        eDesc.getText().toString(),
                        eCost.getText().toString());
            }
        });

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ViewSQLDataExportActivity.class));
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void processInsert(String n, String c, String w, String h, String d, String c2) {
        String res=new dbmanager(this).addrecord(n,c,w,h,d,c2);
        eName.setText("");
        eCategory.setText("");
        eWidth.setText("");
        eHeight.setText("");
        eDesc.setText("");
        eCost.setText("");
        Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();
    }
}