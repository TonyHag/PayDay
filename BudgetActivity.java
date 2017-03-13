package com.example.tony.payday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BudgetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
    }
    public void onclickSave(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
