package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
    	// button to set date and time
        Button btnSetDate = new Button();
        btnSetDate.setText("Edit Date and Time'");
        btnSetDate.setOnAction(e->SetDateAndTime.display("Edit date", " Set date below"));
        
        
        // button to set temperature
        Button btnSetTemperature = new Button();
        btnSetTemperature.setText("Edit temperature'");
        btnSetTemperature.setOnAction(e->SetTemperature.display("Edit temp", " Set temperature here"));
        
        
       // add stuff 
        VBox root = new VBox();
        root.getChildren().addAll(btnSetDate,btnSetTemperature);
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Welcome!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}