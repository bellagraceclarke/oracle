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

    //Increment the customer balance as well as the total machine balance with the coin value deposited
    //Accepts a coin enum value
    //Returns the balance of the user as a big decimal
    public BigDecimal depositCoins(Coin coin) {
        balance = balance.add(coin.getCoinValue());
        totalChange = totalChange.add(coin.getCoinValue());
        return balance;
    }

    /**
     * Checks to see if the vend request is valid
     *
     * @param price of product the user entered
     * @return message to be displayed
     */
    public VendMessage vendProduct(BigDecimal price) {
        VendMessage vendMessage;

        //check product price is positive
        //check balance is greater than or equal to the product price
        //e.g. if product can be afforded
        //return appropriate enum message

        int scale = price.scale();
        if (price.signum() < 0 || scale > 2) {
            vendMessage = VendMessage.INVALID_VALUE;
        } else if (balance.compareTo(price) < 0) {
            vendMessage = VendMessage.BALANCE_INSUFFICIENT;
        } else {
            balance = balance.subtract(price.abs());
            vendMessage = VendMessage.VEND_ACCEPTED;
        }
        return vendMessage;
    }

    public ChangeTuple<List<Coin>, BigDecimal> getChange() {
        //Change for customer after purchase
        BigDecimal change = balance;
        //return a tuple containing a list of coins, as well as the total sum of coins
        ChangeTuple<List<Coin>, BigDecimal> changeTuple = new ChangeTuple<>(calculateCoins(change), change);
        balance = balance.subtract(balance);
        return changeTuple;
    }

    /**
     * Calculates the exact coins to be returned to the user
     *
     * @param change the change to calculate necessary coins for
     * @return a list of type Coin
     */
    public List<Coin> calculateCoins(BigDecimal change) {
        List<Coin> coinChangeList = new ArrayList<>();
        //create a list of the ordered coin values
        List<BigDecimal> orderedCoinValues = Stream.of(Coin.values())
                .map(Coin::getCoinValue)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());

        BigDecimal quotient;
        BigDecimal remainder;

        //iterate through coins
        //if > 0 then find quotient then remainder
        //add coin to list
        //decrement change value and repeat

        for (BigDecimal coin : orderedCoinValues) {
            quotient = change.divide(coin, BigDecimal.ROUND_FLOOR);
            remainder = change.remainder(coin);
            if (quotient.signum() == 1) {
                try {
                    for (int count = 0; count < quotient.intValue(); count++) {
                        coinChangeList.add(Coin.getCoinName(coin));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                change = remainder;
            }
            //when the change reaches 0, the coins needed have been added to the list
            if (change.signum() == 0) {
                break;
            }
        }
        return coinChangeList;
    }

    //user balance getter
    public BigDecimal getBalance() {
        return balance;
    }

    //machine balance getter
    public BigDecimal getMachineTotal() {
        return totalChange;
    }
}
