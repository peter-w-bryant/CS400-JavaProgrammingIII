/**
 * 
 */

/**
 * @author Peter W Bryant
 *
 */
/**
 * 
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import java.util.Random;

public class DessertGame extends Application {
	private int score = 0;
	
    @Override
    public void start(final Stage stage) {
        // Step 3 & 4
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 640, 480);
        stage.setTitle("Dessert in the Desert JavaFX Game");
        
        // Step 5
        Label scoreLabel = new Label("Score: 0");
        borderPane.setTop(scoreLabel);
        BorderPane.setAlignment(scoreLabel, Pos.TOP_LEFT);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            Platform.exit();
        });
        borderPane.setBottom(exitButton);
        BorderPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT);
        
        // Step 6
        Pane pane = new Pane();
        borderPane.setCenter(pane);
        BorderPane.setAlignment(pane, Pos.CENTER);

        // TODO: Step 7-10
        
        //Step 7
        //Add the button with CORRECT spelling
        Button gButton = new Button("Dessert");
        pane.getChildren().add(gButton);
        //Create button array with 7 buttons
        Button[] bButtons = new Button[7];
        
        //Loop through to inititalize with INCORRECT spelling
        //and add them to the pane
        for(int i =0; i<bButtons.length ; i++) {
        	bButtons[i] = new Button("Desert"); 
        	pane.getChildren().add(bButtons[i]);        	
        }
        //Step 8
        //Randomize the location of the buttons
        //Creates a one element array to store the good button in order to pass to the
        //randomizeButtonPositions() method
    	Button[] gbArr = new Button[1];
        gbArr[0] = gButton;
        Random r = new Random();
        randomizeButtonPositions(r, gbArr);
        randomizeButtonPositions(r, bButtons);
        exitButton.requestFocus();
        
        //Step 9
     	//Good Input Event Handler Assignment
    	gButton.addEventHandler(ActionEvent.ACTION, (e) -> {
    	score++;
    	});
    	//Handle Good Input - When the user hits the "Dessert" Button
    	//Good Input Set Action Items
        gButton.setOnAction( (event) -> {
        	  Label newLabel = new Label("Score: " + score);
              borderPane.setTop(newLabel);
              BorderPane.setAlignment(newLabel, Pos.TOP_LEFT);
              randomizeButtonPositions(r, gbArr);
              randomizeButtonPositions(r, bButtons);
              exitButton.requestFocus();
        } );
        
        //Handle Bad Input - When the user hits the "Desert" Buttons
        for(int i =0; i<bButtons.length ; i++) {
        	//Bad Input Event Handler Assignment
        	bButtons[i].addEventHandler(ActionEvent.ACTION, (e) -> {
        	score--;
        	});
        	//Bad Input Set Action Items
	        bButtons[i].setOnAction( (event) -> {
	        	  Label newLabel = new Label("Score: " + score);
	              borderPane.setTop(newLabel);
	              BorderPane.setAlignment(newLabel, Pos.TOP_LEFT);
	              randomizeButtonPositions(r, gbArr);
	              randomizeButtonPositions(r, bButtons);
	              exitButton.requestFocus();
	        } );
        }
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
    
//    Private Helper Method
//    Define a private helper method named randomizeButtonPositions that takes two input parameters in this order: 
//    a java.util.Random random number generator, and an array of Button objects. This method should use the setLayoutX 
//    and setLayoutY methods to randomize the position of each button to be between 0-600 pixels for the X position, and 
//    between 0-400 pixels for the Y position. This method should be called when the program starts running, and then again 
//    after any Dessert or Desert button is pressed.
    void randomizeButtonPositions(java.util.Random r, Button[] buttonArr){
    for(int i =0 ; i<buttonArr.length; i++) {
    	buttonArr[i].setLayoutX(r.nextInt(600));
    	buttonArr[i].setLayoutY(r.nextInt(400)); 
    	}
   }
    
}