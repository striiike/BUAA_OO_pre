import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Goddess {
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

    public static void goddess(int op, ArrayList<Adventurer> adventurers, Scanner scanner) {
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
            case 8:
                adventurer = getAdv(scanner.nextInt(), adventurers);
                bottle = getBot(scanner.nextInt(), adventurer.getBottles());
                System.out.println(bottle.getCapacity());
                break;
            case 9:
                adventurer = getAdv(scanner.nextInt(), adventurers);
                bottle = getBot(scanner.nextInt(), adventurer.getBottles());
                System.out.println((bottle.isFilled()) ? "true" : "false");
                break;
            case 10:
                adventurer = getAdv(scanner.nextInt(), adventurers);
                bottle = getBot(scanner.nextInt(), adventurer.getBottles());
                System.out.println(bottle);
                break;
            case 11:
                adventurer = getAdv(scanner.nextInt(), adventurers);
                BigInteger sum = BigInteger.valueOf(0);
                for (Bottle item : adventurer.getBottles()) {
                    sum = sum.add(BigInteger.valueOf(item.getPrice()));
                }
                System.out.println(sum);
                break;
            case 12:
                adventurer = getAdv(scanner.nextInt(), adventurers);
                long maxBottle = 0;
                for (Bottle item : adventurer.getBottles()) {
                    if (item.getPrice() > maxBottle) {
                        maxBottle = item.getPrice();
                    }
                }
                System.out.println(maxBottle);
                break;
            default:
                break;
        }
    }
}
