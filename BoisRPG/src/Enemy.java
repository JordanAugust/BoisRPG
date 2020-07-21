import java.util.ArrayList;

public class Enemy {

    private int health;
    private int attack;
    private int xp;
    private int level;
    private String attackType;
    private String name;
    private boolean alive;
    private int lootRoll;
    private ArrayList<Item> inventory;

    public Enemy() { //probably going to want to set the enemy's name, xp, and level in the constructor, and maybe do a random roll for what items they have in their inventory for when they are defeated
        this.lootRoll = (int)(Math.random() * 100) + 1; // 1 - 100
        if (lootRoll > 50) {
            Coins coins = new Coins("Coins", 50); // we're going to want to put specific loot in each child class of enemy
            this.inventory.add(coins);
        }

        this.inventory = new ArrayList<Item>();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getLootRoll() {
        return lootRoll;
    }

    public void setLootRoll(int lootRoll) {
        this.lootRoll = lootRoll;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }
}

