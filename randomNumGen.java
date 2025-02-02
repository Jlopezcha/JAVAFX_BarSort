package com.example.newfirstfxproject;

import java.util.*;

public class randomNumGen {
    final int BARS_LIMIT = 10;
    ArrayList<Integer> randomNumArray = new ArrayList<>(BARS_LIMIT);

    //Object used to generate random numbers
    Random randomNumber = new Random();
    public void initialize(){
        //Populate the randomNumArray
        for(int i = 0; i < BARS_LIMIT; i++){
            int randomNum = randomNumber.nextInt(99) + 1;
            randomNumArray.add(randomNum);
        }
    }



}
