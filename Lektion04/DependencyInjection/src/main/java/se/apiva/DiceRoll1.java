package se.apiva;

import java.util.random.RandomGenerator;

public class DiceRoll1 {

    private final int NUMBER_OF_SIDES = 6;

    private final RandomGenerator rnd = RandomGenerator.getDefault();

    public String asText() {
        int rolled = rnd.nextInt(NUMBER_OF_SIDES) + 1;
        return String.format("You rolled %d", rolled);
    }

}
