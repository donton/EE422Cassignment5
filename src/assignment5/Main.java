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

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	static GridPane grid = new GridPane();
    Button makeButton, stepButton, quitButton;
    VBox buttonBox;
    VBox statsBox;

	@Override
	public void start(Stage primaryStage) {
		try {

			grid.setGridLinesVisible(true);

            makeButton = new Button("Make New Critters");
            stepButton = new Button("World Step");
            quitButton = new Button("Quit");

            makeButton.setOnAction(e->handleMakeAction());
            stepButton.setOnAction(e->handleStepAction());
            quitButton.setOnAction(e->handleQuitAction());

            buttonBox = new VBox(makeButton, stepButton, quitButton);
            buttonBox.setSpacing(10);
            buttonBox.setPadding(new Insets(5));

            Text t = new Text();
            t.setFont(new Font(20));
            t.setText("World Statistics");
            statsBox = new VBox(t);


            //create the BorderPane
            BorderPane root = new BorderPane();
            root.setPadding(new Insets(10));

            //add components to regions of BorderPane
            root.setLeft(buttonBox);
            root.setRight(statsBox);

            root.setCenter(grid);

            Scene scene = new Scene(root, 500, 500);
            primaryStage.setScene(scene);
            primaryStage.show();

			// Paints the icons.
			Painter.paint();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		launch(args);
	}

	public void handleMakeAction()
    {

    }

    public void handleStepAction()
    {

    }

    public void handleQuitAction()
    {
    	Platform.exit();
    }

}
