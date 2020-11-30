package shc;

import houseLayout.Room;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import static shp.SmartHomeSecurity.awayModePane;
import static shp.SmartHomeSecurity.displayAwayModePane;
import static shp.SmartHomeSecurity.layout;

/**
 *
 * @author Matthew Giancola (40019131)
 */
public class SmartHomeCore implements SmartHomeSubject{

    public static Room[] roomArray = new Room[10];
    public static GridPane temp = new GridPane();
    public static GridPane aux = new GridPane();
    //front Simulation heading
    static Label outHeading = new Label("Output Console");
    static Label outData = new Label("");
    //active room
    public static Room activeRoom = roomArray[0];

    public static GridPane terminalModule() {
        temp.getChildren().clear();
        temp.add(outHeading, 0, 0);
        //front Simulation heading
        temp.add(outData, 0, 1);
        return temp;
    }

    public static GridPane module() {
        aux.getChildren().clear();
        Button btnSHC = new Button();
        btnSHC.setText("Toogle Light");
        btnSHC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                requestLights();
            }
        });
        aux.add(btnSHC, 0, 0);
        Button btnSHCd = new Button();
        btnSHCd.setText("Toogle Door");
        btnSHCd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                requestDoors();
            }
        });
        aux.add(btnSHCd, 0, 1);
        Button btnSHCw = new Button();
        btnSHCw.setText("Toogle Window");
        btnSHCw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                requestWindows();
            }
        });
        aux.add(btnSHCw, 0, 2);
        aux.add(terminalModule(),0,3);
        return aux;
    }
    
    public static void requestWindows(){
        outData.setText("SHC COMMAND: WINDOWS");
        if (activeRoom != null) {
            activeRoom.blockWindows();
            outData.setText("SHC COMMAND: WINDOWS -|- "+"SUCCESS -|- VALUE: "+activeRoom.windowBlocked);
            return;
        }
        outData.setText("SHC COMMAND: WINDOWS -|- "+"ERROR!");
    }
    
    public static void requestDoors(){
        outData.setText("SHC COMMAND: DOORS");
        if (activeRoom != null) {
            activeRoom.blockDoor();
            outData.setText("SHC COMMAND: DOORS -|- "+"SUCCESS -|- VALUE: "+activeRoom.doorBlocked);
            return;
        }
        outData.setText("SHC COMMAND: DOORS -|- "+"ERROR!");
    }
    
    public static void requestLights(){
        outData.setText("SHC COMMAND: LIGHTS");
        if (activeRoom != null) {
            activeRoom.blockLight();
            outData.setText("SHC COMMAND: LIGHTS -|- "+"SUCCESS -|- VALUE: "+activeRoom.lightBlocked);
            return;
        }
        outData.setText("SHC COMMAND: LIGHTS -|- "+"ERROR!");
    }
    
    public static void requesAuto(){
        outData.setText("SHC COMMAND: AUTO");
        if (activeRoom != null) {
            //activeRoom.blockDoor();
        }
    }

    @Override
    public void setState(Object state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void attach(SmartHomeObserver a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
