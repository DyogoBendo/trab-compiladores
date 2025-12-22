package parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParserNode {
    private String value;
    private final List<ParserNode> children;

    public ParserNode(String value){
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(ParserNode childNode){
        this.children.add(childNode);
    }

    public void print(PrintStream destination, int depth){
        for(int i = 0; i < depth; i++) destination.print("-");
        destination.println(value);
        for(ParserNode child : children) child.print(destination,depth+1);
    }

    public void print(){
        System.out.println("------Árvore de análise sintática-----\n");
        print(System.out, 0);
    }

    public void writeToFile(String filename){
        try (PrintStream writer = new PrintStream(filename)) {
            print(writer, 0);
        } catch (IOException e){
            System.err.println("Um erro ocorreu ao tentar escrever no arquivo");
            e.printStackTrace();
        }
    }

}
