import java.util.ArrayList;

public class NPC {
    private String name;
    private ArrayList<Item> inventory;

    public NPC() {
        this.inventory = new ArrayList<Item>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public void addItemToInventory(Item item) {
        this.inventory.add(item);
    }

    public void talk(Player player) {
        System.out.println("Parents talk method");
    }

    public void gossip() {
        System.out.println("Gossip of the town..");
    }


}
