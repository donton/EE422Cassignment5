/* CRITTERS TestCritter1.java
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

/**
 * 
 * @author Domino Weir
 *
 */
public class Critter1 extends Critter
{

    @Override
    public String toString() { return "1"; }

    private int dir;

    public Critter1() {
        dir = Critter.getRandomInt(8);
    }

    @Override
    public void doTimeStep()
    {
        /* pick a new direction randomly */
        dir = (dir + Critter.getRandomInt(8)) % 8;
        int walkRun = Critter.getRandomInt(3);
        if (walkRun < 1)
        {
            walk(dir);
        }
        else
        {
            run(dir);
        }
    }

    @Override
    public boolean fight(String opponent)
    {
        int fight = Critter.getRandomInt(3);
        if (fight < 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
