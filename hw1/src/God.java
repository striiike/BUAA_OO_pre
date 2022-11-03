import java.util.ArrayList;
import java.util.Scanner;

public class God {
    public static Adventurer getAdv(int advId, ArrayList<Adventurer> adventurers) {
        Adventurer adventurerDefault = new Adventurer();
        for (Adventurer item : adventurers) {
            if (item.getId() == advId) {
                return item;
            }
        }
        return adventurerDefault;
    }

    public static Bottle getBot(int botId, ArrayList<Bottle> bottles) {
        Bottle bottleDefault = new Bottle();
        for (Bottle item : bottles) {
            if (item.getId() == botId) {
                return item;
            }
        }
        return bottleDefault;
    }

    public static void god(int op, ArrayList<Adventurer> adventurers, Scanner scanner) {

        int advId;
        int botId;
        switch (op) {
            case 1:
                Adventurer adventurer = new Adventurer();
                ArrayList<Bottle> bottles = new ArrayList<>();
                adventurer.setAdventurer(scanner.nextInt(), scanner.next(), bottles);
                adventurers.add(adventurer);
                break;
            case 2:
                Bottle bottle = new Bottle();
                adventurer = getAdv(scanner.nextInt(), adventurers);
                int id = scanner.nextInt();
                String name = scanner.next();
                bottle.setBottle(id, name, scanner.nextLong(), scanner.nextDouble(), true);
                adventurer.getBottles().add(bottle);
                break;
            case 3:
                advId = scanner.nextInt();
                botId = scanner.nextInt();
                for (Adventurer item : adventurers) {
                    if (item.getId() == advId) {
                        item.getBottles().removeIf(itemBot -> itemBot.getId() == botId);
                    }
                }
                break;
            case 4:
                advId = scanner.nextInt();
                botId = scanner.nextInt();
                for (Adventurer item : adventurers) {
                    if (item.getId() == advId) {
                        for (Bottle itemBot : item.getBottles()) {
                            if (itemBot.getId() == botId) {
                                itemBot.setPrice(scanner.nextLong());
                            }
                        }
                    }
                }
                break;
            case 5:
                adventurer = getAdv(scanner.nextInt(), adventurers);
                bottle = getBot(scanner.nextInt(), adventurer.getBottles());
                bottle.setFilled(scanner.nextBoolean());
                break;
            case 6:
                adventurer = getAdv(scanner.nextInt(), adventurers);
                bottle = getBot(scanner.nextInt(), adventurer.getBottles());
                System.out.println(bottle.getName());
                break;
            case 7:
                adventurer = getAdv(scanner.nextInt(), adventurers);
                bottle = getBot(scanner.nextInt(), adventurer.getBottles());
                System.out.println(bottle.getPrice());
                break;
            default:
                break;
        }
    }
}
