package com.homecredit.bankingapp;

import com.homecredit.bankingapp.exception.AccountException;
import com.homecredit.bankingapp.model.Account;
import com.homecredit.bankingapp.service.AccountService;
import com.homecredit.bankingapp.service.AccountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collection;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SpringBootApplication
public class BankningappWithSpringBootApplication  implements CommandLineRunner {

    @Autowired
    AccountService accountService;

    public static void main(String[] args) {
        SpringApplication.run(BankningappWithSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        ExecutorService executor = Executors.newCachedThreadPool();

        int input;
        do {
            System.out.println();
            System.out.println("1.CREATE ACCOUNT");
            System.out.println("2.GET ACCOUNT");
            System.out.println("3.UPDATE ACCOUNT");
            System.out.println("4.DELETE ACCOUNT");
            System.out.println("5.ALL ACCOUNTS");
//            System.out.println("6.PRINT STATISTICS");
//            System.out.println("6.IMPORT");
//            System.out.println("7.EXPORT");
            System.out.println("0.EXIT");

            System.out.println();

            System.out.print("CHOOSE OPTION : ");

            Scanner sc = new Scanner(System.in);

            input = sc.nextInt();

            switch (input) {
                case 1: {
                    try {
                        System.out.println("Enter ID : ");
                        int id = sc.nextInt();
                        System.out.println("Enter Name : ");
                        String name = sc.nextLine();
                        while(name.equals("")){
                            name = sc.nextLine();
                        }
                        System.out.println("Balance : ");
                        double balance = sc.nextDouble();
                        System.out.println("Account Type : ");
                        String type = sc.next().toLowerCase();
                        while(!(type.equals("loan") ||type.equals("saving") || type.equals("current"))){
                            System.out.println("Please Enter Loan, Saving or Current type !!");
                            type = sc.next().toLowerCase();
                            if(type.equals("loan") ||type.equals("saving") || type.equals("current")){
                                break;
                            }
                        }
                        System.out.println("Branch : ");
                        String branch = sc.nextLine();
                        while(branch.equals("")){
                            branch = sc.nextLine();
                        }

                        Account account = new Account(id, name, type, balance,  branch);

                        boolean created = accountService.create(account);
                        if (created){
                            System.out.println("Account Created Successfully !!!");
                        }
                    }
                    catch (AccountException ae){
                        System.out.println(ae.getMessage());
                    }catch (Exception ae){
                        System.out.println("Invalid Input !!");
                    }
                }
                break;

                case 2: {
                    System.out.println("Enter ID : ");
                    int id = sc.nextInt();
                    Account account = accountService.get(id);
                    System.out.println(account.toString());
                    System.out.println();

                    System.out.println("1.Show Balance");
                    System.out.println("2.Deposit");
                    System.out.println("3.Withdrawal");

                    int input2 = sc.nextInt();
                    switch (input2) {
                        case 1:
                            System.out.println("Balance : " + account.getBalance());
                            break;
                        case 2:
                            try {
                                System.out.println("Enter The Amount : ");
                                int depositAmount = sc.nextInt();
                                account.deposit(depositAmount);

                                accountService.update(account.getAccountId(), account);
                            } catch (AccountException ae){
                                System.out.println(ae.getMessage());
                            }
                            catch (Exception e){
                                System.out.println("Invalid Input !!");
                            }
                            break;
                        case 3:
                            try {
                                System.out.println("Enter The Amount : ");
                                int withdrawalAmount = sc.nextInt();
                                account.withdraw(withdrawalAmount);

                                accountService.update(account.getAccountId(), account);
                            } catch (AccountException ae){
                                System.out.println(ae.getMessage());
                            }catch (Exception e){
                                System.out.println("Invalid Input !!");
                            }
                            break;

                        default:
                            System.out.println("Wrong Input !!!");
                    }
                }
                break;
                case 3: {
                    System.out.println("Enter the ID : ");
                    int id = sc.nextInt();
                    Account account = accountService.get(id);
                    System.out.println(account.toString());
                    System.out.println();
                    System.out.println("I want to Update :");
                    System.out.println("1.Name");
                    System.out.println("2.Account Type");
                    System.out.println("3.Branch");
                    System.out.println("4.All");
                    int input1 = sc.nextInt();

                    switch (input1) {
                        case 1: {
                            System.out.println("Enter The Name : ");

                            String name = sc.nextLine();
                            while(name.equals("")){
                                name = sc.nextLine();
                            }
                            account.setName(name);
                            try {
                                accountService.update(account.getAccountId(), account);
                            } catch (AccountException ae){
                                System.out.println(ae.getMessage());
                            }
                        }
                        break;

                        case 2: {
                            System.out.println("Enter The Type : ");
                            String type = sc.next();
                            account.setType(type);
                            try {
                                accountService.update(account.getAccountId(), account);
                            } catch (AccountException ae){
                                System.out.println(ae.getMessage());
                            }
                        }
                        break;

                        case 3: {
                            System.out.println("Enter The Branch : ");

                            String branch = sc.nextLine();
                            while(branch.equals("")){
                                branch = sc.nextLine();
                            }
                            account.setBranch(branch);
                            try {
                                accountService.update(account.getAccountId(), account);
                            } catch (AccountException ae){
                                System.out.println(ae.getMessage());
                            }
                        }
                        break;


                        case 4: {
                            System.out.println("Enter The Name : ");
                            String name = sc.nextLine();
                            while(name.equals("")){
                                name = sc.nextLine();
                            }
                            account.setName(name);
                            System.out.println("Enter The Type : ");
                            String type = sc.next();
                            account.setType(type);

                            System.out.println("Enter The Branch : ");
                            String branch = sc.nextLine();
                            while(branch.equals("")){
                                branch = sc.nextLine();
                            }
                            account.setBranch(branch);
                            try {
                                accountService.update(account.getAccountId(), account);
                            } catch (AccountException ae){
                                System.out.println(ae.getMessage());
                            }
                        }
                        break;
                    }
                }
                break;

                case 4: {
                    System.out.println("Enter Account ID : ");
                    int id = sc.nextInt();
                    try {
                        accountService.delete(id);
                    } catch (AccountException ae){
                        System.out.println(ae.getMessage());
                    }
                }
                break;

                case 5:
                    Collection<Account> accounts = accountService.getAll();
                    if(accounts.isEmpty()){
                        System.out.println("No Account is Available !!!");
                    }else{
                        for (Account account : accounts) {
                            System.out.println(account.toString());
                        }
                    }

                    break;
//               case 6:
//                    ((AccountServiceImp) accountService).PrintStatistics();
//                    break;

//                case 6:
//                    Callable<Boolean> importThread = new Callable<Boolean>() {
//                        @Override
//                        public Boolean call() throws Exception {
//                            System.out.println(Thread.currentThread() + "\nWaiting 2s to start import");
//                            Thread.sleep(2000);
//                            ((AccountServiceImp) accountService).bulkImport();
//                            return true;
//                        }
//                    };
//                    Future<Boolean> importFuture =executor.submit(importThread);
//                    System.out.println(Thread.currentThread().getName() + " Import process triggered");
//                    break;
//                case 7:
//                    Callable<Boolean> exportThread = new Callable<Boolean>() {
//                        @Override
//                        public Boolean call() throws Exception {
//                            System.out.println(Thread.currentThread() + "Waiting 2sec to start import");
//                            Thread.sleep(2000);
//                            ((AccountServiceImp) accountService).bulkExport();
//                            return true;
//                        }
//                    };
//                    Future<Boolean> exportFuture =executor.submit(exportThread);
//                    System.out.println(Thread.currentThread().getName() + " Export process triggered");
//                    break;
                case 0 :
                    input = 0;
                    System.out.println("Exiting...");
                    break;


                default:
                    System.out.println("Wrong Input !!!");
            }
        }while(input!=0);


    }
}
