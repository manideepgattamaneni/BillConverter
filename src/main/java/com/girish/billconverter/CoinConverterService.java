package com.girish.billconverter;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CoinConverterService {

    private static final int INITIAL_COIN_COUNT = 100;
    private final Map<Integer, Integer> coinInventory = new HashMap<>();

    public CoinConverterService(){
        for (CoinDenomination denomination: CoinDenomination.values()) {
            coinInventory.put(denomination.getValue(), INITIAL_COIN_COUNT);
        }
    }

    public int convertToMinimumNumberOfCoins(int billAmount) throws CoinConverterException{

        Map<Integer, Integer> coinCounts = new HashMap<>();
        int remainingAmountInCents = billAmount * 100;
        for (int denomination :  getCoinDenominationsSortedByHighest()) {
            if (remainingAmountInCents <= 0) {
                break;
            }
            if (coinInventory.get(denomination) <= 0) {
                continue;
            }
            int numCoins = Math.min(coinInventory.get(denomination), (remainingAmountInCents / denomination));
            remainingAmountInCents -= numCoins * denomination;
            coinCounts.put(denomination, numCoins);
        }
        if (remainingAmountInCents > 0) {
            throw new CoinConverterException("Not enough coins to convert the amount.");
        }else{
            for(Map.Entry<Integer,Integer> coin: coinCounts.entrySet()) {
                coinInventory.put(coin.getKey(),coinInventory.get(coin.getKey()) - coin.getValue());
            }
            return coinCounts.values().stream().mapToInt(Integer::intValue).sum();
        }
    }

    private List<Integer> getCoinDenominationsSortedByHighest(){
        return Arrays.stream(CoinDenomination.values()).sorted(Comparator.comparingInt(CoinDenomination::getValue).reversed()).map(CoinDenomination::getValue).collect(Collectors.toList());
    }

}
