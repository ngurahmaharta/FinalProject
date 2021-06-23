package com.ngurah.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import com.ngurah.finalproject.R;
import com.ngurah.finalproject.model.trip.Bus;

public class BusListAdapter extends RecyclerView.Adapter<BusListAdapter.MyViewHolder>{

    List<Bus> busList;
    private Context context;

    public BusListAdapter(List<Bus> busList, Context a) {
        this.busList = busList;
        this.context = a;
    }

    @Override
    public BusListAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_bus, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BusListAdapter.MyViewHolder holder, int position) {
//        holder.id.setText(String.valueOf(busList.get(position).getId()));
        holder.code.setText(busList.get(position).getCode());
        holder.capacity.setText(String.valueOf(busList.get(position).getCapacity()) + " kursi");
//        holder.agency.setText(busList.get(position).getAgency().getName());
        holder.agency.setText("[" + busList.get(position).getAgency().getCode() + "] " + busList.get(position).getAgency().getName());
    }

    @Override
    public int getItemCount() {
        return busList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView id,code,capacity,agency;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            id = itemView.findViewById(R.id.tvIdBus);
            code = itemView.findViewById(R.id.tvCodeBus);
            capacity = itemView.findViewById(R.id.tvCapacity);
            agency = itemView.findViewById(R.id.tvAgency);
        }
    }
}
