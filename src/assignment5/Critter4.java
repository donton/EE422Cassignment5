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
import assignment5.Critter.CritterShape;
import assignment5.Critter.TestCritter;
import javafx.scene.paint.Color;

/**
 * Critter4 runs in direction 1 every
 * time and only wants to fight algae
 * @author DonTon
 *
 */
public class Critter4 extends TestCritter
{
    @Override
    public javafx.scene.paint.Color viewFillColor() {return Color.FORESTGREEN; }

	@Override
	public CritterShape viewShape()
	{
		return Critter.CritterShape.STAR;
	}
	
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
