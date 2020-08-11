public class Armor extends Item {
    private int armorValue;

    public Armor(String name, int value){
        super(name, value);
        // going to add stats
    }

    public int getArmorValue() {
        return armorValue;
    }

    public void setArmorValue(int armorValue) {
        this.armorValue = armorValue;
    }
}
