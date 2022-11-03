import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

public class God {

    public static void addAdventurer(HashMap<Integer, Adventurer> adventurers, Scanner scanner) {
        Adventurer adventurer = new Adventurer();
        int advId = scanner.nextInt();
        adventurers.put(advId, adventurer);
        adventurer.setId(advId);
        adventurer.setName(scanner.next());


    }

    public static void addEquipment(HashMap<Integer, Adventurer> adventurers, Scanner scanner) {
        Adventurer adventurer = adventurers.get(scanner.nextInt()); // advId
        int equipmentType = scanner.nextInt();
        int equId = scanner.nextInt();

        switch (equipmentType) {
            case 1:
                Bottle bottle = new Bottle();
                adventurer.getEquipments().put(equId, bottle);
                bottle.setEquipment(equId, scanner.next(), scanner.nextLong());
                bottle.setCapacity(scanner.nextDouble());
                bottle.setFilled(true);
                break;
            case 2:
                HealingPotion healingPotion = new HealingPotion();
                adventurer.getEquipments().put(equId, healingPotion);
                healingPotion.setEquipment(equId, scanner.next(), scanner.nextLong());
                healingPotion.setCapacity(scanner.nextDouble());
                healingPotion.setEfficiency(scanner.nextDouble());
                healingPotion.setFilled(true);
                break;
            case 3:
                ExpBottle expBottle = new ExpBottle();
                adventurer.getEquipments().put(equId, expBottle);
                expBottle.setEquipment(equId, scanner.next(), scanner.nextLong());
                expBottle.setCapacity(scanner.nextDouble());
                expBottle.setExpRatio(scanner.nextDouble());
                expBottle.setFilled(true);
                break;
            case 4:
                Sword sword = new Sword();
                adventurer.getEquipments().put(equId, sword);
                sword.setEquipment(equId, scanner.next(), scanner.nextLong());
                sword.setSharpness(scanner.nextDouble());
                break;
            case 5:
                RareSword rareSword = new RareSword();
                adventurer.getEquipments().put(equId, rareSword);
                rareSword.setEquipment(equId, scanner.next(), scanner.nextLong());
                rareSword.setSharpness(scanner.nextDouble());
                rareSword.setExtraExpBonus(scanner.nextDouble());
                break;
            case 6:
                EpicSword epicSword = new EpicSword();
                adventurer.getEquipments().put(equId, epicSword);
                epicSword.setEquipment(equId, scanner.next(), scanner.nextLong());
                epicSword.setSharpness(scanner.nextDouble());
                epicSword.setEvolveRatio(scanner.nextDouble());
                break;
            default:
        }
    }

    public static void removeEquipment(HashMap<Integer, Adventurer> adventurers, Scanner scanner) {
        adventurers.get(scanner.nextInt()).getEquipments().remove(scanner.nextInt());
    }

    public static void inquirePriceTotal(HashMap<Integer, Adventurer> adventurers, Scanner scan) {
        BigInteger priceTotal = BigInteger.ZERO;
        Adventurer adventurer = adventurers.get(scan.nextInt());
        for (Integer i : adventurer.getEquipments().keySet()) {
            long price = adventurer.getEquipments().get(i).getPrice();
            priceTotal = priceTotal.add(BigInteger.valueOf(price));
        }
        System.out.println(priceTotal);
    }

    public static void inquirePriceMax(HashMap<Integer, Adventurer> adventurers, Scanner scanner) {
        long priceMax = 0;
        Adventurer adventurer = adventurers.get(scanner.nextInt());
        for (Integer i : adventurer.getEquipments().keySet()) {
            long price = adventurer.getEquipments().get(i).getPrice();
            priceMax = Math.max(price, priceMax);
        }
        System.out.println(priceMax);
    }

    public static void inquireNumTotal(HashMap<Integer, Adventurer> adventurers, Scanner scanner) {
        int equipmentTotal = 0;
        Adventurer adventurer = adventurers.get(scanner.nextInt());
        for (Integer ignored : adventurer.getEquipments().keySet()) {
            equipmentTotal++;
        }
        System.out.println(equipmentTotal);
    }

    public static void printAttribute(HashMap<Integer, Adventurer> adventurers, Scanner scanner) {
        Adventurer adventurer = adventurers.get(scanner.nextInt());
        System.out.println(adventurer.getEquipments().get(scanner.nextInt()));
    }

    public static void useEquipment(HashMap<Integer, Adventurer> adventurers, Scanner scanner) {
        Adventurer adventurer = adventurers.get(scanner.nextInt());
        Equipment equipment = adventurer.getEquipments().get(scanner.nextInt());
        adventurer.use(equipment);
    }

    public static void printState(HashMap<Integer, Adventurer> adventurers, Scanner scanner) {
        Adventurer adventurer = adventurers.get(scanner.nextInt());
        System.out.println(adventurer);
    }

}
