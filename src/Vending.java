import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Vending {
    //Balance of the customer
    private BigDecimal balance = new BigDecimal("0.0");

    //Total sum of vending machine change
    private BigDecimal totalChange;

    public Vending(String initalTotal) {
        totalChange = new BigDecimal(initalTotal);
    }

    public BigDecimal depositCoins(Coin coin) {
        balance=balance.add(coin.getCoinValue());
        totalChange=totalChange.add(coin.getCoinValue());
        return balance;
    }

    public boolean vendProduct(BigDecimal price) {
        //check product price is not negative
        //check balance is greater than or equal to the product price
        //e.g. if product can be afforded
        if (price.signum()>=0 && balance.compareTo(price) >= 0) {
            balance = balance.subtract(price.abs());
            return true;
        }
        return false;
    }

    public ChangeTuple<List<Coin>, BigDecimal> getChange() {
        //Change for customer after purchase
        BigDecimal change = balance;
        ChangeTuple<List<Coin>, BigDecimal> changeTuple = new ChangeTuple<>(calculateCoins(change), change);
        balance = balance.subtract(balance);
        return changeTuple;
    }

    public List<Coin> calculateCoins(BigDecimal change) {
        List<Coin> coinChangeList = new ArrayList<>();
        //find greatest divisor of change (in terms of the Coin enum)
        //find the quotient and remainder
        List<BigDecimal> orderedCoinValues = Stream.of(Coin.values())
                .map(Coin::getCoinValue)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());

        BigDecimal quotient;
        BigDecimal remainder;
        //iterate through coins
        //change/coin
        //if > 0 then find quotient then remainder
        //add to list
        //decrement change value

        for (BigDecimal coin : orderedCoinValues) {
            quotient = change.divide(coin, BigDecimal.ROUND_FLOOR);
            remainder = change.remainder(coin);
            if (quotient.signum()==1) {
                try {
                    for (int count = 0; count < quotient.intValue(); count++) {
                        coinChangeList.add(Coin.getCoinName(coin));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                change = remainder;
            }
            if (change.signum()==0) {
                break;
            }
        }

        return coinChangeList;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
