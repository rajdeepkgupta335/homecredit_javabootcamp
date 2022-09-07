package com.homecredit.bankingapp.controller;

import com.homecredit.bankingapp.exception.AccountException;
import com.homecredit.bankingapp.model.Account;
import com.homecredit.bankingapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    //GET Account
    // GET http://localhost:8080/accounts
    @GetMapping
    public ResponseEntity<Collection<Account>> getAccounts(){
        return ResponseEntity.ok().body(accountService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccount(@PathVariable int id) throws AccountException{

        try{
            accountService.get(id);
        }catch(AccountException ae){
            return ResponseEntity.badRequest().body(ae.getMessage());
        }

        return ResponseEntity.ok().body(accountService.get(id));
    }

    @PostMapping
    public  ResponseEntity<String> createAccount(@RequestBody @Valid Account account) throws AccountException {

        try{
            accountService.create(account);

        }catch(AccountException ae){
           return ResponseEntity.badRequest().body(ae.getMessage());
        }

        return ResponseEntity.ok().body("Account Created Successfully");
    }

    @PutMapping("/{id}")
    public  ResponseEntity<String> updateAccount (@PathVariable int id, @RequestBody @Valid Account account){

        try{
            accountService.update(id,account);

        }catch(AccountException ae){
            return ResponseEntity.badRequest().body(ae.getMessage());
        }

        return ResponseEntity.ok().body("Account Updated Successfully");
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteAccount(@PathVariable int id){

        try{
            accountService.delete(id);

        }catch(AccountException ae){
            return ResponseEntity.badRequest().body(ae.getMessage());
        }

        return ResponseEntity.ok().body("Account Deleted Successfully");
    }


}
