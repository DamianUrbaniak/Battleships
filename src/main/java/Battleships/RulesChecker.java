package Battleships;

public class RulesChecker {

    public boolean isValidMove(Battleships battleships, PlayerMovement playerMovement) {
        boolean valuated = false;
        int givenXCoordinate = playerMovement.getX();
        int givenYCoordinate = playerMovement.getY();
        int gridSize = battleships.getCurrentGameState().getCurrentPlayer().getGameGrid().length;
        char[][] gridToCompare = battleships.getCurrentGameState().getCurrentPlayer().getGameGrid();

        if (givenYCoordinate >= gridSize || givenXCoordinate >= gridSize) {
            throw new ArrayIndexOutOfBoundsException(
                    "Given coordinates are out of the game grid scope"
            );
        }
        if (gridToCompare[givenYCoordinate][givenXCoordinate] == '█') {
            throw new IllegalArgumentException(
                    "Move with that coordinates was already made"
            );
        }
        if (gridToCompare[givenYCoordinate][givenXCoordinate] == '░') {
            valuated = true;
        }
        return valuated;
    }

    public boolean isValidShot(Battleships battleships, PlayerMovement playerMovement) {
        boolean valuated = false;
        int givenXCoordinate = playerMovement.getX();
        int givenYCoordinate = playerMovement.getY();
        int gridSize = battleships.getCurrentGameState().getCurrentPlayer().getGameGrid().length;
        char[][] gridToCompare = battleships.getCurrentGameState().getCurrentPlayer().getGivenShots();

        if (givenYCoordinate >= gridSize || givenXCoordinate >= gridSize) {
            throw new ArrayIndexOutOfBoundsException(
                    "Given coordinates are out of the game grid scope"
            );
        }
        if (gridToCompare[givenYCoordinate][givenXCoordinate] == 'X' ||
                gridToCompare[givenYCoordinate][givenXCoordinate] == '-') {
            throw new IllegalArgumentException(
                    "Shoot with that coordinates was already given"
            );
        }
        if (gridToCompare[givenYCoordinate][givenXCoordinate] == '░') {
            valuated = true;
        }
        return valuated;
    }
}
