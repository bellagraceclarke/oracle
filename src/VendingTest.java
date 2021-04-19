import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendingTest {

    private Vending vending;

    @BeforeEach
    public void setUp() {
        vending = new Vending("5.0");
    }

    @Test
    @DisplayName("Simple coin deposit")
    public void testDepositedCoinsReturnsCorrectBalance() {
        assertEquals(new BigDecimal("0.5"), vending.depositCoins(Coin.valueOf("FIFTY")),
                "Regular coin deposit should work and return correct balance");
    }

    @Test
    @DisplayName("Ensure correct coins")
    public void testCorrectCoinsForChange() {
        List<Coin> expectedList = Arrays.asList(Coin.valueOf("FIFTY"), Coin.valueOf("TWENTY"), Coin.valueOf("FIVE"), Coin.valueOf("PENNY"));
        assertEquals(expectedList, vending.calculateCoins(new BigDecimal("0.76")), "Correct coins for amount of change");
    }
}