package com.example.tony.payday;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        list = getListFromPref();


        hListView = (ListView) findViewById(R.id.historyListView);


        hAdapter = new HistoryAdapter(this, list);
        hListView.setAdapter(hAdapter);

        hAdapter.notifyDataSetChanged();

    }
    public ArrayList<History> getListFromPref(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        stringlist = preferences.getString("list", "");
        if (!stringlist.equals("")) {
            ArrayList<String> list1 = new ArrayList<String>(Arrays.asList(stringlist.split(",")));
            ArrayList<History> hlist = new ArrayList<History>();

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
            return hlist;
        }

        return null;
    }
}
