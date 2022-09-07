package com.homecredit.bankingapp.dao;

import com.homecredit.bankingapp.model.Account;

import java.util.List;

public interface AccountDao {

    public boolean create(Account account);

    public boolean update(Account account);

    public boolean delete(int id);

    public Account get(int id);

    public List<Account> getAll();
}
