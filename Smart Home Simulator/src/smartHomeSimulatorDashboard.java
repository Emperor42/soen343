import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Matthew Giancola 40019131
 */
public class smartHomeSimulatorDashboard extends Application {
    
    public boolean runSimulation= false;
    public int activeModule = 0;
    
    
    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        //for the simulation pane
        displaySimulationPane("Parent", "Kitchen", 15, root);
        //for the module pane
        displayModuleTabs(root,1,0);
        displayModuleInterface(activeModule,1,1,root);
        
        displayOutputTerminal(root, 1,3,"This is some output data");
        
        displayOutputSimulationView(root, 2,1,"This is my house");
        
        Scene scene = new Scene(root, 900, 300);
        
        primaryStage.setTitle("Smart Home Simulator -- Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Use a numerical value to display different parts of the interface to the GridPane
     * TODO: Make modular using some interface or other component
     * @param module which module to use.
     * @param pane the GridPane to use.
     * @param x the x position on the given GridPane 
     * @param y the y position on the given GridPane
     * @author Matthew Giancola 40019131
     */
    public void displayModuleInterface(int module,int x, int y, GridPane pane){
        GridPane temp = new GridPane();
        switch (module) {
            case 0:
                Button btn = new Button();
                btn.setText("Say 'Hello World'");
                btn.setOnAction(new EventHandler<ActionEvent>() {    
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Hello World!");
                    }
                });
                temp.add(btn, 0,0);

                Button btn1 = new Button();
                btn1.setText("Say Not 'Hello World'");
                btn1.setOnAction(new EventHandler<ActionEvent>() {    
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Not Hello World!");
                    }
                });
                temp.add(btn1, 1,0);
                break;
        }
        pane.add(temp,x,y);
    }
    
    /**
     * 
     * @param user
     * @param location
     * @param outTemp
     * @param temp 
     * @author Matthew Giancola 40019131
     */
    public void displaySimulationPane(String user,String location, int outTemp, GridPane temp){
        //front Simulation heading
        Label simHeading = new Label("Simulation");
        temp.add(simHeading,0,0);
        //Simulation On and Off Button
        Button simOnOff = new Button();
        simOnOff.setText("OFF");
        simOnOff.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
                runSimulation=!runSimulation;
                if(!runSimulation){
                    simOnOff.setText("OFF");
                }
                else {
                    simOnOff.setText("ON");
                }
            }
        });
        temp.add(simOnOff, 0,1);
        //User Name
        Label userName= new Label(user);
        temp.add(userName,0,2);
        //User Location
        Label userLocationHeader= new Label("Location");
        temp.add(userLocationHeader,0,3);
        Label userLocation= new Label(location);
        temp.add(userLocation,0,4);
        //Outdoor Temperature
        Label tempHeader= new Label("Outside Temperature");
        temp.add(tempHeader,0,5);
        Label tempOut= new Label(outTemp+" C");
        temp.add(tempOut,0,6);
        //time and Date
        Label dateTime = new Label("NOW -- NEEDS TO BE PROPERLY SET!");
        temp.add(dateTime, 0, 7);
        
    }
    
    /**
     * 
     * @param append
     * @param x
     * @param y 
     * @author Matthew Giancola 40019131
     */
    public void displayModuleTabs(GridPane append, int x, int y){
        GridPane temp = new GridPane();
        //Temp will make properly modulr later
        Button btnSHS = new Button();
        btnSHS.setText("SHS");
        btnSHS.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
            }
        });
        temp.add(btnSHS, 1,0);
        Button btnSHC = new Button();
        btnSHC.setText("SHC");
        btnSHC.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
            }
        });
        temp.add(btnSHC, 2,0);
        Button btnSHP = new Button();
        btnSHP.setText("SHP");
        btnSHP.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
            }
        });
        temp.add(btnSHP, 3,0);
        Button btnSHH = new Button();
        btnSHH.setText("SHH");
        btnSHH.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
            }
        });
        temp.add(btnSHH, 4,0);
        Button btnPLUS = new Button();
        btnPLUS.setText("+");
        btnPLUS.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
            }
        });
        temp.add(btnPLUS, 5,0);
        append.add(temp, x,y);
    }
    
    /**
     * 
     * @param append
     * @param x
     * @param y
     * @param data 
     * @author Matthew Giancola 40019131
     */
    public void displayOutputTerminal(GridPane append, int x, int y, String data){
        GridPane temp = new GridPane();
        //front Simulation heading
        Label outHeading = new Label("Output Console");
        temp.add(outHeading,0,0);
        //front Simulation heading
        Label outData = new Label(data);
        temp.add(outData,0,1);
        append.add(temp, x,y);
    }
    
    /**
     * 
     * @param append
     * @param x
     * @param y
     * @param data 
     * @author Matthew Giancola 40019131
     */
    public void displayOutputSimulationView(GridPane append, int x, int y, String data){
        GridPane temp = new GridPane();
        //front Simulation heading
        Label outHeading = new Label("House View");
        temp.add(outHeading,0,1);
        //front Simulation heading
        Label outData = new Label(tempStringReader(data));
        temp.add(outData,0,0);
        append.add(temp, x,y);
    }
    
    /**
     * 
     * @param in
     * @return Some element to display
     * @author Matthew Giancola 40019131
     */
    public String tempStringReader(String in){
        return in;
    }
    
}
