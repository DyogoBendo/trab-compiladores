package input;

import java.util.Scanner;

public class InputHelper {
    private Scanner sc;
    public InputHelper(){
        sc = new Scanner(System.in);
    }

    /**
     * Lê qual o nome do arquivo que deve ser processado via console
     */
    public String input(){
        System.out.print("Digite o nome do arquivo ou 0 caso queira encerrar: ");
        return sc.nextLine();
    }
}
