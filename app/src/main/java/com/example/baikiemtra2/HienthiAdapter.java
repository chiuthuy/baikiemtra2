package com.example.baikiemtra2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        final MonanModel ma=modelArrayList.get(position);
        holder.txttenmon.setText(ma.getTen());
        holder.txtgia.setText(ma.getGia());
        Picasso.with(context).load(ma.getImg()).into(holder.img);
        holder.imgsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,UpdateMonan.class);
                intent.putExtra("edit",ma);
                context.startActivity(intent);
            }
        });
        holder.imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogXoa = new AlertDialog.Builder(context);
                dialogXoa.setMessage("Bạn có muốn xoá hay không");
                dialogXoa.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialogXoa.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RequestQueue requestQueue = Volley.newRequestQueue(context);
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://baikiemtra2.000webhostapp.com/Sever/xoa.php", new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("error")){
                                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                                }else {
                                    Log.d("kiemtra", String.valueOf(response));

                                    Intent intent = new Intent(context,MainActivity.class);
                                    context.startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                    Toast.makeText(context, "Xóa thành công: "+String.valueOf(ma.getTen()), Toast.LENGTH_SHORT).show();
//                                           Toast.makeText(context, "Xoá thành công", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("kiemtra", String.valueOf(error));
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String,String> hashMap = new HashMap<String, String>();
                                hashMap.put("id",String.valueOf(ma.getId()));
                                return hashMap;
                            }
                        };
                        requestQueue.add(stringRequest);
                    }
                });
                dialogXoa.show();
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
