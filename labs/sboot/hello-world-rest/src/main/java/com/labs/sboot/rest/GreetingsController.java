package com.labs.sboot.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingsController {


    @RequestMapping(path = "/greetings", method = RequestMethod.GET, produces = {"application/json"})
    public Greetings greetings1(){
        Greetings greetings = new Greetings();
        greetings.setMessage("Hello Spring Boot REST !!!");
        return greetings;
    }

   /* @RequestMapping("/greetings")
    public String greetings(){
        return "Welcome to Spring Boot REST !!!";
    }*/

    @RequestMapping(path = "/greetings", method = RequestMethod.POST, consumes = {"application/json"},produces = {"application/json"})
    public Greetings greetings2(@RequestBody Greetings greetings){

        greetings.setMessage(greetings.getMessage() + "UPDATED !!!");

        return greetings;

    }

    @GetMapping("/greetings/{msg}")
    public Greetings greetings3(@PathVariable String msg){
        Greetings greetings = new Greetings();
        greetings.setMessage(msg);

        return greetings;

    }

    @GetMapping("/greetings/requestparam")
    public Greetings greetings4 (@RequestParam String msg){
        Greetings greetings = new Greetings();
        greetings.setMessage(msg);

        return greetings;
    }

    @PutMapping("/greetings/{id}")
    public ResponseEntity<Greetings> greetings5(@RequestBody Greetings greetings, @PathVariable int id, @RequestParam String msg2){
        greetings.setMessage(greetings.getMessage() +" " + id + " " + msg2);

        return ResponseEntity.accepted().body(greetings);
    }

}

