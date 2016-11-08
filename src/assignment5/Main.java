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

import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	static GridPane grid = new GridPane();

    Button makeButton, stepButton, moreStepsButton, quitButton, statsButton, allButton, seedButton, animationStart, animationStop;
    VBox controllerBox, statsBox;
    Label enterTypeCont, enterTypeStats, enterNumber, statsResults, enterSteps, enterSeed, addAction, runAction, enterAnimationSpeed;
    TextField typeCont, typeStats, number, numSteps, seed, animationSpeed;
    Region buffer1;
    static Text textArea;
    ComboBox classNames1, classNames2;
    static boolean stopFlag = false;

	@Override
	public void start(Stage primaryStage) {
		try {

            grid.setStyle("-fx-background-color: black, #e9ecee; -fx-background-insets: 0, 2;");

            // create buttons and their labels
            makeButton = new Button("Make New Critters");
            stepButton = new Button("One World Step");
            moreStepsButton = new Button("Do World Steps");
            quitButton = new Button("Quit");
            seedButton = new Button("Set Seed");
            animationStart = new Button("Start Animation");
            animationStop = new Button("Stop Animation");

            // set action handlers for all buttons
            makeButton.setOnAction(e->handleMakeAction());
            stepButton.setOnAction(e->handleStepAction());
            moreStepsButton.setOnAction(e->handleMoreStepsAction());
            quitButton.setOnAction(e->handleQuitAction());
            seedButton.setOnAction(e->handleSeedAction());
            animationStart.setOnAction(e->handleAnimationStart());

            // set contents of drop down boxes
            classNames1 = new ComboBox();
            classNames1.setItems(Painter.getClasses());
            classNames2 = new ComboBox();
            classNames2.setItems(Painter.getClasses());

            // setup for controller box
            enterTypeCont = new Label("Select A Critter: ");
            typeCont = new TextField();
            
            enterSteps = new Label("OR\n\nEnter A Valid Step Number: ");
            numSteps = new TextField();
            
            enterTypeStats = new Label("Select A Critter: ");
            typeStats = new TextField();
            
            enterAnimationSpeed = new Label("Enter A Valid Animation Speed: ");
            enterAnimationSpeed.setFont(new Font(15));
            animationSpeed = new TextField();

            enterNumber = new Label("How many?");
            number = new TextField();

            enterSeed = new Label("Enter a seed");
            enterSeed.setFont(new Font(15));
            seed = new TextField();

            addAction = new Label("Add new Critters");
            addAction.setFont(new Font(15));

            runAction = new Label("Do a World Step");
            runAction.setFont(new Font(15));

            Text controlTitle = new Text();
            controlTitle.setFont(new Font(20));
            controlTitle.setText("Change the Critter World");

            buffer1 = new Region();

            controllerBox = new VBox(controlTitle, addAction, enterTypeCont, classNames1, enterNumber, number, makeButton, buffer1, runAction, stepButton, enterSteps, numSteps,
                    moreStepsButton, enterAnimationSpeed, animationSpeed, animationStart, animationStop, enterSeed, seed, seedButton, quitButton);
            controllerBox.setSpacing(10);
            controllerBox.setPadding(new Insets(10));

            // stats panel setup
            Text statsTitle = new Text();
            statsTitle.setFont(new Font(20));
            statsTitle.setText("World Statistics");

            textArea = new Text("");
            typeStats.setPrefColumnCount(20);

            statsResults = new Label("Results:");
            allButton = new Button ("View World Stats");
            allButton.setOnAction(e->handleWorldStatsAction());
            statsButton = new Button("View Stats");
            statsButton.setOnAction(e->handleStatsAction());
            
            statsBox = new VBox(statsTitle, allButton, enterTypeStats, classNames2, statsButton, statsResults, textArea);
            statsBox.setSpacing(10);
            statsBox.setPadding(new Insets(10));

            //create the BorderPane that holds all the content from above
            BorderPane root = new BorderPane();
            root.setPadding(new Insets(10));

            //add components to regions of BorderPane
            root.setLeft(controllerBox);
            root.setRight(statsBox);
            root.setCenter(grid);

            Scene scene = new Scene(root, 800, 600);
            primaryStage.setTitle("Critters 2!");
            primaryStage.setScene(scene);
            primaryStage.show();

			// Paints the icons (basically the world in general)
			Painter.displayWorld();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) { launch(args); }

	public void handleMakeAction()
    {
        Integer numCritters;
        String className = classNames1.getValue().toString();
        if (className != null && number.getText().isEmpty() == false)
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
                Critter.makeCritter(className);
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

    public void handleMoreStepsAction()
    {
        for (int i = 0; i < Integer.parseInt(numSteps.getText()); i ++) {
        	Critter.worldTimeStep();
        }
        Painter.displayWorld();
    }
    
    public void handleQuitAction()
    {
    	Platform.exit();
    }

    public void handleStatsAction(){
        String classStats = classNames2.getValue().toString();
        if (classStats != null && classStats.isEmpty() == false)
            try {
                Critter.runStats(Critter.getInstances(classStats));
            }catch (InvalidCritterException e){
                return;
        }
    }

    public void handleAnimationStart() {
    	int speed = Integer.parseInt(animationSpeed.getText());
    	
	    while(!stopFlag) {	
    		animationStop.setOnAction(e->stopFlag = true);
	    	for (int i = 0; i < speed; i++) {
	    		Critter.worldTimeStep();
	    		animationStop.setOnAction(e->stopFlag = true);
	    	}
	    	for (int i = 0; i < 1000000000; i++) {
	    	}
	    	animationStop.setOnAction(e->stopFlag = true);
	    	Painter.displayWorld();
	    }
    }
 
    public void handleAnimationStop() {
    	
    }
    
    public void handleWorldStatsAction(){
        Critter.runStats(Critter.getPop());
    }

    public void handleSeedAction(){
        String newSeed = seed.getText();
        Integer seed = Integer.getInteger(newSeed);
        Critter.setSeed(Integer.toUnsignedLong(seed));
    }

}
