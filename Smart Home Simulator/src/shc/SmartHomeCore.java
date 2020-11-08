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
public class SmartHomeCore {
    public static Room[] roomArray = new Room[10];
    public static GridPane temp = new GridPane();
    public static GridPane aux = new GridPane();
    //front Simulation heading
    static Label outHeading = new Label("Output Console");
    static Label outData = new Label("");
    //active room
    public static Room activeRoom= roomArray[0];
    
    
    public static GridPane terminalModule(){
        temp.add(outHeading, 0, 0);
        //front Simulation heading
        temp.add(outData, 0, 1);
        return temp;
    }
    
    public static GridPane module(){
        Button btnSHC = new Button();
        btnSHC.setText("Toogle Light");
        btnSHC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (activeRoom!=null){
                    activeRoom.blockLight();
                }
            }
        });
        aux.add(btnSHC, 0, 0);
        Button btnSHCd = new Button();
        btnSHCd.setText("Toogle Door");
        btnSHCd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (activeRoom!=null){
                    activeRoom.blockLight();
                }
            }
        });
        aux.add(btnSHCd, 0, 1);
        return aux;
    }
    

}
