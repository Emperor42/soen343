package application;

import java.util.Calendar;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AdjustableClock extends DigitalClock{
	
    public AdjustableClock() {
        //create default clock which has value of current time
        t = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        setText(" ");
                        String hourString = StringUtilities.pad(2, ' ', time.get(Calendar.HOUR) == 0 ? "12" : time.get(Calendar.HOUR) + "");
                        String minuteString = StringUtilities.pad(2, '0', time.get(Calendar.MINUTE) + "");
                        String secondString = StringUtilities.pad(2, '0', time.get(Calendar.SECOND) + "");
                        String ampmString = time.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
                        setText(hourString + ":" + minuteString + ":" + secondString + " " + ampmString);
                        time.set(Calendar.SECOND, time.get(Calendar.SECOND) + 1);
                    }
                }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        bindToCurrentTime();
    }
	
	public AdjustableClock(int duration) {
        //create default clock which has value of current time
        t = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        setText(" ");
                        String hourString = StringUtilities.pad(2, ' ', time.get(Calendar.HOUR) == 0 ? "12" : time.get(Calendar.HOUR) + "");
                        String minuteString = StringUtilities.pad(2, '0', time.get(Calendar.MINUTE) + "");
                        String secondString = StringUtilities.pad(2, '0', time.get(Calendar.SECOND) + "");
                        String ampmString = time.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
                        setText(hourString + ":" + minuteString + ":" + secondString + " " + ampmString);
                        time.set(Calendar.SECOND, time.get(Calendar.SECOND) + 1);
                    }
                }
                ),
                new KeyFrame(Duration.millis(duration))
        );
        bindToCurrentTime();
    }

//	public void displayRoomsandOccupants(GridPane append, int x, int y, Stage primaryStage, Label occupantHeading, VBox occupantBox) {
//		
//	}
}
