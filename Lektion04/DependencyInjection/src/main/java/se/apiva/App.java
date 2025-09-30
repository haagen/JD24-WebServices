package se.apiva;

import javax.sound.midi.Soundbank;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        DiceRoll1 dice1 = new DiceRoll1();
        System.out.println(dice1.asText());

        RandomlyGeneratedNumbers realRandomNumbers = new RandomlyGeneratedNumbers();
        DiceRoll2 dice2 = new DiceRoll2(realRandomNumbers);
        System.out.println(dice2.asText());

        StubRandomNumbers stubRandomNumbers = new StubRandomNumbers();
        DiceRoll2 dice3 = new DiceRoll2(stubRandomNumbers);
        System.out.println(dice3.asText());

    }
}
