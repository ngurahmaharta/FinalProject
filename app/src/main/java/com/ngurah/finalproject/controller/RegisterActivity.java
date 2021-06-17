package com.ngurah.finalproject.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ngurah.finalproject.R;
import com.ngurah.finalproject.models.User;
import com.ngurah.finalproject.models.UserRegister;
import com.ngurah.finalproject.rest.ApiAuthInterface;
import com.ngurah.finalproject.rest.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextView tvLogin;
    private EditText edFirstName;
    private EditText edLastName;
    private EditText edEmail;
//    private EditText edUsername;
    private EditText edPassword;
    private EditText edMobileNumber;
    private ImageView bRegister;
    ApiAuthInterface authInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvLogin = findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            finish();
        });


        edFirstName = findViewById(R.id.edFirstName);
        edLastName = findViewById(R.id.edLastName);
        edEmail = findViewById(R.id.edEmail);
//        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        edMobileNumber = findViewById(R.id.edMobileNumber);
        bRegister = findViewById(R.id.bRegister);
        authInterface = ApiClient.getClient().create(ApiAuthInterface.class);

        bRegister.setOnClickListener(v ->{
            ArrayList<String> Role = new ArrayList<>();
            Role.add("user");
            UserRegister userRegister = new UserRegister(edEmail.getText().toString(),edFirstName.getText().toString(),edLastName.getText().toString(),
                    edMobileNumber.getText().toString(),edPassword.getText().toString(),Role);
            Call<User> userCall = authInterface.register(userRegister);
            userCall.enqueue(new Callback<User>() {

                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User responRegister = response.body();
                    Log.d("RegisterLog", String.valueOf(response.code()));
                    if(responRegister != null){
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(RegisterActivity.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, " Koneksi anda Buruk", Toast.LENGTH_SHORT).show();
                }
            });
        });


    }
}