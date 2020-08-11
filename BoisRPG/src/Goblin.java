public class Goblin extends Enemy {

    public Goblin() {
        this.setAttack(5);
        this.setHealth(30);
        this.setDefense(1.5);
        this.setMagicDefense(1.5);
        this.setResistance("frost");
        this.setWeakness("melee");
        this.setXp(20);
        this.setName("Goblin");
        Coins coins = new Coins("coins", 5);
        this.setCoins(coins);
        this.addItemToInventory(coins);

    }

}
