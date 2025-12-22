package print;

import comparator.TokenComparator;
import lexer.CLexerConstants;
import lexer.Token;
import symboltable.SymbolTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TablePrintHelper {
    private static final String DELIMITER = "--------";

    /**
     * Imprime tabela de tokens, ordenado pela posição que aparecem pela primeira vez
     */
    public static void printTable(Map<String, Token> map, String title){
        List<Token> tokenList = new ArrayList<>(map.values());
        Collections.sort(tokenList, TokenComparator.POSITION);

        System.out.println(DELIMITER + title + DELIMITER);

        for (Token token : tokenList){
            System.out.println("Token: " + token);
            System.out.println("Posição que aparece pela primeira vez (linha, coluna): (" + token.beginLine + "," + token.beginColumn + ")");
            System.out.println("Tipo do token: " + CLexerConstants.tokenImage[token.kind]);
            System.out.println(DELIMITER);
        }
    }

}
