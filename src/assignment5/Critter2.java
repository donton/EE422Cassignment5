/* CRITTERS TestCritter2.java
 * EE422C Project 4 submission by
 * Domino Weir
 * drw2583
 * 16445
 * Don Ton
 * dt22776
 * 16445
 * Slip days used: <0>
 * Fall 2016
 */
package assignment4;
import assignment4.Critter.TestCritter;

/**
 * 
 * @author Domino Weir
 *
 */
public class Critter2 extends Critter
{
    @Override
    public String toString() { return "2"; }
    private int dir;

    public Critter2() {
        dir = Critter.getRandomInt(8);
    }

    @Override
    public void doTimeStep()
    {
        /* pick a new direction randomly */
        dir = (dir + Critter.getRandomInt(8)) % 8;
        run(dir);
    }

    @Override
    public boolean fight(String opponent)
    {
        return true;
    }
}
