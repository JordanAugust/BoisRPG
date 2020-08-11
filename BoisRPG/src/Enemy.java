import java.util.ArrayList;

public class Enemy {

    private int health;
    private int attack;
    private double defense;
    private double magicDefense;
    private String resistance;
    private String weakness;
    private int xp;
    private int level;
    private String attackType;
    private String name;
    private boolean alive;
    private int lootRoll;
    private boolean hasLoot;
    private ArrayList<Item> inventory;
    private Coins coins;

    public Enemy() { //probably going to want to set the enemy's name, xp, and level in the constructor
        this.inventory = new ArrayList<Item>();

        // making a roll to see if enemy will spawn with loot
        makeLootRoll();
        if (this.hasLoot) {
            addItemToInventory(lootTable());
        }
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

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public double getMagicDefense() {
        return magicDefense;
    }

    public void setMagicDefense(double magicDefense) {
        this.magicDefense = magicDefense;
    }

    public String getResistance() {
        return resistance;
    }

    public void setResistance(String resistance) {
        this.resistance = resistance;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
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
    }  // we can use these in the spawn enemy method in Game, checking what the player's level is, and then writing the code here, the upgradeEnemy() method will go in the Enemy class

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

    public Coins getCoins() {
        return coins;
    }

    public void setCoins(Coins coins) {
        this.coins = coins;
    }

    public boolean getHasLoot() {
        return hasLoot;
    }

    public void setHasLoot(boolean hasLoot) {
        this.hasLoot = hasLoot;
    }

    public void makeLootRoll() {
        boolean enemyPassedLootCheck = false;
        int roll = (int) (Math.random() * 5) + 1;
        if (roll == 5) {
            enemyPassedLootCheck = true;
        }
        setHasLoot(enemyPassedLootCheck);
    }

    public void addItemToInventory(Item item) {
        this.inventory.add(item);
    }

    public Item lootTable() {
        String lootName = "";
        int lootValue = 0;
        int attackValue = 0;
        int armorValue = 0;
        int magicValue = 0;
        Item item = new Item(lootName, lootValue);
        /**
         * weapon, helmet, platelegs, platebody, gauntlets, greaves, amulet, ring, belt, offhand, shoulders, cloak
         * tier for armor (weapon, helmet, platelegs, platebody, gauntlets, greaves, shoulders, belt) - bronze, iron, steel, dragon, light, dark, elemental, colossal
         * tier for  jewelry (amulet, ring) - silver, gold, diamond, radiant, brilliant, sun, moon
         * tier for offhand (offhand/shield) - leather, sturdy, wood, mithril, blazing, bone, ancient
         * come up with values for the items themselves and then add that number to the value of the type
         * weapon values - longsword 100, staff 150, shortsword 75, axe 120, hammer 115, falchion 200, dagger 50, flail 125, katana 500
         * armor values - helmet 20, platelegs 50, platebody 75, gauntlets 15, belt 15, shoulders 50, greaves 25
         * armor/weapon type values - bronze 50, iron 75, steel 100, dragon 500, light 250, dark 250, elemental 300, colossal 1000
         * jewelry values - ring 100, amulet 150
         * jewelry type values - silver 100, gold 200, diamond 300, radiant 400, brilliant 500, sun 700, moon 700
         * offhand and shield values - offhand 200, shield 200
         * offhand/shield type values - leather 75, wood 100, mithril 500, blazing 100, bone 200, ancient 500
         */
        String[] weapon = {"longsword", "staff", "shortsword", "axe", "hammer", "falchion", "dagger", "flail", "katana"};
        String[] armor = {"helmet", "platelegs", "platebody", "gauntlets", "belt", "shoulders", "greaves"};
        String[] armorAndWeaponType = {"bronze", "iron", "steel", "dragon", "light", "dark", "elemental", "colossal"};
        String[] jewelry = {"amulet", "ring"};
        String[] jewelryType = {"silver", "gold", "diamond", "radiant", "brilliant", "sun", "moon"};
        String[] offhandShield = {"offhand", "shield"};
        String[] offhandShieldType = {"leather", "wood", "mithril", "blazing", "bone", "ancient"};

        int rollForWhichItem = (int) (Math.random() * 4) + 1;
        if (rollForWhichItem == 1) { // weapon
            int rollForWhichArmorAndWeaponType = (int) (Math.random() * armorAndWeaponType.length);
            lootName += armorAndWeaponType[rollForWhichArmorAndWeaponType];
            lootName += " ";
            int rollForWhichWeapon = (int) (Math.random() * weapon.length);
            lootName += weapon[rollForWhichWeapon];
            item.setName(lootName);
            item = new Weapon(lootName, lootValue);


        } else if (rollForWhichItem == 2) { // armor
            int rollForWhichArmorAndWeaponType = (int) (Math.random() * armorAndWeaponType.length);
            lootName += armorAndWeaponType[rollForWhichArmorAndWeaponType];
            lootName += " ";
            int rollForWhichArmor = (int) (Math.random() * armor.length);
            lootName += armor[rollForWhichArmor];
            item.setName(lootName);
            item = new Armor(lootName, lootValue);

        } else if (rollForWhichItem == 3) { // jewelry
            int rollForWhichJewelryType = (int) (Math.random() * jewelryType.length);
            lootName += jewelryType[rollForWhichJewelryType];
            lootName += " ";
            int rollForWhichJewelry = (int) (Math.random() * jewelry.length);
            lootName += jewelry[rollForWhichJewelry];
            item.setName(lootName);
            item = new Jewelry(lootName, lootValue);

        } else { // offhand/shield - we need to switch whether its an off hand or shield
            int rollForWhichOffhandAndShieldType = (int) (Math.random() * offhandShieldType.length);
            lootName += offhandShieldType[rollForWhichOffhandAndShieldType];
            lootName += " ";
            int rollForWhichOffhandAndShield = (int) (Math.random() * offhandShield.length);
            lootName += offhandShield[rollForWhichOffhandAndShield];
            item.setName(lootName);
            item = new Armor(lootName, lootValue);

        }
        /**
         *                                                              Weapons
         */
        if (lootName.contains("longsword")) {
            lootValue += 100;
            attackValue += 1;

        }
        if (lootName.contains("staff")) {
            lootValue += 150;
        }
        if (lootName.contains("shortsword")) {
            lootValue += 75;
        }
        if (lootName.contains("axe")) {
            lootValue += 120;

        }
        if (lootName.contains("hammer")) {
            lootValue += 115;

        }
        if (lootName.contains("falchion")) {
            lootValue += 200;

        }
        if (lootName.contains("dagger")) {
            lootValue += 50;

        }
        if (lootName.contains("flail")) {
            lootValue += 125;


        }
        if (lootName.contains("katana")) {
            lootValue += 500;


        }
        /**
         *                                                              Armors
         */
        if (lootName.contains("helmet")) {
            lootValue += 20;
            armorValue += 1;

        }
        if (lootName.contains("platelegs")) {
            lootValue += 50;


        }
        if (lootName.contains("platebody")) {
            lootValue += 75;


        }
        if (lootName.contains("gauntlets")) {
            lootValue += 15;


        }
        if (lootName.contains("belt")) {
            lootValue += 15;


        }
        if (lootName.contains("shoulders")) {
            lootValue += 50;


        }
        if (lootName.contains("greaves")) {
            lootValue += 25;

            /**
             *                                                              Armors/Weapon Types
             */
        }
        if (lootName.contains("bronze")) {
            lootValue += 50;
            attackValue += 1;
            armorValue += 1;


        }
        if (lootName.contains("iron")) {
            lootValue += 75;


        }
        if (lootName.contains("steel")) {
            lootValue += 100;


        }
        if (lootName.contains("dragon")) {
            lootValue += 500;


        }
        if (lootName.contains("light")) {
            lootValue += 250;


        }
        if (lootName.contains("dark")) {
            lootValue += 250;


        }
        if (lootName.contains("elemental")) {
            lootValue += 300;


        }
        if (lootName.contains("colossal")) {
            lootValue += 1000;

            /**
             *                                                              Jewelry
             */
        }
        if (lootName.contains("amulet")) {
            lootValue += 150;


        }
        if (lootName.contains("ring")) {
            lootValue += 100;

            /**
             *                                                              Jewelry types
             */
        }
        if (lootName.contains("silver")) {
            lootValue += 100;


        }
        if (lootName.contains("gold")) {
            lootValue += 200;


        }
        if (lootName.contains("diamond")) {
            lootValue += 300;


        }
        if (lootName.contains("radiant")) {
            lootValue += 400;


        }
        if (lootName.contains("briliant")) {
            lootValue += 500;


        }
        if (lootName.contains("sun")) {
            lootValue += 700;


        }
        if (lootName.contains("moon")) {
            lootValue += 700;

            /**
             *                                                              Offhand/Shield
             */
        }
        if (lootName.contains("offhand")) {
            lootValue += 200;


        }
        if (lootName.contains("shield")) {
            lootValue += 200;

            /**
             *                                                              Offhand/Shield Types
             */
        }
        if (lootName.contains("leather")) {
            lootValue += 75;


        }
        if (lootName.contains("wood")) {
            lootValue += 100;


        }
        if (lootName.contains("mithril")) {
            lootValue += 500;


        }
        if (lootName.contains("blazing")) {
            lootValue += 100;


        }
        if (lootName.contains("bone")) {
            lootValue += 200;


        }
        if (lootName.contains("ancient")) {
            lootValue += 500;


        }
        item.setValue(item.getValue() + lootValue);

        if (item instanceof Weapon) { // this is how we can assign stats to the weapon/armor drops
            ((Weapon) item).setAttackValue(attackValue);
        } else if (item instanceof Armor) {
            ((Armor) item).setArmorValue(armorValue);
        }

        return item;

    }

}

