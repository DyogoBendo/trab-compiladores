package symboltable;

import lexer.Token;

public class Symbol {
    private final Token token;
    private final String type;
    private Object value;
    private boolean initialized;

    public Symbol(Token token, String type) {
        this.token = token;
        this.type = type;
        this.value = null;
        this.initialized = false;
    }

    public Token getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public Object getValue() { return value; }

    public void setValue(Object value) {
        this.value = value;
        this.initialized = true;
    }

    public void setInitialized(boolean value) {
        this.initialized = value;
    }

    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public String toString() {
        return "Symbol{name='" + token.image + "', type='" + type + "', val=" + value + "}";
    }
}
