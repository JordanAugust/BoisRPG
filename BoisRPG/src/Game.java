import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static boolean playerIsAlive = true;
    public static Story story = new Story();
    public static Location town = new Location("town");
    public static CroakingToadInn croakingToadInn = new CroakingToadInn("croaking toad inn");
    public static ArcadiumsShop arcadiumsShop = new ArcadiumsShop("arcadium's shop");
    public static PranTrainingHall pranTrainingHall = new PranTrainingHall("pran training hall");

    public static Woods woods = new Woods("woods");
    public static BanditCamp banditCamp = new BanditCamp("bandit camp");
    public static BanditCampRoute tunnelRoute = new BanditCampRoute("tunnel route");
    public static BanditCampRoute hillRoute = new BanditCampRoute("hill route");

    public static SpiderCave spiderCave = new SpiderCave("spider cave");
    public static TheTreeHouse theTreeHouse = new TheTreeHouse("The Tree House");
    // create location for each possible location the player can go to when they leave town


    public static Swamp swamp = new Swamp("swamp");

    public static Mountains mountains = new Mountains("mountains");

    public static Desert desert = new Desert("desert");


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // prompting user to choose their race
        System.out.println("Choose your race");
        System.out.println("Type 'elf,' 'human,' or 'dwarf'");
        System.out.print("> ");
        String userSelectionForRace = scanner.next();
        userSelectionForRace = userSelectionForRace.toLowerCase();

        //checking if selection of race is valid
        while (!userSelectionForRace.equals("elf") && !userSelectionForRace.equals("human") && !userSelectionForRace.equals("dwarf")) {
            System.out.println("Invalid selection");
            System.out.println("Choose your race");
            System.out.println("Type 'elf,' 'human,' or 'dwarf'");
            userSelectionForRace = scanner.next();
        }

        // prompting user to enter the name of their character
        System.out.println("Please enter your character name");
        System.out.print("> ");
        scanner.nextLine();
        String userSelectionForName = scanner.nextLine();
        userSelectionForName = userSelectionForName.toLowerCase();
        Player player = createCharacter(userSelectionForRace, userSelectionForName);
        player.setRace(userSelectionForRace);
        System.out.println("You chose: " + player.getRace());
        System.out.println("Welcome, " + player.getName());
        System.out.println("Your Journey Begins...");

        /**
         *  Setting up the game
         */

        story = new Story(player);
        player.setStory(story);
        story.openingScene();
        player.setCurrentLocation(town);

        // start of game
        while (playerIsAlive) {

            /**
             * This is the sequence and story progression of the game
             */

            while (player.getCurrentLocation().getLocationName().equals("town")) {
                town(player);
            }
            //map(player);
            while (player.getCurrentLocation().getLocationName().equals("woods")) {
                woods(player);
            }
            while (player.getCurrentLocation().getLocationName().equals("swamp")) {
                swamp(player);
            }
            while (player.getCurrentLocation().getLocationName().equals("mountains")) {
                mountains(player);
            }
            while (player.getCurrentLocation().getLocationName().equals("desert")) {
                desert(player);
            }

        }
    }

    // spawn an enemy
    // kind of an outdated method at this point
    public static Enemy spawnEnemy() {
        Enemy enemy = new Enemy();
        int creationRoll = (int) (Math.random() * 100) + 1;

        if (creationRoll > 75) {
            enemy = new Goblin();
        } else if (creationRoll >= 50) {
            enemy = new Dragon();
        } else if (creationRoll >= 25) {
            enemy = new Orc();
        } else {
            enemy = new Ogre();
        }
        return enemy;
    }


    // checks what type of enemy spawned
    // outdated method
    public static void classCheck(Enemy enemy) {

        if (enemy instanceof Goblin) {
            System.out.println("\nA Goblin has appeared!");

        } else if (enemy instanceof Dragon) {
            System.out.println("\nA Dragon has appeared!");


        } else if (enemy instanceof Orc) {
            System.out.println("\nAn Orc has appeared!");


        } else {
            System.out.println("\nAn Ogre has appeared!");

        }
    }

    // creates a Player object based off input given by user
    public static Player createCharacter(String userSelectionForRace, String userSelectionForName) {

        Player player = new Player(0, 0, 0, 0, "", "", true);

        if (userSelectionForRace.equals("elf")) {
            player = new Elf();
            player.setName(userSelectionForName);

        } else if (userSelectionForRace.equals("human")) {
            player = new Human();
            player.setName(userSelectionForName);

        } else if (userSelectionForRace.equals("dwarf")) {
            player = new Dwarf();
            player.setName(userSelectionForName);
        }
        return player;
    }

    /**
     * @param player combat sequence
     */
    public static void combatSequence(Player player, Enemy enemy) {
        Scanner scanner = new Scanner(System.in);
        //Enemy enemy = spawnEnemy(); // upgradeEnemy() /// going to want to pass in the player to spawn enemy to determine how strong to make the enemy
        // also, so that we can see which area of the map they are in, so it will spawn an enemy according to where the player is in the map

        while (enemy.getHealth() > 0 && player.getHealth() > 0 && playerIsAlive) {
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
            playerIsAlive = false;
        }
    }

    /**
     * town
     * within this method, if the player chooses to leave the town, we can create another method that
     * displays the possible locations they can go to, then only spawn enemies and subsequently the combat sequence,
     * based off their selection of which location they would like to travel to
     */

    public static void town(Player player) {
        Scanner scanner = new Scanner(System.in);

        boolean playerIsInTown = true;
        while (playerIsInTown) {
            System.out.println("Where would you like to go?");
            System.out.println("1. Croaking Toad Inn");
            System.out.println("2. Arcadium's Arcana");
            System.out.println("3. Pran Training Hall");
            System.out.println("4. Leave town");
            System.out.print("> ");
            String playerChoice = scanner.next();
            while (!playerChoice.equals("1") && !playerChoice.equals("2") && !playerChoice.equals("3") && !playerChoice.equals("4")) {
                System.out.println("Invalid Selection");
                System.out.print("> ");
                playerChoice = scanner.next();
            }

            // player chose croaking toad inn
            if (playerChoice.equals("1")) { // call croaking toad inn method
                boolean playerIsInCroakingToadInn = true;
                System.out.println("You are now in the Croaking Toad Inn.");
                while (playerIsInCroakingToadInn) {
                    System.out.println("Who would you like to talk to?");
                    for (int i = 0; i < croakingToadInn.getPeople().size(); i++) {
                        System.out.println((i + 1) + ". " + croakingToadInn.getPeople().get(i).getName());
                    }
                    System.out.println(String.valueOf((croakingToadInn.getPeople().size()) + 1) + ". Leave");
                    System.out.print("> ");
                    String dialogueChoice = scanner.next();
                    if (dialogueChoice.equals("1")) { // have this cost money for the player to rest
                        croakingToadInn.getInnKeeper().talk(player);
                    } else if (dialogueChoice.equals("2")) { // Gambler
                        croakingToadInn.getPeople().get(1).talk(player);

                    } else if (dialogueChoice.equals("3")) { // Drunk
                        // are going to do stuff inside here that is related the dwarf's quest
                        croakingToadInn.getPeople().get(2).talk(player);


                    } else if (dialogueChoice.equals(String.valueOf((croakingToadInn.getPeople().size()) + 1))) {
                        System.out.println("You leave the Inn");
                        playerIsInCroakingToadInn = false;
                    } else {
                        System.out.println("Invalid Selection");
                    }
                }

                // player chose arcadiums arcana
            } else if (playerChoice.equals("2")) {
                System.out.println("You are now in Arcadium's Arcana."); // call arcadiums method
                boolean playerIsInAracdiumsArcana = true;
                while (playerIsInAracdiumsArcana) {
                    System.out.println("Who would you Like to talk to?");
                    for (int i = 0; i < arcadiumsShop.getPeople().size(); i++) {
                        System.out.println((i + 1) + ". " + arcadiumsShop.getPeople().get(i).getName());
                    }
                    System.out.println(String.valueOf((arcadiumsShop.getPeople().size()) + 1) + ". Leave");
                    System.out.print("> ");
                    String dialogueChoice = scanner.next();
                    while (!dialogueChoice.equals("1") && !dialogueChoice.equals("2") && !dialogueChoice.equals("3")) {
                        System.out.println("Invalid Selection");
                        System.out.print("> ");
                        dialogueChoice = scanner.next();
                    }
                    if (dialogueChoice.equals("1")) {
                        arcadiumsShop.getPeople().get(0).talk(player);
                    } else if (dialogueChoice.equals("2")) {
                        arcadiumsShop.getPeople().get(1).talk(player);
                    } else {
                        System.out.println("You leave Arcadium's Arcana.");
                        playerIsInAracdiumsArcana = false;
                    }
                }

                // player chose pran training hall
            } else if (playerChoice.equals("3")) {
                System.out.println("You are now in the Pran Training Hall."); // call blacksmith method
                boolean playerIsInPranTrainingHall = true;
                while (playerIsInPranTrainingHall) {
                    System.out.println("Who would you Like to talk to?");
                    for (int i = 0; i < pranTrainingHall.getPeople().size(); i++) {
                        System.out.println((i + 1) + ". " + pranTrainingHall.getPeople().get(i).getName());
                    }
                    System.out.println(String.valueOf((pranTrainingHall.getPeople().size()) + 1) + ". Leave");
                    System.out.print("> ");
                    String dialogueChoice = scanner.next();
                    while (!dialogueChoice.equals("1") && !dialogueChoice.equals("2") && !dialogueChoice.equals("3")) {
                        System.out.println("Invalid Selection");
                        System.out.print("> ");
                        dialogueChoice = scanner.next();
                    }
                    if (dialogueChoice.equals("1")) {
                        pranTrainingHall.getPeople().get(0).talk(player);
                    } else if (dialogueChoice.equals("2")) {
                        pranTrainingHall.getPeople().get(1).talk(player);
                    } else {
                        System.out.println("You leave Arcadium's Arcana.");
                        playerIsInPranTrainingHall = false;
                    }
                }

                // Player Chose to leave town
            } else {
                System.out.println("You have left town.");
                map(player);
                playerIsInTown = false;
            }

        } // end of while player is in town loop

    } // end of town method

    public static void playerHasBeenDefeated(Player player) {
        //prints out their hiscore and all their achievements and stuff
        //get all the quests they completed and choices they made, print out their total xp and level, along with their morality level/type
    }

    /**
     * Map
     */

    public static void map(Player player) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> locations = new ArrayList<>();
        locations.add("Town");
        locations.add("Woods");
        locations.add("Swamp");
        locations.add("Mountains");
        locations.add("Desert");

        System.out.println("Choose the location you would like to go to.");
        /**
         *    Locations - town, woods, swamp, mountains, desert
         */
        int counter = 1;
        for (int i = 0; i < locations.size(); i++) {
            String location = locations.get(i);
            location = location.toLowerCase();
            if (player.getCurrentLocation().getLocationName().equals(location)) {

            } else {
                System.out.println(counter + ". " + locations.get(i));
                counter++;
            }
        }

        System.out.println(locations.size() + ". Return");
        System.out.print("> ");
        String playerLocationChoice = scanner.next(); //
        while (!playerLocationChoice.equals("1") && !playerLocationChoice.equals("2") && !playerLocationChoice.equals("3") && !playerLocationChoice.equals("4") && !playerLocationChoice.equals("5")) {
            System.out.println("Invalid Selection");
            System.out.print("> ");
            playerLocationChoice = scanner.next();
        }
        if (player.getCurrentLocation().equals(town)) {
            if (playerLocationChoice.equals("1")) {
                player.setCurrentLocation(woods);
            } else if (playerLocationChoice.equals("2")) {
                player.setCurrentLocation(swamp);
            } else if (playerLocationChoice.equals("3")) {
                player.setCurrentLocation(mountains);
            } else if (playerLocationChoice.equals("4")) {
                player.setCurrentLocation(desert);
            } else {
                player.setCurrentLocation(player.getCurrentLocation());
            }
        } else if (player.getCurrentLocation().equals(woods)) {
            if (playerLocationChoice.equals("1")) {
                player.setCurrentLocation(town);
            } else if (playerLocationChoice.equals("2")) {
                player.setCurrentLocation(swamp);
            } else if (playerLocationChoice.equals("3")) {
                player.setCurrentLocation(mountains);
            } else if (playerLocationChoice.equals("4")) {
                player.setCurrentLocation(desert);
            } else {
                player.setCurrentLocation(player.getCurrentLocation());
            }
        } else if (player.getCurrentLocation().equals(swamp)) {
            if (playerLocationChoice.equals("1")) {
                player.setCurrentLocation(town);
            } else if (playerLocationChoice.equals("2")) {
                player.setCurrentLocation(woods);
            } else if (playerLocationChoice.equals("3")) {
                player.setCurrentLocation(mountains);
            } else if (playerLocationChoice.equals("4")) {
                player.setCurrentLocation(desert);
            } else {
                player.setCurrentLocation(player.getCurrentLocation());
            }
        } else if (player.getCurrentLocation().equals(mountains)) {
            if (playerLocationChoice.equals("1")) {
                player.setCurrentLocation(town);
            } else if (playerLocationChoice.equals("2")) {
                player.setCurrentLocation(woods);
            } else if (playerLocationChoice.equals("3")) {
                player.setCurrentLocation(swamp);
            } else if (playerLocationChoice.equals("4")) {
                player.setCurrentLocation(desert);
            } else {
                player.setCurrentLocation(player.getCurrentLocation());
            }
        } else if (player.getCurrentLocation().equals(desert)) {
            if (playerLocationChoice.equals("1")) {
                player.setCurrentLocation(town);
            } else if (playerLocationChoice.equals("2")) {
                player.setCurrentLocation(woods);
            } else if (playerLocationChoice.equals("3")) {
                player.setCurrentLocation(swamp);
            } else if (playerLocationChoice.equals("4")) {
                player.setCurrentLocation(mountains);
            } else {
                player.setCurrentLocation(player.getCurrentLocation());
            }
        }
    }

    public static void woods(Player player) {
        Scanner scanner = new Scanner(System.in);

        boolean playerIsInWoods = true;
        while (playerIsInWoods) {
            System.out.println("Where would you like to go?");
            System.out.println("1. Bandit Camp");
            System.out.println("2. Spider Cave");
            System.out.println("3. The Tree House");
            System.out.println("4. Leave woods");
            System.out.print("> ");
            String playerChoice = scanner.next();
            while (!playerChoice.equals("1") && !playerChoice.equals("2") && !playerChoice.equals("3") && !playerChoice.equals("4")) {
                System.out.println("Invalid Selection");
                System.out.print("> ");
                playerChoice = scanner.next();
            }
            if (playerChoice.equals("1") && !story.getBanditKingQuest()) {
                banditCamp.banditCampGate(player);
                if (banditCamp.getPlayerChoseToComply()) {

                } else if (!banditCamp.getPlayerChoseToComply()) {
                    for (int i = 0; i < banditCamp.getGateBandits().size(); i++) {
                        combatSequence(player, banditCamp.getGateBandits().get(i));
                    }

                    boolean playerIsInBanditCamp = true;

                    while (playerIsInBanditCamp) {
                        System.out.println("\nYou see two routes ahead of you. ");
                        System.out.println("One leads down a tunnel, seemingly dug through the underbelly of the camp.");
                        System.out.println("The other leads toward a golden colored hill. ");
                        System.out.println("Which way do you want to go?");
                        System.out.println("1. Tunnel");
                        System.out.println("2. Hill");
                        System.out.print("> ");
                        String playerRouteChoice = scanner.next();
                        while (!playerRouteChoice.equals("1") && !playerRouteChoice.equals("2")) {
                            System.out.println("Invalid Selection");
                            System.out.print("> ");
                            playerRouteChoice = scanner.next();
                        }

                        boolean banditsDefeated = false;
                        while (!banditsDefeated) {
                            if (playerRouteChoice.equals("1")) {
                                int banditsKilled = 0;
                                banditCamp.setBanditCampRoute(tunnelRoute);
                                tunnelRoute.tunnelRoute(player);
                                for (int i = 0; i < tunnelRoute.getTunnelBandits().size(); i++) {
                                    combatSequence(player, tunnelRoute.getTunnelBandits().get(i));
                                    banditsKilled++;
                                }
                                if (banditsKilled == tunnelRoute.getTunnelBandits().size()) {
                                    banditsDefeated = true;
                                    // continue on
                                }
                            } else if (playerRouteChoice.equals("2")) {
                                int banditsKilled = 0;
                                banditCamp.setBanditCampRoute(hillRoute);
                                hillRoute.hillRoute(player);
                                for (int i = 0; i < hillRoute.getHillBandits().size(); i++) {
                                    combatSequence(player, hillRoute.getHillBandits().get(i));
                                    banditsKilled++;
                                }
                                if (banditsKilled == hillRoute.getHillBandits().size()) {
                                    banditsDefeated = true;

                                }
                            }
                        }

                        if (!story.getBanditKingQuest()) {
                            System.out.println("\n'Ahh, so it is you who has come to seek some sanctimonious vengeance upon me..'");
                            System.out.println("'And for what? Surviving? ..Are we much different?'");
                            System.out.println("'Ultimately, we are seeking the same thing, that is, power..'");
                            System.out.println("'Well, c'mon then. You've ventured quite a ways to deliver my ill-imposed retribution, ' " + player.getRace() + ".'");
                            System.out.println("'The King of these forlorn bandits will not succumb so easily..'");

                            combatSequence(player, banditCamp.getBanditKing());
                            if (banditCamp.getBanditKing().getHealth() <= 0) {
                                System.out.println("'You have fought well, " + player.getRace() + "..");
                                System.out.println("'It seems I have been defeated.. Now, with no guidance, ");
                                System.out.println("'Who knows what is to come of my flock? Lost and bitter are their souls..'");
                                System.out.println("'I wanted to provide them a place of respite, a place of welcome for those abandoned");
                                System.out.println("'By the cold reproach of this world..' ");
                                System.out.println("'Do what you must, traveler..'");
                                System.out.println("Would you like to spare or kill? 'spare' or 'kill'");
                                System.out.print("> ");
                                String playerResponse = scanner.next();
                                while (!playerResponse.equals("spare") && !playerResponse.equals("kill")) {
                                    System.out.println("Invalid Selection");
                                    System.out.print("> ");
                                    playerResponse = scanner.next();
                                }
                                if (playerResponse.equals("spare")) {
                                    System.out.println("'Mercy.. hah! The truest death..'");
                                    System.out.println("'I do not thank thee. You have marked me mediocre'");
                                    System.out.println("'Though I am humbled and therefore have something to'");
                                    System.out.println("'Learn and gain from this defeat..'");
                                    System.out.println("'Here, take this.. and leave..'");
                                    Weapon banditKingBlade = new Weapon("bandit king blade", 500);
                                    player.addItemToInventory(banditKingBlade);
                                    System.out.println("You have received: " + banditKingBlade.getName() + ".");
                                    story.setBanditKingSpared(true);
                                    player.getStory().setBanditKingSpared(true);
                                    player.setMoralityLevel(player.getMoralityLevel() + 10);
                                } else if (playerResponse.equals("kill")) {
                                    System.out.println("Just before your final attack, the lessened king looks upon you with a furrowed brow and saddened eyes..");
                                    System.out.println("With one final blow, you remove the bandit king's head clean from his shoulders..");
                                    Item banditKingHead = new Item("bandit king head", 500);
                                    System.out.println("You have received: " + banditKingHead.getName() + ".");
                                    player.addItemToInventory(banditKingHead);
                                    story.setBanditKingKilled(true);
                                    player.getStory().setBanditKingKilled(true);
                                    player.setMoralityLevel(player.getMoralityLevel() - 10);
                                }
                            }
                            story.setBanditKingQuest(true);
                            player.getStory().setBanditKingQuest(true);
                            playerIsInBanditCamp = false;
                        }
                        // once the bandit king has been defeated, we can change the dialogue of this method
                    }
                }
                //They have already defeated the Bandit King
            } else if (playerChoice.equals("1") && story.getBanditKingQuest()) {
                Bandit bandit = new Bandit();
                combatSequence(player, bandit);
            } else if (playerChoice.equals("2")) {
                spiderCave.spiderCave(player);
            } else if (playerChoice.equals("3")) {
                theTreeHouse.theTreeHouse(player);
            } else if (playerChoice.equals("4")) {
                map(player);
                playerIsInWoods = false;
            }
        }
    }

    public static void swamp(Player player) {
        Scanner scanner = new Scanner(System.in);
        boolean playerIsInSwamp = true;
        while (playerIsInSwamp) {
            System.out.println("Where would you like to go?");
            System.out.println("1. swamp location 1");
            System.out.println("2. swamp location 2");
            System.out.println("3. swamp location 3");
            System.out.println("4. Leave swamp");
            System.out.print("> ");
            String playerChoice = scanner.next();
            while (!playerChoice.equals("1") && !playerChoice.equals("2") && !playerChoice.equals("3") && !playerChoice.equals("4")) {
                System.out.println("Invalid Selection");
                System.out.print("> ");
                playerChoice = scanner.next();
            }
            if (playerChoice.equals("4")) { // change this to else if
                map(player);
                playerIsInSwamp = false;
            }
        }
    }

    public static void mountains(Player player) {
        Scanner scanner = new Scanner(System.in);
        boolean playerIsInMountains = true;
        while (playerIsInMountains) {
            System.out.println("Where would you like to go?");
            System.out.println("1. mountains location 1");
            System.out.println("2. mountains location 2");
            System.out.println("3. mountains location 3");
            System.out.println("4. Leave mountains");
            System.out.print("> ");
            String playerChoice = scanner.next();
            while (!playerChoice.equals("1") && !playerChoice.equals("2") && !playerChoice.equals("3") && !playerChoice.equals("4")) {
                System.out.println("Invalid Selection");
                System.out.print("> ");
                playerChoice = scanner.next();
            }
            if (playerChoice.equals("4")) { // change this to else if
                map(player);
                playerIsInMountains = false;
            }
        }
    }

    public static void desert(Player player) {
        Scanner scanner = new Scanner(System.in);
        boolean playerIsInDesert = true;
        while (playerIsInDesert) {
            System.out.println("Where would you like to go?");
            System.out.println("1. desert location 1");
            System.out.println("2. desert location 2");
            System.out.println("3. desert location 3");
            System.out.println("4. Leave desert");
            System.out.print("> ");
            String playerChoice = scanner.next();
            while (!playerChoice.equals("1") && !playerChoice.equals("2") && !playerChoice.equals("3") && !playerChoice.equals("4")) {
                System.out.println("Invalid Selection");
                System.out.print("> ");
                playerChoice = scanner.next();
            }
            if (playerChoice.equals("4")) { // change this to else if
                map(player);
                playerIsInDesert = false;
            }
        }
    }
}
