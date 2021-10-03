package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineTest {


    @Test
    public void test_money_fed(){
        List<Integer> wholeDollars = new ArrayList<>();
        Integer userInput = 5;

        wholeDollars.add(1);
        wholeDollars.add(2);
        wholeDollars.add(5);
        wholeDollars.add(10);

        if(!wholeDollars.contains(userInput)){
            Assert.assertEquals("Invalid dollar input", true, wholeDollars.contains(userInput));
        }
    }

    @Test
    public void test_change_given_back(){
        Double[] coins = new Double[]{0.25, 0.10, 0.05};
        Integer[] coinTotals = new Integer[]{0, 0, 0};

        //13 quarters, 1 dime, 1 nickel
        BigDecimal testChange = BigDecimal.valueOf(3.4).setScale(2, RoundingMode.HALF_UP);
        //Expected coin count
        Integer[] expected = new Integer[]{13, 1, 1};

        for(int i = 0; i < coins.length; i++){
            while(testChange.compareTo(BigDecimal.valueOf(coins[i]).setScale(2, RoundingMode.HALF_UP)) >= 0){
                coinTotals[i]++;
                testChange = testChange.subtract(BigDecimal.valueOf(coins[i]).setScale(2, RoundingMode.HALF_UP));
            }
        }

        //Deprecated due to both values being exactly the same
        Assert.assertEquals("Coin totals do not match.", expected, coinTotals);
    }

}
