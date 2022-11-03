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
        while (scanner.hasNext()) {
            String temp = scanner.nextLine();
            if (Objects.equals(temp, "END_OF_MESSAGE")) {
                break;
            }

            code.append(temp);
        }
        messages = standardizeCode(code.toString().trim());

        while (scanner.hasNext()) {
            String quireOp = scanner.next();
            String str = scanner.next();
            if (Objects.equals(quireOp, "qdate")) {
                String[] dateParts = str.split("/");
                int year = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int day = Integer.parseInt(dateParts[2]);
                for (Message item : messages) {
                    if (year == item.getYear() && month == item.getMonth() && day == item.getDay())
                    {
                        System.out.println(item.getMessage() + ';');
                    }
                }
            }
            if (Objects.equals(quireOp, "qsend")) {
                for (Message item : messages) {
                    if (Objects.equals(item.getSender(), str.substring(1, str.length() - 1))) {
                        System.out.println(item.getMessage() + ';');
                    }
                }
            }
            if (Objects.equals(quireOp, "qrecv")) {
                for (Message item : messages) {
                    if (Objects.equals(item.getReceiver(), str.substring(1, str.length() - 1))) {
                        System.out.println(item.getMessage() + ';');
                    }
                }
            }


        }
    }
}
