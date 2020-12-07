package main;
import application.AdjustableClock;
import application.DigitalClock;
import application.Profile;
import static application.SetDateAndTime.changeDate;
import static application.SetDateAndTime.changeTime;
import static application.SetDateAndTime.d;
//import static application.SetDateAndTime.Layout;				// Commented by Justin Loh to make the code work
import java.time.LocalDate;
import java.util.Calendar;

import houseLayout.Person;
import houseLayout.Room;
import java.util.ArrayList;
import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import shc.SmartHomeCore;
import shc.SmartHomeObserver;
import shc.SmartHomeSubject;
import shh.SmartHomeHeater;
import shp.SmartHomeSecurity;

/**
 *
 * @author Matthew Giancola 40019131
 */
public class smartHomeSimulatorDashboard extends Application implements SmartHomeSubject{

    static ArrayList<SmartHomeObserver> observers = new ArrayList<SmartHomeObserver>();
    
    public static boolean runSimulation = false;
    public int activeModule = 0;
    public static GridPane SimulationPane = new GridPane();
    public static GridPane RoomControlPane = new GridPane();
    public static GridPane AdjustableClockPane = new GridPane();
    //Check if this is first run
    boolean first = true;
    //
    private static long newTemp = 20;
    private double newTimeSpeed = 0;
    private AdjustableClock adjClock;
    //root pane for the drawing, we attach the component to this
    private static Pane root = new Pane();
    private static GraphicsContext gc;
    application.Profile currentUser = new application.Profile("Parent", true, true, true, true, true, true);
    application.ProfileManagement profileManager = new application.ProfileManagement();
    //Moved alues Start Here
    GridPane rootLayoutMain = new GridPane();
    Scene primaryScene = new Scene(rootLayoutMain, 1000, 450);
    Label consoleOutput = new Label("This is some Output");
    GridPane currentModuleInterface = new GridPane();
    Button changeUser = new Button("Change From User: " + currentUser.getProfileName());
    Button bSHS = new Button("SHS");
    Button bSHC = new Button("SHC");
    Button bSHP = new Button("SHP");
    Button bSHH = new Button("SHH");
    Button bPLUS = new Button("+");
    //Canvas mainCanvas = new Canvas(600,600);
    Label occupantsInRoom = new Label("Select Room");
    GridPane currentUserLabel = new GridPane();
    Label currentLocation = new Label("Select Room");
    VBox listOfOccupants = new VBox();

    shh.SmartHomeHeater shh;
    
    public smartHomeSimulatorDashboard(){  
    }
    
    @Override
    public void start(Stage primaryStage) {
        houseLayout.BuildJsonFile.Prep();
        
        //for the simulation pane
        displaySimulationPane(currentUserLabel, currentLocation, newTemp, rootLayoutMain, primaryStage, occupantsInRoom, listOfOccupants);
        //for the module pane
        displayModuleTabs(rootLayoutMain, 1, 0, bSHS, bSHC, bSHP, bSHH, bPLUS);
        //displayModuleInterface(activeModule, 1, 1, rootLayoutMain, currentModuleInterface, changeUser);

        displayOutputTerminal(rootLayoutMain, 1, 3, consoleOutput);

        primaryStage.setTitle("Smart Home Simulator -- Dashboard ");
        primaryStage.setScene(primaryScene);
        primaryStage.show();

        //add a checker for the stage
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        houseLayout.BuildJsonFile.Prep();
        
        launch(args);
    }

    /**
     * Use a numerical value to display different parts of the interface to the
     * GridPane TODO: Make modular using some interface or other component
     *
     * @param module which module to use.
     * @param pane the GridPane to use.
     * @param x the x position on the given GridPane
     * @param y the y position on the given GridPane
     * @author Matthew Giancola 40019131
     */
    public void displayModuleInterface(int module, int x, int y, GridPane pane, GridPane temp, Button btnChangeUser) {
        //pane.getChildren().remove(temp);
        if (first) {
            SmartHomeSecurity.module();
            //SmartHomeCore.module();
        }
        temp.getChildren().clear();
        switch (module) {
            case 0:
                btnChangeUser.setText("Enter A Valid User Name");
                btnChangeUser.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //Dialogue BOxes are tamp values we dont need to worry about them
                        TextInputDialog dialog = new TextInputDialog("Parent");
                        dialog.setTitle("Request Another Profile");
                        dialog.setHeaderText("Profile Search");
                        dialog.setContentText("Please enter your profile name:");
                        String nextProfile;
                        // Traditional way to get the response value.
                        Optional<String> result = dialog.showAndWait();
                        if (result.isPresent()) {
                            //System.out.println("Your name: " + result.get());
                            nextProfile = result.get();
                        } else {
                            nextProfile = "Parent";
                        }
                        Profile foundProfile = profileManager.findProfileFromName(nextProfile);
                        if (foundProfile != null) {
                            System.out.println("Profile Changed to: " + foundProfile.getProfileName());
                            currentUser = foundProfile;
                            btnChangeUser.setText("Change From User: " + currentUser.getProfileName());
                            currentUser.displayProfile(currentUserLabel);
                        }
                    }
                });
                temp.add(btnChangeUser, 0, 0);
                break;
            case 1:
                temp.add(SmartHomeCore.module(), 0, 0);
                System.out.println("SHC");
                break;
            case 2:
                temp.add(SmartHomeSecurity.module(), 0, 0);
                System.out.println("SHS--not showing!");
                break;
            case 3:
                temp.add(SmartHomeHeater.display("Test SHH","TEST"), 0, 0);
                System.out.println("SHS--not showing!");
                break;
        }
        if (first) {
            pane.add(temp, x, y);
            first = false;
        }
    }

    /**
     *
     * @param userName
     * @param userLocation
     * @param outTemp
     * @param temp
     * @author Matthew Giancola 40019131 & Justin Loh 40073776
     */
    public void displaySimulationPane(GridPane userName, Label userLocation, long outTemp, GridPane temp, Stage primaryStage, Label occupantHeading, VBox occupants) {
        //front Simulation heading, STATIC LABEL
        Label simHeading = new Label("Simulation");
        temp.add(simHeading, 0, 0);
        //Simulation On and Off Button, GRANDFATHER IN
        Button simOnOff = new Button();
        simOnOff.setText("Begin Simulation");
        simOnOff.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                runSimulation = !runSimulation;
                if (!runSimulation) {
                    simOnOff.setText("Initiate Simulation");
                    temp.getChildren().remove(SimulationPane);				// remove simulation
                    temp.getChildren().remove(RoomControlPane);		//remove room control panel
                    temp.getChildren().remove(AdjustableClockPane);		//remove room control panel
                } else {
                    simOnOff.setText("Exit Simulation");

                    displayOutputSimulationView(temp, 2, 1, "This is my house", primaryStage);		// display simulation
                    displayRoomsandOccupants(temp, 1, 4, primaryStage, occupantHeading, occupants);			//display room control panel
                    displayAdjustableClock(temp, 1, 5);
                }
            }
        });
        temp.add(simOnOff, 0, 1);
        //User Name
        //Label userName = new Label(user);
        temp.add(userName, 0, 2);
        //User Location
        Label userLocationHeader = new Label("Location");
        temp.add(userLocationHeader, 0, 3);
        //Label userLocation = new Label(location);
        temp.add(userLocation, 0, 4);
        //Outdoor Temperature
        Label tempHeader = new Label("Outside Temperature");
        temp.add(tempHeader, 0, 5);
        Label tempOut = new Label(outTemp + " C");
        temp.add(tempOut, 0, 6);
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
        setTemp.setOnAction(e -> {
            newTemp = (long) tempSlider.getValue();
            temp.getChildren().remove(tempOut);
            tempOut.setText(newTemp + " C");
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
    public void displayModuleTabs(GridPane append, int x, int y, Button btnSHS, Button btnSHC, Button btnSHP, Button btnSHH, Button btnPLUS) {
        GridPane temp = new GridPane();
        //Temp will make properly modulr later
        //Button btnSHS = new Button();
        btnSHS.setText("SHS");
        btnSHS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Dummy Function");
                activeModule = 0;
                displayModuleInterface(activeModule, 1, 1, rootLayoutMain, currentModuleInterface, changeUser);
            }
        });
        temp.add(btnSHS, 1, 0);
        //Button btnSHC = new Button();
        btnSHC.setText("SHC");
        btnSHC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                activeModule = 1;
                displayModuleInterface(activeModule, 1, 1, rootLayoutMain, currentModuleInterface, changeUser);
            }
        });
        temp.add(btnSHC, 2, 0);
        //Button btnSHP = new Button();
        btnSHP.setText("SHP");
        btnSHP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //SmartHomeSecurity.display("Smart Home Security Settings", "Welcome to the Security Control Panel");
                activeModule = 2;
                displayModuleInterface(activeModule, 1, 1, rootLayoutMain, currentModuleInterface, changeUser);
            }
        });
        temp.add(btnSHP, 3, 0);
        //Button btnSHH = new Button();
        btnSHH.setText("SHH");
        btnSHH.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Dummy Function");
                activeModule = 3;
                displayModuleInterface(activeModule, 1, 1, rootLayoutMain, currentModuleInterface, changeUser);
            }
        });
        temp.add(btnSHH, 4, 0);
        //Button btnPLUS = new Button();
        btnPLUS.setText("+");
        btnPLUS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Dummy Function");
                activeModule = 4;
                displayModuleInterface(activeModule, 1, 1, rootLayoutMain, currentModuleInterface, changeUser);
            }
        });
        temp.add(btnPLUS, 5, 0);
        append.add(temp, x, y);
        displayModuleInterface(activeModule, x, y + 1, rootLayoutMain, currentModuleInterface, changeUser);
    }

    /**
     *
     * @param append
     * @param x
     * @param y
     * @param outData
     * @author Matthew Giancola 40019131
     */
    public void displayOutputTerminal(GridPane append, int x, int y, Label outData) {
        GridPane temp = SmartHomeCore.terminalModule();
        append.add(temp, x, y);
    }

    /**
     *
     * @param append
     * @param x
     * @param y
     * @author Justin Loh 40073776
     */
    public void displayRoomsandOccupants(GridPane append, int x, int y, Stage primaryStage, Label occupantHeading, VBox occupantBox) {
        //GridPane temp = new GridPane();
        //Room Control Heading
        append.getChildren().remove(RoomControlPane);
        RoomControlPane.getChildren().clear();
        Label outHeading = new Label("Room Control : ");
        RoomControlPane.add(outHeading, 0, 0);
        //Room control elements begin here
        //choice box with rooms parsed from json file
        ChoiceBox<String> roomBox = new ChoiceBox<>();

        for (int i = 0; i < SmartHomeCore.roomArray.length; i++) {
            if (SmartHomeCore.roomArray[i] != null) {
                roomBox.getItems().add(SmartHomeCore.roomArray[i].getName());
            }
        }//add in the heaters
        shh = new shh.SmartHomeHeater(SmartHomeCore.roomArray);
        shp.SmartHomeSecurity s = new shp.SmartHomeSecurity();
        s.attach(shh);
        this.attach(shh);
        RoomControlPane.add(roomBox, 0, 1);
        // button which shows room information based on choicebox
        Button btnRoom = new Button("Add Person");
        RoomControlPane.add(btnRoom, 0, 3);
        btnRoom.setOnAction(e -> {
            //Room r = new Room();
            //Room r = null;
            String roomName = roomBox.getValue();
            for (int i = 0; i < SmartHomeCore.roomArray.length; i++) {
                if (SmartHomeCore.roomArray[i] != null) {
                    if (SmartHomeCore.roomArray[i].getName().equals(roomName)) {
                        SmartHomeCore.activeRoom = SmartHomeCore.roomArray[i];
                        currentLocation.setText(SmartHomeCore.activeRoom.getName());
                    }
                }
            }
            SmartHomeCore.activeRoom.addOccupants(new Person(currentUser.getProfileName()));
            occupantHeading.setText("Occupants of " + SmartHomeCore.activeRoom.getName() + " are : ");
            occupantBox.getChildren().clear();
            if (SmartHomeCore.activeRoom.getNoOfOccupants() != 0) {
                occupantBox.getChildren().add(occupantHeading);
                for (int i = 0; i < SmartHomeCore.activeRoom.getNoOfOccupants(); i++) {
                    occupantBox.getChildren().add(new Label(SmartHomeCore.activeRoom.getOccupants()[i].getName()));
                }
            } else {
                occupantBox.getChildren().add(new Label(SmartHomeCore.activeRoom.getName() + " is empty"));
            }
            //was throwing a multiple appending error
            RoomControlPane.getChildren().remove(occupantBox);
            RoomControlPane.add(occupantBox, 0, 6);
            displayOutputSimulationView(RoomControlPane, 2, 1, "This is my house", primaryStage);
        });
        // button which shows room information based on choicebox

        Button btnRoomObject = new Button("Block Windows");
        RoomControlPane.add(btnRoomObject, 0, 2);
        btnRoomObject.setOnAction(e -> {
            String roomName = roomBox.getValue();
            for (int i = 0; i < SmartHomeCore.roomArray.length; i++) {
                if (SmartHomeCore.roomArray[i] != null) {
                    if (SmartHomeCore.roomArray[i].getName().equals(roomName)) {
                        SmartHomeCore.activeRoom = SmartHomeCore.roomArray[i];
                    }
                }
            }
            utilBlockWindows();
            displayOutputSimulationView(RoomControlPane, 2, 1, "This is my house", primaryStage);
        });

        //
        // button which shows room information based on choicebox
        Button btnPickRoom = new Button("Select Room");
        RoomControlPane.add(btnPickRoom, 0, 4);
        btnPickRoom.setOnAction(e -> {
            //Room r = new Room();
            Room r = null;
            String roomName = roomBox.getValue();
            for (int i = 0; i < SmartHomeCore.roomArray.length; i++) {
                if (SmartHomeCore.roomArray[i] != null) {
                    if (SmartHomeCore.roomArray[i].getName().equals(roomName)) {
                        r = SmartHomeCore.roomArray[i];
                        SmartHomeCore.activeRoom = r;
                        currentLocation.setText(r.getName());
                    }
                }
            }
            if(r==null){
                //r.addOccupants(new Person(currentUser.getProfileName()));
                occupantHeading.setText("Occupants of " + r.getName() + " are : ");
                occupantBox.getChildren().clear();
                if (r.getNoOfOccupants() != 0) {
                    occupantBox.getChildren().add(occupantHeading);
                    for (int i = 0; i < r.getNoOfOccupants(); i++) {
                        occupantBox.getChildren().add(new Label(r.getOccupants()[i].getName()));
                    }
                } else {
                    occupantBox.getChildren().add(new Label(r.getName() + " is empty"));
                }
                //was throwing a multiple appending error
                RoomControlPane.getChildren().remove(occupantBox);
                RoomControlPane.add(occupantBox, 0, 6);
            }
            displayOutputSimulationView(RoomControlPane, 2, 1, "This is my house", primaryStage);
        });

        // button which shows room information based on choicebox
        Button btDropRoom = new Button("Remove Person");
        RoomControlPane.add(btDropRoom, 0, 5);
        btDropRoom.setOnAction(e -> {
            //Room r = new Room();
            Room r = null;
            String roomName = roomBox.getValue();
            for (int i = 0; i < SmartHomeCore.roomArray.length; i++) {
                if (SmartHomeCore.roomArray[i] != null) {
                    if (SmartHomeCore.roomArray[i].getName().equals(roomName)) {
                        r = SmartHomeCore.roomArray[i];
                        SmartHomeCore.activeRoom = r;
                        currentLocation.setText(r.getName());
                    }
                }
            }
            //r.addOccupants(new Person(currentUser.getProfileName()));
            r.removeOccupants(new Person(currentUser.getProfileName()));
            occupantHeading.setText("Occupants of " + r.getName() + " are : ");
            occupantBox.getChildren().clear();
            if (r.getNoOfOccupants() != 0) {
                occupantBox.getChildren().add(occupantHeading);
                for (int i = 0; i < r.getNoOfOccupants(); i++) {
                    occupantBox.getChildren().add(new Label(r.getOccupants()[i].getName()));
                }
            } else {
                occupantBox.getChildren().add(new Label(r.getName() + " is empty"));
            }
            //was throwing a multiple appending error
            RoomControlPane.getChildren().remove(occupantBox);
            RoomControlPane.add(occupantBox, 0, 6);
            displayOutputSimulationView(RoomControlPane, 2, 1, "This is my house", primaryStage);
        });
        //RoomControlPane = temp;
        append.add(RoomControlPane, x, y);
    }

    /**
     *
     * @param append
     * @param x
     * @param y
     * @author Justin Loh 40073776
     */
    public void displayAdjustableClock(GridPane append, int x, int y) {

        append.getChildren().remove(AdjustableClockPane);
        AdjustableClockPane.getChildren().clear();
        Label outHeading = new Label("Adjustable Simulation Time Speed : ");
        AdjustableClockPane.add(outHeading, 0, 0);

        adjClock = new AdjustableClock();
        AdjustableClockPane.add(adjClock, 0, 1);

        // slider to change time speed
        Slider timeSlider = new Slider(0, 3, 0);
        timeSlider.setMin(0);
        timeSlider.setMax(3);
        timeSlider.setValue(1);
        timeSlider.setMinorTickCount(0);
        timeSlider.setMajorTickUnit(1);
        timeSlider.setSnapToTicks(true);
        timeSlider.setShowTickMarks(true);
        timeSlider.setShowTickLabels(true);

        // display custom text on slider
        timeSlider.setLabelFormatter(new StringConverter<Double>() {
            public String toString(Double n) {
                if (n < 0.5) {
                    return "Slow";
                }
                if (n < 1.5) {
                    return "Normal";
                }
                if (n < 2.5) {
                    return "Fast";
                }

                return "Super Fast";
            }

            @Override
            public Double fromString(String arg0) {
                return null;
            }

        });

        // add slider to pane
        AdjustableClockPane.add(timeSlider, 0, 2);

        // button to set time speed
        Button setSpeed = new Button();
        setSpeed.setText("Set Time Speed");
        setSpeed.setOnAction(e -> {
            newTimeSpeed = timeSlider.getValue();
            AdjustableClockPane.getChildren().remove(adjClock);
//            System.out.println(adjClock.getTime().get(Calendar.HOUR_OF_DAY));   COMPARE TIME VALUES WITH THIS - JUSTIN
            adjClock.changeSpeed(timeSlider.getValue());
            AdjustableClockPane.add(adjClock, 0, 1);
        });
        AdjustableClockPane.add(setSpeed, 0, 3);

        // button to reset time speed
        Button resetSpeed = new Button();
        resetSpeed.setText("Reset");
        resetSpeed.setOnAction(e -> {
            newTimeSpeed = timeSlider.getValue();
            AdjustableClockPane.getChildren().remove(adjClock);
            adjClock = new AdjustableClock();
            AdjustableClockPane.add(adjClock, 0, 1);
        });
        AdjustableClockPane.add(resetSpeed, 0, 4);

        append.add(AdjustableClockPane, x, y);
    }

    public void utilBlockWindows() {
        if (SmartHomeCore.activeRoom != null) {
            System.out.println("Noy Null Room");
            SmartHomeCore.activeRoom.blockWindows();
        }
    }

    static GridPane ta;
    static int tx, ty;
    static Stage ts;

    /**
     *
     * @param append
     * @param x
     * @param y
     * @param data
     * @author Matthew Giancola 40019131
     */
    public static void displayOutputSimulationView(GridPane append, int x, int y, String data, Stage stage) {     // display output of rooms
        if (ta == null || ts == null) {
            ta = append;
            tx = x;
            ty = y;
            ts = stage;
        }
        System.out.println("ROOMS: " + SmartHomeCore.roomArray.length);
        //GridPane temp = new GridPane();
        append.getChildren().remove(SimulationPane);
        SimulationPane.getChildren().clear();
        Canvas canvas = new Canvas(600, 600);
        gc = canvas.getGraphicsContext2D();
        //gc.clearRect(0,0,600,600);
        houseLayout.ShowGraphics.paint(gc, SmartHomeCore.roomArray);
        root.getChildren().clear();
        // added SmartHomeCore.roomArray parameter-justin
        root.getChildren().add(canvas);
        //stage.setScene(new Scene(root));
        //stage.show();
        SimulationPane.add(root, 0, 0);
        //SimulationPane = temp;
        append.add(SimulationPane, x, y);
        /*
        for (int i = 0; i < SmartHomeCore.roomArray.length; i++) {
            if (SmartHomeCore.roomArray[i] != null) {
                System.out.println("room array inludes" + SmartHomeCore.roomArray[i].getName());
            }
        }
         */
    }

    public static void updateOutput(long second, long month){
        if (ta != null && ts != null) {
            if (runSimulation){
                System.out.println("Runs");
                displayOutputSimulationView(ta, tx, ty, "", ts);
                onUpdate(second, month);
            }
        }
    }
    
    public static void onUpdate(long second, long month){
        SmartHomeHeater.month = month;
        SmartHomeHeater.time=second;
        SmartHomeHeater.outTemp=newTemp;
        smartHomeSimulatorDashboard a = new smartHomeSimulatorDashboard();
        double[] newTemps = (double[]) a.getState();
        SmartHomeCore.updateTemps(newTemps);
        a.setState(observers.get(0));
        observers.get(0).update();
    }
    
    /**
     *
     * @param in
     * @return Some element to display
     * @author Matthew Giancola 40019131 & Justin Loh 40073776
     */
    public String tempStringReader(String in) {
        return in;
    }

    /**
     *
     * @param append
     * @param x
     * @param y Function Grandfathered in
     */
    public void loadDateAndTime(GridPane append, int x, int y) {
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
        ChoiceBox<String> am_pmBox = new ChoiceBox<>();
        am_pmBox.setValue("am");
        am_pmBox.getItems().add("am");
        am_pmBox.getItems().add("pm");

        timeInputs.getChildren().addAll(hrBox, minBox, am_pmBox);

        //Create a button to set time
        Button btnTime = new Button("Set a time");

        //create default clock which has value of current time
        d.bindToCurrentTime();

        //add all children to the layout
        VBox layout = application.SetDateAndTime.layout;
        layout.getChildren().addAll(label, datePicker, d, timeInputs, btndate, btnTime);
        layout.getChildren().add(new Label("Date Unset"));
        // button to apply changes to times
        btndate.setOnAction(e -> {
            LocalDate localDate = datePicker.getValue();
            Label dateLabel = new Label(localDate.toString());
            changeDate(layout, dateLabel);
        });

        // button to apply changes to date
        btnTime.setOnAction(e -> {
            int hr = Integer.parseInt(hrBox.getValue());
            int min = Integer.parseInt(minBox.getValue());
            int am_pm = am_pmBox.getValue() == "am" ? 0 : 1;
            changeTime(layout, hr, min, Calendar.getInstance().SECOND, am_pm);
        }
        );
        append.add(application.SetDateAndTime.layout, x, y);
    }

    @Override
    public void setState(Object state) {
        if (state!=null && adjClock!=null){
            if(state instanceof  SmartHomeHeater){
                //update for the smart home heater
                SmartHomeHeater use = (SmartHomeHeater)state;
                use.time = adjClock.getTimeMinute();//set to the given clock time as of now
                use.outTemp = (double)newTemp;
               System.out.println(use.time);
               System.out.println(use.outTemp);
            }
        }
    }

    @Override
    public Object getState() {
        //pull an array of temps
        return SmartHomeHeater.pullRoomTemps();
    }

    @Override
    public void attach(SmartHomeObserver a) {
        this.observers.add(a);
        a.observe(this);
    }

}
