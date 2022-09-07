package com.homecredit.bankingapp.dao;

import com.homecredit.bankingapp.model.Account;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoJdbcImp implements AccountDao {


    @Autowired
    Connection conn;
    Statement stmt = null;
    ResultSet rs = null;

    public AccountDaoJdbcImp() {
//        datasource = new MysqlDataSource();
//        datasource.setServerName("localhost");
//        datasource.setDatabaseName("jdbctraining");
//        datasource.setUser("training");
//        datasource.setPassword("training");
//
//        try {
//            conn = datasource.getConnection();
//            System.out.println("Connection created successfully. " + conn);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }


    @Override
    public boolean create(Account account) {
        boolean status = false;
        try {
            stmt = conn.createStatement();

            String query =  "INSERT INTO accounts(id, name, type, balance, branch) values(" + account.getAccountId()  + ",\"" + account.getName() + "\",\"" + account.getType() + "\"," + account.getBalance() + ",\"" + account.getBranch() + "\")";


            status = stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public boolean update(Account account) {

        boolean status = false;
        try {
            stmt = conn.createStatement();

            String query = "UPDATE accounts SET name = \"" + account.getName() + "\", type = \"" + account.getType()
                    + "\",balance = " + account.getBalance() + ",branch = \"" + account.getBranch()
                + "\" WHERE id = " + account.getAccountId();
            System.out.println(query);
            status = stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public boolean delete(int id) {

        boolean status = false;
        try {
            stmt = conn.createStatement();

            String query = "DELETE FROM accounts WHERE id = " + id;

            status = stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Account get(int id) {
        Account account = null;
        String query = "SELECT * FROM accounts WHERE id = " + id;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int AccountId = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                double balance = rs.getDouble("balance");
                String branch = rs.getString("branch");
                account = new Account(AccountId, name, type, balance, branch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public List<Account> getAll() {
        List <Account> accs = new ArrayList<>();
        String query = "SELECT * FROM accounts";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int AccountId = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                double balance = rs.getDouble("balance");
                String branch = rs.getString("branch");
                accs.add(new Account(AccountId, name, type, balance, branch));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accs;
    }
}
