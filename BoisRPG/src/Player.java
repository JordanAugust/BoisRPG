import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private int health;
    private int maxHealth;
    private int mana;
    private int maxMana;
    private int meleeDamage;
    private int magicDamage;
    private int level;
    private int xp;
    private int moralityLevel;
    private String moralityType; // we are going to make a string list of morality types. evil(-20) - nefarian, neutral(-20 to +20) - vitruvian, good(20+)- empyrean
    private String attackType;
    private String magicType;
    private String race;
    private String name;
    private boolean alive;
    private ArrayList<Item> inventory;
    private ArrayList<Item> equipped;
    private ArrayList<Integer> equippedStats;
    private Location currentLocation;
    private ArrayList<Location> mapLocations;
    private Story story = new Story();


    public Player(int health, int mana, int meleeDamage, int magicDamage, String name, String race, boolean alive) {
        this.health = health;
        this.maxHealth = health;
        this.mana = mana;
        this.maxMana = mana;
        this.meleeDamage = meleeDamage;
        this.magicDamage = magicDamage;
        this.xp = 0;
        this.level = 1;
        this.moralityLevel = 0;
        this.moralityType = "neutral";
        this.name = name;
        this.race = race;
        this.alive = alive;
        this.inventory = new ArrayList<Item>();
        Coins coins = new Coins("coins", 10000);
        this.addItemToInventory(coins);
        this.equipped = new ArrayList<Item>();
        this.equippedStats = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            this.equippedStats.add(0);
        }
        //    0   ,       1      ,   2   ,     3    ,     4    ,     5    ,  6  ,    7     ,   8    ,   9   ,  10
        // Weapon, OffHand/Shield, helmet, platelegs, platebody, gauntlets, belt, shoulders, greaves, amulet, ring

    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public void setMeleeDamage(int meleeDamage) {
        this.meleeDamage = meleeDamage;
    }

    public int getMeleeDamage() {
        return meleeDamage;
    }

    public int getMagicDamage() {
        return magicDamage;
    }

    public void setMagicDamage(int magicDamage) {
        this.magicDamage = magicDamage;
    }

    public String getMagicType() {
        return magicType;
    }

    public void setMagicType(String magicType) {
        this.magicType = magicType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void levelUpCheck() { // can make a constant variable, and just keep adding that
        if (this.getXp() >= 100 && this.getXp() < 200) { // lvl 2 - we want this to be 1000xp
            if (this.getLevel() < 2) {
                this.setLevel(2);
                this.levelUp();
            }
        } else if (this.getXp() >= 200 && this.getXp() < 300) {
            if (this.getLevel() < 3) {
                this.setLevel(3);
                this.levelUp();
            }
        } else if (this.getXp() >= 300 && this.getXp() < 400) {
            if (this.getLevel() < 4) {
                this.setLevel(4);
                this.levelUp();
            }
        }
    }

    public void levelUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Level Up!");
        System.out.println("Choose one of the following to increase: ");
        System.out.println("1. Max Health");
        System.out.println("2. Max Mana");
        System.out.println("3. Melee Damage");
        System.out.println("4. Magic Damage");
        System.out.print("> ");
        String playerIncreaseChoice = scanner.next();
        while (!playerIncreaseChoice.equals("1") && !playerIncreaseChoice.equals("2") && !playerIncreaseChoice.equals("3") && !playerIncreaseChoice.equals("4")) {
            System.out.println("Invalid Selection");
            System.out.print("> ");
            playerIncreaseChoice = scanner.next();
        }

        if (playerIncreaseChoice.equals("1")) {
            int tenPercentPlayerHealth = (int) (this.getMaxHealth() * .1);
            this.setMaxHealth(this.getMaxHealth() + tenPercentPlayerHealth);
            System.out.println(this.getName() + " max health: " + this.getMaxHealth());
        } else if (playerIncreaseChoice.equals("2")) {
            int tenPercentPlayerMana = (int) (this.getMaxMana() * .1);
            this.setMaxMana(this.getMaxMana() + tenPercentPlayerMana);
            System.out.println(this.getName() + " max mana: " + this.getMaxMana());
        } else if (playerIncreaseChoice.equals("3")) {
            this.setMeleeDamage(this.getMeleeDamage() + 1);
            System.out.println(this.getName() + " melee damage: " + this.getMeleeDamage());
        } else {
            this.setMagicDamage(this.getMagicDamage() + 1);
            System.out.println(this.getName() + " magic damage: " + this.getMagicDamage());
        }
        this.setHealth(this.getMaxHealth());
        this.setMana(this.getMaxMana());
    }

    public int getMoralityLevel() {
        return moralityLevel;
    }

    public void setMoralityLevel(int moralityLevel) {
        this.moralityLevel = moralityLevel;
    }

    public String getMoralityType() {
        return moralityType;
    }

    public void setMoralityType(String moralityType) {
        this.moralityType = moralityType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public String getAttackType() {
        return attackType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean getAlive() {
        return alive;
    }

    public void addItemToInventory(Item item) {
        this.inventory.add(item);
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void updateCoinsValue(int coinsValue) {
        this.getInventory().get(0).setValue(this.getInventory().get(0).getValue() + coinsValue);
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public ArrayList<Location> getMapLocations() {
        return mapLocations;
    }

    public void setMapLocations(ArrayList<Location> mapLocations) {
        this.mapLocations = mapLocations;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }
}

