import java.util.ArrayList;

public class Lexer {

    private String jsonline;
    private ArrayList<String> tokenList;
    private int place = 0;
    private ArrayList<TokenType> tokenTypeList;

    public Lexer(String jsonline) {
        this.jsonline = jsonline;
    }

    public TokenType getTokenType() {
        return tokenTypeList.get(place);
    }

    public String getToken() {
        return tokenList.get(place);
    }

    public void nextPos() {
        this.place = place + 1;
    }

    public void analyze(String jsonline) {
        ArrayList<String> tokenList = new ArrayList<>();
        ArrayList<TokenType> tokenTypeList = new ArrayList<>();
        int nameMarkStart = 0;
        int sign = 0;
        // 0 for out, 1 for in

        // the first one
        tokenList.add("{");
        tokenTypeList.add(TokenType.OPEN_B);
        nameMarkStart = 1;

        for (int i = 1; i < jsonline.length(); i++) {
            char ch = jsonline.charAt(i);
            if (ch == '\"') {
                sign = ~sign;
            }
            if (0 == sign && (ch == '{' || ch == '}' || ch == '[' || ch == ']' || ch == ','
                    || (ch == ':'))) {
                if (nameMarkStart > 0 && i - nameMarkStart > 0) {
                    tokenList.add(jsonline.substring(nameMarkStart, i));
                    tokenTypeList.add(TokenType.STRING);
                }
                nameMarkStart = i + 1;
                switch (ch) {
                    case '{':
                        tokenList.add("{");
                        tokenTypeList.add(TokenType.OPEN_B);
                        break;
                    case '}':
                        tokenList.add("}");
                        tokenTypeList.add(TokenType.CLOSE_B);
                        break;
                    case '[':
                        tokenList.add("[");
                        tokenTypeList.add(TokenType.OPEN_M);
                        break;
                    case ']':
                        tokenList.add("]");
                        tokenTypeList.add(TokenType.CLOSE_M);
                        break;
                    case ',':
                        tokenList.add(",");
                        tokenTypeList.add(TokenType.COMMA);
                        break;
                    case ':':
                        tokenList.add(":");
                        tokenTypeList.add(TokenType.COLON);
                        break;
                    default:
                        break;
                }
            }
        }
        this.tokenList = tokenList;
        this.tokenTypeList = tokenTypeList;
    }

    public String getJsonline() {
        return jsonline;
    }

    public enum TokenType {
        STRING, INT, OPEN_B, CLOSE_B, OPEN_M, CLOSE_M, COMMA, COLON
    }
}
