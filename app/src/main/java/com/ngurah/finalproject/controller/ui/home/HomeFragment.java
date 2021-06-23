package com.ngurah.finalproject.controller.ui.home;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ngurah.finalproject.R;
import com.ngurah.finalproject.adapter.TripListAdapter;
import com.ngurah.finalproject.adapter.TripScheduleListAdapter;
import com.ngurah.finalproject.model.trip.Stop;
import com.ngurah.finalproject.model.trip.Trip;
import com.ngurah.finalproject.model.trip.TripSchedule;
import com.ngurah.finalproject.network.BaseApiService;
import com.ngurah.finalproject.network.RetrofitInstance;
import com.ngurah.finalproject.session.MySession;
import com.ngurah.finalproject.utils.MyUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private View root;
    private BaseApiService baseApiService;
    private MySession session;
    private MyUtils customUtils;

//    private String sToken, sFirstName, sLastName, sRoleId, sRoleName;

    private RecyclerView rvListTrip;
    private RecyclerView rvListTripSchedule;
    private RecyclerView.Adapter mTripAdapter;
    private RecyclerView.Adapter tripScheduleAdapter;
    private RecyclerView.LayoutManager tripScheduleLayoutManager;

    private RecyclerView.LayoutManager mTripLayoutManager;
    private RecyclerView.LayoutManager mTripScheduleLayoutManager;

    private Spinner spSourceStop, spDestStop;
    private HashMap<String, String> listStops;
    private List<String> spinnerListName;
    private List<Integer> spinnerListId;

    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;
    private EditText edCalendar, edStartDate, edEndDate;
    private Button bSearch;
    private String from, to;
    private Integer destStopId, sourceStopId;

    public HomeFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
//        viewProfileData();

        session = new MySession(getActivity());
        HashMap<String, String> sUsernya = session.getUserDetails();
        String key = sUsernya.get(MySession.KEY_TOKEN);
        baseApiService = RetrofitInstance.getRetrofitInstance(""+key).create(BaseApiService.class);

        rvListTripSchedule = root.findViewById(R.id.rvListTrip);
        tripScheduleLayoutManager = new LinearLayoutManager(getActivity());
        rvListTripSchedule.setLayoutManager(tripScheduleLayoutManager);
        getTripSchedule();


        spSourceStop = root.findViewById(R.id.spSourceStop);
        spDestStop = root.findViewById(R.id.spDestStop);
        loadStops();

        myCalendar = Calendar.getInstance();
        edStartDate = root.findViewById(R.id.edStartDate);
        edEndDate = root.findViewById(R.id.edEndDate);
        date = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(edCalendar);
        };
        edStartDate.setOnClickListener(v -> {
            showCalendar(date);
            edCalendar = edStartDate;
        });
        edEndDate.setOnClickListener(v -> {
            showCalendar(date);
            edCalendar = edEndDate;
        });

        bSearch = root.findViewById(R.id.bSearch);
        bSearch.setOnClickListener(v -> {
            from = edStartDate.getText().toString();
            to = edEndDate.getText().toString();
            sourceStopId = spinnerListId.get(spSourceStop.getSelectedItemPosition());
            destStopId = spinnerListId.get(spDestStop.getSelectedItemPosition());

            if (destStopId.equals(null) || destStopId.equals(null) || TextUtils.isEmpty(from) || TextUtils.isEmpty(to) ) {
                Toast.makeText(getActivity(), "Lengkapi form pencarian!", Toast.LENGTH_LONG).show();
            } else {
//                Log.wtf("from : ",from);
//                Log.wtf("to : ",to);
//                Log.wtf("sourceStopId : ",sourceStopId.toString());
//                Log.wtf("destStopId : ",destStopId.toString());

                cariTripSchedule();
            }
        });



        return root;
    }

//    private void loadDataTrip() {
//        Call<List<Trip>> listTripCall = baseApiService.getTripList();
//        listTripCall.enqueue(new Callback<List<Trip>>() {
//            @Override
//            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
//                List<Trip> tripList = response.body();
////                Log.d("tests", String.valueOf(response.code()));
//                mTripAdapter = new TripListAdapter(tripList,getContext());
//                rvListTrip.setAdapter(mTripAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<Trip>> call, Throwable t) {
//                Toast.makeText(getActivity(), "Failed Parsing Data", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


    private void showCalendar(DatePickerDialog.OnDateSetListener date) {
        DatePickerDialog dialog = new DatePickerDialog(
                getActivity(),
                date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMinDate(new Date().getTime());
        dialog.show();
    }

    private void updateLabel(EditText editText) {
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(sdf.format(myCalendar.getTime()));
    }

    private void loadStops(){
        spinnerListName = new ArrayList<>();
        spinnerListId = new ArrayList<>();
        listStops = new HashMap<>();
        Call<List<Stop>> listStopCall = baseApiService.getStopList();

        listStopCall.enqueue(new Callback<List<Stop>>() {
            @Override
            public void onResponse(Call<List<Stop>> call, Response<List<Stop>> response) {
                if (response.body() != null) {
                    List<Stop> getResponse = response.body();

                    for (int i = 0; i < getResponse.size(); i++) {
                        spinnerListId.add(getResponse.get(i).getId());
                        spinnerListName.add(getResponse.get(i).getName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), R.layout.spinner_list_item, spinnerListName);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spSourceStop.setAdapter(adapter);
                    spDestStop.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Stop>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed Parsing Data", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getTripSchedule() {
        Call<List<TripSchedule>> listCall = baseApiService.getTripSchedules();
        listCall.enqueue(new Callback<List<TripSchedule>>() {
            @Override
            public void onResponse(Call<List<TripSchedule>> call, Response<List<TripSchedule>> response) {
                List<TripSchedule> tripScheduleList = response.body();
                tripScheduleAdapter = new TripScheduleListAdapter(tripScheduleList, getContext());
                rvListTripSchedule.setAdapter(tripScheduleAdapter);
            }

            @Override
            public void onFailure(Call<List<TripSchedule>> call, Throwable t) {
            }
        });
    }

    private void cariTripSchedule() {
        Call<List<TripSchedule>> listCall = baseApiService.getTripSchedulesParam(destStopId, from, sourceStopId, to);
        listCall.enqueue(new Callback<List<TripSchedule>>() {
            @Override
            public void onResponse(Call<List<TripSchedule>> call, Response<List<TripSchedule>> response) {
                List<TripSchedule> tripScheduleList = response.body();
                if(!tripScheduleList.isEmpty()) {
                    tripScheduleAdapter = new TripScheduleListAdapter(tripScheduleList, getContext());
                    rvListTripSchedule.setAdapter(tripScheduleAdapter);
                    rvListTripSchedule.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getActivity(), "Jadwal perjalanan tidak ditemukan!", Toast.LENGTH_SHORT).show();
                    rvListTripSchedule.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<TripSchedule>> call, Throwable t) {
            }
        });
    }






//    private void getTripSchedule() {
//        Call<List<TripSchedule>> listTripScheduleCall = baseApiService.getTripSchedules();
//        listTripScheduleCall.enqueue(new Callback<List<TripSchedule>>() {
//            @Override
//            public void onResponse(Call<List<TripSchedule>> call, Response<List<TripSchedule>> response) {
//                List<TripSchedule> tripScheduleList = response.body();
//                tripScheduleAdapter = new TripScheduleListAdapter(tripScheduleList, getContext());
//                rvListTripSchedule.setAdapter(tripScheduleAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<TripSchedule>> call, Throwable t) {
//            }
//        });
//    }
//
//
//    private void cariTripSchedule() {
//        Call<List<TripSchedule>> listCall = baseApiService.getTripSchedulesParam(destStopId, from, sourceStopId, to);
//        listCall.enqueue(new Callback<List<TripSchedule>>() {
//            @Override
//            public void onResponse(Call<List<TripSchedule>> call, Response<List<TripSchedule>> response) {
//                List<TripSchedule> tripScheduleList = response.body();
//                if(!tripScheduleList.isEmpty()) {
//                    tripScheduleAdapter = new TripScheduleListAdapter(tripScheduleList, getContext());
//                    rvListTripSchedule.setAdapter(tripScheduleAdapter);
//                    rvListTripSchedule.setVisibility(View.VISIBLE);
//                } else {
//                    Toast.makeText(getActivity(), "Jadwal perjalanan tidak ditemukan!", Toast.LENGTH_SHORT).show();
//                    rvListTripSchedule.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<TripSchedule>> call, Throwable t) {
//            }
//        });
//    }






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