package com.example.baikiemtra2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UpdateMonan extends AppCompatActivity {
    EditText txtmonan, txthinhanh, txtgia;
    Button edit,cancel;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_monan);
        Anhxa();
        Intent intent = getIntent();
        MonanModel ma = (MonanModel) intent.getSerializableExtra("edit");
        id = ma.getId();
        txtmonan.setText(ma.getTen());
        txthinhanh.setText(ma.getImg());
        txtgia.setText(ma.getGia());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update("https://baikiemtra2.000webhostapp.com/Sever/sua.php");
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void update(String s) {
        final String tenm  = txtmonan.getText().toString();
        final String anh = txthinhanh.getText().toString();
        final String giatien = txtgia.getText().toString();


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, s, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("0")){
                    Toast.makeText(UpdateMonan.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error1", String.valueOf(error));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String, String>();
                hashMap.put("id",String.valueOf(id));
                hashMap.put("tenmon",tenm);
                hashMap.put("anh",anh);
                hashMap.put("gia",giatien);

                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void Anhxa() {
        txtmonan = findViewById(R.id.suatenmon);
        txthinhanh = findViewById(R.id.suahinhanh);
        txtgia = findViewById(R.id.suagia);
        edit = findViewById(R.id.btnsua);
        cancel = findViewById(R.id.btncancel);
    }
}
