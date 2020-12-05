/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shh;

import houseLayout.Room;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shc.SmartHomeObserver;
import shc.SmartHomeSubject;

/**
 *
 * @author Matthew Giancola (40019131)
 */
public class SmartHomeHeater implements SmartHomeObserver{
    
    ArrayList<SmartHomeSubject> subjects = new ArrayList<SmartHomeSubject>();
    
    public static final double tempChange = 0.05;
    public static final double hvacChange = 0.1;
    public static final double tempRange = 0.25;
    public static final double warning = 0;
    public static long sRangeLow =0;
    public static long sRangeHigh=0;
    
    
    public static long time=0;
    public static double outTemp=20;

    public static GridPane heaterPane;

    //public static 
    public static String log="";
    
    static heater[] heaters;
    public static ArrayList<heatZone> zones = new ArrayList<heatZone>();
    
    public SmartHomeHeater(){}
    
    public SmartHomeHeater(Room[] rooms){
        //instantiate and connect the different heaters
        heaters = new heater[rooms.length];
        zones.add(new heatZone(1, 21));
        for (int i=0;i<rooms.length;i++){
            //use the main heating zone for all the rooms by default
            heaters[i]= new heater(rooms[i].getName(), zones.get(0));
        }
    }
    
    public static int makeHeatZone(int id){
        int ret = zones.size();
        zones.add((new SmartHomeHeater()).heatZoneWrapper(id));
        return ret;
    }
    
    private heatZone heatZoneWrapper(int id){
        return new heatZone(id, 21);
    }
    
    public static void changeHeater(int room, heatZone zone){
        heaters[room].zone= zone;
    }
                
    public static void overrideTemp(int room, double temp) {
        heaters[room].override=true;
        heaters[room].target = temp;
    }
    
    public static void useAutoTemp(int room){
        heaters[room].override=heaters[room].override;
    }
    
    public static boolean warnUser(double check){
        return check<=warning;
    }
    
    public static boolean summerTime(double check){
        //if time out of range return false
        return check>=outTemp;
    }
    
    public double[] pullRoomTemps(){
        double[] ret = new double[heaters.length];
        for(int i=0;i<ret.length;i++){
            heaters[i].run(time, outTemp);
        }
        return ret;
    }
    

    public static GridPane display(String title, String message) {
        // place holder for
        // set the message and title
        /*
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(450);
        window.setMinHeight(450);
*/
        if (outTemp<1){
            message = message+" \n PIPE BURST POSSIBLE!";
        }
        GridPane layout = new GridPane();
        Label label = new Label(message);
        layout.add(label, 0, 1);



        Label templabel1 = new Label("A/C control here?");
        layout.add(templabel1, 0, 2);
        displayHeaterPane("Edit connected heaters here");
        layout.add(heaterPane, 0, 3);

        Label headingSetMonths = new Label("Set Months for Winter and Summer Seasons :");
        layout.add(headingSetMonths, 0, 4);

        HBox monthInput1= new HBox();
        Label setWinterLabel = new Label("Winter    :");
        ChoiceBox<String> monthBox1 = new ChoiceBox<>();
        monthBox1.setValue(" ");
        monthBox1.getItems().add("January");
        monthBox1.getItems().add("February");
        monthBox1.getItems().add("March");
        monthBox1.getItems().add("April");
        monthBox1.getItems().add("May");
        monthBox1.getItems().add("June");
        monthBox1.getItems().add("July");
        monthBox1.getItems().add("August");
        monthBox1.getItems().add("September");
        monthBox1.getItems().add("October");
        monthBox1.getItems().add("November");
        monthBox1.getItems().add("December");

        ChoiceBox<String> monthBox2 = new ChoiceBox<>();
        monthBox2.setValue(" ");
        monthBox2.getItems().add("January");
        monthBox2.getItems().add("February");
        monthBox2.getItems().add("March");
        monthBox2.getItems().add("April");
        monthBox2.getItems().add("May");
        monthBox2.getItems().add("June");
        monthBox2.getItems().add("July");
        monthBox2.getItems().add("August");
        monthBox2.getItems().add("September");
        monthBox2.getItems().add("October");
        monthBox2.getItems().add("November");
        monthBox2.getItems().add("December");

        Label label1 = new Label(" to ");
        monthInput1.getChildren().addAll(setWinterLabel,monthBox1, label1, monthBox2);
        layout.add(monthInput1, 0, 5);

        HBox monthInput2= new HBox();
        Label setSummerLabel = new Label("Summer    :");
        ChoiceBox<String> monthBox3 = new ChoiceBox<>();
        monthBox3 .setValue(" ");
        monthBox3 .getItems().add("January");
        monthBox3.getItems().add("February");
        monthBox3.getItems().add("March");
        monthBox3.getItems().add("April");
        monthBox3.getItems().add("May");
        monthBox3.getItems().add("June");
        monthBox3.getItems().add("July");
        monthBox3.getItems().add("August");
        monthBox3.getItems().add("September");
        monthBox3.getItems().add("October");
        monthBox3.getItems().add("November");
        monthBox3.getItems().add("December");

        ChoiceBox<String> monthBox4 = new ChoiceBox<>();
        monthBox4.setValue(" ");
        monthBox4.getItems().add("January");
        monthBox4.getItems().add("February");
        monthBox4.getItems().add("March");
        monthBox4.getItems().add("April");
        monthBox4.getItems().add("May");
        monthBox4.getItems().add("June");
        monthBox4.getItems().add("July");
        monthBox4.getItems().add("August");
        monthBox4.getItems().add("September");
        monthBox4.getItems().add("October");
        monthBox4.getItems().add("November");
        monthBox4.getItems().add("December");

        Label label2 = new Label(" to ");
        monthInput2.getChildren().addAll(setSummerLabel,monthBox3,label2, monthBox4);
        layout.add(monthInput2, 0, 6);

        Button btnSetMonths = new Button("Finish and Save Settings");
        btnSetMonths.setOnAction(e -> {
            //TODO: set months value for winter/Summer here
            //window.close();
        });
        layout.add(btnSetMonths, 0, 7);


        //Scene scene = new Scene(layout);
        //window.setScene(scene);
        //window.show();
        return layout;
    }


    @Override
    public void update() {
        for(int i=0;i<subjects.size();i++){
            //if(subjects.get(i) instanceOf smartHomeSimulatorDashboard){
                
            //}
        }
    }

    @Override
    public void observe(SmartHomeSubject subject) {
        subjects.add(subject);
    }


    public static void displayHeaterPane(String message){
        GridPane layout = new GridPane();

        Label heaterLabel = new Label(message);
        layout.add(heaterLabel ,0,0);

        ChoiceBox<String> heaterChoiceBox = new ChoiceBox<String>();

        for(heat: heaters);// working here


        heaterPane=layout;
    }



    private class heater{
        boolean hvac;
        boolean cool;
        boolean override;
        String room;
        heatZone zone;
        double temp;
        double target;
        public heater(String room, heatZone zone){
            hvac = false;
            cool = false;
            override=false;
            this.room=room;
            this.zone = zone;
            temp = 20;
            target = 20;
        }
        
        private double tempChange(double outdoor){
            if(hvac){
                if(cool){
                    temp = temp-hvacChange;
                }else {
                    temp = temp+hvacChange;
                }
            }else {
                if(outdoor<temp){
                    temp = temp-tempChange;
                } else if (outdoor>temp){
                    temp = temp+tempChange;
                }
            }
            return temp;
        }
        
        public double run(long time, double outdoor){
            double tar = getTempTarget(time);
            double th = tar+tempRange;
            double tl= tar-tempRange;
            if(th<=temp && temp <= tl){
                //the temp is acceptable
                hvac = false;
                cool=false;                
            } else if (th<temp){
                hvac = true;
                cool = true;
            } else {
                hvac = true;
                cool = false;
            }
            //change the temp in the desired direction
            return tempChange(outdoor);
        }
        
        public double getTempTarget(long time){
            if (override){
                return target;
            }else {
                try {
                    return zone.getTempTargetAtTime(time);
                }catch (Exception E){
                    return target;
                }
            }
        }
        
    }
    
    private class heatZone{
        int id;
        double aTemp;
        boolean setA;
        double bTemp;
        boolean setB;
        double cTemp;
        boolean setC;
        //used when more than one is set
        long startA;
        long startB;
        long startC;
        public heatZone(int id, double tempA){
            this.id = id;
            setA = true;
            setB =false;
            setC = false;
            aTemp = tempA;
        }
        
        public heatZone(int id, double tempA, double tempB, long timeA, long timeB){
            this.id = id;
            setA = true;
            setB =true;
            setC = false;
            aTemp = tempA;
            bTemp = tempB;
            startA = timeA;
            startB = timeB;
        }
        public heatZone(int id, double tempA, double tempB, double tempC, long timeA, long timeB, long timeC){
            this.id = id;
            setA = true;
            setB =true;
            setC = true;
            aTemp = tempA;
            bTemp = tempB;
            cTemp = tempC;
            startA = timeA;
            startB = timeB;
            startC = timeC;
        }
        
        private void resetZone(){
            aTemp=0;
            bTemp=0;
            cTemp=0;
            setA=false;
            setB=false;
            setC=false;
            startA=0;
            startB=0;
            startC=0;
        }
        public void setSingleTempZone(double tempA){
            resetZone();
            setA=true;
            aTemp=tempA;
        }
        
        public void setDoubleTempZone(double tempA, double tempB, long timeA, long timeB){
            resetZone();
            setA = true;
            setB =true;
            aTemp = tempA;
            bTemp = tempB;
            startA = timeA;
            startB = timeB;
        }
        public void setTripleTempZone(double tempA, double tempB, double tempC, long timeA, long timeB, long timeC){
            resetZone();
            setA = true;
            setB =true;
            setC = true;
            aTemp = tempA;
            bTemp = tempB;
            cTemp = tempC;
            startA = timeA;
            startB = timeB;
            startC = timeC;
        }
        
        
        public double getTempTargetAtTime(long timeNow) throws Exception{
            if(setA){
                if(setB){
                    if (setC){
                        //use the time frame for A, B and C
                        if (timeNow<startA || timeNow>=startB){
                            //the time is inside of the frame for the B temperature
                            return bTemp;
                        }
                        return aTemp;
                    }else {
                        //use the time frame for A and B
                        if (timeNow>=startA && timeNow<startB){
                            //the time is inside of the frame for the B temperature
                            return aTemp;
                        } else if (timeNow >= startB && timeNow<startC){
                            return bTemp;
                        }else{
                            return cTemp;
                        }
                    }
                }else{
                    //set ignoring the time frame
                    return aTemp;
                }
            } else{
                throw new Exception("The temperature zone has not been set!");
            }
        }


    }
    
}


