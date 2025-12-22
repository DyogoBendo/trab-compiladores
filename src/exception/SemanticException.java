package exception;

import lexer.CLexerConstants;
import lexer.Token;

public class SemanticException extends RuntimeException{
    private final Token token;
    private final String reason;
    public SemanticException(Token token, String reason) {
        this.token = token;
        this.reason = reason;
    }

    @Override
    public String getMessage(){
        return "Erro sintático na linha " + token.beginLine + ", coluna " + token.beginColumn + ": " + reason;
    }
}
