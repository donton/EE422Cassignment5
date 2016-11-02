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
 * Fall 2016
 */

package assignment5;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.List;

public abstract class Critter {
	/* NEW FOR PROJECT 5 */
	public enum CritterShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR
	}
	
	/* the default color is white, which I hope makes critters invisible by default
	 * If you change the background color of your View component, then update the default
	 * color to be the same as you background 
	 * 
	 * critters must override at least one of the following three methods, it is not 
	 * proper for critters to remain invisible in the view
	 * 
	 * If a critter only overrides the outline color, then it will look like a non-filled 
	 * shape, at least, that's the intent. You can edit these default methods however you 
	 * need to, but please preserve that intent as you implement them. 
	 */
	public javafx.scene.paint.Color viewColor() { 
		return javafx.scene.paint.Color.WHITE; 
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); }
	public javafx.scene.paint.Color viewFillColor() { return viewColor(); }
	
	public abstract CritterShape viewShape(); 
	
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	protected String look(int direction, boolean steps) {return "";}
	
	/* rest is unchanged from Project 4 */
	
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
    private boolean hasMoved = false;
    private boolean isFighting = false;
    private int dir = 0;
	
  /** move the critter and deduct the appropriate energy cost
     * @param direction is the direction the child will move
     * @return none
     */
    protected final void walk(int direction)
    {
        dir = direction;
        moveWalk();
        energy -= Params.walk_energy_cost;
    }
    /**
     * move the critter and deduct the appropriate energy cost
     * @param direction is the direction the critter will move
     * @return none
     */
    protected final void run(int direction)
    {
        dir = direction;
        moveRun();
        energy -= Params.run_energy_cost;
    }
    /**
     * creates a new baby and adjust energy levels of the two critters
     * @param offspring is the child created
     * @param direction is the direction the child will move
     * @return none
     */
    protected final void reproduce(Critter offspring, int direction)
    {
        if (energy < Params.min_reproduce_energy)
        {
            return;
        }
        offspring.energy = (int) Math.floor(energy/2);
        offspring.dir = direction;
        energy = (int) Math.ceil(energy/2);
        energy += Params.walk_energy_cost;
        walk(direction);
        babies.add(offspring);
    }

    /**
     * this function must be implemented for all critters
     * though it may look different for different critters
     */
    public abstract void doTimeStep();
    /**
     * this function must be implemented for all critters
     * this represents the critter's behavior during conflict
     */
    public abstract boolean fight(String oponent);

    /**
     * create and initialize a Critter subclass.
     * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
     * an InvalidCritterException must be thrown.
     * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
     * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
     * an Exception.)
     * @param critter_class_name
     * @throws InvalidCritterException
     */
    public static void makeCritter(String critter_class_name) throws InvalidCritterException
    {
        String className = myPackage + "." + critter_class_name;
        String critterClassName = myPackage + ".Critter";
        try
        {
            Class<?> newCritter = Class.forName(className);
            Class<?> critterClass = Class.forName(critterClassName);
            if (critterClass.isAssignableFrom(newCritter))
            {
                try
                {
                    Constructor<?> newConstructor = newCritter.getConstructor();
                    Object obj = newConstructor.newInstance();
                    Critter newOne = (Critter) newCritter.cast(obj);
                    newOne.x_coord = getRandomInt(Params.world_width);
                    newOne.y_coord = getRandomInt(Params.world_height);
                    newOne.energy = Params.start_energy;
                    population.add(newOne);
                } catch (Exception e)
                {
                    throw new InvalidCritterException(critter_class_name);
                }
            }
        } catch (Exception e) {
            throw new InvalidCritterException(critter_class_name);
        }
    }

    /**
     * Gets a list of critters of a specific type.
     * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
     * @return List of Critters.
     * @throws InvalidCritterException
     */
    public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException
    {
        List<Critter> result = new java.util.ArrayList<Critter>();
        String className = myPackage + "." + critter_class_name;
        String critterClassName = myPackage + ".Critter";
        try
        {
            Class<?> newCritter = Class.forName(className);
            Class<?> critterClass = Class.forName(critterClassName);
            if (critterClass.isAssignableFrom(newCritter))
            {
                Class<Critter> actualClass = (Class<Critter>) newCritter;
                for (Critter lilCritter : population)
                {
                    if (actualClass.isInstance(lilCritter))
                    {
                        result.add(lilCritter);
                    }
                }
            }
        } catch (Exception e) {
            throw new InvalidCritterException(critter_class_name);
        }
        return result;
    }



    /**
     * Prints out how many Critters of each type there are on the board.
     * @param critters List of Critters.
     */
    public static void runStats(List<Critter> critters) {
        System.out.print("" + critters.size() + " critters as follows -- ");
        java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
        for (Critter crit : critters) {
            String crit_string = crit.toString();
            Integer old_count = critter_count.get(crit_string);
            if (old_count == null) {
                critter_count.put(crit_string,  1);
            } else {
                critter_count.put(crit_string, old_count.intValue() + 1);
            }
        }
        String prefix = "";
        for (String s : critter_count.keySet()) {
            System.out.print(prefix + s + ":" + critter_count.get(s));
            prefix = ", ";
        }
        System.out.println();
    }

    /**
     * Clear the world of all critters, dead and alive
     */
    public static void clearWorld()
    {
        population.clear();
        babies.clear();
    }
    /**
     * execute doTimeStep for every critter in the population and
     * resolve conflicts if necessary
     */
    public static void worldTimeStep()
    {
        for (Critter c: population)
        {
            c.hasMoved = false;
            c.doTimeStep();
        }
        for (Critter a: population)
        {
            for (Critter b: population)
            {
                if (a.x_coord == b.x_coord &&
                        a.y_coord == b.y_coord &&
                        a.energy > 0 && b.energy > 0 &&
                        !a.equals(b))
                {
                    a.isFighting = true;
                    b.isFighting = true;
                    fighting(a,b);
                    a.isFighting = false;
                    b.isFighting = false;
                }
            }
        }
        population.addAll(babies);
        for (Iterator<Critter> iterator = population.iterator(); iterator.hasNext();)
        {
            Critter c = iterator.next();
            c.energy -= Params.rest_energy_cost;
            if (c.energy <= 0) {
                // Remove the current element from the iterator and the list.
                iterator.remove();
            }
        }
        babies.clear();
    }

    /**
     * adjusts the Critter's x and y coordinates according to their direction
     * basically a long series of switch statements
     */
    private void moveWalk() {
        if (!hasMoved) {
            int x_coordNew = 0;
            int y_coordNew = 0;
            boolean xSafe = false;
            boolean ySafe = false;

            switch (dir) {
                case 0:
                    if (isFighting && hasMoved)
                        return;
                    if (x_coord == Params.world_width - 1)
                    {
                        x_coordNew = 0;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            x_coord = x_coordNew;
                        }
                    }
                    else
                    {
                        x_coordNew = x_coord + 1;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            x_coord = x_coordNew;
                        }
                    }
                    break;
                case 1:
                    if (isFighting && hasMoved)
                        return;
                    if (x_coord == Params.world_width - 1)
                    {
                        x_coordNew = 0;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            xSafe = true;
                        }
                        if (y_coord == 0)
                        {
                            y_coordNew = Params.world_height - 1;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                        else
                        {
                            y_coordNew = y_coord - 1;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                y_coord = y_coordNew;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                    }
                    else if (y_coord == 0)
                    {
                        y_coordNew = Params.world_height - 1;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            ySafe = true;
                        }
                        if (x_coord == Params.world_width - 1)
                        {
                            x_coordNew = 0;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                        else
                        {
                            x_coordNew = x_coordNew + 1;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                    }
                    else
                    {
                        x_coordNew = x_coord + 1;
                        y_coordNew = y_coord - 1;
                        if (isAdjacentSafe(x_coordNew, y_coordNew))
                        {
                            x_coord = x_coordNew;
                            y_coord = y_coordNew;
                        }
                    }
                    break;
                case 2:
                    if (isFighting && hasMoved)
                        return;
                    if (y_coord == 0)
                    {
                        y_coordNew = Params.world_height - 1;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            y_coord = y_coordNew;
                        }
                    }
                    else
                    {
                        y_coordNew =  y_coord - 1;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            y_coord = y_coordNew;
                        }
                    }
                    break;
                case 3:
                    if (isFighting && hasMoved)
                        return;
                    if (x_coord == 0)
                    {
                        x_coordNew = Params.world_width - 1;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            xSafe = true;
                        }
                        if (y_coord == 0)
                        {
                            y_coordNew = Params.world_height - 1;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                        else
                        {
                            y_coordNew = y_coord - 1;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }

                    }
                    else if (y_coord == 0)
                    {
                        y_coordNew = Params.world_height - 1;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            ySafe = true;
                        }
                        if (x_coord == 0)
                        {
                            x_coordNew = Params.world_width - 1;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                        else
                        {
                            x_coordNew = x_coord - 1;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                    }
                    else
                    {
                        x_coordNew = x_coord - 1;
                        y_coordNew = y_coord - 1;
                        if (isAdjacentSafe(x_coordNew, y_coordNew))
                        {
                            x_coord = x_coordNew;
                            y_coord = y_coordNew;
                        }
                    }
                    break;
                case 4:
                    if (isFighting && hasMoved)
                        return;
                    if (x_coord == 0)
                    {
                        x_coordNew = Params.world_width - 1;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            x_coord = x_coordNew;
                        }
                    }
                    else
                    {
                        x_coordNew = x_coord - 1;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            x_coord = x_coordNew;
                        }
                    }
                    break;
                case 5:
                    if (isFighting && hasMoved)
                        return;
                    if (x_coord == 0)
                    {
                        x_coordNew = Params.world_width - 1;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            xSafe = true;
                        }
                        if (y_coord == Params.world_height - 1)
                        {
                            y_coordNew = 0;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                        else
                        {
                            y_coordNew = y_coord + 1;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }

                    }
                    else if (y_coord == Params.world_height - 1)
                    {
                        y_coordNew = 0;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            ySafe = true;
                        }
                        if (x_coord == 0)
                        {
                            x_coordNew = Params.world_width - 1;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                        else
                        {
                            x_coordNew = x_coord - 1;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                    }
                    else
                    {
                        x_coordNew = x_coord - 1;
                        y_coordNew = y_coord + 1;
                        if (isAdjacentSafe(x_coordNew, y_coordNew))
                        {
                            x_coord = x_coordNew;
                            y_coord = y_coordNew;
                        }
                    }
                    break;
                case 6:
                    if (isFighting && hasMoved)
                        return;
                    if (y_coord == Params.world_height - 1)
                    {
                        y_coordNew = 0;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            y_coord = y_coordNew;
                        }

                    }
                    else
                    {
                        y_coordNew = y_coord + 1;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            y_coord = y_coordNew;
                        }
                    }
                    break;
                case 7:
                    if (isFighting && hasMoved)
                        return;
                    if (x_coord == Params.world_width - 1) {
                        x_coordNew = 0;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            xSafe = true;
                        }
                        if (y_coord == Params.world_height - 1)
                        {
                            y_coordNew = 0;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                        else
                        {
                            y_coordNew = y_coord + 1;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }

                    }
                    else if (y_coord == Params.world_height - 1)
                    {
                        y_coordNew = 0;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            ySafe = true;
                        }
                        if (x_coord == Params.world_width - 1)
                        {
                            x_coordNew = 0;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                        else
                        {
                            x_coordNew = x_coord + 1;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                    }
                    else
                    {
                        x_coordNew = x_coord + 1;
                        y_coordNew = y_coord + 1;
                        if (isAdjacentSafe(x_coordNew, y_coordNew))
                        {
                            x_coord = x_coordNew;
                            y_coord = y_coordNew;
                        }
                    }
                    break;
            }
        }
        hasMoved = true;
    }

    /**
     * determines if a space on the board is occupied
     * by any member of the population
     * @param x x_coord to check
     * @param y y_coord to check
     * @return true or false
     */
    private boolean isAdjacentSafe(int x, int y)
    {
        for (Critter c : population)
        {
            if (c.x_coord == x && c.y_coord == y)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * adjusts the Critter's x and y coordinates according to their direction
     * basically a long series of switch statements
     * difference from walk - move farther, larger energy deduction
     */
    private void moveRun() {
        if (!hasMoved) {
            int x_coordNew = 0;
            int y_coordNew = 0;
            boolean xSafe = false;
            boolean ySafe = false;
            int newLeftEdgeX = -1;
            int newTopEdgeY = -1;
            int newRightEdgeX = -1;
            int newBottomEdgeY = -1;

            if (Params.world_width - 1 - x_coord == 0) {
                newLeftEdgeX = 1;
            } else if (Params.world_width - 1 - x_coord == 1) {
                newLeftEdgeX = 0;
            }
            if (Params.world_height - 1 - y_coord == 0) {
                newTopEdgeY = 1;
            } else if (Params.world_height - 1 - y_coord == 1) {
                newTopEdgeY = 0;
            }

            if (x_coord == 0) {
                newRightEdgeX = Params.world_width - 2;
            } else if (x_coord == 1) {
                newRightEdgeX = Params.world_width - 1;
            }
            if (y_coord == 0) {
                newBottomEdgeY = Params.world_height - 2;
            } else if (y_coord == 1) {
                newBottomEdgeY = Params.world_height - 1;
            }

            switch (dir) {
                case 0:
                    if (isFighting && hasMoved)
                        return;
                    if (newLeftEdgeX == 0 || newLeftEdgeX == 1)
                    {
                        x_coordNew = newLeftEdgeX;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            x_coord = x_coordNew;
                        }
                    }
                    else
                    {
                        x_coordNew = x_coord + 2;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            x_coord = x_coordNew;
                        }
                    }
                    break;
                case 1:
                    if (isFighting && hasMoved)
                        return;
                    if (newLeftEdgeX == 0 || newLeftEdgeX == 1) {
                        x_coordNew = newLeftEdgeX;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            xSafe = true;
                        }
                        if (y_coord == 0 || y_coord == 1) {
                            y_coordNew = newBottomEdgeY;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        } else {
                            y_coordNew = y_coord - 2;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }

                    } else if (y_coord == 0 || y_coord == 1) {
                        y_coordNew = newBottomEdgeY;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            ySafe = true;
                        }
                        if (newLeftEdgeX == 0 || newLeftEdgeX == 1) {
                            x_coordNew = newLeftEdgeX;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        } else {
                            x_coordNew = x_coord + 2;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                    } else {
                        x_coordNew = x_coord + 2;
                        y_coordNew = y_coord - 2;
                        if (isAdjacentSafe(x_coordNew, y_coordNew))
                        {
                            x_coord = x_coordNew;
                            y_coord = y_coordNew;
                        }
                    }
                    break;
                case 2:
                    if (isFighting && hasMoved)
                        return;
                    if (y_coord == 0 || y_coord == 1) {
                        y_coordNew = newBottomEdgeY;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            y_coord = y_coordNew;
                        }
                    } else {
                        y_coordNew = y_coord - 2;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            y_coord = y_coordNew;
                        }
                    }
                    break;
                case 3:
                    if (isFighting && hasMoved)
                        return;
                    if (x_coord == 0 || y_coord == 1) {
                        x_coordNew = newRightEdgeX;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            xSafe = true;
                        }
                        if (y_coord == 0 || y_coord == 1) {
                            y_coordNew = newBottomEdgeY;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        } else {
                            y_coordNew = y_coord - 2;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }

                    } else if (y_coord == 0 || y_coord == 1) {
                        y_coordNew = newBottomEdgeY;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            ySafe = true;
                        }
                        if (x_coord == 0 || x_coord == 1) {
                            x_coordNew = newRightEdgeX;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        } else {
                            x_coordNew = x_coord - 2;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                    } else {
                        x_coordNew = x_coord - 2;
                        y_coordNew = y_coord - 2;
                        if (isAdjacentSafe(x_coordNew, y_coordNew))
                        {
                            x_coord = x_coordNew;
                            y_coord = y_coordNew;
                        }
                    }
                    break;
                case 4:
                    if (isFighting && hasMoved)
                        return;
                    if (x_coord == 0 || x_coord == 1)
                    {
                        x_coordNew = newRightEdgeX;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            x_coord = x_coordNew;
                        }
                    }
                    else
                    {
                        x_coordNew = x_coord - 2;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            x_coord = x_coordNew;
                        }
                    }
                    break;
                case 5:
                    if (isFighting && hasMoved)
                        return;
                    if (x_coord == 0 || x_coord == 1) {
                        x_coordNew = newRightEdgeX;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            xSafe = true;
                        }
                        if (newTopEdgeY == 0 || newTopEdgeY == 1) {
                            y_coordNew = newBottomEdgeY;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        } else {
                            y_coordNew = y_coord + 2;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }

                    } else if (newTopEdgeY == 0 || newTopEdgeY == 1) {
                        y_coordNew = newTopEdgeY;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            ySafe = true;
                        }
                        if (x_coord == 0 || x_coord == 1) {
                            x_coordNew = newRightEdgeX;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        } else {
                            x_coordNew = x_coord - 2;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                    } else {
                        x_coordNew = x_coord - 2;
                        y_coordNew = y_coord + 2;
                        if (isAdjacentSafe(x_coordNew, y_coordNew))
                        {
                            x_coord = x_coordNew;
                            y_coord = y_coordNew;
                        }
                    }
                    break;
                case 6:
                    if (isFighting && hasMoved)
                        return;
                    if (newTopEdgeY == 0 || newTopEdgeY == 1)
                    {
                        y_coordNew = newTopEdgeY;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            y_coord = y_coordNew;
                        }
                    }
                    else
                    {
                        y_coordNew = y_coord + 2;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            y_coord = y_coordNew;
                        }
                    }
                    break;
                case 7:
                    if (isFighting && hasMoved)
                        return;
                    if (newLeftEdgeX == 0 || newLeftEdgeX == 1) {
                        x_coordNew = newLeftEdgeX;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            xSafe = true;
                        }
                        if (newTopEdgeY == 0 || newTopEdgeY == 1) {
                            y_coordNew = newTopEdgeY;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        } else {
                            y_coordNew = y_coord + 2;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }

                    } else if (newTopEdgeY == 0 || newTopEdgeY == 1) {
                        y_coordNew = newTopEdgeY;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            ySafe = true;
                        }
                        if (newLeftEdgeX == 0 || newLeftEdgeX == 1) {
                            x_coordNew = newLeftEdgeX;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        } else {
                            x_coordNew = x_coord + 2;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                    } else {
                        x_coordNew = x_coord + 2;
                        y_coordNew = y_coord + 2;
                        if (isAdjacentSafe(x_coordNew, y_coordNew))
                        {
                            x_coord = x_coordNew;
                            y_coord = y_coordNew;
                        }
                    }
                    break;
            }
        }
        hasMoved = true;
    }

    /**
     * actually implements the process of conflict resolution for two critters
     * @param a the first critter involved in the conflict
     * @param b the second critter involved in the conflict
     */
    private static void fighting(Critter a, Critter b)
    {
        boolean aFight = a.fight(b.toString());
        boolean bFight = b.fight(a.toString());

        if (!aFight && !bFight)
        {
            if (!a.toString().equals("@"))
            {
                a.moveRun();
            }
            if (!b.toString().equals("@"))
            {
                b.moveRun();
            }
        }
        if (a.energy > 0 && b.energy > 0 &&
                a.x_coord == b.x_coord &&
                a.y_coord == b.y_coord)
        {
            int diceA = -1;
            int diceB = -1;
            if (!aFight)
            {
                diceA = 0;
            }
            else
            {
                diceA = a.getRandomInt(a.energy);
            }
            if (!bFight)
            {
                diceB = 0;
            }
            else
            {
                diceB = b.getRandomInt(b.energy);
            }

            if (diceA > diceB)
            {
                a.energy += Math.round(b.energy / 2);
                b.energy = 0;
            }
            else if (diceB > diceA || diceA == diceB)
            {
                b.energy += Math.round(a.energy / 2);
                a.energy = 0;
            }
        }
        return;
    }
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure thath the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctup update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}
	
}
