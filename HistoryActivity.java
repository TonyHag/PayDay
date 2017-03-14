package com.example.tony.payday;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

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
    Drawable icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        ImageView bilde = (ImageView) findViewById(R.id.bilde);
        icon = getDrawable(R.drawable.ic_business);
        bilde.setImageDrawable(icon);
        initListView();

    }
    public void initListView(){
        list = getListFromPref();
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
        //splits string into array
        if (!stringlist.equals("")) {
            ArrayList<String> list1 = new ArrayList<String>(Arrays.asList(stringlist.split(",")));

        // splits array into the three values, makes history objects and plots them in an ArrayList<History>
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
    public void deleteHistory(){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("list", "");
        editor.apply();
        Context context = getApplicationContext();
        CharSequence text = "History Deleted";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        initListView();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_delete){
            deleteHistory();
        }


        return super.onOptionsItemSelected(item);
    }
}
