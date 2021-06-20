package com.ngurah.finalproject.controller.ui.bus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ngurah.finalproject.R;
import com.ngurah.finalproject.adapter.BusListAdapter;
import com.ngurah.finalproject.model.bus.Bus;
import com.ngurah.finalproject.network.BaseApiService;
import com.ngurah.finalproject.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusFragment extends Fragment {

    private View root;
    private BaseApiService baseApiService;
    private RecyclerView rvListBus;
    private TextView tInternetHilang;
    private RecyclerView.Adapter mBusAdapter;
    private RecyclerView.LayoutManager mBusLayoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_bus, container, false);

        tInternetHilang = root.findViewById(R.id.internet_hilang);

        rvListBus = root.findViewById(R.id.listBus);
        mBusLayoutManager = new LinearLayoutManager(getContext());
        rvListBus.setLayoutManager(mBusLayoutManager);
        baseApiService = RetrofitInstance.getRetrofitInstance("").create(BaseApiService.class);

        loadDataBus();

        return root;
    }

    private void loadDataBus() {
        Call<List<Bus>> listCall = baseApiService.getAllBus();
        listCall.enqueue(new Callback<List<Bus>>() {
            @Override
            public void onResponse(Call<List<Bus>> call, Response<List<Bus>> response) {
                List<Bus> busList = response.body();
                mBusAdapter = new BusListAdapter(busList,getContext());
                rvListBus.setAdapter(mBusAdapter);
            }

            @Override
            public void onFailure(Call<List<Bus>> call, Throwable t) {
                tInternetHilang.setVisibility(View.VISIBLE);
            }
        });
    }
}