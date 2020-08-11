public class Potion extends Item {
    private int restoreValue;
    public Potion(String name, int value, int restoreValue) {
        super(name, value);
        this.restoreValue = restoreValue;
    }

    public int getRestoreValue() {
        return restoreValue;
    }

    public void setRestoreValue(int restoreValue) {
        this.restoreValue = restoreValue;
    }

    public int drinkPotion() {
        return this.restoreValue;
    }

}
