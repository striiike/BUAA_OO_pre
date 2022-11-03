import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Magic {
    // to make input standardized into lines/records
    public static ArrayList<Message> standardizeCode(String input) {
        // \S.*:\S.*\" for real jsonString
        String[] records = input.substring(0, input.length() - 1).split(";");
        ArrayList<Message> messages = new ArrayList<>();
        String packagePattern;
        // minus 1 is to ignore the last ';'
        for (String record : records) {
            Message message = new Message(record.trim());
            messages.add(message);
            String[] details = message.getMessage().split("[-:]");
            message.setDate(details[0]);
            // set year & month & day and string of date
            String[] dateParts = details[0].split("/");
            message.setYear(Integer.parseInt(dateParts[0]));
            message.setMonth(Integer.parseInt(dateParts[1]));
            message.setDay(Integer.parseInt(dateParts[2]));
            // set content
            message.setContent(details[2]);
            // set sender and receiver and type
            // not-content part    \w+@\w+
            packagePattern = "\\w+@\\w+";
            Pattern pattern = Pattern.compile(packagePattern);
            Matcher matcher = pattern.matcher(details[1]);
            if (matcher.find()) {
                String[] names = matcher.group().split("@");
                message.setSender(names[0]);
                message.setReceiver(names[1]);
                message.setType(0);
                message.setSpecific(true);
            } else {
                message.setSender(details[1]);
            }
            // content part    @[\w]+( )
            packagePattern = "@\\w+( )";
            pattern = Pattern.compile(packagePattern);
            matcher = pattern.matcher(details[2]);
            if (matcher.find()) {
                message.setReceiver(matcher.group().substring(1, matcher.group().length() - 1));
                message.setType(1);
                message.setSpecific(true);
            }
        }
        return messages;
    }

    public static void main(String[] args) {
        StringBuilder code = new StringBuilder();
        ArrayList<Message> messages;
        Scanner scanner = new Scanner(System.in);
        // catch all the messages
        while (scanner.hasNext()) {
            String temp = scanner.nextLine();
            if (Objects.equals(temp, "END_OF_MESSAGE")) {
                break;
            }
            code.append(temp);
        }
        messages = standardizeCode(code.toString().trim());
        // inquire information
        while (scanner.hasNext()) {
            String quireOp = scanner.next();
            String str = scanner.nextLine();
            // inquire sending date
            if (Objects.equals(quireOp, "qdate")) {
                inquireDate(quireOp, str, messages);
            }
            // inquire sender
            if (Objects.equals(quireOp, "qsend")) {
                inquireSend(quireOp, str, messages);

            }
            // inquire receiver
            if (Objects.equals(quireOp, "qrecv")) {
                inquireRecv(quireOp, str, messages);
            }
        }
    }

    //  (-c \”(?<str>[A-Za-z0-9,.?! @]*)\”)*
    public static void inquireDate(String quireOp, String query, ArrayList<Message> messages) {
        String[] parts = query.trim().split("\"");
        String[] params = parts[0].split(" ");
        int paramNum = params.length;
        StringBuilder str = new StringBuilder("0000" + params[0]);
        int first = str.indexOf("/");
        str.insert(first + 1, "0000");
        int second = str.indexOf("/", first + 1);
        str.insert(second + 1, "0000");
        String[] dateParts = str.toString().split("/");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);

        //  -1 means that no input, 0 just means 0

        if (dateParts[0].length() == 4 && year == 0) {
            year = -1;
        }
        if (dateParts[1].length() == 4 && month == 0) {
            month = -1;
        }
        if (dateParts[2].length() == 4 && day == 0) {
            day = -1;
        }


        for (Message item : messages) {
            if (!isLegitimate(year, month, day)) {
                System.out.println("Command Error!: Wrong Date Format! \""
                        + quireOp
                        + query
                        + "\"");
                return;
            } else if (year == -1 || year == item.getYear()) {
                if (month == -1 || month == item.getMonth()) {
                    if (day == -1 || day == item.getDay()) {
                        if (paramNum == 2) {
                            System.out.println(blockWords(item.getMessage(), parts[1]) + ';');
                        } else {
                            System.out.println(item.getMessage() + ';');
                        }
                    }
                }
            }
        }
    }

    public static void inquireSend(String quireOp, String query, ArrayList<Message> messages) {
        String[] parts = query.trim().split("\"");
        String[] params = parts[0].split(" ");
        String str = params[0];
        int partNum = parts.length;
        int paramNum = params.length;
        // judge if vague
        if (Objects.equals(str, "-ssq") || Objects.equals(str, "-ssr")
                || Objects.equals(str, "-pre") || Objects.equals(str, "-pos")) {
            System.out.println("Command Error!: Not Vague Query! \"" + quireOp + query + "\"");
            return;
        } else if (Objects.equals(str, "-v")) {
            for (Message item : messages) {
                if (paramNum > 1) {
                    if (Objects.equals(params[1], "-ssq")) {
                        if (isSubsequence(parts[1], item.getSender())) {
                            System.out.println((partNum > 2) ? blockWords(item.getMessage(),
                                    parts[3]) + ';' : item.getMessage() + ';');
                        }
                    }
                    if (Objects.equals(params[1], "-ssr")) {
                        if (item.getSender().contains(parts[1])) {
                            System.out.println((partNum > 2) ? blockWords(item.getMessage(),
                                    parts[3]) + ';' : item.getMessage() + ';');
                        }
                    }
                    if (Objects.equals(params[1], "-pre")) {
                        if (item.getSender().startsWith(parts[1])) {
                            System.out.println((partNum > 2) ? blockWords(item.getMessage(),
                                    parts[3]) + ';' : item.getMessage() + ';');
                        }
                    }
                    if (Objects.equals(params[1], "-pos")) {
                        if (item.getSender().endsWith(parts[1])) {
                            System.out.println((partNum > 2) ? blockWords(item.getMessage(),
                                    parts[3]) + ';' : item.getMessage() + ';');
                        }
                    }
                } else if (item.getSender().contains(parts[1])) {
                    System.out.println((partNum > 2) ? blockWords(item.getMessage(),
                            parts[3]) + ';' : item.getMessage() + ';');
                }


            }
            return;
        }
        for (Message item : messages) {
            if (Objects.equals(item.getSender(), parts[1])) {
                System.out.println((partNum > 2) ? blockWords(item.getMessage(),
                        parts[3]) + ';' : item.getMessage() + ';');
            }
        }
    }

    public static void inquireRecv(String quireOp, String query, ArrayList<Message> messages) {
        String[] parts = query.trim().split("\"");
        String[] params = parts[0].split(" ");
        String str = params[0];
        int partNum = parts.length;
        int paramNum = params.length;
        // judge if vague
        if (Objects.equals(str, "-ssq") || Objects.equals(str, "-ssr")
                || Objects.equals(str, "-pre") || Objects.equals(str, "-pos")) {
            System.out.println("Command Error!: Not Vague Query! \"" + quireOp + query + "\"");
            return;
        } else if (Objects.equals(str, "-v")) {
            for (Message item : messages) {
                if (paramNum > 1) {
                    if (Objects.equals(params[1], "-ssq") && item.isSpecific()) {
                        if (isSubsequence(parts[1], item.getReceiver())) {
                            System.out.println((partNum > 2) ? blockWords(item.getMessage(),
                                    parts[3]) + ';' : item.getMessage() + ';');
                        }
                    }

                    if (Objects.equals(params[1], "-ssr") && item.isSpecific()) {
                        if (item.getReceiver().contains(parts[1])) {
                            System.out.println((partNum > 2) ? blockWords(item.getMessage(),
                                    parts[3]) + ';' : item.getMessage() + ';');
                        }
                    }
                    if (Objects.equals(params[1], "-pre") && item.isSpecific()) {
                        if (item.getReceiver().startsWith(parts[1])) {
                            System.out.println((partNum > 2) ? blockWords(item.getMessage(),
                                    parts[3]) + ';' : item.getMessage() + ';');
                        }
                    }
                    if (Objects.equals(params[1], "-pos") && item.isSpecific()) {
                        if (item.getReceiver().endsWith(parts[1])) {
                            System.out.println((partNum > 2) ? blockWords(item.getMessage(),
                                    parts[3]) + ';' : item.getMessage() + ';');
                        }
                    }
                } else if (item.isSpecific()) {
                    if (item.getReceiver().contains(parts[1])) {
                        System.out.println((partNum > 2) ? blockWords(item.getMessage(),
                                parts[3]) + ';' : item.getMessage() + ';');
                    }
                }
            }
            return;
        }
        for (Message item : messages) {
            if (Objects.equals(item.getReceiver(), parts[1])) {
                System.out.println((partNum > 2) ?
                        blockWords(item.getMessage(), parts[3]) + ';' : item.getMessage() + ';');
            }
        }
    }

    public static boolean isLegitimate(int year, int month, int day) {
        if (year > 9999 || year == 0) {
            return false;
        } else {
            switch (month) {
                case 2:
                    if ((year % 400 == 0) || ((year % 100 > 0) && (year % 4 == 0)) || year == -1) {
                        if (day == 0 || day >= 30) {
                            return false;
                        }
                    } else {
                        if (day == 0 || day >= 29) {
                            return false;
                        }
                    }
                    break;

                case 4:
                case 6:
                case 9:
                case 11:
                    if (day == 0 || day >= 31) {
                        return false;
                    }
                    break;
                case -1:
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    if (day == 0 || day >= 32) {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    public static String blockWords(String message, String block) {
        StringBuilder str = new StringBuilder(message);
        StringBuilder repStr = new StringBuilder();
        for (int i = 0; i < block.length(); ++i) {
            repStr.append('*');
        }
        int start = str.indexOf("\"");
        while (str.substring(start).contains(block)) {
            str.replace(start + str.substring(start).indexOf(block),
                    start + str.substring(start).indexOf(block) + block.length(),
                    repStr.toString());
        }

        return str.toString();
    }

    public static boolean isSubsequence(String sub, String str) {
        if (sub.length() == 0) {
            return true;
        }
        if (sub.length() > str.length()) {
            return false;
        }
        char[] sc = sub.toCharArray();
        int index = -1;
        for (char c : sc) {
            index = str.indexOf(c, index + 1);
            if (index == -1) {
                return false;
            }
        }
        return true;
    }
}








