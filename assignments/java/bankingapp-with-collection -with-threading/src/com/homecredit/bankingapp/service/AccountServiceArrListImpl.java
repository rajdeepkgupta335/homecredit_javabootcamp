package com.homecredit.bankingapp.service;

import com.homecredit.bankingapp.exception.AccountException;
import com.homecredit.bankingapp.model.Account;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AccountServiceArrListImpl implements  AccountService{

    List<Account> accounts = new ArrayList<Account>();

    public void defaultAccounts(){
        Account account1 = new Account(1,"sam", "loan", 20000, "Shalimar garden");
        Account account2 = new Account(2,"shivani", "saving", 115600, "preet vihar");
        Account account3 = new Account(3,"neha thakur", "current", 10230, "cyber city");
        Account account4 = new Account(4,"priya panchal", "loan", 154000, "laxmi nagar");
        Account account5 = new Account(5,"ram", "saving", 54655, "pitampura");
        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);
        accounts.add(account4);
        accounts.add(account5);
    }

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

        for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).getAccountId()==accountId){
                accounts.set(i,account);
                return true;
            }
        }

        throw new AccountException("Account not found !!!");
    }

    @Override
    public boolean delete(int accountId) throws AccountException{

        for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).getAccountId()==accountId){
                accounts.remove(i);
                return true;
            }
        }

        throw new AccountException("Account not found !!!");
    }

    @Override
    public Account get(int accountId) {

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountId() == accountId) {
                return accounts.get(i);
            }
        }

        return null;
    }

    @Override
    public Collection<Account> getAll() {

        return accounts;
    }

    public void PrintStatistics (){

        System.out.println("1. No of accounts which has balance more than 1 lac");
        System.out.println("2. Show no of account by account type");
        System.out.println("3. Show no of accounts by account type with sorting");
        System.out.println("4. Show avg balance by account type");
        System.out.println("5. List account ids whose account name contains given name");

        Scanner in = new Scanner(System.in);

        int input = in.nextInt();

        switch (input) {
            case 1: {
                for(int i=0;i<accounts.size();i++){
                    if(accounts.get(i).getBalance()>100000){
                        System.out.println(accounts.get(i).toString());
                    }
                }
            }
            break;

            case 2: {
                System.out.println("SELECT THE TYPE OF ACCOUNTS : ");
                System.out.println("1.CURRENT");
                System.out.println("2.LOAN");
                System.out.println("3.SAVING");
                int input2 = in.nextInt();
                switch (input2){
                    case 1:
                        for (int i = 0; i < accounts.size(); i++) {
                            if (accounts.get(i).getType().equals("current")) {
                                System.out.println(accounts.get(i).toString());
                            }
                        }
                        break;
                    case 2 :  for (int i = 0; i < accounts.size(); i++) {
                        if (accounts.get(i).getType().equals("loan")) {
                            System.out.println(accounts.get(i).toString());
                        }
                        }
                        break;
                    case 3 :   for (int i = 0; i < accounts.size(); i++) {
                        if (accounts.get(i).getType().equals("saving")) {
                            System.out.println(accounts.get(i).toString());
                        }
                    }
                    break;
                    default:
                        System.out.println("Invalid Input !!");
                }

            }
            break;

            case 3 :{
                System.out.println("-----Current Accounts-----");
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getType().equals("current")) {
                        System.out.println(accounts.get(i).toString());
                    }
                }
                System.out.println();
                System.out.println("-----Loan Accounts-----");
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getType().equals("loan")) {
                        System.out.println(accounts.get(i).toString());
                    }
                }
                System.out.println();
                System.out.println("-----Saving Accounts-----");
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getType().equals("saving")) {
                        System.out.println(accounts.get(i).toString());
                    }
                }
            }
            break;

            case 4 :{
                System.out.println("------ Average of Accounts ----- ");
                long average = 0;
                int NOA = 0;
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getType().equals("current")) {
                        average += accounts.get(i).getBalance();
                        NOA++;
                    }
                }
                System.out.println("Average of Current Accounts is " + average/NOA);

                average =0;
                NOA=0;

                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getType().equals("loan")) {
                        average += accounts.get(i).getBalance();
                        NOA++;
                    }
                }
                System.out.println("Average of Loan Accounts is " + average/NOA);

                average =0;
                NOA=0;

                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getType().equals("saving")) {
                        average += accounts.get(i).getBalance();
                        NOA++;
                    }
                }
                System.out.println("Average of saving Accounts is " + average/NOA);

            }
            break;

            case 5 :{
                System.out.println("Enter the name : ");
                String name = in.nextLine();
                while(name.equals("")){
                    name = in.nextLine();
                }
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getName().contains(name)) {
                        System.out.println("ID : " + accounts.get(i).getAccountId());
                    }
                }
            }
            break;

            default:
                System.out.println("Invalid Input");
        }
    }




public void bulkImport() {
        int c = 0;
        try (Scanner in = new Scanner(new FileReader("C:\\Training\\homecredit_javabootcamp\\assignments\\java\\bankingapp-with-collection -with-threading\\src\\com\\homecredit\\bankingapp\\inAccounts.txt"))) {
        System.out.println("Importing file...");
        while (in.hasNext()) {
        String account = in.nextLine();
        System.out.println("Importing Account - " + account);
        Account a = new Account();
        StringTokenizer tokenizer = new StringTokenizer(account, ",");
        a.setAccountId(Integer.parseInt(tokenizer.nextToken()));
        a.setName(tokenizer.nextToken());
        a.setType(tokenizer.nextToken());
        a.setBalance(Double.parseDouble(tokenizer.nextToken()));
        a.setBranch(tokenizer.nextToken());

        try {
                create(a);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        c++;
        }
        System.out.println(c + "Accounts Imported successfully !!" );
        } catch (Exception e) {
        System.out.println("Error occur while importing data. " + e.getMessage());

        }
        }

        public void bulkExport(){
                int c =0;
                try(FileWriter out = new FileWriter("C:\\Training\\homecredit_javabootcamp\\assignments\\java\\bankingapp-with-collection -with-threading\\src\\com\\homecredit\\bankingapp\\outAccounts.txt")){

                    for(Account account : accounts){
                        out.write(account.getAccountId() + "," + account.getName() + "," + account.getType() + "," + account.getBalance() + "," + account.getBranch() + "\n");
                        c++;
                    }

                    System.out.println(c + "Accounts Exported !!");

                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

}
