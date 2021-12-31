package com.sir.dbms_mini.SQLProduct;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sir.dbms_mini.R;

import java.util.ArrayList;

public class SQLAdapter extends RecyclerView.Adapter<SQLAdapter.myviewholder>{

    ArrayList<ModelSQL> dataholder;

    public SQLAdapter(ArrayList<ModelSQL> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_single_row_export,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder1, int position)
    {
        holder1.dName.setText(dataholder.get(position).geteName());
        holder1.dCategory.setText(dataholder.get(position).geteCategory());
        holder1.dWidth.setText(dataholder.get(position).geteWidth());
        holder1.dHeight.setText(dataholder.get(position).geteHeight());
        holder1.dDesc.setText(dataholder.get(position).geteDesc());
        holder1.dCost.setText(dataholder.get(position).geteCost());
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView dName, dCategory, dWidth, dHeight, dDesc, dCost;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            dName = (TextView)itemView.findViewById(R.id.pro_name_export);
            dCategory = (TextView)itemView.findViewById(R.id.pro_category_export);
            dWidth = (TextView)itemView.findViewById(R.id.pro_width_export);
            dHeight = (TextView)itemView.findViewById(R.id.pro_height_export);
            dDesc = (TextView)itemView.findViewById(R.id.pro_desc_export);
            dCost = (TextView)itemView.findViewById(R.id.pro_cost_export);
        }
    }
}
