package com.sir.dbms_mini.FirebaseProduct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sir.dbms_mini.FirebaseProduct.ModelProduct;
import com.sir.dbms_mini.FirebaseProduct.ViewProductActivity;
import com.sir.dbms_mini.R;

public class UpdateProductActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText name;
    private EditText category;
    private EditText width;
    private EditText height;
    private EditText description;
    private EditText cost;

    private FirebaseFirestore update_firestore;

    private ModelProduct productInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        productInput = (ModelProduct) getIntent().getSerializableExtra("Product");
        update_firestore = FirebaseFirestore.getInstance();

        name = findViewById(R.id.pro_name_update);
        category = findViewById(R.id.pro_category_update);
        width = findViewById(R.id.pro_width_update);
        height = findViewById(R.id.pro_height_update);
        description = findViewById(R.id.pro_desc_update);
        cost = findViewById(R.id.pro_cost_update);

        name.setText(productInput.getProName());
        category.setText(productInput.getProCategory());
        width.setText(productInput.getProWidth());
        height.setText(productInput.getProHeight());
        description.setText(productInput.getProDesc());
        cost.setText(productInput.getProCost());

        findViewById(R.id.btn_product_delete).setOnClickListener(this);
        findViewById(R.id.btn_product_update).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_product_update:
                updateProduct();
                break;
            case R.id.btn_product_delete:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Are you sure about this?");
                builder.setMessage("Deletion is permanent...");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteStudent();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
                break;
        }
    }

    private void deleteStudent() {
        update_firestore.collection("Product").document(productInput.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), ViewProductActivity.class));
                        }
                    }
                });
    }

    private void updateProduct() {
        String name2 = name.getText().toString().trim();
        String category2 = category.getText().toString().trim();
        String width2 = width.getText().toString().trim();
        String height2 = height.getText().toString().trim();
        String desc2= description.getText().toString().trim();
        String cost2= cost.getText().toString().trim();

        ModelProduct mp= new ModelProduct(
                name2, category2, width2,height2,desc2,cost2);

        update_firestore.collection("Product").document(productInput.getId())
                .update(
                        "name", mp.getProName(),
                        "category", mp.getProCategory(),
                        "width", mp.getProWidth(),
                        "height", mp.getProHeight(),
                        "description", mp.getProDesc(),
                        "cost", mp.getProCost()
                )
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), " Updated", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), ViewProductActivity.class));
                    }
                });
    }
}