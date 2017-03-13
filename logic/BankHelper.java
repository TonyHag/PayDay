package com.example.tony.payday.logic;

import com.example.tony.payday.model.Bank;

/**
 * Created by Tony on 20.02.2017.
 */

public class BankHelper {
    Bank bank;

    public BankHelper(Bank bank){
        this.bank = bank;
    }


    public int payMoney(int cost){
        int balance = bank.getBalance();
        int outcome = bank.getOutcome();
        bank.setOutcome(outcome + cost);
        bank.setBalance(balance - cost);
        return bank.getBalance();
    }

    public int recieveMoney(int income){
        int balance = bank.getBalance();
        int currentIncome = bank.getIncome();
        bank.setIncome(currentIncome + income);
        bank.setBalance(balance + income);
        return bank.getBalance();
    }
}
