package com.ngurah.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ngurah.finalproject.R;
import com.ngurah.finalproject.model.trip.Ticket;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TicketListAdapter extends RecyclerView.Adapter<TicketListAdapter.MyViewHolder>{

    List<Ticket> ticketList;
    Context context;

    public TicketListAdapter(List<Ticket> ticketList, Context context) {
        this.ticketList = ticketList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public TicketListAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_ticket, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TicketListAdapter.MyViewHolder holder, int position) {
        holder.tvSeatNumber.setText("#"+String.valueOf(ticketList.get(position).getSeatNumber()));
        holder.tvSourceStop.setText(ticketList.get(position).getTripSchedule().getTripDetail().getSourceStop().getName());
        holder.tvDestinationStop.setText(ticketList.get(position).getTripSchedule().getTripDetail().getDestStop().getName());
        holder.tvDuration.setText(ticketList.get(position).getTripSchedule().getTripDetail().getJourneyTime());
        holder.tvDate.setText(" ["+ticketList.get(position).getJourneyDate()+"]");
        holder.tvCodeBus.setText("Bus "+ticketList.get(position).getTripSchedule().getTripDetail().getBus().getCode());
        holder.tvAgency.setText(" ["+ticketList.get(position).getTripSchedule().getTripDetail().getBus().getAgency().getName()+"]");
        String fare = "Rp. "+ String.valueOf(ticketList.get(position).getTripSchedule().getTripDetail().getFare());
        holder.tvFare.setText(fare);
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tvSeatNumber, tvSourceStop,tvDestinationStop, tvDuration, tvDate, tvCodeBus, tvAgency, tvFare;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSeatNumber = itemView.findViewById(R.id.tvSeatNumber);
            tvSourceStop = itemView.findViewById(R.id.tvSourceStop);
            tvDestinationStop = itemView.findViewById(R.id.tvDestinationStop);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvCodeBus = itemView.findViewById(R.id.tvCodeBus);
            tvAgency = itemView.findViewById(R.id.tvAgency);
            tvFare = itemView.findViewById(R.id.tvFare);
        }
    }
}
