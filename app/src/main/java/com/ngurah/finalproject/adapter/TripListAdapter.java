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
import com.ngurah.finalproject.model.trip.Trip;

public class TripListAdapter extends RecyclerView.Adapter<TripListAdapter.MyViewHolder>{

    List<Trip> tripList;
    Context context;

    public TripListAdapter(List<Trip> tripList, Context context) {
        this.tripList = tripList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public TripListAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_trip, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TripListAdapter.MyViewHolder holder, int position) {
        String fare = "Rp. "+ String.valueOf(tripList.get(position).getFare());
//        String times = "- "+ tripList.get(position).getJourneyTime() + " menit -";
        String times = tripList.get(position).getJourneyTime();
        holder.tvCodeBus.setText(tripList.get(position).getBus().getCode());
        holder.tvSourceStop.setText(tripList.get(position).getSourceStop().getName());
        holder.tvDestinationStop.setText(tripList.get(position).getDestStop().getName());
        holder.tvDuration.setText(times);
        holder.tvFare.setText(fare);
//        holder.tvAgency.setText(tripList.get(position).getAgency().getName());
        holder.tvAgency.setText("[" + tripList.get(position).getAgency().getCode() + "] " + tripList.get(position).getAgency().getName());
    }

    @Override
    public int getItemCount() {
        return tripList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tvCodeBus,tvSourceStop,tvDestinationStop,tvDuration,tvAgency,tvFare;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodeBus = itemView.findViewById(R.id.tvCodeBus);
            tvSourceStop = itemView.findViewById(R.id.tvSourceStop);
            tvDestinationStop = itemView.findViewById(R.id.tvDestinationStop);
            tvAgency = itemView.findViewById(R.id.tvAgency);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            tvFare = itemView.findViewById(R.id.tvFare);
        }
    }
}
