package com.example.tony.payday;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

public class PaydayActivity extends AppCompatActivity {
    public String date;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payday);
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_other, menu);
        return true;
    }

    public void onClickPickDate(View view){
        // Lagre dato i Shared pref
        //SharedPreferences sharedPref = getSharedPreferences("payday", Context.MODE_PRIVATE);
        //date = sharedPref.getString("payday", "");

        // Samme Dato hver måned?

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
