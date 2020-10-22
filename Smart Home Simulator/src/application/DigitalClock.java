//ISSUE 1 CLEAR CODE IS WORKING
package application;

import static application.SetDateAndTime.d;
import java.io.IOException;
import java.net.URL;
import javafx.animation.*;
import javafx.event.*;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.Calendar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Creates a digital clock display as a simple label. Format of the clock
 * display is hh:mm:ss aa, where: hh Hour in am/pm (1-12) mm Minute in hour ss
 * Second in minute aa Am/pm marker Time is the system time for the local
 * timezone. Written by Justin Loh King Wei 40073776
 */
public class DigitalClock extends Label {

    public DigitalClock() {
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

    public void bindToCurrentTime() {
        time = Calendar.getInstance();
        bindToThisTime(time.get(Calendar.HOUR), time.get(Calendar.MINUTE), time.get(Calendar.SECOND), time.get(Calendar.AM_PM));
    }

    public static Timeline t;
    Calendar time;

    public void bindToThisTime(int hr, int min, int sec, int am_pm) {

        time = Calendar.getInstance();
        time.set(Calendar.HOUR, hr);
        time.set(Calendar.MINUTE, min);
        time.set(Calendar.SECOND, sec);
        time.set(Calendar.AM_PM, am_pm);

        t.setCycleCount(Animation.INDEFINITE);
        t.play();
    }
    
    public Calendar getTime(){
        return time;
    }
    
}

class StringUtilities {

    /**
     * Creates a string left padded to the specified width with the supplied
     * padding character.
     *
     * @param fieldWidth the length of the resultant padded string.
     * @param padChar a character to use for padding the string.
     * @param s the string to be padded.
     * @return the padded string.
     */
    public static String pad(int fieldWidth, char padChar, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < fieldWidth; i++) {
            sb.append(padChar);
        }
        sb.append(s);

        return sb.toString();
    }
    
    
}
