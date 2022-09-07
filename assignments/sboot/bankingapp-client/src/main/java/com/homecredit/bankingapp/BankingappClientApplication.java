package com.homecredit.bankingapp;

import com.homecredit.bankingapp.model.Account;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BankingappClientApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BankingappClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Logic to consume BankingApp Service

        RestTemplate restClient = new RestTemplate();

        Account account = new Account();

        account.setAccountId(12);
        account.setBranch("gurgaon");
        account.setBalance(78622);
        account.setName("Krishna");
        account.setType("Loan");

        String baseUrl = "http://localhost:8080/accounts/";

        String getURL1 = "http://localhost:8080/accounts/" + account.getAccountId();

        String creatingAccount = restClient.postForObject(baseUrl, account, String.class);

        System.out.println(creatingAccount);

        Object fetchAccount = restClient.getForObject(getURL1, Object.class);
        System.out.println(fetchAccount);

        Account account2 = new Account();

        account.setAccountId(12);
        account.setBranch("chandigarh");
        account.setBalance(78622);
        account.setName("Krishna");
        account.setType("Saving");

        String updateURL = "http://localhost:8080/accounts/" + account2.getAccountId();

//        restClient.put(updateURL, account2);

        Object updateAccount = restClient.getForObject(updateURL, Object.class);

        System.out.println(updateAccount);



//        Object object = restClient.getForObject(baseUrl, Object.class);
//
//        System.out.println(object);








    }
}
