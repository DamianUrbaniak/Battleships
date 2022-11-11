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


    public void startGame() throws InterruptedException {
        emptygrid(battleships.getCurrentGameState().getPlayer(0).getGameGrid());
        emptygrid(battleships.getCurrentGameState().getPlayer(1).getGameGrid());

        ui.println("Battleships game.");
        ui.println("Number of players is set to " + DEFAULT_NUMBER_OF_PLAYERS);

        ui.println("Player number " + battleships.getCurrentGameState().getCurrentPlayer());
        ui.println("Please set your ships on the grid.");
        putShipsOnAGrid(battleships.getCurrentGameState().getCurrentPlayer());
        playersSwitcher(1);

        ui.println("Player number " + battleships.getCurrentGameState().getCurrentPlayer());
        ui.println("Please set your ships on the grid.");
        putShipsOnAGrid(battleships.getCurrentGameState().getCurrentPlayer());
        playersSwitcher(2);

        int roundsCounter = 0;

        while (!battleships.getCurrentGameState().hasWinner(battleships)) {
            ui.println("Player's number " + battleships.getCurrentGameState().getCurrentPlayer() + " turn.");
            ui.println("Enter the coordinates to take a shot: ");
            PlayerMovement playerMovement = readPlayerMovementUntilNoException();
            while (isIllegalShot(playerMovement)) {
                playerMovement = readPlayerMovementUntilNoException();
            }
            fire(playerMovement, battleships.getCurrentGameState().getCurrentPlayer());
            optionAfterEachMove();
            playersSwitcher(battleships.getCurrentGameState().getCurrentPlayer());
        }
    }

    private char[][] emptygrid(char[][] gameGrid) {

        for (char[] line : gameGrid) {
            for (char square : line) {
                square = '░';
            }
        }
        return gameGrid;
    }

    private void setShips(PlayerMovement playerMovement, int player) {
        char[][] playerGrid = battleships.getCurrentGameState().getPlayer(player - 1).getGameGrid();
        int x = playerMovement.getX();
        int y = playerMovement.getY();

        while (playerGrid[y][x] == '█') {
            ui.println("Given coordinates are already taken, choose some other ");
            playerMovement = readPlayerMovementUntilNoException();
        }
        if (playerGrid[y][x] != '█') {
            playerGrid[y][x] = '█';
        }
    }

    private PlayerMovement readPlayerMovementUntilNoException() {
        while (true) {
            try {
                return coordinationParser.parse(ui.nextLine());
            } catch (IllegalArgumentException e) {
                ui.print(e.getMessage() + "Enter coordinates again.");
            }
        }
    }

    private void putShipsOnAGrid(int player) {
        for (int i = 0; i < 5; i++) {
            ui.println("There is a map of your ships: ");
            printGrid(battleships.getCurrentGameState().getPlayer(player - 1).getGameGrid());
            ui.println("Enter coordinates, you have " + (5 - i) + " places left.");
            PlayerMovement coordinates = readPlayerMovementUntilNoException();
            while (isIllegal(coordinates)) {
                coordinates = readPlayerMovementUntilNoException();
                isIllegal(coordinates);
            }
            setShips(coordinates, player);
        }
    }

    private void fire(PlayerMovement playerMovement, int player) {
        int attacker = 0;
        int defender = 0;
        if (player == 1) {
            attacker = 0;
            defender = 1;
        } else {
            attacker = 1;
            defender = 0;
        }

        char[][] gridUnderTheFire = battleships.getCurrentGameState().getPlayer(defender).getGameGrid();
        char[][] attackerMarkingGrid = battleships.getCurrentGameState().getPlayer(attacker).getGivenShots();
        int x = playerMovement.getX();
        int y = playerMovement.getY();

        if (gridUnderTheFire[y][x] == '█') {
            attackerMarkingGrid[y][x] = 'X';
            gridUnderTheFire[y][x] = 'X';
            ui.println("BOOOM!!! You have hit the target! ");
        } else{
            attackerMarkingGrid[y][x] = '-';
            ui.println("You have missed.");
        }

    }

    private void printGrid(char[][] gridToPrint) {
        char[][] grid = gridToPrint;
        for (int i = 0; i < grid.length; i++) {
            if (i == 5) {
                ui.print(i + " ");
                continue;
            }
            ui.print(i);
        }
        ui.print("\n");
        for (int i = 0; i < grid.length; i++) {
            ui.print(i);
            for (int j = 0; j < grid.length; j++) {
                ui.print(grid[i][j]);
            }
            ui.print("\n");
        }
    }

    private boolean isIllegal(PlayerMovement playerMovement) {
        int currentPlaya = battleships.getCurrentGameState().getCurrentPlayer();
        try {
            rulesChecker.isValidMove(battleships, playerMovement, currentPlaya);

        } catch (IllegalArgumentException e) {
            ui.print(e.getMessage() + ", type in your move again. \n");
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.print(e.getMessage() + ", type in your move again. \n");
            return true;
        }
        return false;
    }


    private boolean isIllegalShot(PlayerMovement playerMovement) {
//        start here, implement code that will switch players grids between each other
        int currentPlaya = battleships.getCurrentGameState().getCurrentPlayer();
        try {
            rulesChecker.isValidShot(battleships, playerMovement, currentPlaya);

        } catch (IllegalArgumentException e) {
            ui.print(e.getMessage() + ", type in your move again. \n");
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.print(e.getMessage() + ", type in your move again. \n");
            return true;
        }
        return false;
    }

    private void playersSwitcher(int player) {
        if (player == 1) {
            battleships.getCurrentGameState().setCurrentPlayer(2);
        }
        if (player == 2) {
            battleships.getCurrentGameState().setCurrentPlayer(1);
        }
    }

    private void optionAfterEachMove() throws InterruptedException {
        ui.println("What would you like to do next?");
        ui.println("Display my ships. Press 1.");
        ui.println("Display my given shoots so far. Press 2.");
        ui.println("End my turn. Press 3.");
        String choice = ui.nextLine();
        if (choice.matches("1")) {
            int currentPlayer = battleships.getCurrentGameState().getCurrentPlayer();
            printGrid(battleships.getCurrentGameState().getPlayer(currentPlayer - 1).getGameGrid());
            optionAfterEachMove();
        }
        if (choice.matches("2")) {
            int currentPlayer = battleships.getCurrentGameState().getCurrentPlayer();
            printGrid(battleships.getCurrentGameState().getPlayer(currentPlayer - 1).getGivenShots());
            optionAfterEachMove();
        }
        if (choice.matches("3")) {
            for (int i = 5; i > 0; i--) {
                ui.println("Your turn will end for " + i + " seconds. Move away from monitor.");
                Thread.sleep(1000);
            }

        }
    }

}

