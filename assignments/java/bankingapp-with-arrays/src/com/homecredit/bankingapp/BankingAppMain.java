package com.homecredit.bankingapp;


import com.homecredit.bankingapp.exception.AccountException;
import com.homecredit.bankingapp.model.Account;
import com.homecredit.bankingapp.service.AccountService;
import com.homecredit.bankingapp.service.AccountServiceArrImpl;

import java.util.Collection;
import java.util.Scanner;

public class BankingAppMain {

    public static void main(String[] args) {

        AccountService accountService = new AccountServiceArrImpl();

        int input;
        do {
            System.out.println();
            System.out.println("1.CREATE ACCOUNT");
            System.out.println("2.GET ACCOUNT");
            System.out.println("3.UPDATE ACCOUNT");
            System.out.println("4.DELETE ACCOUNT");
            System.out.println("5.ALL ACCOUNTS");
            System.out.println("0.Exit");

            System.out.println();

            Scanner sc = new Scanner(System.in);

            input = sc.nextInt();

            switch (input) {
                case 1: {
                    System.out.println("Enter ID : ");
                    int id = sc.nextInt();
                    System.out.println("Enter Name : ");
                    String name = sc.next();
                    System.out.println("Balance : ");
                    double balance = sc.nextDouble();
                    System.out.println("Account Type : ");
                    String type = sc.next();
                    System.out.println("Branch : ");
                    String branch = sc.next();


                    Account account = new Account(id, name, type, balance,  branch);
                    try {
                        accountService.create(account);
                    }
                    catch (AccountException ae){
                        System.out.println(ae.getMessage());
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
                            System.out.println("Enter The Amount : ");
                            int depositAmount = sc.nextInt();
                            account.deposit(depositAmount);
                            try {
                                accountService.update(account.getAccountId(), account);
                            } catch (AccountException ae){
                                System.out.println(ae.getMessage());
                            }
                            break;
                        case 3:
                            System.out.println("Enter The Amount : ");
                            int withdrawalAmount = sc.nextInt();
                            account.withdraw(withdrawalAmount);
                            try {
                                accountService.update(account.getAccountId(), account);
                            } catch (AccountException ae){
                                System.out.println(ae.getMessage());
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
                    System.out.println("3.Both");

                    int input1 = sc.nextInt();

                    switch (input1) {
                        case 1: {
                            System.out.println("Enter The Name : ");

                            String name = sc.next();
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
                            System.out.println("Enter The Name : ");
                            String name = sc.next();
                            account.setName(name);
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
                   Account [] accounts = accountService.getAll();
                    if(accounts == null){
                        System.out.println("No Account is Available !!!");
                    }else{
                        for (Account account : accounts) {
                            System.out.println(account.toString());
                        }
                    }

                    break;

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
