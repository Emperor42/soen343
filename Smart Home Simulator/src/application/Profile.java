//ISSUE 1 CLEAR
package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Profile {

    String profileName;
    boolean windowAccess = false,
            doorAccess = false,
            garageAccess = false,
            lightAccess = false,
            houseAccess = false,
            offPropertyAccess = false;
    
    

    public Profile(String a,boolean b,boolean c, boolean d, boolean e,boolean f,boolean g){
        profileName = a;
        windowAccess =b;
        doorAccess =c;
        garageAccess = d;
        lightAccess = e;
        houseAccess = f;
        offPropertyAccess = g;
    }
    
    public void displayProfile(GridPane g){
        //return profileName+ " Window Access: "+windowAccess+"\n"+ " door Access: "+doorAccess+"\n"+ " garage Access: "+garageAccess+"\n"+ " light Access: "+lightAccess+"\n"+ " house Access: "+houseAccess+"\n"+ " off Property Access: "+offPropertyAccess+"\n";
        g.getChildren().clear();
        g.add(new Label("User: "), 0, 0);
        g.add(new Label(profileName), 1, 0);
        g.add(new Label("Window Access: "), 0, 1);
        Button a = new Button(""+windowAccess);
        a.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                windowAccess = !windowAccess;
                a.setText(""+windowAccess);
            }
        });
        g.add(a, 1, 1);
        g.add(new Label("Door Access: "), 0, 2);
        Button b = new Button(""+doorAccess);
        b.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                doorAccess = !doorAccess;
                b.setText(""+doorAccess);
            }
        });
        g.add(b, 1, 2);
        g.add(new Label("Garage Access: "), 0, 3);
        Button c = new Button(""+garageAccess);
        c.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                garageAccess = !garageAccess;
                c.setText(""+garageAccess);
            }
        });
        g.add(c, 1, 3);
        //
        g.add(new Label("Light Access: "), 0, 4);
        Button d = new Button(""+lightAccess);
        d.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                lightAccess = !lightAccess;
                d.setText(""+lightAccess);
            }
        });
        g.add(d, 1, 4);
        g.add(new Label("House Access: "), 0, 5);
        Button e = new Button(""+houseAccess);
        e.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                houseAccess = !houseAccess;
                e.setText(""+houseAccess);
            }
        });
        g.add(e, 1, 5);
        g.add(new Label("Off Property Access: "), 0, 6);
        Button f = new Button(""+offPropertyAccess);
        f.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                offPropertyAccess = !offPropertyAccess;
                f.setText(""+offPropertyAccess);
            }
        });
        g.add(f, 1, 6);
    }
    
    public Profile(String profileName) {
        this.profileName = profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setWindowAccess(boolean windowAccess) {
        this.windowAccess = windowAccess;
    }

    public boolean getWindowAccess() {
        return windowAccess;
    }

    public void setDoorAccess(boolean doorAccess) {
        this.doorAccess = doorAccess;
    }

    public boolean getDoorAccess() {
        return doorAccess;
    }

    public void setGarageAccess(boolean garageAccess) {
        this.garageAccess = garageAccess;
    }

    public boolean getGarageAccess() {
        return garageAccess;
    }

    public void setLightAccess(boolean lightAccess) {
        this.lightAccess = lightAccess;
    }

    public boolean getLightAccess() {
        return lightAccess;
    }

    public void setHouseAccess(boolean houseAccess) {
        this.houseAccess = houseAccess;
    }

    public boolean getHouseAccess() {
        return houseAccess;
    }

    public void setOffPropertyAccess(boolean offPropertyAccess) {
        this.offPropertyAccess = offPropertyAccess;
    }

    public boolean getOffPropertyAccess() {
        return offPropertyAccess;
    }
}
