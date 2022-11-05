package Battleships;

public class GameInterface {

    private static final int DEFAULT_NUMBER_OF_PLAYERS = 2;

    private final Battleships battleships;

    private final RulesChecker rulesChecker;

    private final UserInterface ui;

    private final CoordinationParser;

    public GameInterface(Battleships battleships,
                         RulesChecker rulesChecker,
                         UserInterface ui) {
        this.battleships = battleships;
        this.rulesChecker = rulesChecker;
        this.ui = ui;
    }

    public void setShips(){
        ui.println("Battleships game.");
        ui.println("Number of players is set to " + DEFAULT_NUMBER_OF_PLAYERS);
        ui.println("Player number " + );
    }
}
