package com.girish.billconverter;

public enum CoinDenomination {
    TWENTY_FIVE(25),
    TEN(10),
    FIVE(5),
    ONE(1);
    public final int value;
    CoinDenomination(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
