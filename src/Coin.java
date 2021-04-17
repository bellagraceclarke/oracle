public enum Coin {
    PENNY(0.01f),
    TWO_PENNY(0.02f),
    FIVE(0.05f),
    TEN(0.1f),
    TWENTY(0.2f),
    FIFTY(0.5f),
    POUND(1.0f),
    TWO_POUND(2.0f);

    private final float coinValue;

    Coin(Float coin) {
        this.coinValue = coin;
    }

    public float getCoinValue() {
        return coinValue;
    }


public static Coin getCoinName(Float coinFloat) throws Exception {
    if (coinFloat == 0.01f) {
        return PENNY;
    } else if (coinFloat == 0.02f) {
        return TWO_PENNY;
    } else if (coinFloat == 0.05f) {
        return FIVE;
    } else if (coinFloat == 0.1f) {
        return TEN;
    } else if (coinFloat == 0.2f) {
        return TWENTY;
    } else if (coinFloat == 0.5f) {
        return FIFTY;
    } else if (coinFloat == 1.0f) {
        return POUND;
    } else if (coinFloat == 2.0f) {
        return TWO_POUND;
    } else {
        throw new Exception("Coin not found.");
    }

}}