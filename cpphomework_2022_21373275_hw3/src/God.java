import java.math.BigInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class God {

    public static void addAdventurer(int curId, Scanner scan, HashMap<Integer, Branch> branches) {
        Adventurer adventurer = new Adventurer();
        int advId = scan.nextInt();
        branches.get(curId).getAdventurers().put(advId, adventurer);
        adventurer.setId(advId);
        adventurer.setName(scan.next());
        branches.get(curId).getCommodityHashMap().put(advId, adventurer);
    }

    public static void addEquipment(int curId, Scanner scan, HashMap<Integer, Branch> branches) {
        Adventurer adventurer = branches.get(curId).getAdventurers().get(scan.nextInt()); // advId
        int equipmentType = scan.nextInt();
        int equId = scan.nextInt();
        Branch branch = branches.get(curId);
        switch (equipmentType) {
            case 1:
                Bottle bottle = new Bottle();
                adventurer.getCommodities().put(equId, bottle);
                bottle.setEquipment(equId, scan.next(), scan.nextBigInteger());
                bottle.setCapacity(scan.nextDouble());
                bottle.setFilled(true);
                break;
            case 2:
                HealingPotion healingPotion = new HealingPotion();
                adventurer.getCommodities().put(equId, healingPotion);
                healingPotion.setEquipment(equId, scan.next(), scan.nextBigInteger());
                healingPotion.setCapacity(scan.nextDouble());
                healingPotion.setEfficiency(scan.nextDouble());
                healingPotion.setFilled(true);
                break;
            case 3:
                ExpBottle expBottle = new ExpBottle();
                adventurer.getCommodities().put(equId, expBottle);
                expBottle.setEquipment(equId, scan.next(), scan.nextBigInteger());
                expBottle.setCapacity(scan.nextDouble());
                expBottle.setExpRatio(scan.nextDouble());
                expBottle.setFilled(true);
                break;
            case 4:
                Sword sword = new Sword();
                adventurer.getCommodities().put(equId, sword);
                sword.setEquipment(equId, scan.next(), scan.nextBigInteger());
                sword.setSharpness(scan.nextDouble());
                break;
            case 5:
                RareSword rareSword = new RareSword();
                adventurer.getCommodities().put(equId, rareSword);
                rareSword.setEquipment(equId, scan.next(), scan.nextBigInteger());
                rareSword.setSharpness(scan.nextDouble());
                rareSword.setExtraExpBonus(scan.nextDouble());
                break;
            case 6:
                EpicSword epicSword = new EpicSword();
                adventurer.getCommodities().put(equId, epicSword);
                epicSword.setEquipment(equId, scan.next(), scan.nextBigInteger());
                epicSword.setSharpness(scan.nextDouble());
                epicSword.setEvolveRatio(scan.nextDouble());
                break;
            default:
        }
        branch.getCommodityHashMap().put(equId, adventurer.getCommodities().get(equId));
        branch.getEmployMap()[0][branch.getEmployNum()] = 1;
        branch.getEmployMap()[1][branch.getEmployNum()] = adventurer.getId();
        branch.getEmployMap()[2][branch.getEmployNum()] = equId;
        branch.setEmployNum(branch.getEmployNum() + 1);

    }

    public static void removeEquipment(int curId, Scanner scan, HashMap<Integer, Branch> branches) {
        Adventurer adventurer = branches.get(curId).getAdventurers().get(scan.nextInt());
        int removeId = scan.nextInt();
        Branch branch = branches.get(curId);
        adventurer.getCommodities().remove(removeId);
        adventurer.setCommodities(sortValues(adventurer.getCommodities()));
        for (int i = 0; i < branches.get(curId).getEmployNum(); ++i) {
            if (branch.getEmployMap()[1][i] == adventurer.getId()) {
                if (branch.getEmployMap()[2][i] == removeId) {
                    branch.getEmployMap()[0][i] = 0;
                    branch.getEmployMap()[1][i] = 0;
                    branch.getEmployMap()[2][i] = 0;
                }
            }
        }

    }

    public static void inqPriceTot(int curId, Scanner scan, HashMap<Integer, Branch> branches) {
        BigInteger priceTotal = BigInteger.ZERO;
        Adventurer adventurer = branches.get(curId).getAdventurers().get(scan.nextInt());
        for (Integer i : adventurer.getCommodities().keySet()) {
            BigInteger price = adventurer.getCommodities().get(i).getPrice();
            priceTotal = priceTotal.add(price);
        }

        System.out.println(priceTotal);
    }

    public static void inqPriceMax(int curId, Scanner scan, HashMap<Integer, Branch> branches) {
        BigInteger priceMax = BigInteger.ZERO;
        Adventurer adventurer = branches.get(curId).getAdventurers().get(scan.nextInt());
        for (Integer i : adventurer.getCommodities().keySet()) {
            BigInteger price = adventurer.getCommodities().get(i).getPrice();
            priceMax = priceMax.max(price);
        }
        System.out.println(priceMax);
    }

    public static void inqComTotal(int curId, Scanner scan, HashMap<Integer, Branch> branches) {
        int commodityTotal = 0;
        Adventurer adventurer = branches.get(curId).getAdventurers().get(scan.nextInt());
        for (Integer ignored : adventurer.getCommodities().keySet()) {
            commodityTotal++;
        }
        System.out.println(commodityTotal);
    }

    public static void printAttribute(int curId, Scanner scan, HashMap<Integer, Branch> branches) {
        Adventurer adventurer = branches.get(curId).getAdventurers().get(scan.nextInt());
        System.out.println(adventurer.getCommodities().get(scan.nextInt()));
    }

    public static void useCommodity(int curId, Scanner scanner, HashMap<Integer, Branch> branches) {
        int advId = scanner.nextInt();
        Adventurer adventurer = branches.get(curId).getAdventurers().get(advId);
        adventurer.setCommodities(sortValues(adventurer.getCommodities()));
        Set<Map.Entry<Integer, Commodity>> set = adventurer.getCommodities().entrySet();
        for (Object o : set) {
            Map.Entry map = (Map.Entry) o;
            adventurer.use((Commodity) map.getValue());
        }

    }

    public static void printState(int curId, Scanner scanner, HashMap<Integer, Branch> branches) {
        Adventurer adventurer = branches.get(curId).getAdventurers().get(scanner.nextInt());
        System.out.println(adventurer);
    }

    public static void employAdv(int curId, Scanner scanner, HashMap<Integer, Branch> branches) {
        Adventurer employer = branches.get(curId).getAdventurers().get(scanner.nextInt());
        Adventurer employee = branches.get(curId).getAdventurers().get(scanner.nextInt());
        employer.getCommodities().put(employee.getId(), employee);
        Branch branch = branches.get(curId);
        branch.getEmployMap()[0][branch.getEmployNum()] = 1;
        branch.getEmployMap()[1][branch.getEmployNum()] = employer.getId();
        branch.getEmployMap()[2][branch.getEmployNum()] = employee.getId();
        branch.setEmployNum(branch.getEmployNum() + 1);
    }

    public static HashMap sortValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, (Comparator) (o1, o2) ->
                ((Comparable) ((Map.Entry) (o1)).getValue()).
                compareTo(((Map.Entry) (o2)).getValue()));

        HashMap sortedHashMap = new LinkedHashMap();

        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public static int addBranch(int formerId, Scanner scanner, HashMap<Integer, Branch> branches)
            throws CloneNotSupportedException {
        Branch branch = new Branch();
        int curId = scanner.nextInt();
        branches.put(curId, branch);
        cloneBranch(branches.get(curId), branches.get(formerId));
        return curId;
    }

    public static int checkoutBranch(Scanner scanner) {
        return scanner.nextInt();
    }

    public static void cloneBranch(Branch cur, Branch former)
            throws CloneNotSupportedException {
        cur.setEmployNum(former.getEmployNum());
        cur.setEmployMap(new int[3][60000]);
        for (int i = 0; i < former.getEmployNum(); ++i) {
            cur.getEmployMap()[0][i] = former.getEmployMap()[0][i];
            cur.getEmployMap()[1][i] = former.getEmployMap()[1][i];
            cur.getEmployMap()[2][i] = former.getEmployMap()[2][i];


        }
        Set<Map.Entry<Integer, Commodity>> set = former.getCommodityHashMap().entrySet();
        for (Object o : set) {
            Map.Entry map = (Map.Entry) o;
            if (map.getValue() instanceof Adventurer) {
                Adventurer adventurer = new Adventurer();
                adventurer.setId(((Adventurer) map.getValue()).getId());
                adventurer.setName(((Adventurer) map.getValue()).getName());
                adventurer.setMoney(((Adventurer) map.getValue()).getMoney());
                adventurer.setExp(((Adventurer) map.getValue()).getExp());
                adventurer.setHealth(((Adventurer) map.getValue()).getHealth());
                cur.getCommodityHashMap().put((Integer) map.getKey(), adventurer);
                cur.getAdventurers().put((Integer) map.getKey(), adventurer);
            } else {
                Equipment equipment = ((Equipment) map.getValue()).clone();
                cur.getCommodityHashMap().put((Integer) map.getKey(), equipment);
            }
        }
        for (int i = 0; i < former.getEmployNum(); ++i) {
            if (former.getEmployMap()[0][i] > 0) {

                cur.getAdventurers().get(former.getEmployMap()[1][i]).getCommodities().
                        put(former.getEmployMap()[2][i], cur.getCommodityHashMap().
                                get(former.getEmployMap()[2][i]));
            }
        }
    }
}
