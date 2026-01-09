package analyzer;

import exception.LexicalException;
import exception.SemanticException;
import lexer.CLexer;
import lexer.CLexerConstants;
import lexer.ParseException;
import lexer.Token;
import parser.ParserNode;
import print.ExceptionPrintHelper;
import print.TablePrintHelper;
import symboltable.SymbolTable;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class CAnalyzer {
    private final Map<String, Token> symbolTable;
    private final Map<String, Token> reservedWordTable;
    private final SymbolTable identifierTable;
    private final ParserNode parserTree;

    public CAnalyzer() {
        symbolTable = new HashMap<>();
        reservedWordTable = new HashMap<>();
        identifierTable = new SymbolTable();
        parserTree = new ParserNode("start");
    }

    /**
     * Analisa o arquivo em uma única passagem, controlada pelo parser.
     */
    public void analyzeFile(String filename) {
        System.out.println("Iniciando análise");
        try (FileReader fileReader = new FileReader(filename)) {
            CLexer lexParser = new CLexer(fileReader);
            checkLex(lexParser);
        } catch (LexicalException e) {
            ExceptionPrintHelper.printLexicalException(e);
            return;
        } catch (Exception e) {
            ExceptionPrintHelper.printException(e);
            return;
        }

        try (FileReader fileReader = new FileReader(filename)) {
            CLexer parser = new CLexer(fileReader);
            checkSyntactical(parser);
        }  catch (ParseException e){
            ExceptionPrintHelper.printParseException(e);
            return;
        } catch (SemanticException e){
            ExceptionPrintHelper.printSemanticException(e);
            return;
        } catch (Exception e) {
            ExceptionPrintHelper.printException(e);
            return;
        }

        TablePrintHelper.printTable(reservedWordTable, "Tabela de palavras reservadas e operadores");
        TablePrintHelper.printTable(symbolTable, "Tabela de símbolos");
        TablePrintHelper.printSymbolTable(identifierTable.getScopeStack().peek(), "Tabela de identificadores");

        // parserTree.print();
        parserTree.writeToFile("arvore_" + filename);
    }

    /**
     * Realiza análise léxica
     */
    private void checkLex(CLexer lexer) throws LexicalException{
        Token token;

        System.out.println("Análise léxica");
        while ((token = lexer.getNextToken()).kind != CLexerConstants.EOF){
            if(token.kind == CLexerConstants.INVALID_IDENTIFIER    ||
                    token.kind == CLexerConstants.INVALID_OPERATOR      ||
                    token.kind == CLexerConstants.UNTERMINATED_CHAR     ||
                    token.kind == CLexerConstants.MULTIPLE_DECIMAL_POINTS){
                throw new LexicalException(token);
            }
        }

    }

    /**
     * Realiza análise sintática
     */
    private void checkSyntactical(CLexer parser) throws ParseException {
        System.out.println("Análise sintática");
        parser.setTables(this.symbolTable, this.reservedWordTable, identifierTable);
        parser.start(this.parserTree);
    }

}
