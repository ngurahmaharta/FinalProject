package com.ngurah.finalproject.controller.ui.home;

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
import com.ngurah.finalproject.adapter.TripListAdapter;
import com.ngurah.finalproject.controller.LoginActivity;
import com.ngurah.finalproject.model.trip.Trip;
import com.ngurah.finalproject.network.BaseApiService;
import com.ngurah.finalproject.network.RetrofitInstance;
import com.ngurah.finalproject.session.MySession;
import com.ngurah.finalproject.utils.MyUtils;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private View root;
    private BaseApiService baseApiService;
    private MySession session;
    private MyUtils customUtils;

    private String sToken, sFirstName, sLastName, sRoleId, sRoleName;

    private RecyclerView rvListTrip;
    private RecyclerView.Adapter mTripAdapter;
    private RecyclerView.LayoutManager mTripLayoutManager;

    public HomeFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
//        viewProfileData();

        session = new MySession(getActivity());
        HashMap<String, String> sUsernya = session.getUserDetails();
        String key = sUsernya.get(MySession.KEY_TOKEN);

        rvListTrip = root.findViewById(R.id.rvListTrip);
        mTripLayoutManager =new LinearLayoutManager(getActivity());
        rvListTrip.setLayoutManager(mTripLayoutManager);
//        Log.d("tests",key);
        baseApiService = RetrofitInstance.getRetrofitInstance(""+key).create(BaseApiService.class);
        loadDataTrip();


        return root;
    }

    private void loadDataTrip() {
        HashMap<String, String> sUsernya = session.getUserDetails();
        Call<List<Trip>> listCall = baseApiService.getTripList();
        listCall.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                List<Trip> tripList = response.body();
//                Log.d("tests", String.valueOf(response.code()));
                mTripAdapter = new TripListAdapter(tripList,getContext());
                rvListTrip.setAdapter(mTripAdapter);
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {

            }
        });
    }

//    private void viewProfileData() {
//        //Utils
//        customUtils = new MyUtils(root.getContext());
//
//        mySession = new MySession(root.getContext());
//        mySession.checkLogin();
//
//        if(mySession.isLoggedIn()) {
//            HashMap<String, String> sUser = mySession.getUserDetails();
//            sToken = sUser.get(MySession.KEY_TOKEN);
//            sFirstName = sUser.get(MySession.KEY_FIRST_NAME);
//            sLastName = sUser.get(MySession.KEY_LAST_NAME);
//            sRoleId = sUser.get(MySession.KEY_ROLE_ID);
//            sRoleName = sUser.get(MySession.KEY_ROLE_NAME);
//        }
//
//        baseApiService = RetrofitInstance.getRetrofitInstance(sToken).create(BaseApiService.class);
//    }
}