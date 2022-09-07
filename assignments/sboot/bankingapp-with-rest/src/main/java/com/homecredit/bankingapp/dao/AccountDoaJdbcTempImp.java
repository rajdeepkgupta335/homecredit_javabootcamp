package com.homecredit.bankingapp.dao;

import com.homecredit.bankingapp.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDoaJdbcTempImp implements AccountDao {

    @Autowired
    JdbcTemplate jdbcTemp;

    @Override
    public boolean create(Account account) {

            String query =  "INSERT INTO accounts(id, name, type, balance, branch) values(" + account.getAccountId()  + ",\"" + account.getName() + "\",\"" + account.getType() + "\"," + account.getBalance() + ",\"" + account.getBranch() + "\")";
        System.out.println(query);
            jdbcTemp.execute(query);

        return true;
    }

    @Override
    public boolean update(Account account) {
        String query = "UPDATE accounts SET name = \"" + account.getName() + "\", type = \"" + account.getType()
                + "\",balance = " + account.getBalance() + ",branch = \"" + account.getBranch()
                + "\" WHERE id = " + account.getAccountId();
      jdbcTemp.execute(query);

        return true;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM accounts WHERE id = " + id;

        int delete = jdbcTemp.update(query);
        System.out.println(delete);
        if(delete>0){
            return  true;
        }
        return false;
    }

    @Override
    public Account get(int id) {
        String query = "SELECT * FROM accounts WHERE id = " + id;
        List<Account> list = jdbcTemp.query(query, new AccountRowMappper());
        if(list.size()>0){
            return list.get(0);
        }

      return null;
    }

    @Override
    public List<Account> getAll() {
        String query = "SELECT * FROM accounts";
        List<Account> accounts = jdbcTemp.query(query, new AccountRowMappper());
        return accounts;
    }

    public class AccountExtractor implements ResultSetExtractor<Account>{

        @Override
        public Account extractData(ResultSet rs) throws SQLException, DataAccessException {
            int AccountId = rs.getInt("id");
            String name = rs.getString("name");
            String type = rs.getString("type");
            double balance = rs.getDouble("balance");
            String branch = rs.getString("branch");
            Account account = new Account(AccountId, name, type, balance, branch);

            return  account;
        }
    }

    public class AccountRowMappper implements RowMapper<Account>{

        @Override
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            int AccountId = rs.getInt("id");
            String name = rs.getString("name");
            String type = rs.getString("type");
            double balance = rs.getDouble("balance");
            String branch = rs.getString("branch");
            Account account = new Account(AccountId, name, type, balance, branch);

            return  account;
        }
    }

}
