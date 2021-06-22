package com.ngurah.finalproject.controller.ui.ticket;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ngurah.finalproject.R;
import com.ngurah.finalproject.adapter.TicketListAdapter;
import com.ngurah.finalproject.model.trip.Ticket;
import com.ngurah.finalproject.network.BaseApiService;
import com.ngurah.finalproject.network.RetrofitInstance;
import com.ngurah.finalproject.session.MySession;
import com.ngurah.finalproject.utils.MyUtils;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TicketFragment extends Fragment {

    private View root;
    private BaseApiService baseApiService;
    private MySession session;

    private String sToken, sFirstName, sLastName, sRoleId, sRoleName;

    private RecyclerView rvListTicket;
    private RecyclerView.Adapter mTicketAdapter;
    private RecyclerView.LayoutManager mTicketLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_ticket, container, false);

        session = new MySession(getActivity());
        HashMap<String, String> sUsernya = session.getUserDetails();
        String key = sUsernya.get(MySession.KEY_TOKEN);

        rvListTicket = root.findViewById(R.id.rvListTicket);
        mTicketLayoutManager =new LinearLayoutManager(getActivity());
        rvListTicket.setLayoutManager(mTicketLayoutManager);
//        Log.d("tests",key);
        baseApiService = RetrofitInstance.getRetrofitInstance(""+key).create(BaseApiService.class);
        loadDataTicket();

        return root;
    }

    private void loadDataTicket() {
        Call<List<Ticket>> listCall = baseApiService.getTicketList();
        listCall.enqueue(new Callback<List<Ticket>>() {
            @Override
            public void onResponse(Call<List<Ticket>> call, Response<List<Ticket>> response) {
                List<Ticket> ticketList = response.body();
                Log.d("tests", String.valueOf(response.code()));
                mTicketAdapter = new TicketListAdapter(ticketList,getContext());
                rvListTicket.setAdapter(mTicketAdapter);
            }

            @Override
            public void onFailure(Call<List<Ticket>> call, Throwable t) {

            }
        });
    }
}