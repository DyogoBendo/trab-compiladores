package symboltable;

import lexer.Token;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SymbolTable {
    private final Stack<Map<String, Symbol>> scopeStack = new Stack<>();

    public SymbolTable() {
        scopeStack.push(new HashMap<>());
    }

    public void enterScope() {
        scopeStack.push(new HashMap<>());
    }

    public void leaveScope() {
        if (!scopeStack.isEmpty()) {
            scopeStack.pop();
        }
    }

    public boolean add(Token token, String type) {
        Map<String, Symbol> currentScope = scopeStack.peek();
        String name = token.image;

        if (currentScope.containsKey(name)) {
            return false;
        }
        currentScope.put(name, new Symbol(token, type));
        return true;
    }

    public Symbol getSymbol(String name) {
        for (int i = scopeStack.size() - 1; i >= 0; i--) {
            Map<String, Symbol> scope = scopeStack.get(i);
            if (scope.containsKey(name)) {
                return scope.get(name);
            }
        }
        return null;
    }
}
