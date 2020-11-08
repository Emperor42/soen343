package shp;


import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;




/**
 *
 * @author Justin Loh 40073776
 */
public class SmartHomeSecurity {

    public static GridPane layout = new GridPane();
    public static GridPane awayModePane = new GridPane();
    public static GridPane lightControlPane = new GridPane();

    public static Button saveAndClose = new Button("Save All Changes and Close Window"); // set static in case need to remove/readd

    public static int hr;
    public static int min;

    public static Label timeLabel;

    public static void display(String title, String message) {

        // set the message and title
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(350);
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

        saveAndClose.setOnAction(event -> {
            window.close();
        });
        layout.add(saveAndClose ,0,6);

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

        HBox timeInputs = new HBox();
        ChoiceBox<String> hrBox = new ChoiceBox<>();
        hrBox.setValue("0");
        for (int i = 1; i < 13; i++) {
            String j = Integer.toString(i);
            hrBox.getItems().add(j);
        }
        ChoiceBox<String> minBox = new ChoiceBox<>();
        minBox.setValue("00");
        for (int i = 0; i < 60; i++) {
            String j = Integer.toString(i);
            minBox.getItems().add(j);
        }

        Label hoursLabel = new Label("Set Hours");
        temp.add(hoursLabel, 0 ,3);
        temp.add(hrBox, 0, 4);
        Label minLabel = new Label("Set Minutes");
        temp.add(minLabel, 0 ,5);
        temp.add(minBox  , 0, 6);


        Button btngetTime = new Button("Set Time Amount for Authority Intervention");
        temp.add(btngetTime   , 0, 7);
        btngetTime.setOnAction(e -> {
                    hr = Integer.parseInt(hrBox.getValue());
                    min = Integer.parseInt(minBox.getValue());
                    temp.getChildren().remove(timeLabel);
                    timeLabel = new Label("Set for: "+hr+" Hours and "+min+" Minutes");
                    temp.add(timeLabel  , 0, 8);
                });

        displayLightControlPanel(temp, 0, 10);

        awayModePane=temp;
        append.add(temp, x, y);
    }

    public static void displayLightControlPanel(GridPane append, int x, int y){
        GridPane temp = new GridPane();
        Label headingLightControl= new Label("This is the light control panel");
        temp.add(headingLightControl, 0, 1);


        lightControlPane=temp;
        append.add(temp, x, y);
    }

    
        public static GridPane update(){
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
            return layout;
        }
    
}

