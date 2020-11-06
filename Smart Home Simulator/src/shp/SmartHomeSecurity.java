package shp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SmartHomeSecurity {

    public static GridPane layout = new GridPane();
    public static GridPane awayModePane = new GridPane();

    public static void display(String title, String message) {

        // set the message and title
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(300);
        Label label = new Label(message);
        layout.add(label,0,1);
        Label headingAwayMode = new Label("Set Away Mode :");
        layout.add(headingAwayMode,0,2);

        ToggleButton buttonOn = new ToggleButton("ON");
        ToggleButton buttonOff = new ToggleButton("OFF");
        HBox toggleBox = new HBox();
        toggleBox.getChildren().addAll(buttonOn,buttonOff);

        ToggleGroup group = new ToggleGroup();
        buttonOn .setToggleGroup(group);
        buttonOff.setToggleGroup(group);
        buttonOff.setSelected(true);
        layout.add(toggleBox,0,4);

        buttonOn.setOnAction(event -> {
            System.out.println("ON");
            displayAwayModePane(layout, 0,5);
        });

        buttonOff.setOnAction(event -> {
            System.out.println("OFF");
            layout.getChildren().remove(awayModePane);
        });

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    public static void displayAwayModePane(GridPane append, int x, int y){
        GridPane temp = new GridPane();

        Label headingAwayMode= new Label("You have turned on AWAY MODE");
        temp.add(headingAwayMode, 0, 1);
        Label headingTimePassed = new Label("Set time passed before alerting authorities :");
        temp.add(headingTimePassed , 0, 2);

        awayModePane=temp;
        append.add(temp, x, y);
    }


}

