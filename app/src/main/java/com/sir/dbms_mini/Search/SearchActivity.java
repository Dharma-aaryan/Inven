package com.sir.dbms_mini.Search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sir.dbms_mini.Utils.BottomNavigationHelper;
import com.sir.dbms_mini.FirebaseProduct.ModelProduct;
import com.sir.dbms_mini.FirebaseProduct.ProductAdapter;
import com.sir.dbms_mini.R;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    //bottom navigation
    private static final String TAG = "SearchActivity";
    private Context mcontext1 = SearchActivity.this;
    private static final int ACTIVITY_NUM = 1;

    private RecyclerView productRecyclerView;
    private ProductAdapter adapter;
    private List<ModelProduct> productList;
    private ProgressBar progressBar;

    FirebaseFirestore firestore;

    //Search Products
    EditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        productRecyclerView = findViewById(R.id.ProductRecyclerView);
        productRecyclerView.setHasFixedSize(true);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar = findViewById(R.id.progressBar);

        productList = new ArrayList<>();
        adapter = new ProductAdapter(this, productList);
        productRecyclerView.setAdapter(adapter);

        editTextSearch = findViewById(R.id.edittext_search);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


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


        setupBottomNavigation();
    }

    //Bottom Navigation Function
    private void setupBottomNavigation(){
        Log.d(TAG, "setupBottomNavigation : setting up Bottom Navigation View");
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);

        //BMV helper
        BottomNavigationHelper.setupBottomNavigation(bottomNavigationView);

        //enabling BMV
        BottomNavigationHelper.enableNavigation(mcontext1, bottomNavigationView);

        //call getMenu for anim
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    //Search method Filter
    private void filter(String text) {
        ArrayList<ModelProduct> filteredList = new ArrayList<>();

        for (ModelProduct item : productList) {
            if (item.getProName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }
}