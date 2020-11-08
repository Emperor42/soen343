package shc;

import houseLayout.Room;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static shp.SmartHomeSecurity.layout;

/**
 *
 * @author Matthew Giancola (40019131)
 */
public class SmartHomeCore {
    public static boolean mainDoorClosed=true;
    public static boolean mainDoorLocked = false;
    public static boolean outdoorLights = true;
    public static boolean entranceLights = true;
    public static boolean outdoorAuto =false;
    public static boolean entranceAuto = false;
    
    public static GridPane pane = new GridPane();
    
    //saving pointers to the rooms and such
    private static Room[] houseRooms;
    

    
    public static GridPane update(Room[] roomArray){
        houseRooms = roomArray;//blindly point to this new array
        //clear the children
        pane.getChildren().clear();
        //the header
        Label callHeader = new Label("ROOM");
        Label lightHeader = new Label("LIGHTS");
        Label doorHeader = new Label("DOORS");
        Label windowHeader = new Label("WINDOWS");
        pane.add(callHeader, 0, 0);
        pane.add(lightHeader, 1, 0);
        pane.add(doorHeader, 2, 0);
        pane.add(windowHeader, 3, 0);
        //the outdoor and other areas, 
        Label backyard = new Label("Backyard");
        pane.add(backyard, 0, 1);
        // Button to set backyard lights
        Button setLight = new Button();
        setLight.setText("Lights On/Off");
        setLight.setOnAction(e -> {
            outdoorLights = !outdoorLights;
        });
        pane.add(setLight, 1, 1);
        // Button to set backyard lights
        Button setAutoLight = new Button();
        setAutoLight.setText("Lights Auto On/Off");
        setAutoLight.setOnAction(e -> {
            outdoorAuto = !outdoorAuto;
        });
        pane.add(setAutoLight, 1, 2);
        //Entranve
        Label entrance = new Label("Entrance");
        pane.add(entrance, 0, 3);
        // Button to set indoor lights
        Button setELight = new Button();
        setELight.setText("Doors On/Off");
        setELight.setOnAction(e -> {
            entranceLights = !entranceLights;
        });
        pane.add(setELight, 1, 3);
        // Button to set doors lights
        Button setEAutoLight = new Button();
        setEAutoLight.setText("Lights Auto On/Off");
        setEAutoLight.setOnAction(e -> {
            entranceAuto = !entranceAuto;
        });
        pane.add(setEAutoLight, 1, 4);
        //DOORS
        // Button to set indoor lights
        Button setDoor = new Button();
        setDoor.setText("Doors Closed");
        setDoor.setOnAction(e -> {
            mainDoorClosed = !mainDoorClosed;
        });
        pane.add(setDoor, 2, 3);
        // Button to set doors lights
        Button setLock = new Button();
        setLock.setText("Door Lock");
        setLock.setOnAction(e -> {
            mainDoorLocked = !mainDoorLocked;
        });
        pane.add(setLock, 2, 4);
        //display the rooms and such
        for(int i=0;i<houseRooms.length;i++){
            Room tmp = houseRooms[i];
            //the name of the room
            Label name = new Label(houseRooms[i].getName());
            pane.add(name, 0, 5+(i*2));
            //the lights in the room
            Button light = new Button();
            light.setText("Lights ON/OFF");
            light.setOnAction(e -> {
                tmp.blockLights();
            });
            pane.add(light, 1, 5+(i*2));
            //the auto setting for the button
            Button aLight = new Button();
            aLight.setText("Auto ON/OFF");
            aLight.setOnAction(e -> {
                tmp.auto();
            });
            pane.add(aLight, 1, 6+(i*2));
            //the lights in the room
            Button door = new Button();
            door.setText("Door ON/OFF");
            door.setOnAction(e -> {
                tmp.blockDoors();
            });
            pane.add(door, 2, 5+(i*2));
           //the lights in the room
            Button window = new Button();
            window.setText("Window ON/OFF");
            window.setOnAction(e -> {
                tmp.blockWindows();
            });
            pane.add(window, 3, 5+(i*2));
        }
        return pane;
          
    }


    public static GridPane layout = new GridPane();
    public static GridPane awayModePane = new GridPane();
    public static GridPane lightControlPane = new GridPane();

    public static Button saveAndClose = new Button("Save All Changes and Close Window"); // set static in case need to remove/readd

    public static int hr;
    public static int min;

    public static Label timeLabel;

    public static void display(String title, String message, Room[] rooms) {
        update(rooms);
        // set the message and title
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(350);
        layout.add(pane, 0, 1);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    
}


    
    

