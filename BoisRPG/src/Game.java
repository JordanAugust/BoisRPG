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
        String userSelectionForName = scanner.next();
        userSelectionForName = userSelectionForName.toLowerCase();
        Player player = createCharacter(userSelectionForRace, userSelectionForName);
        System.out.println("You chose: " + player.getRace());
        System.out.println("Welcome, " + player.getName());
        System.out.println("Your Journey Begins...");

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
}
