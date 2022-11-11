package Battleships;

import java.util.Scanner;

public class BattleshipsApp {
    public static void main(String[] args) throws InterruptedException {

        RulesChecker rulesChecker = new RulesChecker();
        Battleships battleships = new Battleships();
        CoordinationParser coordinationParser = new CoordinationParser();
        UserInterface ui = new UserInterface(new Scanner(System.in), System.out);

        new GameInterface(battleships, rulesChecker, ui, coordinationParser).startGame();

    }
}
