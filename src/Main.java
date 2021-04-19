import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        // Create a Scanner object
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Inital State");
        // Read user input
        String initialChange = scanner.nextLine();
        //Initialise an instance of Vending class with the initial known state with positive balance
        Vending vending = new Vending(initialChange);
        //Create string builder to add the options to prompt the user
        StringJoiner optionsPrompt = new StringJoiner(System.lineSeparator());
        optionsPrompt.add("To deposit coins enter d")
                .add("To vend a product enter v")
                .add("To view your balance press b")
                .add("To quit or cancel transaction press q");
        // Output user input
        System.out.println("Total vending state: " + initialChange);
        System.out.println(optionsPrompt);
        String nextLine = scanner.nextLine();

        //Loop until the user enters 'q' for quit
        while (!nextLine.toLowerCase(Locale.ROOT).equals("q")) {
            switch (nextLine.toLowerCase(Locale.ROOT)) {
                case "d":
                    //Print out the enum options
                    System.out.println(java.util.Arrays.asList(Coin.values()));
                    String coin = scanner.nextLine();
                    //Display user's balance
                    System.out.print("Total balance: ");
                    try {
                        //If the input is valid, deposit the coins
                        System.out.printf("%.2f%n", vending.depositCoins(Coin.valueOf(coin.toUpperCase(Locale.ROOT))));  // Output user input
                    } catch (Exception e) {
                        //Otherwise prompt the user to enter a valid coin
                        System.out.println("Invalid coin");
                    }
                    break;
                case "v":
                    System.out.println("Enter product price");
                    String productPrice = scanner.nextLine();
                    if (vending.vendProduct(new BigDecimal(productPrice))) {
                        ChangeTuple<List<Coin>, BigDecimal> changeTuple = vending.getChange();
                        System.out.printf("Total change: %.2f%n", changeTuple.getSecond());
                        System.out.println("Coins returned " + changeTuple.getFirst());
                    } else {
                        System.out.println("Balance insufficient");
                    }
                    break;
                case "b":
                    System.out.printf("Balance: %.2f%n", vending.getBalance());
                    break;
                default:
                    System.out.println("Invalid input");
            }
            System.out.println(optionsPrompt);
            nextLine = scanner.nextLine();

        }

    }
}