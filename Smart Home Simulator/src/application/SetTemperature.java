package application;

import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SetTemperature {

	public static void display(String title,String message){
		// set the message and title
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Label label = new Label(message);
		
		
		//TODO ADD Temp STUFF
		
		
		//add stuff to the layout
		VBox layout = new VBox(20);
        layout.getChildren().addAll(label);
        Scene scene = new Scene(layout, 300, 250);
		window.setScene(scene);
		window.showAndWait();
	}
}
