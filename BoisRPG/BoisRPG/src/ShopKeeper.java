import java.util.Scanner;

public class ShopKeeper extends NPC {

    public void talk() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("I'm the shopkeeper.");
        System.out.println("Would you like to 'buy' or 'sell'");
        System.out.print("> ");
        String userResponse = scanner.next();
        userResponse = userResponse.toLowerCase();

        if (userResponse.equals("buy")) {
            for (Item item : this.getItems()) {
                System.out.println(item);
            }
        }
    }
}
