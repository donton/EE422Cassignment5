/* CRITTERS TestCritter4.java
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
 * Critter4 runs in direction 1 every
 * time and only wants to fight algae
 * @author DonTon
 *
 */
public class Critter4 extends TestCritter
{
	@Override
    public String toString() { return "4"; }

    private int dir;

    public Critter4() {
       dir = 0;
    }

    @Override
    public void doTimeStep()
    {
    	run(1);
    }

    @Override
    public boolean fight(String opponent)
    {
        if (opponent.equals("@")){
        	return true;
        }
    	return false;
    }
}
