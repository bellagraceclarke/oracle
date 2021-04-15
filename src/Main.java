import java.util.*;

public class Main {
    public static void main(String[] args) {

        Vending vending = new Vending(5.0f);
        vending.depositCoins(Coin.FIFTY);
        //display balance
        vending.vendProduct(0.3f);
    }
}