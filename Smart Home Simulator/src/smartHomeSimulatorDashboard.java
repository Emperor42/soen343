import static application.SetDateAndTime.changeDate;
import static application.SetDateAndTime.changeTime;
import static application.SetDateAndTime.d;
//import static application.SetDateAndTime.Layout;				// Commented by Justin Loh to make the code work
import java.time.LocalDate;
import java.util.Calendar;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        
        Scene scene = new Scene(root, 900, 450);
        
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
     * @author Matthew Giancola 40019131 & Justin Loh 40073776
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
        // Slider object to set a new temperature
        Slider tempSlider = new Slider(-22, 30, 0.5);
        tempSlider.setShowTickMarks(true);
        tempSlider.setShowTickLabels(true);
        tempSlider.setMajorTickUnit(1);
        tempSlider.setBlockIncrement(1);
        temp.add(tempSlider, 0, 7);
        
        // Button to set temperature
        Button setTemp = new Button();
        setTemp.setText("Set Temperature");
        setTemp.setOnAction(e->{
        	int newTemp = (int) tempSlider.getValue();
        	temp.getChildren().remove(tempOut);
        	tempOut.setText(newTemp+" C");
        	temp.add(tempOut, 0, 6);
		});
        temp.add(setTemp, 0, 8);
        
        
        //time and Date
        loadDateAndTime(temp, 0, 10);
        
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
    
    public void loadDateAndTime(GridPane append, int x, int y){
        //append.add(new Label("SOME TIME"),x,y);
        
		Label label = new Label("TIME");
		//Create Datepicker Item
		DatePicker datePicker = new DatePicker();
		
		
		//Create button to set date
		Button btndate = new Button("Set the Date");
		
		
		
		//Input drop down boxes for hr min and am/pm
		HBox timeInputs = new HBox();
		ChoiceBox<String> hrBox = new ChoiceBox<>();
	     hrBox.setValue("0");
		for(int i = 1 ;i<13;i++) {
			String j = Integer.toString(i);
			hrBox.getItems().add(j);
		}
		ChoiceBox<String> minBox = new ChoiceBox<>();
	minBox.setValue("00");
		for(int i = 0 ;i<60;i++) {
			String j = Integer.toString(i);
			minBox .getItems().add(j);
		}
		ChoiceBox<String> am_pmBox = new ChoiceBox<>();
		am_pmBox.setValue("am");
		am_pmBox.getItems().add("am");
		am_pmBox.getItems().add("pm");
		
		
		
		timeInputs.getChildren().addAll(hrBox,minBox,am_pmBox);
		
		//Create a button to set time
		Button btnTime = new Button("Set a time'");
		
		//create default clock which has value of current time
		d.bindToCurrentTime();
		
		//add all children to the layout
		VBox layout = application.SetDateAndTime.layout;			//  this segment added by justin Loh so code will run 
                layout.getChildren().addAll(label,datePicker,d,timeInputs,btndate,btnTime);
		layout.getChildren().add(new Label("Date Unset"));
		// button to apply changes to times
		btndate.setOnAction(e->{
			LocalDate localDate = datePicker.getValue();
			Label dateLabel = new Label(localDate.toString());
			changeDate(layout,dateLabel);
		});
		
		// button to apply changes to date
		btnTime.setOnAction(e->{
			int hr=  Integer.parseInt(hrBox.getValue());
			int min= Integer.parseInt(minBox.getValue());
			int am_pm= am_pmBox.getValue()=="am"? 0:1;
			changeTime(layout,hr,min,Calendar.getInstance().SECOND,am_pm);}
				);
        append.add(application.SetDateAndTime.layout,x,y);
    }
    
}
