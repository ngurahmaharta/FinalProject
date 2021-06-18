package com.ngurah.finalproject.controller.ui.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.ngurah.finalproject.R;
import com.ngurah.finalproject.controller.LoginActivity;
import com.ngurah.finalproject.network.BaseApiService;
import com.ngurah.finalproject.session.MySession;
import com.ngurah.finalproject.utils.MyUtils;


public class ProfileFragment extends Fragment {

    private View root;

    private MySession session;
    private BaseApiService baseApiService;
    private MyUtils customUtils;

    private Button bLogout;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_profile, container, false);

//        viewData();
        session = new MySession(getActivity());

        bLogout = root.findViewById(R.id.bLogout);
        bLogout.setOnClickListener(v -> logout());

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

}