package com.ngurah.finalproject.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ngurah.finalproject.R;
import com.ngurah.finalproject.model.user.User;
import com.ngurah.finalproject.model.user.UserRegister;
import com.ngurah.finalproject.network.BaseApiService;
import com.ngurah.finalproject.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextView tvLogin;
    private EditText edFirstName, edLastName, edEmail, edPassword, edConfirmPassword, edMobileNumber;
//    private EditText edUsername;
    private String FirstNameHolder, LastNameHolder, EmailHolder, MobileNumberHolder, PasswordHolder, ConfirmPasswordHolder;
    private ImageView bRegister;
    private Boolean CheckEditText;
    private Boolean CheckPasswordConfirm;

    private BaseApiService baseApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edFirstName = findViewById(R.id.edFirstName);
        edLastName = findViewById(R.id.edLastName);
        edEmail = findViewById(R.id.edEmail);
//        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        edConfirmPassword = findViewById(R.id.edConfirmPassword);
        edMobileNumber = findViewById(R.id.edMobileNumber);
        bRegister = findViewById(R.id.bRegister);

        tvLogin = findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            finish();
        });

//        baseApiService = ApiClient.getClient("").create(BaseApiService.class);
        baseApiService = RetrofitInstance.getRetrofitInstance("").create(BaseApiService.class);

        bRegister.setOnClickListener(v -> cekRegister());
    }

    private void cekRegister(){
        CheckPasswordConfirm();
        CheckEditTextIsEmptyOrNot();

        if(!CheckEditText){
            Toast.makeText(RegisterActivity.this,"Data Masih Kosong !",Toast.LENGTH_LONG).show();
        } else{
            if(!CheckPasswordConfirm) {
                Toast.makeText(RegisterActivity.this,"Password yang diisi tidak sama",Toast.LENGTH_LONG).show();
            } else{
                SyncCek();
            }
        }
    }

    private void CheckPasswordConfirm(){
        if(edPassword.getText().toString().equals(edConfirmPassword.getText().toString())){
            CheckPasswordConfirm = true;
        } else {
            CheckPasswordConfirm = false;
        }
    }

    private void CheckEditTextIsEmptyOrNot(){
        FirstNameHolder = edFirstName.getText().toString();
        LastNameHolder = edLastName.getText().toString();
        EmailHolder = edEmail.getText().toString();
        MobileNumberHolder = edMobileNumber.getText().toString();
        PasswordHolder = edPassword.getText().toString();
        ConfirmPasswordHolder = edConfirmPassword.getText().toString();

        if(TextUtils.isEmpty(FirstNameHolder)||TextUtils.isEmpty(LastNameHolder)||TextUtils.isEmpty(EmailHolder)||TextUtils.isEmpty(MobileNumberHolder)||TextUtils.isEmpty(PasswordHolder)||TextUtils.isEmpty(ConfirmPasswordHolder)){
            CheckEditText = false;
        } else {
            CheckEditText = true;
        }
    }

    private void SyncCek(){
        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage("Mohon menunggu...");
        progressDialog.show();
        new Thread(){
            @Override
            public void run(){
                super.run();
                try {
                    Thread.sleep(2000);
                    if(progressDialog.isShowing()) progressDialog.dismiss();
                    RegisterTrue();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void RegisterTrue(){
        ArrayList<String> Role = new ArrayList<>();
        Role.add("user");
        UserRegister userRegister = new UserRegister(edEmail.getText().toString(),edFirstName.getText().toString(),edLastName.getText().toString(),
                edMobileNumber.getText().toString(),edPassword.getText().toString(),Role);
        Call<User> userCall = baseApiService.register(userRegister);
        userCall.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User responRegister = response.body();
                Log.d("Register", String.valueOf(response.code()));
                if(responRegister != null){
                    Sukses();
                }else{
                    Toast.makeText(RegisterActivity.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Registrasi gagal, periksa koneksi anda!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void Sukses(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        builder.setTitle("Info");
        builder.setMessage("Registrasi berhasil!");
        builder.setPositiveButton("Ok", (dialog, which) -> {
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            finish();
        });

        builder.setCancelable(false);
        builder.show();
    }

}