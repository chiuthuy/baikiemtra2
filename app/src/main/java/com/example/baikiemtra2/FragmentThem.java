package com.example.baikiemtra2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class FragmentThem extends Fragment {
    View view;
    EditText edten,edgia,edhinhanh;
    Button btnadd,btnhuy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_them,container,false);
        edten = view.findViewById(R.id.edten);
        edgia = view.findViewById(R.id.edgia);
        edhinhanh = view.findViewById(R.id.edhinhanh);
        btnadd = view.findViewById(R.id.btnthem);
        btnhuy = view.findViewById(R.id.btnhuy);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ADD("https://baikiemtra2.000webhostapp.com/Sever/them.php");
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edten.setText("");
                edhinhanh.setText("");
                edgia.setText("");
            }
        });
        return view;
    }
    private void ADD(String s) {
        final String ten = edten.getText().toString();
        final String gia = edgia.getText().toString();
        final String hinh = edhinhanh.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, s, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("error")){
                    Toast.makeText(getActivity(), "Lỗi", Toast.LENGTH_SHORT).show();
                }else {
                    Log.d("kiemtra", String.valueOf(response));
                    Intent intent = new Intent(getContext(),MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getContext(), "Đã thêm thành công", Toast.LENGTH_SHORT).show();
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
                hashMap.put("tenmon",ten);
                hashMap.put("anh",hinh);
                hashMap.put("gia",gia);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}
