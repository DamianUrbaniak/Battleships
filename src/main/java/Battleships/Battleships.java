package Battleships;

import java.util.ArrayList;
import java.util.List;

public class Battleships {

    public static final int DEFAULT_GRID_SIZE = 7;

    private final GameState gameState;

    public Battleships() {
        Player player1 = new Player(1, new char[DEFAULT_GRID_SIZE][DEFAULT_GRID_SIZE],
                new char[DEFAULT_GRID_SIZE][DEFAULT_GRID_SIZE]);

        Player player2 = new Player(2, new char[DEFAULT_GRID_SIZE][DEFAULT_GRID_SIZE],
                new char[DEFAULT_GRID_SIZE][DEFAULT_GRID_SIZE]);
        gameState = new GameState(player1, player2);
    }

    public GameState getCurrentGameState() {
        return gameState;
    }

    public static class GameState {
        private Player player;

        private Player player2;

        private Player currentPlayer;

        private Player winner = null;

        List<Player> players = new ArrayList<>();

        public GameState(Player player, Player player2) {
            players.add(player);
            players.add(player2);
            setCurrentPlayer(1);
        }

        public Player getCurrentPlayer() {
            return currentPlayer;
        }

        public Player getPlayerByID(int playerID) {
            return players.get(playerID - 1);
        }

        public void setCurrentPlayer(int playerID) {
            this.currentPlayer = getPlayerByID(playerID);
        }

        public void setWinner(Player player) {
            winner = player;
        }

        public boolean hasWinner() {
            for (int i = 0; i < 2; i++) {
                int shipsleft = 5;
                char[][] grid = getPlayerByID(i).getGivenShots();
                for (int j = 0; j < grid.length; j++) {
                    for (int k = 0; k < grid.length; k++) {
                        if (grid[j][k] == 'X') {
                            shipsleft--;
                        }
                    }
                    if (shipsleft == 0) {
                        setWinner(getPlayerByID(i));
                        System.out.println("Player number " + winner + " is the winner!");
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static class Player {

//        private final String name;

        private final int idPlayer;

        public int getIdPlayer() {
            return idPlayer;
        }

        private final char gameGrid[][];

        private final char givenShots[][];


        public Player(int idPlayer, char[][] gameGrid, char[][] givenShots) {
            this.idPlayer = idPlayer;
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
