package com.example.tony.payday.logic;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tony.payday.R;
import com.example.tony.payday.model.History;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony on 13.03.2017.
 */

public class HistoryAdapter extends ArrayAdapter<History> {
    private Context context;

    public HistoryAdapter(Context context, ArrayList<History> historyList) {
        super(context, 0, (List) historyList);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        History HistoryItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.history_item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.itemName);
        TextView amount = (TextView) convertView.findViewById(R.id.amount);
        TextView date = (TextView) convertView.findViewById(R.id.date);

        name.setText(HistoryItem.getName());
        amount.setText(HistoryItem.getAmount());
        date.setText(HistoryItem.getDate());


        return convertView;
    }

}
