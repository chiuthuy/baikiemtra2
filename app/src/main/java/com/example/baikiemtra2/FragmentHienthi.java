package com.example.baikiemtra2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentHienthi extends Fragment {
    View view;
    HienthiAdapter hienthiAdapter;
    ArrayList<MonanModel> arrayList;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_hienthi,container,false);
        recyclerView = view.findViewById(R.id.recyclerview);
        arrayList = new ArrayList<>();
        int[] a={R.drawable.abc};
        MonanModel monan=new MonanModel(a[0],"ten mon an","10000");
        arrayList.add(monan);
        arrayList.add(monan);
        arrayList.add(monan);
        hienthiAdapter = new HienthiAdapter(getActivity(),arrayList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(hienthiAdapter);

        return view;
    }
}
