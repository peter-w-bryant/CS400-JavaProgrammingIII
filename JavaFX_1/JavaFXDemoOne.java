import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class JavaFXDemoOne extends Application {
    @Override
    public void start(Stage stage) {
        Label label = new Label("Press this button: ");
        label.setLayoutX(150);
        
       // Group group = new Group(label);
        BorderPane borderPane = new BorderPane();
        //GridPane gridPane = new GridPane();
       
        //VBox Creation
        VBox vbox = new VBox();
        vbox.setSpacing(8);
        vbox.setAlignment(Pos.TOP_CENTER);
        
        //Add Event Filter and Handler
        vbox.addEventFilter(ActionEvent.ACTION, (e) ->{
	        	
	        System.out.println("vbox filtering action event");
	        //e.consume();
	        
        });
        
        vbox.addEventHandler(ActionEvent.ACTION, (e) -> {
        	System.out.println("vbox handling action event");
        	//e.consume();
        });
        
        borderPane.setTop(label);
        borderPane.setRight(vbox);
        
//        gridPane.getChildren().add(label);
//        gridPane.getChildren().add(vbox);
//        
//        gridPane.setRowIndex(vbox, 1);
//        gridPane.setColumnIndex(vbox, 1);
        
        Button[] button = new Button[3];
        for(int i =0 ; i<button.length; i++) {
        	 button[i] = new Button("Press me.");
        	 button[i].setLayoutX(170);
             button[i].setLayoutY(24+i*24); 
             
             button[i].addEventFilter(ActionEvent.ACTION, (e) -> 
             {
            	 System.out.println("button filtering action event");
            	// e.consume();
             });
             
             button[i].addEventHandler(ActionEvent.ACTION, (e) -> System.out.println("button handling action event"));

             
             final int index = i;
             button[i].setOnAction( (event) -> {
                 //System.out.println("Button was pressed: "+event);
                 button[index].setText(new String[]{"trick", "or", "treat"}[index]);
                 button[index].setStyle("-fx-background-color: orange;");
                 vbox.getChildren().remove(label);
             } );
             vbox.getChildren().add(button[i]);
        }
        label.addEventFilter(MouseEvent.MOUSE_PRESSED, (e) -> {
        	for(int i = 0 ; i < button.length ; i++) {
        		button[i].fireEvent(new ActionEvent());
        	}
        });
           
        Scene scene = new Scene(borderPane,400,300);
        stage.setScene(scene);
        stage.setTitle("Hello window");
        stage.show();
    }
    
    public static void main(String[] args) {
        Application.launch();
    }
}