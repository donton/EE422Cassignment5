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

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
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
        int x = critter.getX();
        int y = critter.getY();

        switch(shapeIndex) {
            case 0: s = new Rectangle(x % Params.world_width, y % Params.world_height, (x + size) % Params.world_width, (y + size) % Params.world_height);
                s.setFill(critter.viewFillColor());
                break;
            case 1: s = new Circle(size/2);
                s.setFill(critter.viewFillColor());
                break;
            case 2: s = new Polygon((20 + x) % Params.world_width, y % Params.world_height, (27 + x) % Params.world_width, (15 + y) % Params.world_height,
                    (13 + x) % Params.world_width, (15 + y) % Params.world_height);
                s.setFill(critter.viewFillColor());
                break;
            case 3: s = new Polygon((20 + x) % Params.world_width, y % Params.world_height, (30 + x) % Params.world_width, (12 + y) % Params.world_height,
                    (20 + x) % Params.world_width, (24 + y) % Params.world_height, (10 + x) % Params.world_width, (12 + y) % Params.world_height);
                s.setFill(critter.viewFillColor());
                break;
            case 4: s = new Polygon((20 + x) % Params.world_width, (5 + y) % Params.world_height, (25 + x) % Params.world_width, (12 + y) % Params.world_height,
                    (30 + x) % Params.world_width, (12 + y) % Params.world_height, (25 + x) % Params.world_width, (16 + y) % Params.world_height,
                    (27 + x) % Params.world_width, (22 + y) % Params.world_height, (20 + x) % Params.world_width, (18 + y) % Params.world_height,
                    (13 + x) % Params.world_width, (22 + y) % Params.world_height, (15 + x) % Params.world_width, (16 + y) % Params.world_height,
                    (10 + x) % Params.world_width, (12 + y) % Params.world_height, (15 + x) % Params.world_width, (12 + y) % Params.world_height);
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
            Main.grid.add(s, critter.getY(), critter.getX()); // add the shape to the grid.
        }

    }
}
