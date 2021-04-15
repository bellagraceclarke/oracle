enum Coin {
    FIVE(0.05f),
    TEN(0.1f),
    TWENTY(0.2f),
    FIFTY(0.5f),
    POUND(1.0f),
    TWOPOUND(2.0f);

    private final float coinValue;

    Coin(Float coin) {
        this.coinValue = coin;
    }

    public float getCoinValue(){
        return coinValue;
    }
}

public class Vending {
    private Float balance;
    private Float totalChange;

    public Vending(Float initalTotal){
        this.totalChange = initalTotal;
    }

    public void depositCoins(Coin coin){
        balance += coin.getCoinValue();
        totalChange += coin.getCoinValue();
    }

    public void depositAmount(Float amount){
        balance += amount;
        totalChange += amount;
    }

    public void vendProduct(Float price){
        //main logic
    }


}
