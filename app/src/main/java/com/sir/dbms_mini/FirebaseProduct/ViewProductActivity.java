package com.sir.dbms_mini.FirebaseProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sir.dbms_mini.HomePage.HomePageActivity;
import com.sir.dbms_mini.R;

import java.util.ArrayList;
import java.util.List;

public class ViewProductActivity extends AppCompatActivity {
    private RecyclerView productRecyclerView;
    private ProductAdapter adapter;
    private List<ModelProduct> productList;
    private ProgressBar progressBar;
    ImageView backAdd;

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        productRecyclerView = findViewById(R.id.ProductRecyclerView);
        productRecyclerView.setHasFixedSize(true);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar = findViewById(R.id.progressBar);
        backAdd = findViewById(R.id.backArrow_view);

        productList = new ArrayList<>();
        adapter = new ProductAdapter(this, productList);
        productRecyclerView.setAdapter(adapter);

        firestore = FirebaseFirestore.getInstance();
        firestore.collection("Product").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        progressBar.setVisibility( View.GONE);
                        if(!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : list){
                                ModelProduct pro = d.toObject(ModelProduct.class);
                                pro.setId(d.getId());
                                productList.add(pro);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });


        backAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
            }
        });
    }
}