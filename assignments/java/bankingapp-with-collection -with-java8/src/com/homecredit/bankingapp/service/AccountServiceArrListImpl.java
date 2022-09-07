package com.homecredit.bankingapp.service;

import com.homecredit.bankingapp.exception.AccountException;
import com.homecredit.bankingapp.model.Account;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public void printBalanceCondition(double amount) {
        Collection <Account> list = getAll()
                        .stream()
                        .filter(e -> e.getBalance() > amount)
//                        .map(e -> e)
                        .collect(Collectors.toList());
        list.forEach(acc -> System.out.println(acc)); ;

    }


    public void printNameContains(String name) {
        Collection<Integer> list =
                         getAll()
                        .stream()
                        .filter(e -> e.getName().startsWith(name))
                        .map(e -> e.getAccountId())
                        .collect(Collectors.toList());
        System.out.println(list);
    }

    public void printAccountType() {
        Map<String, Long> l = getAll()
                .stream()
                .map(Account::getType)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        l.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    public void printAccountTypeSorted() {
        Map<String, Long> list = getAll()
                .stream()
                .sorted(Comparator.comparing(Account::getType))
                .collect(Collectors.groupingBy(Account::getType, LinkedHashMap::new, Collectors.counting()));

        list.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    public void showAvg() {
        Map<String, Double> m = getAll()
                .stream()
                .sorted(Comparator.comparing(Account::getType))
                .collect(Collectors.groupingBy(Account::getType, LinkedHashMap::new, Collectors.averagingDouble(Account::getBalance)));

        m.forEach((k, v) -> System.out.println(k + " : " + v));
    }


    public void PrintStatistics (){

        System.out.println("1. No of accounts which has balance more than given amount");
        System.out.println("2. Show no of account by account type");
        System.out.println("3. Show no of accounts by account type with sorting");
        System.out.println("4. Show avg balance by account type");
        System.out.println("5. List account ids whose account name contains given name");

        Scanner in = new Scanner(System.in);

        int input = in.nextInt();

        switch (input) {
            case 1: {
                System.out.println("Enter the Amount : ");
                double amount = in.nextDouble();
                printBalanceCondition(amount);
            }
            break;

            case 2: {
               printAccountType();
            }
            break;

            case 3 :{
              printAccountTypeSorted();
            }
            break;

            case 4 :{
                System.out.println("------ Average of Accounts ----- ");
                showAvg();
            }
            break;

            case 5 :{
                System.out.println("Enter the name : ");
                String name = in.nextLine();
                while(name.equals("")){
                    name = in.nextLine();
                }
                printNameContains(name);
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
