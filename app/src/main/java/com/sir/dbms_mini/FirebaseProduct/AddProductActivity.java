package com.sir.dbms_mini.FirebaseProduct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sir.dbms_mini.HomePage.HomePageActivity;
import com.sir.dbms_mini.R;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {
    TextView proName, proCategory, proWidth, proHeight, proDesc, proCost;
    FirebaseFirestore product_firebaseFirestore;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        proName = findViewById(R.id.object_name);
        proCategory = findViewById(R.id.object_category);
        proWidth = findViewById(R.id.object_width);
        proHeight = findViewById(R.id.object_height);
        proDesc = findViewById(R.id.object_desc);
        proCost = findViewById(R.id.object_cost);

        back = findViewById(R.id.backArrow_add);

        product_firebaseFirestore = FirebaseFirestore.getInstance();

        findViewById(R.id.btn_save_object).setOnClickListener(this);
        //findViewById(R.id.btn_view_object).setOnClickListener(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
            }
        });
    }

    private boolean hasValidationErrors(String Pname, String Pcategory, String Pwidth, String Pheight, String Pdescription, String Pcost) {
        return false;
    }

    private void saveProduct() {
        String Pname = proName.getText( ).toString( ).trim( );
        String Pcategory = proCategory.getText( ).toString( ).trim( );
        String Pwidth = proWidth.getText( ).toString( ).trim( );
        String Pheight = proHeight.getText( ).toString( ).trim( );
        String Pdescription= proDesc.getText( ).toString( ).trim( );
        String Pcost = proCost.getText( ).toString( ).trim( );

        if (!hasValidationErrors(Pname, Pcategory, Pwidth, Pheight, Pdescription, Pcost)) {

            CollectionReference collectionReference = product_firebaseFirestore.collection("Product");
            ModelProduct modelProduct = new ModelProduct(Pname, Pcategory, Pwidth, Pheight, Pdescription, Pcost);

            collectionReference.add(modelProduct)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            proName.setText("");
                            proCategory.setText("");
                            proWidth.setText("");
                            proHeight.setText("");
                            proDesc.setText("");
                            proCost.setText("");
                            Toast.makeText(getApplicationContext(), "Data Added", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Could Not Be Added", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save_object:
                saveProduct();
                break;

            /*case R.id.btn_view_object:
                startActivity(new Intent(getApplicationContext(), ViewProductActivity.class));
                break;*/
        }
    }
}