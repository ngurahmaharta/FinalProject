package com.ngurah.finalproject.controller.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ngurah.finalproject.R;
import com.ngurah.finalproject.controller.MainActivity;
import com.ngurah.finalproject.controller.ui.ticket.TicketFragment;
import com.ngurah.finalproject.model.trip.Ticket;
import com.ngurah.finalproject.model.trip.TicketReservation;
import com.ngurah.finalproject.model.trip.TripSchedule;
import com.ngurah.finalproject.network.RetrofitInstance; //APIClient
import com.ngurah.finalproject.network.BaseApiService; //APIInterface
import com.ngurah.finalproject.session.MySession;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity {
    private MySession mySession;
    private BaseApiService baseApiService;
    private TripSchedule tripSchedule;
    private TextView txtJourneyDate, txtSourceStop, txtDestinationStop, txtAgency, txtBus;
    private Button btnPesanTiket;
    private Integer passengerId;
    private TicketReservation ticketReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mySession = new MySession(BookingActivity.this);
        HashMap<String, String> userSession = mySession.getUserDetails();
        passengerId = Integer.valueOf(userSession.get(MySession.KEY_ID));
        String key = userSession.get(MySession.KEY_TOKEN);
        baseApiService = RetrofitInstance.getRetrofitInstance(""+key).create(BaseApiService.class);

        txtJourneyDate = findViewById(R.id.txtJourneyDate);
        txtSourceStop = findViewById(R.id.txtSourceStop);
        txtDestinationStop = findViewById(R.id.txtDestinationStop);
        txtAgency = findViewById(R.id.txtAgency);
        txtBus = findViewById(R.id.txtBus);
        btnPesanTiket = findViewById(R.id.btnPesanTiket);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        getTripSchedule(id);

        btnPesanTiket.setOnClickListener(v -> {
            pesanTiket();
        });
    }

    private void getTripSchedule(String id) {
        Call<TripSchedule> tripScheduleCall = baseApiService.getTripSchedule(id);

        tripScheduleCall.enqueue(new Callback<TripSchedule>() {
            @Override
            public void onResponse(Call<TripSchedule> call, Response<TripSchedule> response) {
                tripSchedule = response.body();
                txtJourneyDate.setText(tripSchedule.getTripDate());
                txtSourceStop.setText(tripSchedule.getTripDetail().getSourceStop().getName());
                txtDestinationStop.setText(tripSchedule.getTripDetail().getDestStop().getName());
                txtAgency.setText(tripSchedule.getTripDetail().getAgency().getName());
                txtBus.setText(tripSchedule.getTripDetail().getBus().getCode());
            }

            @Override
            public void onFailure(Call<TripSchedule> call, Throwable t) {

            }
        });
    }

    private void pesanTiket() {
        ticketReservation = new TicketReservation(1, false, tripSchedule.getTripDate(), tripSchedule.getId(), passengerId);
        Call<Ticket> ticketCall = baseApiService.createTicket(ticketReservation);
        ticketCall.enqueue(new Callback<Ticket>() {
            @Override
            public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                Toast.makeText(BookingActivity.this, "Tiket berhasil dipesan", Toast.LENGTH_LONG).show();
                Intent i = new Intent(BookingActivity.this, MainActivity.class);
                i.putExtra("ticketFragment",1);
                startActivity(i);
                finish();
            }

            @Override
            public void onFailure(Call<Ticket> call, Throwable t) {

            }
        });
    }
}
