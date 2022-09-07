package com.homecredit.bankingapp.service;

import com.homecredit.bankingapp.exception.AccountException;
import com.homecredit.bankingapp.model.Account;

import java.util.Collection;

public interface AccountService {
    public boolean create(Account account) throws AccountException;
    public boolean update(int accountId, Account account) throws AccountException;
    public boolean delete(int accountId) throws AccountException;
    public Account get(int accountId) ;
    public Collection<Account> getAll();

}
