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

        private int winner = 0;

        List<Player> players = new ArrayList<>();

        public GameState(Player player, Player player2) {
            setCurrentPlayer(1);
            players.add(player);
            players.add(player2);
        }

        public int getCurrentPlayer() {
            return currentPlayer;
        }

        public Player getPlayer(int index) {
            return players.get(index);
        }

        public void setCurrentPlayer(int currentPlayer) {
            this.currentPlayer = currentPlayer;
        }

        public void setWinner(int player){
            winner = player;
        }


        public boolean hasWinner(Battleships battleships){
            for (int i = 0; i < 2; i++) {
                char[][] grid = battleships.getCurrentGameState().getPlayer(i).getGameGrid();
                for (int j = 0; j < grid.length; j++) {
                    for (int k = 0; k < grid.length; k++) {
                        if(grid[i][j] == '█'){
                            return false;
                        }
                    }
                }
                setWinner(i + 1);
                System.out.println("Player number " + winner + " is the winner!" );
                return true;
            }
            return false;
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
