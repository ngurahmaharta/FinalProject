package com.ngurah.finalproject.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ngurah.finalproject.R;
import com.ngurah.finalproject.controller.ui.ticket.TicketFragment;
import com.ngurah.finalproject.controller.ui.bus.BusFragment;
import com.ngurah.finalproject.controller.ui.home.HomeFragment;
import com.ngurah.finalproject.controller.ui.profile.ProfileFragment;
import com.ngurah.finalproject.session.MySession;

public class MainActivity extends AppCompatActivity {

    MySession mySession;
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySession = new MySession(this);

        navView = findViewById(R.id.bottomNavigationView);
        navView.setOnNavigationItemSelectedListener(item -> {

            Fragment fragment = null;
            int id = item.getItemId();
            if (id == R.id.tab_home){
                fragment = new HomeFragment();
            }else if (id == R.id.tab_profile){
                fragment = new ProfileFragment();
            }else if (id == R.id.tab_bus){
                fragment = new BusFragment();
            }else if (id == R.id.tab_ticket){
                fragment = new TicketFragment();
            }
            return loadFragment(fragment);
        });

        loadFragment(new HomeFragment());
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_frame, fragment)
                    .commit();
            return true;
        }
        return false;
    }




    private void callIntent(Class act){
        Intent i = new Intent(getApplicationContext(), act);
        startActivity(i);
    }

//    @Override
//    public  boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.header_menu, menu);
//        return true;
//    }

//    @Override
//    public  boolean onOptionsItemSelected(MenuItem item){
//        int id = item.getItemId();
//        if(id == R.id.menu_exit){
//            nampilExit();
//            return  true;
//        }
//        switch (item.getItemId())
//        {
//            case android.R.id.home:
//                onBackPressed();
//                return  true;
//        }
//        return  MainActivity.super.onOptionsItemSelected(item);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                onBackPressed();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    private void nampilExit(){
//        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setTitle("Information");
//        builder.setMessage("Apakah anda yakin ingin menutup aplikasi ini?");
//        builder.setPositiveButton("Ya",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        mySession.logoutUser();
//
//                        Intent i = new Intent(MainActivity.this, SplashActivity.class);
//                        finish();
//                    }
//                });
//
//        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.setCancelable(false);
//        builder.show();
//    }


}