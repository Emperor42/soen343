/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shh;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shc.SmartHomeObserver;

/**
 *
 * @author Matthew Giancola (40019131)
 */
public class SmartHomeHeater implements SmartHomeObserver{

    public static GridPane layout = new GridPane();

    public static void display(String title, String message) {
        // place holder for
        // set the message and title
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(450);
        window.setMinHeight(450);
        Label label = new Label(message);
        layout.add(label, 0, 1);



        Label templabel1 = new Label("A/C control here?");
        layout.add(templabel1, 0, 2);
        Label templabel2 = new Label("Heater control here?");
        layout.add(templabel2, 0, 3);

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
            window.close();
        });
        layout.add(btnSetMonths, 0, 7);


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();

    }


    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
