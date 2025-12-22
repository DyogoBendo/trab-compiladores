package symboltable;

import exception.SemanticException;
import lexer.Token;
import print.TablePrintHelper;

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
            TablePrintHelper.printSymbolTable(scopeStack.pop(), "Identificadores do escopo que estão sendo removidos");
        }
    }

    public Stack<Map<String, Symbol>> getScopeStack() {
        return scopeStack;
    }

    public void add(Token token, String type) {
        Map<String, Symbol> currentScope = scopeStack.peek();
        String name = token.image;

        if (currentScope.containsKey(name)) {
            throw new SemanticException(token, "a variável/função/estrutura '" + token.image + "' já foi declarada nesse escopo");
        }
        currentScope.put(name, new Symbol(token, type));
    }

    public Symbol getSymbol(Token token) {
        String name = token.image;
        for (int i = scopeStack.size() - 1; i >= 0; i--) {
            Map<String, Symbol> scope = scopeStack.get(i);
            if (scope.containsKey(name)) {
                return scope.get(name);
            }
        }
        return null;
    }

    public void checkFunction(Token token){
        if(getSymbol(token) == null) throw new SemanticException(token, "a função '" + token.image + "' não foi declarada nesse escopo");
    }

    public void checkVariable(Token token){
        if(getSymbol(token) == null) throw new SemanticException(token, "a variável '" + token.image + "' não foi declarada nesse escopo");
    }

    public void checkStruct(Token token){
        if(getSymbol(token) == null) throw new SemanticException(token, "a estrutura '" + token.image + "' não foi declarada nesse escopo");
    }
}
