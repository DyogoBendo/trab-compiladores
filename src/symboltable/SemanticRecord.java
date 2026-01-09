package symboltable;

public class SemanticRecord {
    public String type;
    public Object value;
    public Symbol sourceSymbol;

    public SemanticRecord(String type, Object value, Symbol sourceSymbol) {
        this.type = type;
        this.value = value;
        this.sourceSymbol = sourceSymbol;
    }

    public SemanticRecord(String type, Object value) {
        this(type, value, null);
    }

    public SemanticRecord(String type) {
        this(type, null, null);
    }

    public String getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public Symbol getSourceSymbol(){
        return sourceSymbol;
    }

    @Override
    public String toString() {
        return "Record{type='" + type + "', value=" + value + "}";
    }
}