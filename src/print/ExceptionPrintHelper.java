package print;

import exception.LexicalException;
import lexer.ParseException;

import static lexer.CLexerConstants.tokenImage;

public class ExceptionPrintHelper {
    public static void printLexicalException(LexicalException e){
        System.err.println("\nErro léxico:");
        System.err.println(e.getMessage());
    }

    public static void printParseException(ParseException e){
        System.err.println("\nErro Sintático:");
        if (e.currentToken != null) {
            StringBuilder builder = new StringBuilder();
            String location = " (linha: " + e.currentToken.beginLine + ", coluna: " + e.currentToken.beginColumn + ").";
            builder.append("Erro sintático: Ocorreu um erro de sintaxe perto do token \"")
                    .append(e.currentToken.image).append("\"").append(location).append("\n");

            builder.append("Esperado um dos seguintes tokens: ");
            for (int[] expectedSequence : e.expectedTokenSequences) {
                for (int tokenId : expectedSequence) {
                    builder.append(tokenImage[tokenId]).append(" ");
                }
            }
            System.err.println(builder);
        }
        System.err.println(e.getMessage());
    }

    public static void printException(Exception e){
        System.err.println("\nUm erro inesperado ocorreu: " + e.getMessage());
        e.printStackTrace();
    }
}
