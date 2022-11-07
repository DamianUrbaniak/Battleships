package Battleships;

public class CoordinationParser {

    public PlayerMovement parse(String input){
        if (!input.matches("\\[?\\d+,\\d+]?")){
            throw new IllegalArgumentException(
                    "Movement should be ine the format [x, y], brackets are optional, x and y have to be a integer."
            );
        }

        if (input.charAt(0) == '[') {
            input = input.substring(1, input.length() - 1);
        }

        String[] coordinates = input.split(",");

        return new PlayerMovement(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
    }

}
