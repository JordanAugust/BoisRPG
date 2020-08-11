import java.util.ArrayList;
import java.util.Scanner;

public class CroakingToadInn extends Location {
    private ArrayList<NPC> people = new ArrayList<>();
    private InnKeeper innKeeper = new InnKeeper();
    private Gambler gambler = new Gambler();
    private Drunk drunk = new Drunk();

    public CroakingToadInn(String locationName) {
        super(locationName);
        this.people.add(this.innKeeper);
        this.people.add(this.gambler);
        this.people.add(this.drunk);
    }

    public ArrayList<NPC> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<NPC> people) {
        this.people = people;
    }

    public InnKeeper getInnKeeper() {
        return innKeeper;
    }

    public void setInnKeeper(InnKeeper innKeeper) {
        this.innKeeper = innKeeper;
    }

}

class InnKeeper extends NPC { // Cost money to rest, sell drinks, player needs to access inventory

    public InnKeeper() {
        this.setName("Inn Keeper");
        Coins coins = new Coins("coins", 1000);
        this.addItemToInventory(coins);
        Item beer = new Item("beer", 5);
        this.addItemToInventory(beer);
        Item ale = new Item("ale", 5);
        this.addItemToInventory(ale);
    }

    public void talk(Player player) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Trade");
        System.out.println("2. Rest");
        System.out.println("3. Gossip");
        System.out.println("4. Leave");
        System.out.print("> ");
        String playerChoice = scanner.next();
        while (!playerChoice.equals("1") && !playerChoice.equals("2") && !playerChoice.equals("3") && !playerChoice.equals("4")) {
            System.out.println("Invalid Selection");
            System.out.print("> ");
            playerChoice = scanner.next();
        }
        // playerTradingWithShopKeeper
        if (playerChoice.equals("1")) {
            System.out.println("Would you like to 'buy' or 'sell'?");
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

        } else if (playerChoice.equals("2")) {
            System.out.println("Would you like to rest? (30 coins) 'y' or 'n'");
            System.out.print("> ");
            String userResponse = scanner.next();
            while (!userResponse.equals("y") && !userResponse.equals("n")) {
                System.out.println("Invalid Selection");
                System.out.print("> ");
                userResponse = scanner.next();
            }
            if (userResponse.equals("y")) {
                if (player.getInventory().get(0).getValue() >= 30) {
                    player.getInventory().get(0).setValue(player.getInventory().get(0).getValue() - 30);
                    this.getInventory().get(0).setValue(this.getInventory().get(0).getValue() + 30);
                    this.rest(player);
                } else {
                    System.out.println("You don't have enough coins.");
                }
            } else if (userResponse.equals("n")) {
                System.out.println("Is there something else you would like?");
                this.talk(player);
            }
        } else if (playerChoice.equals("3")) {
            super.gossip();
        } else {
            System.out.println("Goodbye for now.");
        }
    }

    public void rest(Player player) {

        player.setHealth(player.getMaxHealth());
        player.setMana(player.getMaxMana());
        System.out.println("Your health and mana have been restored.");
        System.out.println(player.getName() + " health: " + player.getHealth() + "   mana: " + player.getMana());
        System.out.println("30 coins are deducted from your coin pouch.");
    }

}

class Gambler extends NPC {
    private boolean playerIsGambling;

    public Gambler() {
        this.setName("Gambler");
        Coins coins = new Coins("coins", 1000);
        this.addItemToInventory(coins);
        this.playerIsGambling = false;
    }

    public boolean getPlayerIsGambling() {
        return playerIsGambling;
    }

    public void setPlayerIsGambling(boolean playerIsGambling) {
        this.playerIsGambling = playerIsGambling;
    }

    public void gossip(Player player) {
    }

    public void talk(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Gamble");
        System.out.println("2. Gossip");
        System.out.println("3. Leave");
        System.out.print("> ");
        String playerResponse = scanner.next();
        while (!playerResponse.equals("1") && !playerResponse.equals("2") && !playerResponse.equals("3")) {
            System.out.println("Invalid Selection");
            System.out.print("> ");
            playerResponse = scanner.next();
        }
        if (playerResponse.equals("1")) {
            this.gamble(player);
        } else if (playerResponse.equals("2")) {
            this.gossip(player);
        } else {
            System.out.println("Player leaves.");
        }
    }

    public void gamble(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which game would you like to play?");
        System.out.println("1. Flames Bolts Icicles");
        System.out.println("2. Summa");
        System.out.println("3. Leave");
        System.out.print("> ");
        String playersGameSelection = scanner.next();
        while (!playersGameSelection.equals("1") && !playersGameSelection.equals("2") && !playersGameSelection.equals("3")) {
            System.out.println("Invalid Selection");
            System.out.print("> ");
            playersGameSelection = scanner.next();
        }
        this.setPlayerIsGambling(true);

        while (this.getPlayerIsGambling() && playersGameSelection.equals("1")) { // Flames Bolts Icicles
            int wager = 0;
            int playerWins = 0;
            int gamblerWins = 0;
            int round = 1;
            System.out.println("Rules of the game:");
            System.out.println("Flames beats Icicles, Bolts beats Flames, Icicles beats Bolts");
            System.out.println("Best of 3 wins.");
            while (playerWins != 3 && gamblerWins != 3) {

                System.out.println("How much would you like to wager?");
                System.out.print("> ");
                boolean inputAccepted = false;
                while (!inputAccepted) {
                    try {
                        wager = Integer.parseInt(scanner.next());
                        inputAccepted = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Selection.");
                        System.out.print("> ");
                    }
                }
                while (wager > player.getInventory().get(0).getValue()) {
                    System.out.println("You do not have enough coins.");
                    System.out.println("How much would you like to wager?");
                    System.out.print("> ");
                    boolean wagerAccepted = false;
                    while (!wagerAccepted) {
                        try {
                            wager = Integer.parseInt(scanner.next());
                            wagerAccepted = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid Selection.");
                            System.out.print("> ");
                        }
                    }
                }
                System.out.println("You wager: " + wager);
                player.getInventory().get(0).setValue(player.getInventory().get(0).getValue() - wager);
                System.out.println("Your remaining coins: " + player.getInventory().get(0).getValue());

                while (playerWins != 3 && gamblerWins != 3) {
                    System.out.println(player.getName() + " wins: " + playerWins + "\tGambler wins: " + gamblerWins);
                    String gameResult = "";
                    System.out.println("Round " + round);
                    System.out.println("1. Flames ");
                    System.out.println("2. Bolts");
                    System.out.println("3. Icicles");
                    System.out.print("> ");
                    String playerChoice = scanner.next();
                    while (!playerChoice.equals("1") && !playerChoice.equals("2") && !playerChoice.equals("3")) {
                        System.out.println("Invalid Selection");
                        System.out.print("> ");
                        playerChoice = scanner.next();
                    }
                    int playersChoice = Integer.parseInt(playerChoice);
                    if (playersChoice == 1) {
                        gameResult += "Player chose: Flames\n";
                    } else if (playersChoice == 2) {
                        gameResult += "Player chose: Bolts\n";
                    } else {
                        gameResult += "Player chose: Icicles\n";
                    }
                    // 1 = flames, 2 = bolts, 3 = icicles
                    int gamblersChoice = (int) (Math.random() * 3) + 1;
                    if (gamblersChoice == 1) {
                        gameResult += "Gambler chose: Flames";
                    } else if (gamblersChoice == 2) {
                        gameResult += "Gambler chose: Bolts";
                    } else {
                        gameResult += "Gambler chose: Icicles"; // will reset gameresult string after round is over
                    }
                    if (playersChoice == gamblersChoice) {
                        System.out.println("Tied.");
                        System.out.println(gameResult);
                        round++;
                    } else if (playersChoice == 1) {
                        if (gamblersChoice == 3) {
                            playerWins++;
                        } else if (gamblersChoice == 2) {
                            gamblerWins++;
                        }
                        System.out.println(gameResult);
                        round++;
                    } else if (playersChoice == 2) {
                        if (gamblersChoice == 1) {
                            playerWins++;
                        } else if (gamblersChoice == 3) {
                            gamblerWins++;
                        }
                        System.out.println(gameResult);
                        round++;
                    } else if (playersChoice == 3) {
                        if (gamblersChoice == 2) {
                            playerWins++;
                        } else if (gamblersChoice == 1) {
                            gamblerWins++;
                        }
                        System.out.println(gameResult);
                        round++;
                    }
                }
                if (playerWins == 3) {
                    System.out.println(player.getName() + " wins: " + playerWins + "\tGambler wins: " + gamblerWins);
                    wager *= 2;
                    System.out.println("You've won: " + wager + " coins!"); // double the wager and put it into inventory
                    player.getInventory().get(0).setValue(player.getInventory().get(0).getValue() + wager);
                } else {
                    System.out.println(player.getName() + " wins: " + playerWins + "\tGambler wins: " + gamblerWins);
                    System.out.println("You've lost: " + wager + " coins.."); // double the wager and put it into inventory
                }
            }


            System.out.println("Would you like to quit? 'y' or 'n'");
            System.out.print("> ");
            String playerResponse = scanner.next();
            while (!playerResponse.equals("y") && !playerResponse.equals("n")) {
                System.out.println("Invalid Selection");
                System.out.print("> ");
                playerResponse = scanner.next();
            }
            if (playerResponse.equals("y")) {
                this.setPlayerIsGambling(false);
            } else {
                gamble(player);
            }
        }
        while (this.getPlayerIsGambling() && playersGameSelection.equals("2")) { // Summa
            System.out.println("Cool");
            System.out.println("Would you like to quit?");
            System.out.print("> ");
            String playerResponse = scanner.next();
            if (playerResponse.equals("y")) {
                this.setPlayerIsGambling(false);
            }
        }
        if (playersGameSelection.equals("3")) { // Leave
            this.setPlayerIsGambling(false);
        } else {

        }
    }
}

class Drunk extends NPC {
    private boolean dwarfQuestStarted;
    private boolean dwarfQuestCompleted;

    public Drunk() {
        this.setName("Drunk");
        this.dwarfQuestStarted = false;
        this.dwarfQuestCompleted = false;
    }

    public boolean isDwarfQuestStarted() {
        return dwarfQuestStarted;
    }

    public void setDwarfQuestStarted(boolean dwarfQuestStarted) {
        this.dwarfQuestStarted = dwarfQuestStarted;
    }

    public boolean isDwarfQuestCompleted() {
        return dwarfQuestCompleted;
    }

    public void setDwarfQuestCompleted(boolean dwarfQuestCompleted) {
        this.dwarfQuestCompleted = dwarfQuestCompleted;
    }

    public void talk(Player player) {
        Scanner scanner = new Scanner(System.in);
        if (player.getRace().equals("dwarf") && !dwarfQuestStarted && !dwarfQuestCompleted) {
            System.out.println("Buy me a beer, dwarf!");
            dwarfQuestStarted = true;


        } else if (player.getRace().equals("dwarf") && dwarfQuestStarted) {
            System.out.println("Have you bought me my beer? 'y' or 'n'");
            System.out.print("> ");
            String playerResponse = scanner.next();
            while (!playerResponse.equals("y") && !playerResponse.equals("n")) {
                System.out.println("Invalid Response");
                System.out.print("> ");
                playerResponse = scanner.next();
            }
            boolean playerHasBeer = false;
            boolean playerHasAle = false;
            int beerIndex = 0;
            int aleIndex = 0;
            if (playerResponse.equals("y")) {
                for (int i = 0; i < player.getInventory().size(); i++) {
                    if (player.getInventory().get(i).getName().equals("beer")) {
                        beerIndex = i;
                        playerHasBeer = true;
                    }
                    if (player.getInventory().get(i).getName().equals("ale")) {
                        aleIndex = i;
                        playerHasAle = true;
                    }
                }
                if (playerHasAle && playerHasBeer) {
                    System.out.println("Thanks! And you brought me an ale, how nice.");
                    Item tradedBeer = player.getInventory().get(beerIndex);
                    Item tradedAle = player.getInventory().get(aleIndex);
                    this.addItemToInventory(tradedBeer);
                    this.addItemToInventory(tradedAle);
                    player.getInventory().remove(beerIndex);
                    aleIndex--;
                    player.getInventory().remove(aleIndex);
                    dwarfQuestCompleted = true;
                } else if (playerHasAle && !playerHasBeer) {
                    System.out.println("I said get me a beer, dwarf! Not an ale!\nI'll still take the ale though.");
                    this.addItemToInventory(player.getInventory().get(aleIndex));
                    player.getInventory().remove(aleIndex);
                } else if (playerHasBeer && !playerHasAle) {
                    System.out.println("Thanks for the beer, dwarf.");
                    this.addItemToInventory(player.getInventory().get(beerIndex));
                    player.getInventory().remove(beerIndex);
                    dwarfQuestCompleted = true;
                } else {
                    System.out.println("I thought you had my beer, you liar!");
                }

            } else if (playerResponse.equals("n")) {
                System.out.println("I said get me a beer, dwarf!");
            }


        } else if (player.getRace().equals("dwarf") && dwarfQuestCompleted) {
            System.out.println("This is my last possession. You may have it, dwarf.");
            Weapon drunksWeapon = new Weapon("drunken sword", 150); // can create this is constructor
            System.out.println("You received " + drunksWeapon.getName());
            player.addItemToInventory(drunksWeapon);


        } else if (player.getRace().equals("elf") || player.getRace().equals("human")) {
            System.out.println("Care to buy me a drink, " + player.getRace() + "? 'y' or 'n'");
            String playerResponse = scanner.next();
            while (!playerResponse.equals("y") && !playerResponse.equals("n")) {
                System.out.println("Invalid Response");
                System.out.print("> ");
                playerResponse = scanner.next();
            }
            boolean playerHasBeer = false;
            boolean playerHasAle = false;
            int beerIndex = 0;
            int aleIndex = 0;
            if (playerResponse.equals("y")) {
                for (int i = 0; i < player.getInventory().size(); i++) {
                    if (player.getInventory().get(i).getName().equals("beer")) {
                        beerIndex = i;
                        playerHasBeer = true;
                    }
                    if (player.getInventory().get(i).getName().equals("ale")) {
                        aleIndex = i;
                        playerHasAle = true;
                    }
                }
                if (playerHasAle && playerHasBeer) {
                    System.out.println("Thanks! And you brought me an ale, how nice.");
                    Item tradedBeer = player.getInventory().get(beerIndex);
                    Item tradedAle = player.getInventory().get(aleIndex);
                    this.addItemToInventory(tradedBeer);
                    this.addItemToInventory(tradedAle);
                    player.getInventory().remove(beerIndex);
                    aleIndex--;
                    player.getInventory().remove(aleIndex);
                } else if (playerHasAle && !playerHasBeer) {
                    System.out.println("I prefer beer, " + player.getRace() + ".\nI'll still take the ale though.");
                    this.addItemToInventory(player.getInventory().get(aleIndex));
                    player.getInventory().remove(aleIndex);
                } else if (playerHasBeer && !playerHasAle) {
                    System.out.println("Thanks for the beer, " + player.getRace() + ".");
                    this.addItemToInventory(player.getInventory().get(beerIndex));
                    player.getInventory().remove(beerIndex);
                } else {
                    System.out.println("I thought you had my drink..");
                }

            } else if (playerResponse.equals("n")) {
                System.out.println("Maybe some other time then.");
            }

        }
    }

}


