package com.example.baikiemtra2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HienthiAdapter extends RecyclerView.Adapter<HienthiAdapter.ViewHolder>{
    Context context;
    private ArrayList<MonanModel> modelArrayList;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txttenmon.setText(modelArrayList.get(position).getTen());
        holder.txtgia.setText(modelArrayList.get(position).getGia());
        holder.img.setImageResource(modelArrayList.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txttenmon,txtgia;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtgia=itemView.findViewById(R.id.txtgia);
            txttenmon=itemView.findViewById(R.id.txttenmon);
            img=itemView.findViewById(R.id.img);

        }
    }
}
