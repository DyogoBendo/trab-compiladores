package comparator;

import lexer.Token;

import java.util.Comparator;

public class TokenComparator {
    /**
     * Compara dois tokens pela posição que aparecem no arquivo pela primeira vez
     */
    public static final Comparator<Token> POSITION = (o1, o2) -> {
        if(o1.beginLine == o2.beginLine){
            return o1.beginColumn - o2.beginColumn;
        }
        return o1.beginLine - o2.beginLine;
    };
}
