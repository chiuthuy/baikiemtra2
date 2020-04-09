package com.example.baikiemtra2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HienthiAdapter extends RecyclerView.Adapter<HienthiAdapter.ViewHolder>{
    Context context;
     ArrayList<MonanModel> modelArrayList;

    public HienthiAdapter(Context context, ArrayList<MonanModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_hienthi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txttenmon.setText(modelArrayList.get(position).getTen());
        holder.txtgia.setText(modelArrayList.get(position).getGia());
        Picasso.with(context).load(modelArrayList.get(position).getImg()).into(holder.img);
        holder.imgsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,UpdateMonan.class);
                intent.putExtra("edit",modelArrayList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txttenmon,txtgia;
        ImageView img;
        ImageButton imgsua, imgxoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtgia=itemView.findViewById(R.id.txtgia);
            txttenmon=itemView.findViewById(R.id.txttenmon);
            img=itemView.findViewById(R.id.img);
            imgsua=itemView.findViewById(R.id.imgsua);
            imgxoa=itemView.findViewById(R.id.imgxoa);
        }
    }
}
