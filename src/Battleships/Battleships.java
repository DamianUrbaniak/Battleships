package Battleships;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Battleships {

    public static final int DEFAULT_GRID_SIZE = 7;

    private final GameState gameState;

    public Battleships() {
        Player player1 = new Player(new char[DEFAULT_GRID_SIZE][DEFAULT_GRID_SIZE],
                new char [DEFAULT_GRID_SIZE][DEFAULT_GRID_SIZE]);

        Player player2 = new Player(new char[DEFAULT_GRID_SIZE][DEFAULT_GRID_SIZE],
                new char [DEFAULT_GRID_SIZE][DEFAULT_GRID_SIZE]);
         gameState = new GameState(player1, player2);
    }

    public static class GameState{
        public GameState(Player player, Player player2) {
        }
    }

    public static class Player{

        private boolean isCurrent;

        private final char gameGrid[][];

        private final char givenShots[][];


        public Player(char[][] gameGrid, char[][] givenShots) {
            this.gameGrid = gameGrid;
            this.givenShots = givenShots;
        }

        public void setCurrentPlayer(boolean current) {
            isCurrent = current;
        }

        public char[][] getGameGrid() {
            return gameGrid;
        }

        public char[][] getGivenShots() {
            return givenShots;
        }
    }
}
