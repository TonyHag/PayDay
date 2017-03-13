package com.example.tony.payday;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class PreMainActivity extends AppCompatActivity {
    public static String bank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_main);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        bank = preferences.getString("BANK", "");

        if (!bank.equals("")) {
            Intent intentmain = new Intent(this, MainActivity.class);
            startActivity(intentmain);
        }else {
            Intent intent = new Intent(this, InitialSetupActivity.class);
            startActivity(intent);
        }
    }
}
