package com.homecredit.bankingapp.service;

import com.homecredit.bankingapp.exception.AccountException;
import com.homecredit.bankingapp.model.Account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

public class AccountServiceTreeMapImp implements AccountService {

    private TreeMap <Integer, Account > accounts = new TreeMap<>();

    @Override
    public boolean create(Account account) throws AccountException {
        for(int key :accounts.keySet()) {
            if (key == account.getAccountId()) {
                throw new AccountException("Account Already Exists with this ID !!!");
            }
        }
        accounts.put(account.getAccountId(),account);
        return true;
    }

    @Override
    public boolean update(int accountId, Account account) throws AccountException {

        if(accounts.containsKey(accountId)){
            accounts.replace(accountId,account);
            return true;

        }
        throw new AccountException("Account not found !!! ");
    }

    @Override
    public boolean delete(int accountId) throws AccountException {

        if(accounts.containsKey(accountId)){
            accounts.remove(accountId);
            return true;

        }
        throw new AccountException("Account not found !!! ");
    }

    @Override
    public Account get(int accountId) {
        return accounts.get(accountId);
    }

    @Override
    public Collection<Account> getAll() {
        ArrayList<Account> allAccounts = new ArrayList<>();

        for(int key :accounts.keySet()) {
            allAccounts.add(accounts.get(key));
        }
        return allAccounts;
    }
}
