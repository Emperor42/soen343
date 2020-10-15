package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;
public class SetDateAndTime {
	static DigitalClock d = new DigitalClock();
	
	public static void display(String title,String message){
		
		// set the message and title
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Label label = new Label(message);
		
		
		//Create Datepicker Item
		DatePicker datePicker = new DatePicker();
		
		
		//Create button to set date
		Button btndate = new Button("Set the Date");
		
		
		
		//Input drop down boxes for hr min and am/pm
		HBox timeInputs = new HBox();
		ChoiceBox<String> hrBox = new ChoiceBox<>();
	     hrBox.setValue("0");
		for(int i = 1 ;i<13;i++) {
			String j = Integer.toString(i);
			hrBox.getItems().add(j);
		}
		ChoiceBox<String> minBox = new ChoiceBox<>();
	minBox.setValue("00");
		for(int i = 0 ;i<60;i++) {
			String j = Integer.toString(i);
			minBox .getItems().add(j);
		}
		ChoiceBox<String> am_pmBox = new ChoiceBox<>();
		am_pmBox.setValue("am");
		am_pmBox.getItems().add("am");
		am_pmBox.getItems().add("pm");
		
		
		
		timeInputs.getChildren().addAll(hrBox,minBox,am_pmBox);
		
		//Create a button to set time
		Button btnTime = new Button("Set a time'");
		
		//create default clock which has value of current time
		d.bindToCurrentTime();
		
		//add all children to the layout
		VBox layout = new VBox();
        layout.getChildren().addAll(label,datePicker,d,timeInputs,btndate,btnTime);
        Scene scene = new Scene(layout, 300, 250);
		window.setScene(scene);
		window.show();
		
		// button to apply changes to times
		btndate.setOnAction(e->{
			LocalDate localDate = datePicker.getValue();
			Label dateLabel = new Label(localDate.toString());
			changeDate(layout,dateLabel);
		});
		
		// button to apply changes to date
		btnTime.setOnAction(e->{
			int hr=  Integer.parseInt(hrBox.getValue());
			int min= Integer.parseInt(minBox.getValue());
			int am_pm= am_pmBox.getValue()=="am"? 0:1;
			changeTime(layout,hr,min,Calendar.getInstance().SECOND,am_pm);}
				);
	}
	// method to apply changes to time
	public static void changeTime(VBox v,int hr,int min ,int sec, int am_pm) {
		v.getChildren().remove(2);
		DigitalClock c= new DigitalClock();
		c.bindToThisTime(hr,min,sec,am_pm);
		v.getChildren().add(2, c);
		
	}
	
	// method to apply changes to date
	public static void changeDate(VBox v,Label l) {;
		v.getChildren().add(l);
	}
	
}
