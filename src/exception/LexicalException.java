package exception;

import lexer.CLexerConstants;
import lexer.Token;

/**
 * Classe de exception que representa todos os erros lexicos identificadas
 */
public class LexicalException extends RuntimeException{
    private final Token token;
    public LexicalException(Token token){
        this.token = token;
    }
    public Token getToken(){
        return token;
    }

    @Override
    public String getMessage(){
        String message = "Erro léxico na linha " + token.beginLine + ", coluna " + token.beginColumn + ": ";
        if (token.kind == CLexerConstants.UNTERMINATED_CHAR){
            message += "Char não terminado: " + token;
        }
        if (token.kind == CLexerConstants.MULTIPLE_DECIMAL_POINTS){
            message += "Mais de um ponto decimal usado: " + token;
        }
        if(token.kind == CLexerConstants.INVALID_OPERATOR){
            message += "Operador inválido - " + token.image;
        }
        if(token.kind == CLexerConstants.INVALID_IDENTIFIER){
            message += "Identificador inválido: " + token;
        }
        return message;
    }
}
