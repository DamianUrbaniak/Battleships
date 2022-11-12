package Battleships;

import java.io.PrintStream;
import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner;

    private final PrintStream output;

    public UserInterface(Scanner scanner, PrintStream output) {
        this.scanner = scanner;
        this.output = output;
    }

    public void print(Object message){
        output.print(message);
    }

    public void println(String message){
        output.println(message);
    }

    public String nextLine(){
        return scanner.nextLine();
    }
}