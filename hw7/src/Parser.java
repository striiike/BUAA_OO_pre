// 语法分析器
public class Parser {

    private static Lexer lexer;

    public Parser(String jsonline) {
        lexer = new Lexer(jsonline);
        lexer.analyze(lexer.getJsonline());
    }

    public Json parseTweet() {
        return parseObject();
    }

    // parseObject专门解析并返回json对象
    public static Json parseObject() {
        Json json = new Json();
        for (; ; lexer.nextPos()) {
            if (lexer.getTokenType() == Lexer.TokenType.COMMA) {
                continue;
            }
            if (lexer.getTokenType() == Lexer.TokenType.STRING) {
                Attribute attribute = parseAttribute();
                json.getAttributes().put(attribute.getName(), attribute);
                continue;
            }
            if (lexer.getTokenType() == Lexer.TokenType.CLOSE_B) {
                return json;
            }
        }
    }

    //parseAttribute专门解析并返回一个属性对象
    public static Attribute parseAttribute() {
        Attribute attribute = new Attribute();


        attribute.setName(lexer.getToken().substring(1, lexer.getToken().length() - 1));
        lexer.nextPos();
        lexer.nextPos();
        if (lexer.getTokenType() == Lexer.TokenType.STRING) {
            attribute.setValue(lexer.getToken());
        }
        if (lexer.getTokenType() == Lexer.TokenType.OPEN_B) {
            lexer.nextPos();
            Json valueJson = parseObject();
            attribute.setValue(valueJson);
        }
        if (lexer.getTokenType() == Lexer.TokenType.OPEN_M) {
            lexer.nextPos();
            Array valueArray = parseArray();
            attribute.setValue(valueArray);
        }
        return attribute;
    }

    // parseArray专门解析并返回数组对象
    public static Array parseArray() {
        Array array = new Array();
        for (; ; lexer.nextPos()) {
            if (lexer.getTokenType() == Lexer.TokenType.COMMA) {
                continue;
            }
            if (lexer.getTokenType() == Lexer.TokenType.STRING) {
                Attribute attribute = parseAttribute();
                array.getArray().add(attribute);
                continue;
            }
            if (lexer.getTokenType() == Lexer.TokenType.OPEN_B) {
                lexer.nextPos();
                Json valueJson = parseObject();
                array.getArray().add(valueJson);
            }
            if (lexer.getTokenType() == Lexer.TokenType.OPEN_M) {
                lexer.nextPos();
                Array valueArray = parseArray();
                array.getArray().add(valueArray);
            }
            if (lexer.getTokenType() == Lexer.TokenType.CLOSE_M) {
                return array;
            }
        }
    }

}