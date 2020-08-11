import java.util.ArrayList;
import java.util.Scanner;

public class ArcadiumsShop extends Location {
    private ArrayList<NPC> people = new ArrayList<>();
    private Arcadium arcadium = new Arcadium();
    private MagicMan magicMan = new MagicMan();

    public ArcadiumsShop(String locationName) {
        super(locationName);
        this.people.add(this.arcadium);
        this.people.add(this.magicMan);
    }

    public ArrayList<NPC> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<NPC> people) {
        this.people = people;
    }
}

class Arcadium extends NPC {

    public Arcadium() {
        this.setName("Arcadium");
        Item test = new Item("test item", 1000);
        this.addItemToInventory(test);
    }

    public void talk(Player player) {
//        super.talk(player); // just so you know, this is how you would call the parent's talk method
        System.out.println("Greetings, " + player.getRace());
        if (player.getRace().equals("elf")) {
            System.out.println("Have you earned enough money for your record?"); // create while loop where player can choose y or n and then check coins
        }
        // need to expand this to finish the elf quest, to unlock the record

    }
}

class MagicMan extends ShopKeeper {
    public MagicMan() {
        this.setName("Magic Man");


    }

    public void talk(Player player) {
        this.populateInventory(player);
        Scanner scanner = new Scanner(System.in);
        // we can give player option to return to town or not, but we are not going to right now
        System.out.println("Welcome to my shoppe!");
        System.out.println("Would you like to 'buy' or 'sell'?"); // first, we are going to implement a town method that gives them the option to go to the shop and go to other parts of the town
        System.out.print("> ");
        String userResponse = scanner.next();
        userResponse = userResponse.toLowerCase();
        while (!userResponse.equals("buy") && !userResponse.equals("sell")) {
            System.out.println("Invalid Selection");
            System.out.println("'buy' or 'sell'?");
            System.out.print("> ");
            userResponse = scanner.next();
        }

        boolean playerIsInteractingWithShopKeeper = true;
        while (playerIsInteractingWithShopKeeper) {

            // when player chooses to buy
            while (userResponse.equals("buy")) {
                System.out.println("Your coins: " + player.getInventory().get(0).getValue());
                System.out.println("Shop keeper's coins: " + this.getInventory().get(0).getValue());
                System.out.println("Purchase by selecting options: '1' - '" + (this.getInventory().size() + 1) + "'");
                System.out.println(this.getName() + " inventory: ");
                for (int i = 1; i < this.getInventory().size(); i++) {
                    System.out.println((i) + ". " + this.getInventory().get(i).getName() + " - " + this.getInventory().get(i).getValue() + " coins");
                }
                System.out.println(this.getInventory().size() + ". sell");
                System.out.println((this.getInventory().size() + 1) + ". leave");
                System.out.print("> ");

                boolean inputAccepted = false;
                int userBuyChoice = 0;
                while (!inputAccepted) {
                    try {
                        userBuyChoice = Integer.parseInt(scanner.next());
                        inputAccepted = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Selection.");
                        System.out.print("> ");
                    }
                }
                if (this.getInventory().size() > 1 && userBuyChoice < this.getInventory().size()) {
                    Item boughtItem = this.getInventory().get(userBuyChoice);
                    if (player.getInventory().get(0).getValue() >= boughtItem.getValue()) {
                        player.getInventory().get(0).setValue(player.getInventory().get(0).getValue() - boughtItem.getValue());
                        player.getInventory().add(boughtItem);
                        this.getInventory().get(0).setValue(this.getInventory().get(0).getValue() + boughtItem.getValue());
                        this.getInventory().remove(boughtItem);
                    } else {
                        System.out.println("You don't have enough coins to buy that.");
                    }
                } else if (userBuyChoice == this.getInventory().size()) {
                    System.out.println("Player selected sell");
                    userResponse = "sell";
                } else if (userBuyChoice == this.getInventory().size() + 1) {
                    System.out.println("You leave.");
                    playerIsInteractingWithShopKeeper = false;
                    userResponse = "leave";
                } else {
                    System.out.println("Invalid Selection.\n");
                }

                // when player chooses to sell
            }
            while (userResponse.equals("sell")) {
                System.out.println("Your coins: " + player.getInventory().get(0).getValue());
                System.out.println("Shop keeper's coins: " + this.getInventory().get(0).getValue());
                System.out.println("Sell by selecting options: '1' - '" + (player.getInventory().size() + 1) + "'");
                System.out.println(player.getName() + " inventory: ");
                for (int i = 1; i < player.getInventory().size(); i++) {
                    System.out.println((i) + ". " + player.getInventory().get(i).getName() + " - " + player.getInventory().get(i).getValue() + " coins");
                }
                System.out.println(player.getInventory().size() + ". buy");
                System.out.println((player.getInventory().size() + 1) + ". leave");
                System.out.print("> ");

                boolean inputAccepted = false;
                int userSellChoice = 0;
                while (!inputAccepted) {
                    try {
                        userSellChoice = Integer.parseInt(scanner.next());
                        inputAccepted = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Selection.");
                        System.out.print("> ");
                    }
                }
                if (player.getInventory().size() > 1 && userSellChoice < player.getInventory().size()) {
                    Item soldItem = player.getInventory().get(userSellChoice);
                    if (this.getInventory().get(0).getValue() >= soldItem.getValue()) {
                        this.getInventory().get(0).setValue(this.getInventory().get(0).getValue() - soldItem.getValue());
                        player.getInventory().get(0).setValue(player.getInventory().get(0).getValue() + soldItem.getValue());
                        this.getInventory().add(soldItem);
                        player.getInventory().remove(soldItem);
                    } else {
                        System.out.println("The shop keeper does not have enough coins to buy that.");
                    }
                } else if (userSellChoice == player.getInventory().size()) {
                    System.out.println("Player selected buy");
                    userResponse = "buy";
                } else if (userSellChoice == player.getInventory().size() + 1) {
                    System.out.println("You leave.");
                    playerIsInteractingWithShopKeeper = false;
                    userResponse = "leave";
                } else {
                    System.out.println("Invalid Selection.\n");
                }
            }
        }
    }


    public void populateInventory(Player player) {
        if (player.getLevel() < 3) {

//            Coins coins = new Coins("coins", 1000); // Coins has a newCoinsValue method that we can use here
//            this.addItemToInventory(coins);
            Potion lesserManaPotion1 = new Potion("mana potion", 15, 5);
            Potion lesserManaPotion2 = new Potion("mana potion", 15, 5);
            this.addItemToInventory(lesserManaPotion1);
            this.addItemToInventory(lesserManaPotion2);
        }
    }
}