import java.util.HashMap;
import java.util.Scanner;

public class Magic {
    public static void main(String[] args) {

        HashMap<Integer, Adventurer> adventurers = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int opNum = scanner.nextInt();
        for (int i = 0; i < opNum; i += 1) {
            int op = scanner.nextInt();
            switch (op) {
                case 1:
                    God.addAdventurer(adventurers, scanner);
                    break;
                case 2:
                    God.addEquipment(adventurers, scanner);
                    break;
                case 3:
                    God.removeEquipment(adventurers, scanner);
                    break;
                case 4:
                    God.inquirePriceTotal(adventurers, scanner);
                    break;
                case 5:
                    God.inquirePriceMax(adventurers, scanner);
                    break;
                case 6:
                    God.inquireNumTotal(adventurers, scanner);
                    break;
                case 7:
                    God.printAttribute(adventurers, scanner);
                    break;
                case 8:
                    God.useEquipment(adventurers, scanner);
                    break;
                case 9:
                    God.printState(adventurers, scanner);
                    break;
                default:
                    break;

            }


        }


    }
}
