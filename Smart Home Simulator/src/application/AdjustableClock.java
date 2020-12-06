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

/**
 *
 * @author Justin Loh 40073776
 */
public class AdjustableClock extends DigitalClock {
    public static double rate =1 ;

    public void changeSpeed(double speed) {
        if (speed == 0.0) {
            t.setRate(0.5);
            rate = 0.5;
        };
        if (speed == 1.0) {
            t.setRate(1);
            rate = 1.0;
        }
        if (speed == 2.0) {
            t.setRate(100);
            rate = 100;
        }
        if (speed == 3.0) {
            t.setRate(5000);
            rate = 5000;
        }
    }

}
