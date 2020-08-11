public class Dragon extends Enemy{

    public Dragon() {
        this.setAttack(15);
        this.setHealth(100);
        this.setDefense(1.5);
        this.setMagicDefense(1.5);
        this.setResistance("fire");
        this.setWeakness("frost");
        this.setXp(50);
        this.setName("Dragon");
        Coins coins = new Coins("coins", 200);
        this.setCoins(coins);
        this.addItemToInventory(coins);

    }

}
