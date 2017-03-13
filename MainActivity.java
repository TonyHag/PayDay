package com.example.tony.payday;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import static com.example.tony.payday.R.drawable.ic_business;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    String maxbar;
    String balance;
    TextView funds;
    ProgressBar bar;
    String balancetext;
    Drawable icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        //drawing logo on top
        ImageView bilde = (ImageView) findViewById(R.id.bilde);
        icon = getDrawable(R.drawable.ic_business);
        bilde.setImageDrawable(icon);
        //getting balance from sharedpref
        getBankFromPref();
        bar = (ProgressBar) findViewById(R.id.progressBar);
        setNewMax(Integer.parseInt(maxbar));
        Log.v("balance:", balance);
        //setting funds
        funds = (TextView) findViewById(R.id.funds_view);
        balancetext = balance + ",-";
        funds.setText(balancetext);
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(2000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateFunds();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    Context context = getApplicationContext();
                    CharSequence text = "Error updating funds";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        };
        t.start();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
        if (id == R.id.action_balance){
            onClickBalance();
        }
        if(id == R.id.action_budget){
            onClickBudget();
        }
        if(id == R.id.action_history){
            onClickHistory();
        }

        return super.onOptionsItemSelected(item);
    }
    public void onClickHistory(){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
    public void onClickBudget(){
        Context context = getApplicationContext();
        CharSequence text = "Not yet implemented";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void onClickBalance(){
        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.setbalanceprompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                int newBalance = Integer.parseInt(userInput.getText().toString());
                                setNewMax(newBalance);
                                String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
                                setNewBalance(newBalance, newBalance, "Manual balance change", date);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
    public void onClickPayday(View view){
        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.paydayprompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                int currentbalance = Integer.parseInt(balance);
                                int payday = Integer.parseInt(userInput.getText().toString());
                                int newBalance = currentbalance + payday;
                                setNewMax(newBalance);
                                String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
                                setNewBalance(newBalance, payday, "Payday", date);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void onClickPurchase(View view){

            // get prompts.xml view
            LayoutInflater li = LayoutInflater.from(this);
            View promptsView = li.inflate(R.layout.prompt, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(promptsView);

            final EditText userInput = (EditText) promptsView
                    .findViewById(R.id.editTextDialogUserInput);

            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    int currentbalance = Integer.parseInt(balance);
                                    int purchase = Integer.parseInt(userInput.getText().toString());
                                    int newBalance = currentbalance - purchase;
                                    String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
                                    setNewBalance(newBalance, purchase, "Purchase", date);
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });
            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
    }
    public void getBankFromPref(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        maxbar = preferences.getString("MAXBAR", "");
        balance = preferences.getString("BANK", "");

    }
    public void setNewBalance(int balance, int payday, String type, String date){
        String balanceString = Integer.toString(balance);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        if(payday != 0) {
            String stringlist = sharedPref.getString("list", "");
            editor.putString("list", type + "-" + date + "-" + payday + "," + stringlist);
        }
        editor.putString("BANK", balanceString);
        editor.apply();

    }
    public void updateFunds(){
        getBankFromPref();
        balancetext = balance + ",-";
        funds.setText(balancetext);
        bar.setProgress(Integer.parseInt(balance));
    }
    public void setNewMax(int max){

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("MAXBAR", Integer.toString(max));
        editor.apply();
        bar.setMax(max);
    }

}
