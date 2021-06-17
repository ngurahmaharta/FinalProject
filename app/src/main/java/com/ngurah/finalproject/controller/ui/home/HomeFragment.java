package com.ngurah.finalproject.controller.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ngurah.finalproject.R;
import com.ngurah.finalproject.controller.LoginActivity;
import com.ngurah.finalproject.network.BaseApiService;
import com.ngurah.finalproject.network.RetrofitInstance;
import com.ngurah.finalproject.session.MySession;
import com.ngurah.finalproject.utils.MyUtils;

import java.util.HashMap;

public class HomeFragment extends Fragment {

    private View root;
    private BaseApiService baseApiService;
    private MySession mySession;
    private MyUtils customUtils;

    private String sToken, sFirstName, sLastName, sRoleId, sRoleName;

    public HomeFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
        viewData();
        return root;
    }

    private void viewData() {

        //Utils
        customUtils = new MyUtils(root.getContext());

        mySession = new MySession(root.getContext());
        mySession.checkLogin();

        if(mySession.isLoggedIn()) {
            HashMap<String, String> sUser = mySession.getUserDetails();
            sToken = sUser.get(MySession.KEY_TOKEN);
            sFirstName = sUser.get(MySession.KEY_FIRST_NAME);
            sLastName = sUser.get(MySession.KEY_LAST_NAME);
            sRoleId = sUser.get(MySession.KEY_ROLE_ID);
            sRoleName = sUser.get(MySession.KEY_ROLE_NAME);
        }

        baseApiService = RetrofitInstance.getRetrofitInstance(sToken).create(BaseApiService.class);

    }
}