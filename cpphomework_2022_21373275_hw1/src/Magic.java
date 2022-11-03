import java.util.ArrayList;
import java.util.Scanner;

public class Magic {
    public static void main(String[] argv) {
        ArrayList<Adventurer> adventurers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opNum = scanner.nextInt();
        for (int i = 0; i < opNum; i += 1) {
            int op = scanner.nextInt();
            if (op >= 8) {
                Goddess.goddess(op, adventurers, scanner);
            }
            else {
                God.god(op, adventurers, scanner);
            }

        }

    }
}
