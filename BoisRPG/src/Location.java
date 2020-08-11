import java.util.ArrayList;
import java.util.Scanner;

public class Location {

    private ArrayList<NPC> people = new ArrayList<>();
    private String locationName;

    public Location(String locationName) {
        this.locationName = locationName;
    }

    public ArrayList<NPC> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<NPC> people) {
        this.people = people;
    }

    public void addPeopleToLocation(NPC person) {
        this.people.add(person);
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void combatSequence(Player player, Enemy enemy) {
        Scanner scanner = new Scanner(System.in);
        //Enemy enemy = spawnEnemy(); // upgradeEnemy() /// going to want to pass in the player to spawn enemy to determine how strong to make the enemy
        // also, so that we can see which area of the map they are in, so it will spawn an enemy according to where the player is in the map

//        if (player.getAlive()) {
//            classCheck(enemy);
//        }
        while (enemy.getHealth() > 0 && player.getHealth() > 0) {
            System.out.println(player.getName() + " health: " + player.getHealth() + "   mana: " + player.getMana());
            System.out.println(enemy.getName() + " health: " + enemy.getHealth());
            System.out.println("\nChoose your attack:");
            System.out.println("1. melee attack");
            System.out.println("2. lightning spell");
            System.out.println("3. fire spell");
            System.out.println("4. frost spell");
            System.out.println("5. Inventory");
            System.out.print("> ");
            String userAttack = scanner.next();

            // melee attack
            if (userAttack.equals("1")) {
                player.setAttackType("melee");
                int damageDoneByPlayer;
                if (player.getAttackType().equals(enemy.getWeakness())) {
                    damageDoneByPlayer = (int) (player.getMeleeDamage() * enemy.getDefense());
                    enemy.setHealth(enemy.getHealth() - (int) (player.getMeleeDamage() * enemy.getDefense()));

                } else if (player.getAttackType().equals(enemy.getResistance())) {
                    enemy.setHealth(enemy.getHealth() - (int) (player.getMeleeDamage() / enemy.getDefense()));
                    damageDoneByPlayer = (int) (player.getMeleeDamage() / enemy.getDefense());

                } else {
                    enemy.setHealth(enemy.getHealth() - player.getMeleeDamage());
                    damageDoneByPlayer = player.getMeleeDamage();

                }
                System.out.println("\nYou attack the " + enemy.getName() + " for " + damageDoneByPlayer);

                // lightning magic spell
            } else if (userAttack.equals("2")) {

                if (player.getMana() > 0) {
                    player.setMagicType("lightning");
                    String playersMagicType = player.getMagicType();
                    int damageDoneByPlayer;

                    if (playersMagicType.equals(enemy.getResistance())) {
                        enemy.setHealth(enemy.getHealth() - (int) (player.getMagicDamage() / enemy.getMagicDefense()));
                        damageDoneByPlayer = (int) (player.getMagicDamage() / enemy.getMagicDefense());
                    } else if (player.getMagicType().equals(enemy.getWeakness())) {
                        enemy.setHealth(enemy.getHealth() - (int) (player.getMagicDamage() * enemy.getMagicDefense()));
                        damageDoneByPlayer = (int) (player.getMagicDamage() * enemy.getMagicDefense());
                    } else {
                        enemy.setHealth(enemy.getHealth() - player.getMagicDamage());
                        damageDoneByPlayer = player.getMagicDamage();
                    }

                    player.setMana(player.getMana() - 1);
                    System.out.println("\nYou attack the " + enemy.getName() + " for " + damageDoneByPlayer);

                } else {
                    System.out.println("Out of mana!");
                }

                // fire magic spell
            } else if (userAttack.equals("3")) {

                if (player.getMana() > 0) {
                    player.setMagicType("fire");
                    String playersMagicType = player.getMagicType();
                    int damageDoneByPlayer;

                    if (playersMagicType.equals(enemy.getResistance())) {
                        enemy.setHealth(enemy.getHealth() - (int) (player.getMagicDamage() / enemy.getMagicDefense()));
                        damageDoneByPlayer = (int) (player.getMagicDamage() / enemy.getMagicDefense());
                    } else if (player.getMagicType().equals(enemy.getWeakness())) {
                        enemy.setHealth(enemy.getHealth() - (int) (player.getMagicDamage() * enemy.getMagicDefense()));
                        damageDoneByPlayer = (int) (player.getMagicDamage() * enemy.getMagicDefense());
                    } else {
                        enemy.setHealth(enemy.getHealth() - player.getMagicDamage());
                        damageDoneByPlayer = player.getMagicDamage();
                    }

                    player.setMana(player.getMana() - 1);
                    System.out.println("\nYou attack the " + enemy.getName() + " for " + damageDoneByPlayer);

                } else {
                    System.out.println("Out of mana!");

                }

                // frost magic spell
            } else if (userAttack.equals("4")) {

                if (player.getMana() > 0) {
                    player.setMagicType("frost");
                    String playersMagicType = player.getMagicType();
                    int damageDoneByPlayer;

                    if (playersMagicType.equals(enemy.getResistance())) {
                        enemy.setHealth(enemy.getHealth() - (int) (player.getMagicDamage() / enemy.getMagicDefense()));
                        damageDoneByPlayer = (int) (player.getMagicDamage() / enemy.getMagicDefense());
                    } else if (player.getMagicType().equals(enemy.getWeakness())) {
                        enemy.setHealth(enemy.getHealth() - (int) (player.getMagicDamage() * enemy.getMagicDefense()));
                        damageDoneByPlayer = (int) (player.getMagicDamage() * enemy.getMagicDefense());
                    } else {
                        enemy.setHealth(enemy.getHealth() - player.getMagicDamage());
                        damageDoneByPlayer = player.getMagicDamage();
                    }

                    player.setMana(player.getMana() - 1);
                    System.out.println("\nYou attack the " + enemy.getName() + " for " + damageDoneByPlayer);

                } else {
                    System.out.println("Out of mana!");
                }

                // inventory
            } else if (userAttack.equals("5")) {

                System.out.println("Items in your inventory: ");
                for (int i = 1; i < player.getInventory().size(); i++) {
                    System.out.println((i) + ". " + String.valueOf(player.getInventory().get(i).getName()));
                }
                System.out.println((player.getInventory().size()) + ". End Turn.");
                System.out.print("> ");

                int playersInventoryChoice = 0;
                boolean inputAccepted = false;
                while (!inputAccepted) {
                    try {
                        playersInventoryChoice = Integer.parseInt(scanner.next());
                        inputAccepted = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Selection.");
                        System.out.print("> ");
                    }
                }

                System.out.println(player.getInventory().size());
                while (playersInventoryChoice < 1 || playersInventoryChoice > player.getInventory().size()) {
                    System.out.println("Invalid Selection");
                    System.out.print("> ");
                    playersInventoryChoice = 0;
                    boolean secondAccepted = false;
                    while (!secondAccepted) {
                        try {
                            playersInventoryChoice = Integer.parseInt(scanner.next());
                            secondAccepted = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid Selection.");
                            System.out.print("> ");
                        }
                    }
                }

                if ((playersInventoryChoice == player.getInventory().size())) {
                    System.out.println("Leaves Inventory");

                } else if (player.getInventory().get(playersInventoryChoice) instanceof Potion) {// now we have room to use other children of Item - this is where we could use other items than just Potions in combat
                    if (player.getInventory().get(playersInventoryChoice).getName().contains("health potion")) { // we will just need to change the names of the potions but include health/mana
                        if (player.getHealth() + ((Potion) player.getInventory().get(playersInventoryChoice)).getRestoreValue() > player.getMaxHealth()) {
                            int healthPotionRestoreValue = ((Potion) player.getInventory().get(playersInventoryChoice)).getRestoreValue();
                            player.setHealth(player.getMaxHealth());
                            System.out.println("You restore " + healthPotionRestoreValue + " health");
                            player.getInventory().remove(playersInventoryChoice);

                        } else if (player.getHealth() + ((Potion) player.getInventory().get(playersInventoryChoice)).getRestoreValue() <= player.getMaxHealth()) {
                            int healthPotionRestoreValue = ((Potion) player.getInventory().get(playersInventoryChoice)).getRestoreValue();
                            player.setHealth(player.getHealth() + healthPotionRestoreValue);
                            System.out.println("You restore " + healthPotionRestoreValue + " health");
                            player.getInventory().remove(playersInventoryChoice);
                        }

                    } else if (player.getInventory().get(playersInventoryChoice).getName().contains("mana potion")) {
                        if (player.getMana() + ((Potion) player.getInventory().get(playersInventoryChoice)).getRestoreValue() > player.getMaxMana()) {
                            int manaPotionRestoreValue = ((Potion) player.getInventory().get(playersInventoryChoice)).getRestoreValue();
                            player.setMana(player.getMaxMana());
                            System.out.println("You restore " + manaPotionRestoreValue + " mana");
                            player.getInventory().remove(playersInventoryChoice);

                        } else if (player.getMana() + ((Potion) player.getInventory().get(playersInventoryChoice)).getRestoreValue() <= player.getMaxMana()) {
                            int manaPotionRestoreValue = ((Potion) player.getInventory().get(playersInventoryChoice)).getRestoreValue();
                            player.setMana(player.getMana() + manaPotionRestoreValue);
                            System.out.println("You restore " + manaPotionRestoreValue + " mana");
                            player.getInventory().remove(playersInventoryChoice);
                        }
                    }
                }
            } else {
                System.out.println("\nYour attack missed");
            }

            // this is the enemy's attack roll
            if (enemy.getHealth() > 0 && player.getHealth() > 0) {
                int enemyAttackRoll = (int) (Math.random() * 100) + 1;
                if (enemyAttackRoll > 50) {
                    player.setHealth(player.getHealth() - enemy.getAttack());
                    System.out.println(enemy.getName() + " attacks you for " + enemy.getAttack());
                } else {
                    System.out.println(enemy.getName() + "'s attack missed");
                }
            }

            if (enemy.getHealth() < 0) {
                enemy.setHealth(0);
            }

        }
        // executes once the enemy is defeated, or if the player is defeated
        if (enemy.getHealth() <= 0) {
            player.setXp(player.getXp() + enemy.getXp());
            player.updateCoinsValue(enemy.getCoins().getValue());
            System.out.println("You defeated the " + enemy.getName());

            // if enemy passed loot roll, then populate their loot
            if (enemy.getHasLoot()) {
                System.out.println(enemy.getName() + " has dropped: " + enemy.getInventory().get(0).getName());
                player.addItemToInventory(enemy.getInventory().get(0));
                System.out.println(enemy.getInventory().get(0).getClass());
            }
            System.out.println("You have gained " + enemy.getCoins().getValue() + " coins. Total coins: " + player.getInventory().get(0).getValue());
            System.out.println("You have gained " + enemy.getXp() + " xp. Total xp: " + player.getXp());
            player.levelUpCheck();
            System.out.println(player.getName() + " level: " + player.getLevel());
        } else {
            System.out.println("Game Over..");
            playerHasBeenDefeated(player);
            Runtime.getRuntime().halt(0);
            player.setAlive(false);
        }
    }

    private void playerHasBeenDefeated(Player player) {
    }
}
