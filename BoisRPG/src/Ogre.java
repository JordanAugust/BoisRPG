public class Ogre extends Enemy {

    public Ogre() {
        this.setAttack(10);
        this.setHealth(50);
        this.setDefense(1.5);
        this.setMagicDefense(1.5);
        this.setResistance("fire");
        this.setWeakness("lightning");
        this.setXp(30);
        this.setName("Ogre");
        Coins coins = new Coins("coins", 50);
        this.setCoins(coins);
        this.addItemToInventory(coins);

    }

}
