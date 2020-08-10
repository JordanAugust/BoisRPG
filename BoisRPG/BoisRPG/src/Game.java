import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
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

        System.out.println("Please enter your character name");
        System.out.print("> ");
        scanner.nextLine();
        String userSelectionForName = scanner.nextLine();
        userSelectionForName = userSelectionForName.toLowerCase();
        Player player = createCharacter(userSelectionForRace, userSelectionForName);
        System.out.println("You chose: " + player.getRace());
        System.out.println("Welcome, " + player.getName());
        System.out.println("Your Journey Begins...");

        System.out.println("your initial health: " + player.getHealth());
        System.out.println("You encounter a goblin.. goblin attacks you for 10 damage");
        //do some random roll for an encounter - I encounter a goblin
        Goblin goblin = new Goblin(); //let's say this enemy's lootRoll is 9, if it's a 9,
        goblin.setAttack(10);
        System.out.println("Your health is now: " + player.getHealth());
        //lets say I kill the goblin. goblin drops some coins
        Item coins = new Item("Coins"); //Create Coin class that extends item - that contain attributes of (value)
        //create an NPC parent class
        //create shopkeeper that extends the NPC class
        player.addItemToInventory(coins);
        combatSequence(player, goblin);


    }

    public static Player createCharacter(String userSelectionForRace, String userSelectionForName) {

        Player player = new Player(0, 0, "", "", true);

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

    public static void combatSequence(Player player, Enemy enemy) {
        Scanner scanner = new Scanner(System.in);


        while (enemy.getHealth() > 0 || player.getHealth() > 0) {
            System.out.println("Choose your attack");
            System.out.println("1. melee attack");
            System.out.println("2. lightning spell");
            System.out.println("3. fire spell");
            System.out.println("4. frost spell");
            System.out.println("5. Inventory");
            System.out.print("> ");
            String userAttack = scanner.next();
            if (userAttack.equals("1")) {
                //set players attack to melee style and make roll check if the attack is successful,
                //then deduct that number from enemy's health
            } else if (userAttack.equals("2")) {
                //
            } else if (userAttack.equals("3")) {

            } else if (userAttack.equals("4")) {

            } else if (userAttack.equals("5")) {
                System.out.println("Items in your inventory: ");
                for (int i = 0; i < player.getInventory().size(); i++) {
                    System.out.println((i + 1) + ". " + String.valueOf(player.getInventory().get(i).getName()));
                }
            } else {
                //they miss - because they mistyped
            }



        }

        player.setHealth(player.getHealth() - enemy.getAttack());
    }
}
