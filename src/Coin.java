import java.math.BigDecimal;

public enum Coin {
    PENNY("0.01"),
    TWO_PENNY("0.02"),
    FIVE("0.05"),
    TEN("0.1"),
    TWENTY("0.2"),
    FIFTY("0.5"),
    POUND("1.0"),
    TWO_POUND("2.0");

    private final BigDecimal coinValue;

    Coin(String coin) {
        this.coinValue = new BigDecimal(coin);
    }

    public BigDecimal getCoinValue() {
        return coinValue;
    }


public static Coin getCoinName(BigDecimal coinBigDec) throws Exception {
    if (coinBigDec.equals(new BigDecimal("0.01"))) {
        return PENNY;
    } else if (coinBigDec.equals(new BigDecimal("0.02"))) {
        return TWO_PENNY;
    } else if (coinBigDec.equals(new BigDecimal("0.05"))) {
        return FIVE;
    } else if (coinBigDec.equals(new BigDecimal("0.1"))) {
        return TEN;
    } else if (coinBigDec.equals(new BigDecimal("0.2"))) {
        return TWENTY;
    } else if (coinBigDec.equals(new BigDecimal("0.5"))) {
        return FIFTY;
    } else if (coinBigDec.equals(new BigDecimal("1.0"))) {
        return POUND;
    } else if (coinBigDec.equals(new BigDecimal("2.0"))) {
        return TWO_POUND;
    } else {
        throw new Exception("Coin not found.");
    }

}}