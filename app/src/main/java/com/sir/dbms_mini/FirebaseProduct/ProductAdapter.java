package com.sir.dbms_mini.FirebaseProduct;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sir.dbms_mini.R;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context mCtx1;
    private List<ModelProduct> productList;

    public ProductAdapter(Context mCtx1, List<ModelProduct> productList) {
        this.mCtx1 = mCtx1;
        this.productList = productList;
    }

    public ProductAdapter(ArrayList<ModelProduct> productlist) {
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductAdapter.ProductViewHolder(
                LayoutInflater.from(mCtx1).inflate(R.layout.layout_single_row, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        ModelProduct productInput = productList.get(position);

        holder.nameP.setText(productInput.getProName());
        holder.categoryP.setText(productInput.getProCategory());
        holder.widthP.setText(productInput.getProWidth());
        holder.heightP.setText(productInput.getProHeight());
        holder.descP.setText(productInput.getProDesc());
        holder.costP.setText(productInput.getProCost());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nameP, categoryP, widthP, heightP, descP, costP;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            nameP =itemView.findViewById(R.id.pro_name);
            categoryP =itemView.findViewById(R.id.pro_category);
            widthP =itemView.findViewById(R.id.pro_width);
            heightP =itemView.findViewById(R.id.pro_height);
            descP =itemView.findViewById(R.id.pro_desc);
            costP =itemView.findViewById(R.id.pro_cost);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ModelProduct productInput = productList.get(getAdapterPosition());
            Intent intent = new Intent(mCtx1, UpdateProductActivity.class);
            intent.putExtra("Product", productInput);
            mCtx1.startActivity(intent);
        }
    }

    public void filterList(ArrayList<ModelProduct> filteredList) {
        productList = filteredList;
        notifyDataSetChanged();
    }

}
