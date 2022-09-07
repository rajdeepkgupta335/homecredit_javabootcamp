package com.homecredit.bankingapp.service;

import com.homecredit.bankingapp.exception.AccountException;
import com.homecredit.bankingapp.model.Account;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AccountServiceHashsetImpl implements AccountService {

    Set <Account> accounts = new HashSet<>();

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

        for(Account account1 :accounts) {
            if (account1.getAccountId() == account.getAccountId()) {
                accounts.remove(account1);
                accounts.add(account);
               return true;
            }

        }

        throw new AccountException("Account not found !!! ");

    }

    @Override
    public boolean delete(int accountId) throws AccountException {

        for(Account account1 :accounts) {
            if (account1.getAccountId() == accountId) {
                accounts.remove(account1);
                return true;
            }
        }
        throw new AccountException("Account not found !!! ");
    }

    @Override
    public Account get(int accountId) {
        for(Account account1 :accounts) {
            if (account1.getAccountId() == accountId) {
               return  account1;
            }
        }
        return null;
    }

    @Override
    public Collection<Account> getAll() {
        return accounts;
    }
}
