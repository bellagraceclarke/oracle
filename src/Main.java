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
        Vending vending = new Vending(initialChange);

        //Create string builder
        StringJoiner optionsPrompt = new StringJoiner(System.lineSeparator());
        optionsPrompt.add("To deposit coins enter d")
                .add("To vend a product enter v")
                .add("To view your balance press b")
                .add("To quit or cancel transaction press q");

        // Output user input
        System.out.println("Total vending state: " + initialChange);
        System.out.println(optionsPrompt);
        String nextLine = scanner.nextLine();

        while (!nextLine.toLowerCase(Locale.ROOT).equals("q")) {
            // Initialise instance of vending with initial state
            switch (nextLine.toLowerCase(Locale.ROOT)) {
                case "d":
                    System.out.println(java.util.Arrays.asList(Coin.values()));
                    String coin = scanner.nextLine();
                    System.out.print("Total balance: ");
                    try {
                        System.out.printf("%.2f%n", vending.depositCoins(Coin.valueOf(coin.toUpperCase(Locale.ROOT))));  // Output user input
                    } catch (Exception e) {
                        System.out.println("Invalid coin");
                    }
                    break;
                case "v":
                    System.out.println("Enter product price");
                    String productPrice = scanner.nextLine();
                    System.out.print("Total change: ");
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