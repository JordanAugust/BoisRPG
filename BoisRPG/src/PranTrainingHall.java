import java.util.Scanner;

public class PranTrainingHall extends Location {
    private Pran pran = new Pran();
    private AjinSmith ajinSmith = new AjinSmith();

    public PranTrainingHall(String locationName) {
        super(locationName);
        this.addPeopleToLocation(pran);
        this.addPeopleToLocation(ajinSmith);
    }
}

class Pran extends NPC {
    private boolean paymentReceived;

    public Pran() {
        this.setName("Pran");
        this.paymentReceived = false;
    }

    public void talk(Player player) {
        Scanner scanner = new Scanner(System.in);
        if (player.getRace().equals("human") && !this.paymentReceived) {
            System.out.println("Do you have my money? 'y' or 'n'");
            System.out.print("> ");
            String playerResponse = scanner.next();
            while (!playerResponse.equals("y") && !playerResponse.equals("n")) {
                System.out.println("Invalid Selection");
                System.out.print("> ");
                playerResponse = scanner.next();
            }
            if (playerResponse.equals("y")) {
                if (player.getInventory().get(0).getValue() >= 50) {
                    player.getInventory().get(0).setValue(player.getInventory().get(0).getValue() - 50);
                    System.out.println("Thank you for your payment.");
                    this.setPaymentReceived(true);
                } else {
                    System.out.println("You don't have my money, you filthy liar!");
                    System.out.println("I will not talk to you until you pay me.");
                }
            } else {
                System.out.println("I won't talk to you until you pay me.");
            }
        } else if (player.getRace().equals("human") && this.getPaymentReceived()) {
            System.out.println("Welcome to my training."); // this is where we will call the training method
        }
        else if ((player.getRace().equals("elf") || player.getRace().equals("dwarf")) && !this.getPaymentReceived()) {
            System.out.println("Welcome, " + player.getRace() + ".");
            System.out.println("Would you like to buy a membership to my training hall? 'y' or 'n'");
            System.out.print("> ");
            String playerResponse = scanner.next();
            while (!playerResponse.equals("y") && !playerResponse.equals("n")) {
                System.out.println("Invalid Selection");
                System.out.print("> ");
                playerResponse = scanner.next();
            }
            if (playerResponse.equals("y")) {
                if (player.getInventory().get(0).getValue() >= 25) {
                    player.getInventory().get(0).setValue(player.getInventory().get(0).getValue() - 25);
                    System.out.println("Thank you for your payment.");
                    this.setPaymentReceived(true);
                } else {
                    System.out.println("It seems you don't have enough money.");
                    System.out.println("Talk to me again when you have 25 coins.");
                }
            } else {
                System.out.println("I won't talk to you until you pay me.");
            }
        } else {
            System.out.println("Welcome to my training."); // this is where we will call the training method
        }
    }

    public boolean getPaymentReceived() {
        return paymentReceived;
    }

    public void setPaymentReceived(boolean paymentReceived) {
        this.paymentReceived = paymentReceived;
    }

    public void training() {

    }

}

class AjinSmith extends ShopKeeper {

    public AjinSmith() {
        this.setName("A'jin the smith");

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

//            Coins coins = new Coins("coins", 1000);  this will instead need to add coins to their inventory, after checking the player's level
//            this.addItemToInventory(coins);
            Potion lesserHealthPotion1 = new Potion("health potion", 10, 15);
            Potion lesserHealthPotion2 = new Potion("health potion", 10, 15);
            this.addItemToInventory(lesserHealthPotion1);
            this.addItemToInventory(lesserHealthPotion2);
        }
    }
}
