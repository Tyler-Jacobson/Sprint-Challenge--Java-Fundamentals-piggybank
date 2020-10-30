package com.tylerj.piggybank.controllers;

import com.tylerj.piggybank.models.Coin;
import com.tylerj.piggybank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinController {
    @Autowired
    CoinRepository coinpos;

    // http://localhost:2019/total
    @GetMapping(value = "/total", produces = {"application/json"})
    public ResponseEntity<?> findTotal() {
        List<Coin> myList = new ArrayList<>();
        coinpos.findAll().iterator().forEachRemaining(myList::add);

        double total = 0;
        for (Coin c: myList) {
            System.out.println(c.getQuantity() + " " + c.getName());
            total += (c.getQuantity() * c.getValue());
        }
        System.out.println("The piggy bank holds " + total);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
