public class Elf extends Player {

    public Elf() {
        super(500, 150, 10, 10, "", "Elf", true);
        Potion testPotion = new Potion("health potion", 10, 15);
        Potion manaTestPotion = new Potion("test mana potion", 10, 5);
        this.addItemToInventory(testPotion);
        this.addItemToInventory(manaTestPotion);
    }
}
