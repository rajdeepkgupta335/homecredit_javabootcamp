package com.homecredit.bankingapp.service;

import com.homecredit.bankingapp.exception.AccountException;
import com.homecredit.bankingapp.model.Account;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AccountServiceArrListImpl implements  AccountService{

    List<Account> accounts = new ArrayList<Account>();

    public void defaultAccounts(){
        Account account1 = new Account(1,"sam", "loan", 20000, "Shalimar garden");
        Account account2 = new Account(2,"shivani", "saving", 115600, "preet vihar");
        Account account3 = new Account(3,"neha thakur", "current", 10230, "cyber city");
        Account account4 = new Account(4,"priya panchal", "loan", 154000, "laxmi nagar");
        Account account5 = new Account(5,"ram", "saving", 54655, "pitampura");
        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);
        accounts.add(account4);
        accounts.add(account5);
    }

    @Override
    public boolean create(Account account) throws AccountException {
        for(Account account1 :accounts) {
            if (account1.getAccountId() == account.getAccountId()) {
                throw new AccountException("Account Already Exists with this ID !!!");
            }
        }
        accounts.add(account);
        return true;
    }

    @Override
    public boolean update(int accountId, Account account) throws AccountException {

        for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).getAccountId()==accountId){
                accounts.set(i,account);
                return true;
            }
        }

        throw new AccountException("Account not found !!!");
    }

    @Override
    public boolean delete(int accountId) throws AccountException{

        for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).getAccountId()==accountId){
                accounts.remove(i);
                return true;
            }
        }

        throw new AccountException("Account not found !!!");
    }

    @Override
    public Account get(int accountId) {

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountId() == accountId) {
                return accounts.get(i);
            }
        }

        return null;
    }

    @Override
    public Collection<Account> getAll() {

        return accounts;
    }
}
