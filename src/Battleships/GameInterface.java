package Battleships;

public class GameInterface {

    private static final int DEFAULT_NUMBER_OF_PLAYERS = 2;

    private final Battleships battleships;

    private final RulesChecker rulesChecker;

    private final UserInterface ui;

    private final CoordinationParser coordinationParser;

    public GameInterface(Battleships battleships,
                         RulesChecker rulesChecker,
                         UserInterface ui,
                         CoordinationParser coordinationParser) {
        this.battleships = battleships;
        this.rulesChecker = rulesChecker;
        this.ui = ui;
        this.coordinationParser = coordinationParser;
    }


    public void startGame() {
        emptygrid(battleships.getCurrentGameState().getPlayer(0).getGameGrid());
        emptygrid(battleships.getCurrentGameState().getPlayer(1).getGameGrid());
        ui.println("Battleships game.");
        ui.println("Number of players is set to " + DEFAULT_NUMBER_OF_PLAYERS);
        ui.println("Player number " + battleships.getCurrentGameState().getCurrentPlayer());
        ui.println("Please set your ships on the grid.");
        putShipsOnAGrid(0);
        ui.println("Player number 2");
        ui.println("Please set your ships on the grid.");
        putShipsOnAGrid(1);
    }

    private char[][] emptygrid(char[][] gameGrid) {

        for (char[] line : gameGrid) {
            for (char square : line) {
                square = '░';
            }
        }
        return gameGrid;
    }

    private char[][] setShips(char[][] gameGrid, int x, int y) {
        gameGrid[x][y] = '█';

        return gameGrid;
    }

    private PlayerMovement readPlayerMovementUntilNoException() {
        while (true) {
            try {
                return coordinationParser.parse(ui.nextLine());
            } catch (IllegalArgumentException e) {
                ui.print(e.getMessage() + "enter coordinates again.");
            }
        }
    }

    private void putShipsOnAGrid(int player) {
        for (int i = 0; i < 30; i++) {
            ui.println("Enter coordinates, you have " + (30 - i) + " places left.");
            PlayerMovement coordinates = readPlayerMovementUntilNoException();
            setShips(battleships.getCurrentGameState()
                            .getPlayer(player).getGameGrid(),
                    coordinates.getX(), coordinates.getY());
        }
    }

}

