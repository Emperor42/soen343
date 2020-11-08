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

    public static int min;

    public static Label timeLabel;

    public static void display(String title, String message) {

        // set the message and title
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(450);
        window.setMinHeight(450);
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
    
     public static GridPane module() {
         System.out.println("Run");
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

        Label minLabel = new Label("Set Minutes");
        temp.add(minLabel, 0 ,3);
        temp.add(minBox  , 0, 4);


        Button btngetTime = new Button("Set Time Amount for Authority Intervention");
        temp.add(btngetTime   , 0, 7);
        btngetTime.setOnAction(e -> {
                    min = Integer.parseInt(minBox.getValue());
                    temp.getChildren().remove(timeLabel);
                    timeLabel = new Label("Time is set at "+min+" Minutes");
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

        GridPane lightPane = new GridPane();
        displayLightRow(lightPane,0,"KITCHEN LIGHT");           //dummy light
        displayLightRow(lightPane,1,"BATHROOM LIGHT");
        temp.add(lightPane, 0, 2);

        lightControlPane=temp;
        append.add(temp, x, y);
    }

    public static void displayLightRow(GridPane append,int x , String lightName ){

        Label headinglightName = new Label(lightName);
        append.add(headinglightName,0,x);

        HBox timeSetBox = new HBox();

        HBox timeInput1 = new HBox();
        ChoiceBox<String> hrBox1 = new ChoiceBox<>();
        hrBox1.setValue("0");
        for (int i = 1; i < 13; i++) {
            String j = Integer.toString(i);
            hrBox1.getItems().add(j);
        }
        ChoiceBox<String> minBox1 = new ChoiceBox<>();
        minBox1.setValue("00");
        for (int i = 0; i < 60; i++) {
            String j = Integer.toString(i);
            minBox1.getItems().add(j);
        }
        ChoiceBox<String> am_pmBox1 = new ChoiceBox<>();
        am_pmBox1.setValue("am");
        am_pmBox1.getItems().add("am");
        am_pmBox1.getItems().add("pm");

        timeInput1.getChildren().addAll(hrBox1, minBox1, am_pmBox1);

        HBox timeInput2 = new HBox();
        ChoiceBox<String> hrBox2 = new ChoiceBox<>();
        hrBox2.setValue("0");
        for (int i = 1; i < 13; i++) {
            String j = Integer.toString(i);
            hrBox2.getItems().add(j);
        }
        ChoiceBox<String> minBox2 = new ChoiceBox<>();
        minBox2.setValue("00");
        for (int i = 0; i < 60; i++) {
            String j = Integer.toString(i);
            minBox2.getItems().add(j);
        }
        ChoiceBox<String> am_pmBox2 = new ChoiceBox<>();
        am_pmBox2.setValue("am");
        am_pmBox2.getItems().add("am");
        am_pmBox2.getItems().add("pm");

        timeInput2.getChildren().addAll(hrBox2, minBox2, am_pmBox2);

        timeSetBox.getChildren().addAll(timeInput1, new Label(" to "), timeInput2);

        append.add(timeSetBox,1,x);
    }
    

}

