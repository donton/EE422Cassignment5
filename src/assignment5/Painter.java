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

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;

public class Painter {

    /*
     * Returns a square or a circle, according to shapeIndex
     */
    static Shape getIcon(Critter critter, int shapeIndex) {
        Shape s = null;
        int size = 10;

        switch(shapeIndex) {
            case 0: s = new Rectangle(size, size);
                s.setFill(critter.viewFillColor());
                break;
            case 1: s = new Circle(size/2);
                s.setFill(critter.viewFillColor());
                break;
            case 2: s = new Circle(size);
                s.setFill(critter.viewFillColor());
                break;
            case 3: s = new Rectangle(size/2, size/2);
                s.setFill(critter.viewFillColor());
                break;
            case 4: s = new Rectangle(size, size*2);
                s.setFill(critter.viewFillColor());
                break;
            case 5: s = new Rectangle(size/2, size/2);
                s.setFill(critter.viewFillColor());
                break;
        }
        // set the outline of the shape
        s.setStroke(critter.viewOutlineColor()); // outline
        return s;
    }

    /*
     * Paints the shape on a grid.
     */
    public static void displayWorld() {
        Main.grid.getChildren().clear();
        ArrayList<Critter> population = (ArrayList<Critter>) Critter.getPop();
        for (Critter critter : population)
        {
            Critter.CritterShape shape = critter.viewShape();
            Shape s = getIcon(critter, shape.ordinal());	// convert the index to an icon.
            Main.grid.add(s, critter.getX(), critter.getY()); // add the shape to the grid.
        }

    }
}
