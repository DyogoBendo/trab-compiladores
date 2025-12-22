package symboltable;

import lexer.Token;

public class Symbol {
    private final Token token;
    private final String type;

    public Symbol(Token token, String type) {
        this.token = token;
        this.type = type;
    }

    public Token getToken() {
        return token;
    }

    public String getType() {
        return type;
    }
}
