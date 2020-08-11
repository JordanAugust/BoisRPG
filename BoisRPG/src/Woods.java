import java.util.ArrayList;
import java.util.Scanner;
// Woods will have a BanditCamp, SpidersCave, etc.. that will be private BanditCamp banditCamp, VillageInTheWoods = which has people in it

public class Woods extends Location {
    private ArrayList<Enemy> enemies;

    public Woods(String locationName) {
        super(locationName);
    }
}

class BanditCamp extends Location {
    private ArrayList<Bandit> bandits;
    private ArrayList<Bandit> gateBandits;
    private BanditKing banditKing;
    private BanditCampRoute banditCampRoute;
    private boolean playerChoseToComply;

    public BanditCamp(String locationName) {
        super(locationName);
        this.gateBandits = new ArrayList<>();
        Bandit gateBandit1 = new Bandit();
        Bandit gateBandit2 = new Bandit();
        this.gateBandits.add(gateBandit1);
        this.gateBandits.add(gateBandit2);
        this.banditKing = new BanditKing();
    }

    public ArrayList<Bandit> getBandits() {
        return bandits;
    }

    public void setBandits(ArrayList<Bandit> bandits) {
        this.bandits = bandits;
    }

    public ArrayList<Bandit> getGateBandits() {
        return gateBandits;
    }

    public void setGateBandits(ArrayList<Bandit> gateBandits) {
        this.gateBandits = gateBandits;
    }

    public BanditKing getBanditKing() {
        return banditKing;
    }

    public void setBanditKing(BanditKing banditKing) {
        this.banditKing = banditKing;
    }

    public BanditCampRoute getBanditCampRoute() {
        return banditCampRoute;
    }

    public void setBanditCampRoute(BanditCampRoute banditCampRoute) {
        this.banditCampRoute = banditCampRoute;
    }

    public boolean getPlayerChoseToComply() {
        return playerChoseToComply;
    }

    public void setPlayerChoseToComply(boolean playerChoseToComply) {
        this.playerChoseToComply = playerChoseToComply;
    }


    public void banditCampGate(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You approach a large wooden gate, operated by a crank. Guarded by two ne'erdowell bandits,");
        System.out.println("They scowl and sneer as you encroach closer and closer to the gate.");
        System.out.println("Atop the gate, beside the bandits, you see an ominous flag that warns 'Bandit King Territory'");
        System.out.println("'Oi! Piss off, " + player.getRace() + "!' Ye'd be wise to come no further.");
        System.out.println("Comply? 'y' or 'n'");
        System.out.print("> ");
        String playerResponse = scanner.next();
        while (!playerResponse.equals("y") && !playerResponse.equals("n")) {
            System.out.println("Invalid Selection");
            System.out.print("> ");
            playerResponse = scanner.next();
        }
        if (playerResponse.equals("y")) {
            System.out.println("You scurry back into the woods..");
            setPlayerChoseToComply(true);
        } else if (playerResponse.equals("n")) {
            setPlayerChoseToComply(false);
            System.out.println("After opening the gate, the bandits rush toward and attack you!\n");
        }

    }
}

class BanditCampRoute extends BanditCamp {
    private ArrayList<Bandit> tunnelBandits;
    private ArrayList<Bandit> hillBandits;

    public BanditCampRoute(String routeName) {
        super(routeName);
        this.tunnelBandits = new ArrayList<>();
        this.hillBandits = new ArrayList<>();
    }

    public ArrayList<Bandit> getTunnelBandits() {
        return tunnelBandits;
    }

    public ArrayList<Bandit> getHillBandits() {
        return hillBandits;
    }

    public void tunnelRoute(Player player) { // 5 enemies. have clues on which route to take. maybe dwarf can say something about taking the hill route not the tunnel route
        System.out.println("A foul stench permeates your nostrils.");
        System.out.println("This place wreaks of sulfur and rotted carcasses.");
        System.out.println("Several bandits are alerted of your presence!\n");
        Bandit tunnelBandit1 = new Bandit();
        Bandit tunnelBandit2 = new Bandit();
        Bandit tunnelBandit3 = new Bandit();
        Bandit tunnelBandit4 = new Bandit();
        Bandit tunnelBandit5 = new Bandit();
        this.tunnelBandits.add(tunnelBandit1);
        this.tunnelBandits.add(tunnelBandit2);
        this.tunnelBandits.add(tunnelBandit3);
        this.tunnelBandits.add(tunnelBandit4);
        this.tunnelBandits.add(tunnelBandit5);
        for (Bandit bandit : this.tunnelBandits) {
            bandit.setName("Tunnel Bandit");
        }

    }

    public void hillRoute(Player player) { // 3 enemies
        System.out.println("A less foolish choice; you reach the top of the hill.");
        System.out.println("Some bandits spot you and begin their uphill assault!\n");
        Bandit hillBandit1 = new Bandit();
        Bandit hillBandit2 = new Bandit();
        Bandit hillBandit3 = new Bandit();
        this.hillBandits.add(hillBandit1);
        this.hillBandits.add(hillBandit2);
        this.hillBandits.add(hillBandit3);
        for (Bandit bandit : this.hillBandits) {
            bandit.setName("Hill Bandit");
        }

    }
}

class Bandit extends Enemy {

    public Bandit() {
        this.setAttack(5);
        this.setHealth(50);
        this.setDefense(1.5);
        this.setMagicDefense(1.5);
        this.setResistance("melee");
        this.setWeakness("fire");
        this.setXp(15);
        this.setName("Bandit");
        Coins coins = new Coins("coins", 20);
        this.setCoins(coins);
        this.addItemToInventory(coins);
    }
}

class BanditKing extends Bandit {

    public BanditKing() {
        this.setAttack(15);
        this.setHealth(100);
        this.setDefense(1.5);
        this.setMagicDefense(1.5);
        this.setResistance("fire");
        this.setWeakness("lightning");
        this.setXp(50);
        this.setName("Bandit King");
        Coins coins = new Coins("coins", 200);
        this.setCoins(coins);
        this.addItemToInventory(coins);
    }
}

class SpiderCave extends Location { // will need to use spawnEnemy()
    private ArrayList<Spider> spidersR2;
    private ArrayList<Spider> spidersL2;
    private ArrayList<BabySpider> babySpidersL1;
    private boolean playerOpenedChest;

    public SpiderCave(String locationName) {
        super(locationName);
        this.spidersR2 = new ArrayList<>();
        this.spidersL2 = new ArrayList<>();
        this.babySpidersL1 = new ArrayList<>();
        playerOpenedChest = false;
    }

    public boolean isPlayerOpenedChest() {
        return playerOpenedChest;
    }

    public void setPlayerOpenedChest(boolean playerOpenedChest) {
        this.playerOpenedChest = playerOpenedChest;
    }

    public void spiderCave(Player player) {
        Spider spider1 = new Spider();
        Spider spider2 = new Spider();
        Spider spider3 = new Spider();
        Spider spider4 = new Spider();
        Spider spider5 = new Spider();
        Spider spider6 = new Spider();
        spidersL2.add(spider1);
        spidersL2.add(spider2);
        spidersL2.add(spider3);
        spidersR2.add(spider4);
        spidersR2.add(spider5);
        spidersR2.add(spider6);
        BabySpider babySpider1 = new BabySpider();
        BabySpider babySpider2 = new BabySpider();
        BabySpider babySpider3 = new BabySpider();
        BabySpider babySpider4 = new BabySpider();
        BabySpider babySpider5 = new BabySpider();
        babySpidersL1.add(babySpider1);
        babySpidersL1.add(babySpider2);
        babySpidersL1.add(babySpider3);
        babySpidersL1.add(babySpider4);
        babySpidersL1.add(babySpider5);
        Item chest = new Item("chest", 100);
        Scanner scanner = new Scanner(System.in);
        
        boolean playerIsInSpiderCave = true;
        while (playerIsInSpiderCave) {
            System.out.println("Spider cave entrance dialogue.");
            System.out.println("You see two paths which way do you want to go?");
            System.out.println("1. Left");
            System.out.println("2. Right");
            System.out.println("3. Leave Spider Cave.");
            System.out.print("> ");
            String playerChoice = scanner.next();
            while (!playerChoice.equals("1") && !playerChoice.equals("2") && !playerChoice.equals("3")) {
                System.out.println("Invalid Selection");
                System.out.print("> ");
                playerChoice = scanner.next();
            }
            if (playerChoice.equals("1")) {
                boolean playerIsOnLeftPath = true;
                while (playerIsOnLeftPath) {
                    System.out.println("You see two paths which way do you want to go?");
                    System.out.println("1. Left");
                    System.out.println("2. Right");
                    System.out.println("3. Go Back.");
                    System.out.print("> ");
                    playerChoice = scanner.next();
                    while (!playerChoice.equals("1") && !playerChoice.equals("2") && !playerChoice.equals("3")) {
                        System.out.println("Invalid Selection");
                        System.out.print("> ");
                        playerChoice = scanner.next();
                    }
                    if (playerChoice.equals("1")) {
                        System.out.println("Player is in L1");
                        boolean playerIsInL1 = true;
                        while (playerIsInL1) {
                            if (babySpidersL1.size() > 0) {
                                for (int i = 0; i < babySpidersL1.size(); i++) {
                                    combatSequence(player, babySpidersL1.get(i));
                                    babySpidersL1.remove(i);
                                    i--;
                                }
                                System.out.println("You've reached the end of this path. Turn around.");
                            }
                            playerIsInL1 = false;
                        }
                    } else if (playerChoice.equals("2")) {
                        System.out.println("Player is in L2");
                        boolean playerIsInL2 = true;
                        while (playerIsInL2) {
                            if (spidersL2.size() > 0) {
                                for (int i = 0; i < spidersL2.size(); i++) {
                                    combatSequence(player, spidersL2.get(i));
                                    spidersL2.remove(i);
                                    i--;
                                }
                                System.out.println("You've reached the end of this path. Turn around.");
                            }
                            playerIsInL2 = false;
                        }
                    } else {
                        playerIsOnLeftPath = false;
                    }
                }
            } else if (playerChoice.equals("2")) {
                boolean playerIsOnRightPath = true;
                while (playerIsOnRightPath) {
                    System.out.println("You see two paths which way do you want to go?");
                    System.out.println("1. Left");
                    System.out.println("2. Right");
                    System.out.println("3. Go Back.");
                    System.out.print("> ");
                    playerChoice = scanner.next();
                    while (!playerChoice.equals("1") && !playerChoice.equals("2") && !playerChoice.equals("3")) {
                        System.out.println("Invalid Selection");
                        System.out.print("> ");
                        playerChoice = scanner.next();
                    }
                    if (playerChoice.equals("1")) {
                        System.out.println("Player is in R1");
                        boolean playerIsInR1 = true;
                        while (playerIsInR1) {
                            if (!isPlayerOpenedChest()) {
                                System.out.println("You find a chest.");
                                setPlayerOpenedChest(true);
                                // will figure out later
                            }
                            System.out.println("Proceed or go back.");
                            System.out.println("1. Proceed");
                            System.out.println("2. Go Back.");
                            System.out.print("> ");
                            playerChoice = scanner.next();
                            while (!playerChoice.equals("1") && !playerChoice.equals("2")) {
                                System.out.println("Invalid Selection");
                                System.out.print("> ");
                                playerChoice = scanner.next();
                            }
                            if (playerChoice.equals("1")) {
                                System.out.println("Spider queen nest");
                                if (!player.getStory().getSpiderQueenQuest()) {
                                    System.out.println("Fighting spider queen.");
                                    SpiderQueen spiderQueen = new SpiderQueen();
                                    combatSequence(player, spiderQueen);
                                    player.getStory().setSpiderQueenQuest(true);
                                } else {
                                    System.out.println("you have already defeated the spider queen.");
                                }
                            } else {
                                playerIsInR1 = false;
                            }
                        }
                    } else if (playerChoice.equals("2")) {
                        System.out.println("Player is in R2");
                        boolean playerIsInR2 = true;
                        while (playerIsInR2) {
                            if (spidersR2.size() > 0) {
                                for (int i = 0; i < spidersR2.size(); i++) {
                                    combatSequence(player, spidersR2.get(i));
                                    spidersR2.remove(i);
                                    i--;
                                }
                                System.out.println("You've reached the end of this path. Turn around.");
                            }
                            playerIsInR2 = false;
                        }
                    } else {
                        playerIsOnRightPath = false;
                    }
                }
            } else {
                playerIsInSpiderCave = false;
            }
        }
    }
}

class Spider extends Enemy {

    public Spider() {
        this.setAttack(7);
        this.setHealth(30);
        this.setDefense(1.5);
        this.setMagicDefense(1.5);
        this.setResistance("melee");
        this.setWeakness("fire");
        this.setXp(30);
        this.setName("Spider");
        Coins coins = new Coins("coins", 50);
        this.setCoins(coins);
        this.addItemToInventory(coins);
    }
}

class BabySpider extends Spider {

    public BabySpider() {
        this.setAttack(3);
        this.setHealth(10);
        this.setDefense(1.5);
        this.setMagicDefense(1.5);
        this.setResistance("melee");
        this.setWeakness("fire");
        this.setXp(10);
        this.setName("Baby Spider");
        Coins coins = new Coins("coins", 50);
        this.setCoins(coins);
        this.addItemToInventory(coins);
    }

}

class SpiderQueen extends Spider {

    public SpiderQueen() {
        this.setAttack(20);
        this.setHealth(100);
        this.setDefense(1.5);
        this.setMagicDefense(1.5);
        this.setResistance("fire");
        this.setWeakness("lightning");
        this.setXp(100);
        this.setName("Spider Queen");
        Coins coins = new Coins("coins", 200);
        this.setCoins(coins);
        this.addItemToInventory(coins);
    }

}

class TheTreeHouse extends Location {
    private ArrayList<NPC> people = new ArrayList<>();
    private TreeHouseInnKeeper treeHouseInnKeeper = new TreeHouseInnKeeper();
    private WoodElf woodElf = new WoodElf();
    private BanditTraitor banditTraitor = new BanditTraitor();


    public TheTreeHouse(String locationName) {
        super(locationName);
        this.people.add(this.treeHouseInnKeeper);
        this.people.add(this.woodElf);
        this.people.add(this.banditTraitor);

    }

    public void theTreeHouse(Player player) {
        Scanner scanner = new Scanner(System.in);

        boolean playerIsInTheTreeHouse = true;
        while (playerIsInTheTreeHouse) {
            System.out.println("Who would you like to talk to?");
            System.out.println("1. The Tree House Innkeeper");
            System.out.println("2. Wood Elf");
            System.out.println("3. Bandit Traitor");
            System.out.println("4. Leave The Tree House");
            System.out.print("> ");
            String playerChoice = scanner.next();
            while (!playerChoice.equals("1") && !playerChoice.equals("2") && !playerChoice.equals("3") && !playerChoice.equals("4")) {
                System.out.println("Invalid Selection");
                System.out.print("> ");
                playerChoice = scanner.next();
            }
            if (playerChoice.equals("1")) {
                treeHouseInnKeeper.talk(player);
            } else if (playerChoice.equals("2")) {
                woodElf.talk(player);
            } else if (playerChoice.equals("3")) {
                banditTraitor.talk(player);
            } else {
                playerIsInTheTreeHouse = false;
            }
        }
    }

    @Override
    public ArrayList<NPC> getPeople() {
        return people;
    }

    @Override
    public void setPeople(ArrayList<NPC> people) {
        this.people = people;
    }

    public TreeHouseInnKeeper getTreeHouseInnKeeper() {
        return treeHouseInnKeeper;
    }

}

class TreeHouseInnKeeper extends NPC { // Cost money to rest, sell drinks, player needs to access inventory

    public TreeHouseInnKeeper() {
        this.setName("Tree House Inn Keeper");
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

class WoodElf extends NPC {
    // can sell health potions and mana potions, sells wood and steel items, part of elf quest
    private boolean playerBoughtElfQuestItem;
    private Item elfQuestItem;

    public WoodElf() {
        this.setName("Wood Elf");
        this.setPlayerBoughtElfQuestItem(false);
        this.elfQuestItem = new Item("Elf Quest Item", 1);
    }

    public boolean getPlayerBoughtElfQuestItem() {
        return playerBoughtElfQuestItem;
    }

    public void setPlayerBoughtElfQuestItem(boolean playerBoughtElfQuestItem) {
        this.playerBoughtElfQuestItem = playerBoughtElfQuestItem;
    }

    public Item getElfQuestItem() {
        return elfQuestItem;
    }

    public void setElfQuestItem(Item elfQuestItem) {
        this.elfQuestItem = elfQuestItem;
    }

    public void populateInventory(Player player) {
        if (player.getLevel() <= 2) {
            Coins coins = new Coins("coins", 1000);
            this.addItemToInventory(coins);
            Potion lesserManaPotion = new Potion("mana potion", 15, 5);
            this.addItemToInventory(lesserManaPotion);
            Potion lesserHealthPotion = new Potion("health potion", 15, 20);
            this.addItemToInventory(lesserHealthPotion);

        } else if (player.getLevel() > 2) {
            for (int i = 0; i < this.getInventory().size(); i++) {
                this.getInventory().remove(i);
                i--;
            }
            Coins coins = new Coins("coins", 1000);
            this.addItemToInventory(coins);
            if (player.getRace().equals("elf") && !this.getPlayerBoughtElfQuestItem()) {
                this.addItemToInventory(this.getElfQuestItem());
            }
            Potion greaterHealthPotion = new Potion("greater health potion", 75, 100);
            Potion greaterManaPotion = new Potion("greater mana potion", 75, 15);
            this.addItemToInventory(greaterHealthPotion);
            this.addItemToInventory(greaterManaPotion);

        }
    }

    public void talk(Player player) {
        this.populateInventory(player);
        Scanner scanner = new Scanner(System.in);
        // we can give player option to return to town or not, but we are not going to right now
        System.out.println("Have a look at my wares.");
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
                System.out.println(this.getName() + " coins: " + this.getInventory().get(0).getValue());
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
                    if (boughtItem.getName().equals("Elf Quest Item")) {
                        this.setPlayerBoughtElfQuestItem(true);
                    }
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
                System.out.println(this.getName() + " coins: " + this.getInventory().get(0).getValue());
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
                        System.out.println(this.getName() + " does not have enough coins to buy that.");
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
    } // end of talk()
}

class BanditTraitor extends NPC {
    // will offer gambling and hint about bandit camp route
    private boolean playerIsGambling;
    public BanditTraitor() {
        this.setName("Bandit Traitor");
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

    public void gossip(Player player) {
        System.out.println("Take the hill route at the bandit camp.");
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
