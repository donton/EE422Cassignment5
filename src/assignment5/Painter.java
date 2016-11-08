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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.io.File;
import java.util.ArrayList;

import static javafx.scene.layout.Priority.ALWAYS;

public class Painter {

    /*
     * Returns a square or a circle, according to shapeIndex
     */
    static Shape getIcon(Critter critter, int shapeIndex) {

        /*
        public enum CritterShape {
            CIRCLE,
            SQUARE,
            TRIANGLE,
            DIAMOND,
            STAR
        }
         */
        Shape s = null;
        int size = 10;
        int x = critter.getX();
        int y = critter.getY();

        switch(shapeIndex) {
            case 0: s = new Circle(x, y, size/2);
                s.setFill(critter.viewFillColor());
                break;
            case 1: s = new Rectangle(x, y, size, size);
                s.setFill(critter.viewFillColor());
                break;
            case 2: s = new Polygon((20 + x), y, (27 + x), (15 + y), (13 + x), (15 + y));
                s.setFill(critter.viewFillColor());
                break;
            case 3: s = new Polygon((20 + x), y, (30 + x), (12 + y), (20 + x), (24 + y), (10 + x), (12 + y));
                s.setFill(critter.viewFillColor());
                break;
            case 4: s = new Polygon((20 + x), (5 + y), (25 + x), (12 + y),
                    (30 + x), (12 + y), (25 + x), (16 + y),
                    (27 + x), (22 + y), (20 + x), (18 + y),
                    (13 + x), (22 + y), (15 + x), (16 + y),
                    (10 + x), (12 + y), (15 + x), (12 + y));
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
            Main.grid.add(s, critter.getX(), critter.getY(), 1, 1); // add the shape to the grid.
            Main.grid.setHgrow(s, ALWAYS);
            Main.grid.setVgrow(s, ALWAYS);
        }

    }

    public static ObservableList<String> getClasses (){

        // grab names of class files under current directory and any subdirectories
        File directory = new File("src/assignment5");
        File[] listOfFiles = directory.listFiles();
        ObservableList<String> classes = FXCollections.observableArrayList();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].toString().endsWith(".java")) {
                classes.add(listOfFiles[i].toString());
            } else if (listOfFiles[i].isDirectory()) {
                File subDir = new File(listOfFiles[i].toString());
                File[] subFiles = subDir.listFiles();
                for (int j = 0; i < subFiles.length; j++) {
                    if (subFiles[i].isFile() && subFiles[i].toString().endsWith(".java")) {
                        classes.add(subFiles[i].toString());
                    }
                }
            }
        }

        // strip off file paths, keep file names
        for (int i = 0; i < classes.size(); i++)
        {
            int index = classes.get(i).toString().lastIndexOf('\\');
            String stripped = classes.get(i).toString().substring(index + 1);
            index = stripped.lastIndexOf('.');
            if (index != -1) {
                String secondStripped = stripped.substring(0, index);
                classes.set(i, secondStripped);
            }
            else
            {
                classes.set(i, stripped);
            }
        }

        String myPackage = Critter.class.getPackage().toString().split(" ")[1];

        for (int i = 0; i < classes.size(); i++) {
            try {
                String className = myPackage + "." + classes.get(i);
                String critterClassName = myPackage + ".Critter";
                Class<?> newCritter = Class.forName(className);
                Class<?> critterClass = Class.forName(critterClassName);
                if (!critterClass.isAssignableFrom(newCritter)) {
                    classes.remove(i);
                    i -= 1;
                }
            } catch (Exception e)
            {
                classes.remove(i);
                System.out.println("caught exception");
            }
        }

        return classes;
    }
}
