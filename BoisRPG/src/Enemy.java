import java.util.ArrayList;

public class Enemy {

    private int health;
    private int attack;
    private String attackType;
    private String name;
    private boolean alive;
    private ArrayList<Item> inventory;

    public Enemy() {
        this.inventory = new ArrayList<Item>();
    }
}

