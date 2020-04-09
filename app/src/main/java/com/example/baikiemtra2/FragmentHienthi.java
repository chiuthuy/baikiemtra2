package com.example.baikiemtra2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentHienthi extends Fragment {
    View view;
    public static HienthiAdapter hienthiAdapter;
    public static ArrayList<MonanModel> arrayList;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_hienthi,container,false);
        recyclerView = view.findViewById(R.id.recyclerview);
        arrayList = new ArrayList<>();
        int[] a={R.drawable.abc};
        hienthiAdapter = new HienthiAdapter(getContext(),arrayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(hienthiAdapter);
        ReadJSON("https://baikiemtra2.000webhostapp.com/Sever/data.php");
        return view;
    }
    private void ReadJSON(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
                //Log.d("kiemtra", String.valueOf(response));
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        arrayList.add(new MonanModel(
                                jsonObject.getInt("id"),
                                jsonObject.getString("anh"),
                                jsonObject.getString("tenmon"),
                                jsonObject.getString("gia")
                        ));
                        Log.d("kiemtra",arrayList.get(i).getTen());
                        hienthiAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("kiemtra", String.valueOf(error));
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
