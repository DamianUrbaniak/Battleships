package Battleships;

public class SetShipsOnTheGrid {

    public char[][] setShips(char[][] gameGrid, int x, int y){
        gameGrid[x][y] = '█';

        return gameGrid;
    }
}
