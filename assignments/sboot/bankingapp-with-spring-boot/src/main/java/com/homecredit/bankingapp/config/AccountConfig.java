package com.homecredit.bankingapp.config;

import com.homecredit.bankingapp.dao.AccountDao;
import com.homecredit.bankingapp.dao.AccountDaoJdbcImp;
import com.homecredit.bankingapp.service.AccountService;
import com.homecredit.bankingapp.service.AccountServiceImp;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class AccountConfig {

    @Bean("accountService")
    public AccountService accountService (){
        return new AccountServiceImp();
    }

    @Bean("accountDao")
    public AccountDao accountDao (){
        return new AccountDaoJdbcImp();
    }

    @Bean("datasource")
    public MysqlDataSource dataSource() {
        MysqlDataSource	datasource = new MysqlDataSource();
        datasource.setServerName("localhost");
        datasource.setDatabaseName("jdbctraining");
        datasource.setUser("training");
        datasource.setPassword("training");
        return datasource;
    }

    @Bean("connection")
    public Connection connection() {
        Connection conn = null;
        try {
            conn = dataSource().getConnection();
            System.out.println("Connection created successfully. " + conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
