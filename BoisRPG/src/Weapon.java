public class Weapon extends Item {
    private int attackValue;

    public Weapon(String name, int value) {
        super(name, value);
        // going to add stats
    }

    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }
}
