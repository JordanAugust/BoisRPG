import java.util.ArrayList;
import java.util.HashMap;

public class Story {
    private ArrayList<String> areas;
    private Player player;
    private int playerChoice;
    private HashMap<String, String> playersChoices;
    private boolean elfQuest;
    private boolean dwarfQuest;
    private boolean humanQuest;
    private boolean banditKingQuest;
    private boolean banditKingKilled;
    private boolean banditKingSpared;
    private boolean spiderQueenQuest;

    public Story(Player player) {
        this.player = player;
        this.areas = new ArrayList<>();


    }

    public Story() {
        super();
    }

    public ArrayList<String> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<String> areas) {
        this.areas = areas;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean getElfQuest() {
        return elfQuest;
    }

    public void setElfQuest(boolean elfQuest) {
        this.elfQuest = elfQuest;
    }

    public boolean getDwarfQuest() {
        return dwarfQuest;
    }

    public void setDwarfQuest(boolean dwarfQuest) {
        this.dwarfQuest = dwarfQuest;
    }

    public boolean getHumanQuest() {
        return humanQuest;
    }

    public void setHumanQuest(boolean humanQuest) {
        this.humanQuest = humanQuest;
    }

    public boolean getBanditKingQuest() {
        return banditKingQuest;
    }

    public void setBanditKingQuest(boolean banditKingQuest) {
        this.banditKingQuest = banditKingQuest;
    }

    public boolean getBanditKingKilled() {
        return banditKingKilled;
    }

    public void setBanditKingKilled(boolean banditKingKilled) {
        this.banditKingKilled = banditKingKilled;
    }

    public boolean isBanditKingSpared() {
        return banditKingSpared;
    }

    public void setBanditKingSpared(boolean banditKingSpared) {
        this.banditKingSpared = banditKingSpared;
    }

    public boolean getSpiderQueenQuest() {
        return spiderQueenQuest;
    }

    public void setSpiderQueenQuest(boolean spiderQueenQuest) {
        this.spiderQueenQuest = spiderQueenQuest;
    }

    public void openingScene() {
        if (this.player.getRace().equals("elf")) {
            System.out.println("Elf's opening story");
            System.out.println("Flipping through the shellac disks at Arcadium's Arcana, a muzikk and oddities emporium, ");
            System.out.println("You think about the recent surge in magic created sound and its inferiority to");
            System.out.println("The analog contemporary of the electric oud and bouzaki. Spotting what you've been after, ");
            System.out.println("You remove from your ears your inserted muzikk amplification device, (MAD), used to heighten the eardrum's sensitivity.");
            System.out.println("'The Sheiksters', your favorite band, just released their newest disc, 'Thoughts and Such'.");
            System.out.println("As you attempt to acquire this physical necessity, Arcadium informs you of its price.");
            System.out.println("Blinking dramatically, you are stunned that he doesn't realize what this means to you.");
            System.out.println("Scoffing, you reach in your pocket only to have your fingers greeted by the empty space");
            System.out.println("Where coins once clanked.");
            System.out.println("An inner monologue ensues, 'At some point, whether we like it or not, we all have to make money'");
            System.out.println("You step outside of Arcadium's and wonder over what to do.");

        } else if (this.player.getRace().equals("human")) { //will need to check if pran has received human's payment, otherwise, kick him out of the building
            System.out.println("Lifting a large stone inside Pran Training Hall, you set the stone back down.");
            System.out.println("''You're getting stronger,'' an old raspy voice says.");
            System.out.println("It's Pran, the owner of the Training Hall, ''what could he want this time?''");
            System.out.println("''You haven't paid your monthly dues, " + player.getName() + ", and I know you've been");
            System.out.println("Eating just fine,'' with raised eyebrows, Pran eyeballs your stomach.");
            System.out.println("Sighing, you know he's right. Before continuing to train, you agree to pay");
            System.out.println("Pran 50 coins. ''Don't come back 'till you have my money..'");
            System.out.println("You leave the Training Hall and venture out into the harsh, often unforgiving, town.");

        } else if (this.player.getRace().equals("dwarf")) {
            System.out.println("You look down at your empty mug, eyeing for any residual remnants of fermented barley");
            System.out.println("Sighing, you reluctantly reach for your coin purse, only to find your fingers following the cross stitching of the bottom of the pouch");
            System.out.println("* further exhaling *");
            System.out.println("An inner monologue ensues, 'At some point, whether we like it or not, we all have to make money'");
            System.out.println("You unenthusiastically push against the table, though slightly dramatically, as you arise from your all too comfortable seat, seemingly crafted just for you");
            System.out.println("You venture from the warm caress of the Town's inn, the Croaking Toad, to try your hand in adventuring..");

        }
    }
}
