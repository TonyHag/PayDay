package com.example.tony.payday.model;

import java.util.Date;

/**
 * Created by Tony on 13.03.2017.
 */

public class History {
    String date;
    String name;
    String amount;

    public History(String name, String date, String amount) {
        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
