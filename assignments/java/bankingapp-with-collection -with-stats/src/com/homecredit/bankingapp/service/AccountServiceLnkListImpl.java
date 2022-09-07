package com.homecredit.bankingapp.service;

import com.homecredit.bankingapp.exception.AccountException;
import com.homecredit.bankingapp.model.Account;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class AccountServiceLnkListImpl implements AccountService {

    List<Account> accounts = new LinkedList<Account>();

    @Override
    public boolean create(Account account) throws AccountException {
        for(Account account1 :accounts) {
            if (account.getAccountId() == account.getAccountId()) {
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
    public boolean delete(int accountId) throws AccountException {
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

        for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).getAccountId()==accountId){
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
