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

import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.scene.layout.Priority.ALWAYS;

public class Main extends Application {
	static GridPane grid = new GridPane();
<<<<<<< HEAD
    Button makeButton, stepButton, step100Button, step1000Button, quitButton, statsButton;
=======
    Button makeButton, stepButton, quitButton, statsButton, allButton;
>>>>>>> 6a68674aae20e8ab8a8138136fce156c65494703
    VBox buttonBox, statsBox, makeBox;
    Label enterTypeCont, enterTypeStats, enterNumber, statsResults;
    TextField typeCont, typeStats, number;
    Region buffer1, buffer2;
    static Text textArea;

	@Override
	public void start(Stage primaryStage) {
		try {

            grid.setStyle("-fx-background-color: #e9ecee;");
            grid.setPadding(new Insets(5));
            grid.setPrefSize(Params.world_width, Params.world_height);

            final int width = Params.world_width;
            final int height = Params.world_height;

            for (int j = 0; j < width; j++) {
                ColumnConstraints cc = new ColumnConstraints();
                cc.setHgrow(ALWAYS);
                grid.getColumnConstraints().add(cc);
            }

            for (int j = 0; j < height; j++) {
                RowConstraints rc = new RowConstraints();
                rc.setVgrow(ALWAYS);
                grid.getRowConstraints().add(rc);
            }

            makeButton = new Button("Make New Critters");
            stepButton = new Button("World Step");
            step100Button = new Button("World Step 100");
            step1000Button = new Button("World Step 1000");
            quitButton = new Button("Quit");
            
            makeButton.setOnAction(e->handleMakeAction());
            stepButton.setOnAction(e->handleStepAction());
            step100Button.setOnAction(e->handleStep100Action());
            step1000Button.setOnAction(e->handleStep1000Action());
            quitButton.setOnAction(e->handleQuitAction());

            enterTypeCont = new Label("Enter A Valid Critter Name: ");
            typeCont = new TextField();
            
            enterTypeStats = new Label("Enter A Valid Critter Name: ");
            typeStats = new TextField();

            enterNumber = new Label("How many?");
            number = new TextField();

            Text controlTitle = new Text();
            controlTitle.setFont(new Font(20));
            controlTitle.setText("Controller");
            makeBox = new VBox(controlTitle, enterTypeCont, typeCont, enterNumber, number, makeButton);

            buffer1 = new Region();
            buffer2 = new Region();
            
            buttonBox = new VBox(makeBox, buffer1, stepButton, step100Button, step1000Button, buffer2, quitButton);
            buttonBox.setSpacing(10);
            buttonBox.setPadding(new Insets(10));

            Text statsTitle = new Text();
            statsTitle.setFont(new Font(20));
            statsTitle.setText("World Statistics");

            textArea = new Text("");
            typeStats.setPrefColumnCount(30);

            statsResults = new Label("Results:");
            allButton = new Button ("View World Stats");
            allButton.setOnAction(e->handleWorldStatsAction());
            statsButton = new Button("View Stats");
            statsButton.setOnAction(e->handleStatsAction());
            
            statsBox = new VBox(statsTitle, allButton, enterTypeStats, typeStats, statsButton, statsResults, textArea);
            statsBox.setSpacing(10);
            statsBox.setPadding(new Insets(10));

            //create the BorderPane
            BorderPane root = new BorderPane();
            root.setPadding(new Insets(10));

            //add components to regions of BorderPane
            root.setLeft(buttonBox);
            root.setRight(statsBox);
            root.setCenter(grid);

            Scene scene = new Scene(root, 800, 600);
            primaryStage.setTitle("Critters 2!");
            primaryStage.setScene(scene);
            primaryStage.show();

			// Paints the icons.
			Painter.displayWorld();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) { launch(args); }

	public void handleMakeAction()
    {
        Integer numCritters;
        if (typeCont.getText() != null && number.getText().isEmpty() == false)
        {
            if (number.getText() == null | number.getText().isEmpty())
            {
                numCritters = 1;
            }
            else
            {
                numCritters = Integer.valueOf(number.getText().toString());
            }
            while (numCritters != 0)
            {
                try
                {
                Critter.makeCritter(typeCont.getText().toString());
                }
                catch (InvalidCritterException e)
                {
                    return;
                }
                numCritters -= 1;
            }

        }
        Painter.displayWorld();
    }

    public void handleStepAction()
    {
        Critter.worldTimeStep();
        Painter.displayWorld();
    }

    public void handleStep100Action()
    {
        for (int i = 0; i < 100; i ++) {
        	Critter.worldTimeStep();
        }
    }
    
    public void handleStep1000Action()
    {
        for (int i = 0; i < 1000; i ++) {
        	Critter.worldTimeStep();
        }
    }
    
    public void handleQuitAction()
    {
    	Platform.exit();
    }

    public void handleStatsAction(){
        if (typeStats.getText() != null && typeStats.getText().isEmpty() == false)
            try {
                Critter.runStats(Critter.getInstances(typeStats.getText()));
            }catch (InvalidCritterException e){
                return;
        }
    }

    public void handleWorldStatsAction(){
        Critter.runStats(Critter.getPop());
    }

}
