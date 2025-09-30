package se.apiva;

import java.util.Random;

public class DiceRoll2 {
    private final int NUMBER_OF_SIDES = 6;
    private final RandomNumbers rnd;

    public DiceRoll2(RandomNumbers r) {
        this.rnd = r;
    }

    public String asText() {
        int rolled = rnd.nextInt(NUMBER_OF_SIDES) + 1;
        return String.format("You rolled %d", rolled);
    }
}
