package com.ngurah.finalproject.controller.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ngurah.finalproject.R;
import com.ngurah.finalproject.model.trip.Ticket;
import com.ngurah.finalproject.model.user.ChangePasswordRequest;
import com.ngurah.finalproject.model.user.User;
import com.ngurah.finalproject.network.BaseApiService;
import com.ngurah.finalproject.network.RetrofitInstance;
import com.ngurah.finalproject.session.MySession;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    private MySession session;
    private BaseApiService baseApiService;

    private EditText etNewPassword, etNewPasswordConfirmation;
    private Button bChangePassword;
//    UserInterface userInterface;
//    MainSession mainSession;
    private HashMap<String, String> user;
    String token, userId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        loadComponent();
        bChangePassword.setOnClickListener(v -> {
            final ProgressDialog progressDialog = new ProgressDialog(ChangePasswordActivity.this, R.style.dialogWaiting);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progressDialog.show();

            new Thread(){
                @Override
                public void run(){
                    super.run();
                    try{
                        Thread.sleep(1000);
                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                        try{
                            runOnUiThread(() -> {
                                btnChangeClicked();
                            });
                        } catch(Exception e) {
                            e.printStackTrace();
                            Log.wtf("Error : ", e.getMessage());
                        }
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        });


    }

    private void loadComponent(){
        etNewPassword = findViewById(R.id.etNewPassword);
        etNewPasswordConfirmation = findViewById(R.id.etNewPasswordConfirmation);
        bChangePassword = findViewById(R.id.bChangePassword);
        session = new MySession(ChangePasswordActivity.this);
        HashMap<String, String> sUsernya = session.getUserDetails();
        token = sUsernya.get("token");
        String key = sUsernya.get(MySession.KEY_TOKEN);
        userId = sUsernya.get(MySession.KEY_ID);
        baseApiService = RetrofitInstance.getRetrofitInstance(""+key).create(BaseApiService.class);
    }

    private void btnChangeClicked(){
        if (etNewPassword.getText().toString().equals("") || etNewPasswordConfirmation.getText().toString().equals("")){
            etNewPassword.setError("Harap isi kata sandi baru");
            etNewPassword.findFocus();
        } else{
            if (etNewPassword.getText().toString().equals(etNewPasswordConfirmation.getText().toString())){
                ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(etNewPassword.getText().toString());

                Call<User> userCall = baseApiService.changePassword(changePasswordRequest);

                userCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.wtf("response:", response.toString());
                        if (response.body() != null){
                            Toast.makeText(ChangePasswordActivity.this, "Password berhasil diubah.", Toast.LENGTH_SHORT).show();
                            finish();
                        } else{
                            Toast.makeText(ChangePasswordActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(ChangePasswordActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
    }
}