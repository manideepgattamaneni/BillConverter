package com.girish.billconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ConverterController {

    @Autowired CoinConverterService coinConverterService;

    @GetMapping("/convert/{billAmount}")
    public ResponseEntity<Integer> convertToCoins(@PathVariable int billAmount){
        return ResponseEntity.ok(coinConverterService.convertToMinimumNumberOfCoins(billAmount));
    }

}
