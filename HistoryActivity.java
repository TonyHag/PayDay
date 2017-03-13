package com.example.tony.payday;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ListView;

import com.example.tony.payday.logic.HistoryAdapter;
import com.example.tony.payday.model.History;

import java.util.ArrayList;
import java.util.Arrays;

public class HistoryActivity extends AppCompatActivity {
    ListView hListView;
    HistoryAdapter hAdapter;
    ArrayList<History> list;
    String stringlist;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        list = getListFromPref();
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        hListView = (ListView) findViewById(R.id.historyListView);


        hAdapter = new HistoryAdapter(this, list);
        hListView.setAdapter(hAdapter);

        hAdapter.notifyDataSetChanged();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_other, menu);
        return true;
    }
    public ArrayList<History> getListFromPref(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        stringlist = preferences.getString("list", "");
        ArrayList<History> hlist = new ArrayList<History>();
        if (!stringlist.equals("")) {
            ArrayList<String> list1 = new ArrayList<String>(Arrays.asList(stringlist.split(",")));


            for (String item : list1) {
                String a; // name
                String b; // date
                String c;    // amount
                String[] parts = item.split("-");
                a = parts[0];
                b = parts[1];
                c = parts[2];
                History h = new History(a, b, c);
                hlist.add(h);
            }

        }

        return hlist;
    }
}
