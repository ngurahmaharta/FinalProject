package com.ngurah.finalproject.controller.ui.home;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ngurah.finalproject.R;
import com.ngurah.finalproject.adapter.TripListAdapter;
import com.ngurah.finalproject.controller.LoginActivity;
import com.ngurah.finalproject.model.trip.Stop;
import com.ngurah.finalproject.model.trip.Trip;
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

    private String sToken, sFirstName, sLastName, sRoleId, sRoleName;

    private RecyclerView rvListTrip;
    private RecyclerView.Adapter mTripAdapter;
    private RecyclerView.LayoutManager mTripLayoutManager;

    private Spinner spSourceStop, spDestStop;
    private HashMap<String, String> listStops;
    private List<String> spinnerListName;
    private List<String> spinnerListId;

    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;
    private EditText edCalendar, edStartDate, edEndDate;

    public HomeFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
//        viewProfileData();

        session = new MySession(getActivity());
        HashMap<String, String> sUsernya = session.getUserDetails();
        String key = sUsernya.get(MySession.KEY_TOKEN);
        //        Log.d("tests",key);
        baseApiService = RetrofitInstance.getRetrofitInstance(""+key).create(BaseApiService.class);

        rvListTrip = root.findViewById(R.id.rvListTrip);
        mTripLayoutManager =new LinearLayoutManager(getActivity());
        rvListTrip.setLayoutManager(mTripLayoutManager);
        loadDataTrip();


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



        return root;
    }

    private void loadDataTrip() {
        Call<List<Trip>> listTripCall = baseApiService.getTripList();
        listTripCall.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                List<Trip> tripList = response.body();
//                Log.d("tests", String.valueOf(response.code()));
                mTripAdapter = new TripListAdapter(tripList,getContext());
                rvListTrip.setAdapter(mTripAdapter);
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed Parsing Data", Toast.LENGTH_SHORT).show();
            }
        });
    }


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
                        spinnerListId.add(String.valueOf(getResponse.get(i).getId()));
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