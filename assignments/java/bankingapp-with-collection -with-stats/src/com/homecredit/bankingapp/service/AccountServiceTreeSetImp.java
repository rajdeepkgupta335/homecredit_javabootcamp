package com.homecredit.bankingapp.service;

import com.homecredit.bankingapp.exception.AccountException;
import com.homecredit.bankingapp.model.Account;

import java.util.Collection;
import java.util.TreeSet;

public class AccountServiceTreeSetImp implements AccountService {

    private TreeSet <Account> accounts = new TreeSet<>();

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


        Account account1 = get(accountId);
        if(account1 != null) {
            accounts.remove(account1);
            accounts.add(account);
            return  true;
        }
    throw new AccountException("Account not found !!!");

    }

    @Override
    public boolean delete(int accountId) throws AccountException {
        for(Account acc :accounts) {
            if (acc.getAccountId() == accountId) {
                accounts.remove(acc);
                return true;
            }
        }
        throw new AccountException("Account not found !!!");
    }

    @Override
    public Account get(int accountId) {
        for(Account acc :accounts) {
            if (acc.getAccountId() == accountId) {
                return acc;
            }
        }
        return null;
    }

    @Override
    public Collection<Account> getAll() {
        return accounts;
    }
}
