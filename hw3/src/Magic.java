import java.util.HashMap;
import java.util.Scanner;

public class Magic {
    public static void main(String[] args) throws CloneNotSupportedException {
        HashMap<Integer, Branch> branches = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        Branch branch = new Branch();
        branches.put(1, branch);
        int curId = 1;
        int opNum = scanner.nextInt();
        for (int i = 0; i < opNum; i += 1) {
            int op = scanner.nextInt();
            switch (op) {
                case 1:
                    God.addAdventurer(curId, scanner, branches);
                    break;
                case 2:
                    God.addEquipment(curId, scanner, branches);
                    break;
                case 3:
                    God.removeEquipment(curId, scanner, branches);
                    break;
                case 4:
                    God.inqPriceTot(curId, scanner, branches);
                    break;
                case 5:
                    God.inqPriceMax(curId, scanner, branches);
                    break;
                case 6:
                    God.inqComTotal(curId, scanner, branches);
                    break;
                case 7:
                    God.printAttribute(curId, scanner, branches);
                    break;
                case 8:
                    God.useCommodity(curId, scanner, branches);
                    break;
                case 9:
                    God.printState(curId, scanner, branches);
                    break;
                case 10:
                    God.employAdv(curId, scanner, branches);
                    break;
                case 11:
                    curId = God.addBranch(curId, scanner, branches);
                    break;
                case 12:
                    curId = God.checkoutBranch(scanner);
                    break;
                default:
                    break;
            }
        }
    }
}
