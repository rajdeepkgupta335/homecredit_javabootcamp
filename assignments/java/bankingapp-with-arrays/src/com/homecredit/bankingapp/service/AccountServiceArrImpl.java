package com.homecredit.bankingapp.service;

import com.homecredit.bankingapp.exception.AccountException;
import com.homecredit.bankingapp.model.Account;

public class AccountServiceArrImpl implements  AccountService{

    private static int counter =0;
    Account [] accounts = new Account[10];

    @Override
    public boolean create(Account account) throws AccountException {

        if(accounts!=null) {
            for (Account account1 : accounts) {
                if(account1!=null) {
                    if (account1.getAccountId() == account.getAccountId()) {
                        throw new AccountException("Account Already Exists with this ID !!!");
                    }
                }
            }
        }

        accounts [counter] = account;
        counter++;

        return true;
    }

    @Override
    public boolean update(int accountId, Account account) throws AccountException  {

        for(int i =0; i<accounts.length;i++){
            if(accounts[i].getAccountId()==accountId){
                accounts[i]= account;
                return true;
            }
        }

        throw new AccountException("Account not found !!!");
    }

    @Override
    public boolean delete(int accountId) throws AccountException {
        for(int i =0; i<counter;i++){
            if(accounts[i].getAccountId()==accountId){
                counter--;
                accounts[i] =accounts[counter];
                accounts[counter] =null;
                return true;
            }
        }
        throw new AccountException("Account not found !!!");
    }

    @Override
    public Account get(int accountId) {
        for(int i =0; i<counter;i++){
            if(accounts[i].getAccountId()==accountId){
                return accounts[i];
            }
        }

        return null;
    }

    @Override
    public Account[] getAll() {
        return accounts;
    }
}
