public class Orc extends Enemy {

    public Orc() {
        this.setAttack(7);
        this.setHealth(70);
        this.setDefense(1.5);
        this.setMagicDefense(1.5);
        this.setResistance("lightning");
        this.setWeakness("fire");
        this.setXp(30);
        this.setName("Orc");
        Coins coins = new Coins("coins", 20);
        this.setCoins(coins);
        this.addItemToInventory(coins);

    }

}
