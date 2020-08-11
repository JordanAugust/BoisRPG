public class Coins extends Item {
    public Coins(String name, int value) {
        super(name, value);
    }

    public void changeCoinsValue(int newCoinsValue) {
        this.setValue(newCoinsValue);
    }

}
