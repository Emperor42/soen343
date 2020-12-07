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
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shc.SmartHomeCore;
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
    public static long wRangeLow =0;
    public static long wRangeHigh=0;
    
    public static long month=0;
    public static long time=0;
    public static double outTemp=20;

    //public static GridPane heaterPane;

    //public static 
    public static String log="";
    
    static heater[] heaters;
    public static ArrayList<heatZone> zones = new ArrayList<heatZone>();
    public static heatZone activeZone;
    private static int heatZones = 1;
    
    static ChoiceBox<String> heatZoneChoiceBox = new ChoiceBox<String>();

    
    public SmartHomeHeater(){}
    
    public SmartHomeHeater(Room[] rooms){
        //instantiate and connect the different heaters
        System.out.println(rooms.length);
        heaters = new heater[rooms.length];
        zones.add(new heatZone(1, 21));
        heatZones++;
        activeZone = zones.get(0);
        for (int i=0;i<rooms.length;i++){
            //use the main heating zone for all the rooms by default
            System.out.println(zones.get(0));
            System.out.println(i);
            //System.out.println(rooms[i].getName());
            String tmp = "";//rooms[i].getName();
            if (tmp==null){
                tmp = "HEATER_"+i;
            }
            heaters[i]= new heater(tmp, zones.get(0));
        }
    }
    
    public static int makeHeatZone(int id){
        int ret = zones.size();
        zones.add((new SmartHomeHeater()).heatZoneWrapper(id));
        return ret;
    }
    
    public static int makeHeatZone(){
        System.out.println("MAKE NEWZONE!!!!");
        int ret = zones.size();
        zones.add((new SmartHomeHeater()).heatZoneWrapper(ret+1));
        return ret;
    }
    
    private heatZone heatZoneWrapper(int id){
        return new heatZone(id, 21);
    }
    
    public static void changeHeater(int room, heatZone zone){
        heaters[room].zone= zone;
    }
    
    public static double currentSetTemp(int room){
        return heaters[room].getDisplayTemp();
    }
                
    public static void overrideTemp(int room, double temp) {
        heaters[room].override=true;
        heaters[room].target = temp;
    }
    
    public static boolean over(int i){
        return heaters[i].override;
    }
    
    public static void useAutoTemp(int room){
        heaters[room].override=false;
    }
    
    public static boolean warnUser(double check){
        return check<=warning;
    }
    
    public static boolean summerTime(){
        //if time out of range return false
        return sRangeLow<=month && month <=sRangeHigh;
    }
    
    public static double[] pullRoomTemps(){
        System.out.println("SUMMER START: "+sRangeLow );
        System.out.println("SUMMER START: "+sRangeHigh );
        System.out.println("Month Now: "+month);
        double[] ret = new double[heaters.length];
        for(int i=0;i<ret.length;i++){
            heaters[i].run(time, outTemp);
            ret[i] = heaters[i].temp;
        }
        return ret;
    }
    
    public static boolean hvacState(int i){
        return heaters[i].hvac;
    }

    public static boolean coolState(int i){
        return heaters[i].cool;
    }
    
    public static int zone(int i){
        return heaters[i].zone.id;
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
            sRangeLow =monthStringToLong(monthBox3.getValue());
            sRangeHigh=monthStringToLong(monthBox4.getValue());
            wRangeLow =monthStringToLong(monthBox1.getValue());
            wRangeHigh=monthStringToLong(monthBox2.getValue());
        });
        layout.add(btnSetMonths, 0, 7);
        System.out.println();
        //only display if smart home core is not null and room is occupied
        if(SmartHomeCore.activeRoom!=null){
            System.out.println("OCCUPIED: "+SmartHomeCore.activeRoom.occupied());
            if(SmartHomeCore.activeRoom.occupied()){
                
            //Outdoor Temperature
            Label tempHeader = new Label(SmartHomeCore.activeRoom.getName()+": Room Temperature");
            layout.add(tempHeader, 0, 8);
            Label tempOut = new Label(currentSetTemp(SmartHomeCore.activeRoomIndex())+ " C");
            layout.add(tempOut, 0,9);
            // Slider object to set a new temperature
            Slider tempSlider = new Slider(-22, 30, 0.5);
            tempSlider.setShowTickMarks(true);
            tempSlider.setShowTickLabels(true);
            tempSlider.setMajorTickUnit(1);
            tempSlider.setBlockIncrement(1);
            layout.add(tempSlider, 0, 10);

            // Button to set temperature
            Button setTemp = new Button();
            setTemp.setText("Set Override Temperature");
            setTemp.setOnAction(e -> {
                double newOverTemp = (long) tempSlider.getValue();
                overrideTemp(SmartHomeCore.activeRoomIndex(), newOverTemp);
                layout.getChildren().remove(tempOut);
                tempOut.setText(newOverTemp + " C");
                layout.add(tempOut, 0, 9);
            });
            layout.add(setTemp, 0, 11);
        }
            }
        
        if(activeZone!=null){
            layout.add(displayHeaterPane("Edit connected heaterzones here"), 1, 0);
        }

        
        //new zone
        


        //Scene scene = new Scene(layout);
        //window.setScene(scene);
        //window.show();
        return layout;
    }
    
    private static long monthStringToLong(String s){
        long r=0;
        switch(s){
            case "January": r =1;break;
            case "February": r=2;break;
            case "March": r=3;break;
            case "April": r=4;break;
            case "May": r=5;break;
            case "June": r=6;break;
            case "July": r=7;break;
            case "August": r=8;break;
            case "September": r=9;break;
            case "October": r=10;break;
            case "November": r=11;break;
            case "December": r=12;break;
        }
        return r;
    }


    @Override
    public void update() {
        for(int i=0;i<subjects.size();i++){
            if(subjects.get(i) instanceof main.smartHomeSimulatorDashboard){
                for(int k=0;k<heaters.length;k++){
                    heaters[k].run(time, outTemp);
                    System.out.println(SmartHomeCore.activeRoom);
                    if(SmartHomeCore.activeRoom!=null){
                        System.out.println("OCCUPIED: "+SmartHomeCore.activeRoom.occupied());
                    }
                }
            }
            if(subjects.get(i) instanceof shp.SmartHomeSecurity){
                if(summerTime() && !shp.SmartHomeSecurity.away){
                    //we can try and open windows
                    for(int k=0;k<heaters.length;k++){
                        if(heaters[k].openWindow(outTemp)){
                            shc.SmartHomeCore.requestWindows(k);//sets the windows for the specific area
                        }
                    }
                }
            }
        }
    }

    @Override
    public void observe(SmartHomeSubject subject) {
        subjects.add(subject);
    }


    public static GridPane displayHeaterPane(String message){
        GridPane layout = new GridPane();

        Label heaterLabel = new Label(message);
        layout.add(heaterLabel ,0,0);


        //for(heatZone h :zones){
        //    heatZoneChoiceBox.getItems().add(Integer.toString(h.getid()));
        //}
        
        heatZoneChoiceBox.getItems().clear();
        
        for(int i=0;i<zones.size();i++){
            heatZoneChoiceBox.getItems().add(""+zones.get(i).id);
        }
        
        //Zone Temperature
            Label tempHeader = new Label("Zone "+activeZone.id+": Rooms Temperature");
            layout.add(tempHeader, 0, 1);
            //the ability to set the temps for the zones
            Label tempA = new Label("Range A temp("+activeZone.setA+" for time: "+activeZone.startA+"): "+activeZone.aTemp+ " C");
            Label tempB = new Label("Range B temp("+activeZone.setB+" for time: "+activeZone.startB+"): "+activeZone.bTemp+ " C");
            Label tempC = new Label("Range C temp("+activeZone.setC+" for time: "+activeZone.startC+"): "+activeZone.cTemp+ " C");
            layout.add(tempA, 0, 2);
            tempSetter(tempA,tempB,tempC,1, layout, 0,3);
            layout.add(tempB, 1, 2);
            tempSetter(tempB,tempA,tempC,2, layout, 1,3);
            layout.add(tempC, 2, 2);
            tempSetter(tempC, tempA, tempB,3, layout, 2,3);
            // Button to set new zone
            Button setZone = new Button();
            setZone.setText("Set Active To This Zone");
            setZone.setOnAction(e -> {
                
                int indexBox;
                try{
                    indexBox= Integer.parseInt(heatZoneChoiceBox.getValue())-1;
                } catch(Exception exp) {
                    indexBox=0;
                }
                        
                //heaters[SmartHomeCore.activeRoomIndex()].zone=activeZone;
                if (indexBox<zones.size()){
                    activeZone = zones.get(indexBox);
                }
            });
            layout.add(setZone, 0, 8);
            Button setRoomZone = new Button();
            setRoomZone.setText("Set Room To This Zone");
            setRoomZone.setOnAction(e -> {
                //int indexBox = Integer.parseInt(heatZoneChoiceBox.getValue())-1;
                if(activeZone!=null){
                    heaters[SmartHomeCore.activeRoomIndex()].zone=activeZone;
                }
            });
            layout.add(setRoomZone, 0, 6);
            // Button to set zone on active room
            Button setNew = new Button();
            setNew.setText("New Zone");
            setNew.setOnAction(e -> {
                System.out.println(makeHeatZone());
            });
            layout.add(setNew, 0, 7);

        layout.add(heatZoneChoiceBox,0,1);

        return layout;
    }
    
    private static void tempSetter(Label tempOut,Label tempOutA,Label tempOutB,int change,GridPane layout, int x, int y) {
        //GridPane layout = new GridPane();
        Slider t = SmartHomeCore.timeSlider(24, 3600);
        layout.add(t, x+0, y+0);
        //Label tempOut = new Label(activeZone.getTempTargetAtTime(time)+ " C");
            //layout.add(tempOut, 0,0);
            // Slider object to set a new temperature
            Slider tempSlider = new Slider(-22, 30, 0.5);
            tempSlider.setShowTickMarks(true);
            tempSlider.setShowTickLabels(true);
            tempSlider.setMajorTickUnit(1);
            tempSlider.setBlockIncrement(1);
            layout.add(tempSlider, x, y+1);

            // Button to set temperature
            Button setTemp = new Button();
            setTemp.setText("Set Temperature: "+change);
            setTemp.setOnAction(e -> {
                //activeZone. = (long) tempSlider.getValue();
                //long newTime = (long) t.getValue();
                //overrideTemp(SmartHomeCore.activeRoomIndex(), newOverTemp);
                //do the change
                layout.getChildren().remove(tempOut);
                layout.getChildren().remove(tempOutA);
                layout.getChildren().remove(tempOutB);
                switch(change){
                    case 1: 
                        //only change one value
                        activeZone.aTemp = (double) tempSlider.getValue();
                        activeZone.startA = (long) t.getValue();
                        activeZone.setA=true;
                        activeZone.setB=false;
                        activeZone.setC=false;
                        //layout.getChildren().remove(tempOut);
                        tempOut.setText("Range A temp("+activeZone.setA+" for time: "+activeZone.startA+"): "+activeZone.aTemp+ " C");
                        tempOutA.setText("Range B temp("+activeZone.setB+" for time: "+activeZone.startB+"): "+activeZone.bTemp+ " C");
                        tempOutB.setText("Range C temp("+activeZone.setC+" for time: "+activeZone.startB+"): "+activeZone.cTemp+ " C");
                        layout.add(tempOut, 0, 2);
                        layout.add(tempOutA, 1, 2);
                        layout.add(tempOutB, 2, 2);
                        break;
                    case 2: 
                        activeZone.bTemp = (double) tempSlider.getValue();
                        activeZone.startB = (long) t.getValue();
                        activeZone.setA=true;
                        activeZone.setB=true;
                        activeZone.setC=false;
                        //layout.getChildren().remove(tempOut);
                        tempOut.setText("Range B temp("+activeZone.setB+" for time: "+activeZone.startB+"): "+activeZone.bTemp+ " C");
                        tempOutA.setText("Range A temp("+activeZone.setA+" for time: "+activeZone.startA+"): "+activeZone.aTemp+ " C");
                        tempOutB.setText("Range C temp("+activeZone.setC+" for time: "+activeZone.startC+"): "+activeZone.cTemp+ " C");
                        //layout.add(tempOut, 0, 0);
                        layout.add(tempOut, 1, 2);
                        layout.add(tempOutA, 0, 2);
                        layout.add(tempOutB, 2, 2);
                        break;
                    case 3:
                        //only change one value
                        activeZone.cTemp = (double) tempSlider.getValue();
                        activeZone.startC = (long) t.getValue();
                        activeZone.setA=true;
                        activeZone.setB=true;
                        activeZone.setC=true;
                        //layout.getChildren().remove(tempOut);
                        tempOut.setText("Range C temp("+activeZone.setC+" for time: "+activeZone.startC+"): "+activeZone.cTemp+ " C");
                        tempOutA.setText("Range A temp("+activeZone.setA+" for time: "+activeZone.startA+"): "+activeZone.aTemp+ " C");
                        tempOutB.setText("Range B temp("+activeZone.setB+" for time: "+activeZone.startB+"): "+activeZone.bTemp+ " C");
                        //layout.add(tempOut, 0, 0);
                        layout.add(tempOut, 2, 2);
                        layout.add(tempOutA, 0, 2);
                        layout.add(tempOutB, 1, 2);
                        break;
                }
                //end the change
                
            });
            layout.add(setTemp, x, y+2);
            //core.add(layout, x,y);
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
                    this.temp = this.temp-hvacChange;
                }else {
                    this.temp = this.temp+hvacChange;
                }
            }else {
                if(outdoor<=temp){
                    this.temp = this.temp-tempChange;
                } else if (outdoor>temp){
                    this.temp = this.temp+tempChange;
                }
            }
            System.out.println(room+" temp "+temp+" HVAC/COOL "+hvac+"/"+cool+" OD:"+outdoor);
            return temp;
        }
        
        public double run(long time, double outdoor){
            double tar = getTempTarget(time);
            double th = tar+tempRange;
            double tl= tar-tempRange;
            System.out.println(tar);
            System.out.println(th);
            System.out.println(tl);
            if(th>temp && tl< temp){
                //the temp is acceptable
                System.out.println("flag1");
                hvac = false;
                cool=false;                
            } else if (th<temp){
                System.out.println("flag2");
                hvac = true;
                cool = true;
            } else {
                System.out.println("flag3");
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
        
        public double getDisplayTemp(){
            return target;
        }
        
        public boolean openWindow(double temp){
            return temp<=this.temp;
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

        public int getid(){
            return this.id;
        }

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
        
        
        public double getTempTargetAtTime(long timeNow){
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
                return aTemp;//returns a temp if all else ails
            }
        }


    }
    
}


