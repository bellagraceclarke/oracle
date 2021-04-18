import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Vending {
    //Balance of the customer
    private Float balance = 0.0f;

    //Total sum of vending machine change
    private Float totalChange = 0.0f;

    public Vending(Float initalTotal) {
        this.totalChange = initalTotal;
    }

    public float depositCoins(Coin coin) {
        balance += coin.getCoinValue();
        totalChange += coin.getCoinValue();
        return balance;
    }

    public float depositAmount(Float amount) {
        totalChange += amount;
        balance += amount;
        return balance;
    }

    public boolean vendProduct(Float price) {
        //main logic
        if (balance >= price) {
            balance -= price;
            return true;
        }
        return false;
    }

    public ChangeTuple<List<Coin>, Float> getChange() {
        //Change for customer after purchase
        Float change = balance;
        ChangeTuple<List<Coin>, Float> changeTuple = new ChangeTuple<>(calculateCoins(change), change);
        balance = 0.0f;
        return changeTuple;
    }

    public List<Coin> calculateCoins(Float change) {
        List<Coin> coinChangeList = new ArrayList<>();
        //find greatest divisor of change (in terms of the Coin enum)
        //find the quotient and remainder
        List<Float> orderedCoinValues = Stream.of(Coin.values())
                .map(Coin::getCoinValue)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
        int changeInPence = Math.round(change*100);
        int quotient;
        int remainder;
        //iterate through coins
        //change/coin
        //if > 0 then find quotient then remainder
        //add to list
        //decrement change value

        for (Float coin : orderedCoinValues) {
            quotient = (int) (changeInPence/(coin*100));
            remainder = (int) (changeInPence%(coin*100));
            if (quotient>0) {
                try {
                    for (int count = 0; count < quotient; count++) {
                        coinChangeList.add(Coin.getCoinName(coin));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //our issue is that the remainder ends up being a stupid decimal
                changeInPence = remainder;
            }
            if (changeInPence == 0) {
                break;
            }
        }

        return coinChangeList;
    }

    public float getBalance() {
        return balance;
    }
}
