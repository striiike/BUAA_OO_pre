import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class Magic {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Json> tweets = new ArrayList<>();
        while (scanner.hasNext()) {
            String jsonline = scanner.nextLine();
            if (Objects.equals(jsonline, "END_OF_MESSAGE")) {
                break;
            }
            Parser parser = new Parser(jsonline);
            Json json = parser.parseTweet();
            tweets.add(json);
        }

        // inquire information
        while (scanner.hasNext()) {
            String quireOp = scanner.next();

            // inquire sending date
            if (Objects.equals(quireOp, "Qdate")) {
                inquireDate(scanner, tweets);
            }

            // inquire emoji
            if (Objects.equals(quireOp, "Qemoji")) {
                inquireEmoji(scanner, tweets);
            }

            // inquire count
            if (Objects.equals(quireOp, "Qcount")) {
                inquireCount(scanner, tweets);
            }

            // inquire text
            if (Objects.equals(quireOp, "Qtext")) {
                inquireText(scanner, tweets);
            }
            // inquire sensitive
            if (Objects.equals(quireOp, "Qsensitive")) {
                inquireSen(scanner, tweets);
            }
            // inquire lang
            if (Objects.equals(quireOp, "Qlang")) {
                inquireLang(scanner, tweets);
            }
        }
    }

    public static void inquireDate(Scanner scanner, ArrayList<Json> tweets) {
        String userId = scanner.next();
        String reqDate = scanner.next();
        String[] mon = {"zero", "Jan", "Feb", "Mar", "Apr", "May"
                , "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        int tweetNum = 0;
        int reNum = 0;
        int favNum = 0;
        int comNum = 0;
        int start = Integer.parseInt(reqDate.substring(0, 4)) * 10000
                + Integer.parseInt(reqDate.substring(5, 7)) * 100
                + Integer.parseInt(reqDate.substring(8, 10));
        int end = Integer.parseInt(reqDate.substring(11, 15)) * 10000
                + Integer.parseInt(reqDate.substring(16, 18)) * 100
                + Integer.parseInt(reqDate.substring(19, 21));
        for (Json item : tweets) {
            String id = (String) ((Json) item.getAttributes().get("raw_value").getValue())
                    .getAttributes().get("user_id").getValue();
            String strDate = (String) ((Json) item.getAttributes().get("raw_value").getValue())
                    .getAttributes().get("created_at").getValue();
            int date = 0;
            String monDate = strDate.substring(5, 8);
            for (int i = 0; i < mon.length; ++i) {
                if (Objects.equals(mon[i], monDate)) {
                    date += i * 100;
                    break;
                }
            }
            date += Integer.parseInt(strDate.substring(21, 25)) * 10000
                    + Integer.parseInt(strDate.substring(9, 11));
            if (Objects.equals(userId, id)) {
                if (date >= start && date <= end) {
                    tweetNum++;
                    reNum += Integer.parseInt((String) ((Json) item.getAttributes()
                            .get("raw_value").getValue()).getAttributes()
                            .get("retweet_count").getValue());
                    favNum += Integer.parseInt((String) ((Json) item.getAttributes()
                            .get("raw_value").getValue()).getAttributes()
                            .get("favorite_count").getValue());
                    comNum += Integer.parseInt((String) ((Json) item.getAttributes()
                            .get("raw_value").getValue()).getAttributes()
                            .get("reply_count").getValue());
                }
            }
        }
        System.out.println(tweetNum + " " + reNum + " " + favNum + " " + comNum);
    }

    public static void inquireCount(Scanner scanner, ArrayList<Json> tweets) {
        String reqDate = scanner.next();
        int tweetNum = 0;
        int start = Integer.parseInt(reqDate.substring(0, 4)) * 10000
                + Integer.parseInt(reqDate.substring(5, 7)) * 100
                + Integer.parseInt(reqDate.substring(8, 10));
        int end = Integer.parseInt(reqDate.substring(11, 15)) * 10000
                + Integer.parseInt(reqDate.substring(16, 18)) * 100
                + Integer.parseInt(reqDate.substring(19, 21));
        for (Json item : tweets) {
            String strDate = (String) (item.getAttributes().get("download_datetime").getValue());
            int date;
            date = Integer.parseInt(strDate.substring(1, 5)) * 10000
                    + Integer.parseInt(strDate.substring(6, 8)) * 100
                    + Integer.parseInt(strDate.substring(9, 11));
            if (date >= start && date <= end) {
                tweetNum++;
            }
        }
        System.out.println(tweetNum);
    }

    public static void inquireEmoji(Scanner scanner, ArrayList<Json> tweets) {
        String userId = scanner.next();
        for (Json item : tweets) {
            String id = (String) ((Json) item.getAttributes().get("raw_value").getValue())
                    .getAttributes().get("id").getValue();
            if (Objects.equals(userId, id)) {
                Array emojis = (Array) ((Json) item.getAttributes().get("raw_value").getValue())
                        .getAttributes().get("emojis").getValue();
                if (0 == emojis.getArray().size()) {
                    System.out.println("None");
                } else {
                    ArrayList<Emoji> emos = new ArrayList<>();
                    for (Object jtem : emojis.getArray()) {
                        Emoji emoji = new Emoji((String) ((Json) jtem).getAttributes()
                                .get("name").getValue()
                                , Integer.parseInt((String) ((Json) jtem)
                                .getAttributes().get("count").getValue()));
                        emos.add(emoji);
                    }
                    emos.sort(new Comparator<Emoji>() {
                        @Override
                        public int compare(Emoji o1, Emoji o2) {
                            //return o1.getScore() - o2.getScore();//升序
                            if (o1.getCount() != o2.getCount()) {
                                return o2.getCount() - o1.getCount(); //降序
                            } else {
                                return o1.getName().compareTo(o2.getName());
                            }
                        }
                    });
                    for (Emoji jtem : emos) {
                        System.out.print(jtem.getName().substring(1
                                , jtem.getName().length() - 1) + " ");
                    }
                    System.out.println();
                }

            }
        }

    }

    public static void inquireText(Scanner scanner, ArrayList<Json> tweets) {
        String reqId = scanner.next();
        for (Json item : tweets) {
            String id = (String) ((Json) item.getAttributes().get("raw_value").getValue())
                    .getAttributes().get("id").getValue();
            if (Objects.equals(reqId, id)) {
                String text = (String) ((Json) item.getAttributes().get("raw_value").getValue())
                        .getAttributes().get("full_text").getValue();
                if (Objects.equals(text, "null")) {
                    System.out.println("None");
                } else {
                    if (Objects.equals(text, "\"\"")) {
                        return;
                    }
                    System.out.println(text.substring(1, text.length() - 1));
                }
            }
        }
    }

    public static void inquireSen(Scanner scanner, ArrayList<Json> tweets) {
        String userId = scanner.next();
        int sensNum = 0;
        for (Json item : tweets) {
            String id = (String) ((Json) item.getAttributes().get("raw_value").getValue())
                    .getAttributes().get("user_id").getValue();
            if (Objects.equals(userId, id)) {
                String sens = (String) ((Json) item.getAttributes().get("raw_value").getValue())
                        .getAttributes().get("possibly_sensitive_editable").getValue();
                if (Objects.equals(sens, "true")) {
                    sensNum++;
                }
            }
        }
        System.out.println(sensNum);
    }

    public static void inquireLang(Scanner scanner, ArrayList<Json> tweets) {
        String reqId = scanner.next();
        for (Json item : tweets) {
            String id = (String) ((Json) item.getAttributes().get("raw_value").getValue())
                    .getAttributes().get("id").getValue();
            if (Objects.equals(reqId, id)) {
                String lang = (String) ((Json) item.getAttributes().get("raw_value").getValue())
                        .getAttributes().get("lang").getValue();
                System.out.println(lang.substring(1, lang.length() - 1));
            }
        }
    }
}





















