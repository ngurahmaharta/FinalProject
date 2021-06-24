package com.ngurah.finalproject.controller.ui.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.ngurah.finalproject.R;
import com.ngurah.finalproject.controller.ComingSoonActivity;
import com.ngurah.finalproject.controller.ui.auth.LoginActivity;
import com.ngurah.finalproject.network.BaseApiService;
import com.ngurah.finalproject.network.RetrofitInstance;
import com.ngurah.finalproject.session.MySession;

import java.util.HashMap;


public class ProfileFragment extends Fragment {

    private View root;

    private MySession session;
    private BaseApiService baseApiService;
//    private MyUtils customUtils;

    private Button bLogout;
    private TextView tvFullName, tvEmail, tvMobileNumber, tvUbahDataDiri, tvUbahKataSandi, tvPusatBantuan, tvPengaturan;
    private Intent i;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_profile, container, false);

        session = new MySession(getActivity());

        getUserProfile();

        listener();

        return root;
    }

    private  void  logout(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Logout");
        builder.setMessage("Apakah anda yakin ingin Logout?");
        builder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        session.logoutUser();
                        dialog.dismiss();
                        Intent i = new Intent(getActivity(), LoginActivity.class);
                        startActivity(i);
                    }
                });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void getUserProfile() {

        tvFullName = root.findViewById(R.id.tvFullName);
        tvEmail = root.findViewById(R.id.tvEmail);
        tvMobileNumber = root.findViewById(R.id.tvMobileNumber);

        HashMap<String, String> sUser = session.getUserDetails();
        String sFirstName = sUser.get(MySession.KEY_FIRST_NAME);
        String sLastName = sUser.get(MySession.KEY_LAST_NAME);
        String sFullName = sFirstName+ " "+sLastName;
        String sEmail = sUser.get(MySession.KEY_EMAIL);
        String sMobileNumber = sUser.get(MySession.KEY_MOBILE_NUMBER);
        String sToken = sUser.get(MySession.KEY_TOKEN);

        baseApiService = RetrofitInstance.getRetrofitInstance(""+sToken).create(BaseApiService.class);

        tvFullName.setText(sFullName);
        tvEmail.setText(sEmail);
        tvMobileNumber.setText(sMobileNumber);
    }

    private void listener(){

        bLogout = root.findViewById(R.id.bLogout);
        bLogout.setOnClickListener(v -> logout());

        tvUbahDataDiri = root.findViewById(R.id.tvUbahDataDiri);
        tvUbahDataDiri.setOnClickListener(v -> {
            i = new Intent(getActivity(), ComingSoonActivity.class);
            startActivity(i);
        });

        tvUbahKataSandi = root.findViewById(R.id.tvUbahKataSandi);
        tvUbahKataSandi.setOnClickListener(v -> {
//            i = new Intent(getActivity(), ComingSoonActivity.class);
            i = new Intent(getActivity(), ChangePasswordActivity.class);
            startActivity(i);
        });

        tvPusatBantuan = root.findViewById(R.id.tvPusatBantuan);
        tvPusatBantuan.setOnClickListener(v -> {
            i = new Intent(getActivity(), ComingSoonActivity.class);
            startActivity(i);
        });

        tvPengaturan = root.findViewById(R.id.tvPengaturan);
        tvPengaturan.setOnClickListener(v -> {
            i = new Intent(getActivity(), ComingSoonActivity.class);
            startActivity(i);
        });

    }

}