package Battleships;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Battleships {

    public static final int DEFAULT_GRID_SIZE = 7;

    private final GameState gameState;

    public Battleships() {
        Player player1 = new Player(new char[DEFAULT_GRID_SIZE][DEFAULT_GRID_SIZE],
                new char[DEFAULT_GRID_SIZE][DEFAULT_GRID_SIZE]);

        Player player2 = new Player(new char[DEFAULT_GRID_SIZE][DEFAULT_GRID_SIZE],
                new char[DEFAULT_GRID_SIZE][DEFAULT_GRID_SIZE]);
        gameState = new GameState(player1, player2);
    }

    public GameState getCurrentGameState() {
        return gameState;
    }

    public static class GameState {
        private Player player;

        private Player player2;

        private int currentPlayer;

        List<Player> players = new ArrayList<>();

        public GameState(Player player, Player player2) {
            setCurrentPlayer(1);
            players.add(player);
            players.add(player2);
        }

        public int getCurrentPlayer() {
            return currentPlayer;
        }

        public void setCurrentPlayer(int currentPlayer) {
            this.currentPlayer = currentPlayer;
        }

        public Player getPlayer(int index) {
            return players.get(index);
        }

    }

    public static class Player {

        private final char gameGrid[][];

        private final char givenShots[][];


        public Player(char[][] gameGrid, char[][] givenShots) {
            this.gameGrid = gameGrid;
            this.givenShots = givenShots;
        }

        public char[][] getGameGrid() {
            return gameGrid;
        }

        public char[][] getGivenShots() {
            return givenShots;
        }
    }
}
