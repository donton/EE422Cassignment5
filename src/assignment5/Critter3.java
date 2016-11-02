/* CRITTERS GUI <MyClass.java>
 * EE422C Project 4b submission by
 * Domino Weir
 * drw2583
 * 16445
 * Don Ton
 * dt22776
 * 16445
 * Slip days used: <0>
 * Fall 2016
 */
package assignment5;
import assignment5.Critter.TestCritter;

/**
 * Critter3 runs and fights if 
 * its energy is even and it 
 * makes a new twin every time
 * @author Don Ton
 *
 */
public class Critter3 extends TestCritter
{
	@Override
    public String toString() { return "3"; }

    private int dir;

    public Critter3() {
        dir = Critter.getRandomInt(8);
    }

    @Override
    public void doTimeStep()
    {
    	if (getEnergy() % 2 == 0)
    	{
    		run(dir);
    	}
    	Critter3 twin = new Critter3();
    	reproduce(twin, Critter.getRandomInt(8));
    	
    }

    @Override
    public boolean fight(String opponent)
    {
    	if (getEnergy() % 2 == 0) 
    	{
    		return true;
    	}
        return false;
    }
}
