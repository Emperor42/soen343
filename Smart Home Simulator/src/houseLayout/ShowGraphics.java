package houseLayout;

import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import shc.SmartHomeCore;

public class ShowGraphics {

    private static final long serialVersionUID = 1L;
    private static Color mainDrawingColour = Color.BLUE;
    private static Color onColour = Color.GREEN;
    private static Color offColour = Color.RED;

    private static void drawRoomLeft(GraphicsContext g, String s, Room data) {
        g.strokeRect(0, 0, 150, 100);
        if (data.windowBlocked) {
            g.setStroke(offColour);
        } else {
            g.setStroke(onColour);
        }
        g.strokeRect(0, 0, 25, 25);

        g.strokeText("W", 10, 15);          //Represents the windows

        g.translate(25, 0);
        g.setStroke(mainDrawingColour);
        g.strokeRect(95, 0, 30, 30);

        if (data.doorBlocked) {
            g.setStroke(offColour);
        } else {
            g.setStroke(onColour);
        }
        g.strokeText("D", 105, 15);
        g.setStroke(mainDrawingColour);
        g.strokeText(s, 20, 50);
        g.translate(-25, 75);
        g.strokeOval(0, 0, 50, 25);
        if (data.lightBlocked) {
            g.setStroke(offColour);
        } else {
            g.setStroke(onColour);
        }
        g.strokeText("L", 25, 20);		    //Represents the lights
        g.setStroke(mainDrawingColour);
        g.translate(0, 25);
    }

    //All bedrooms are on the right which contain a bed, door, window and two lights
    private static void drawRoomRight(GraphicsContext g, String s, Room data) {
        g.strokeRect(0, 0, 150, 100);
        g.strokeRect(0, 0, 25, 25);
        if (data.doorBlocked) {
            g.setStroke(offColour);
        } else {
            g.setStroke(onColour);
        }
        g.strokeText("D", 10, 15);
        g.setStroke(mainDrawingColour);
        g.translate(25, 0);
        if (data.windowBlocked) {
            g.setStroke(offColour);
        } else {
            g.setStroke(onColour);
        }
        g.strokeRect(100, 0, 25, 25);

        g.strokeText("W", 105, 15);
        g.setStroke(mainDrawingColour);
        g.strokeText(s, 20, 50);
        g.translate(-25, 75);
        g.strokeOval(0, 0, 50, 25);
        if (data.lightBlocked) {
            g.setStroke(offColour);
        } else {
            g.setStroke(onColour);
        }
        g.strokeText("L", 125, 20);
        g.setStroke(mainDrawingColour);
        g.strokeRect(65, 0, 25, 25);
        g.strokeText("B", 75, 15); 			//Represents Bed
        g.strokeOval(100, 0, 50, 25);
        if (data.lightBlocked) {
            g.setStroke(offColour);
        } else {
            g.setStroke(onColour);
        }
        g.strokeText("L", 25, 20);
        g.setStroke(mainDrawingColour);
        g.translate(0, 25);
    }

    public static void paint(GraphicsContext g, Room[] roomArray) {
        g.setStroke(mainDrawingColour);
        g.setLineWidth(1);
        g.strokeText("L", 25, 20);
        ReadJsonFile readJson = new ReadJsonFile("myJSON.json");
        if (readJson.getRoomArray() == null) {
            System.out.println("I could not find anything in the json file!, Loading temp data");
            JSONArray rooms = new JSONArray();
            rooms.add("Kitchen");
            rooms.add("Living RM");
            rooms.add("Bath RM");
            rooms.add("Garage");
            rooms.add("Master BR");
            rooms.add("Child BR");
            rooms.add("Guest BR");
            JSONObject container = new JSONObject();
            container.put("Rooms", rooms);
            readJson.setRoomArray(rooms);
            // Parses items from the Json file into a room object and stores it into the roomArray variable in the main class -justin
            for (int i = 0; i < readJson.getRoomArray().size(); i++) {
                if (roomArray[i] == null) {
                    Room room = new Room();
                    room.setName(readJson.getRoomArray().get(i).toString());
                    roomArray[i] = room;
                    System.out.println("++" + roomArray[i].getName());
                }
            }
            g.clearRect(0, 0, 600, 600);
            //g.setStroke(mainDrawingColour);
            //g.setLineWidth(5);
            //g.strokeText("L", 25, 20);
            for (int i = 0; i < readJson.getRoomArray().size(); i++) {
                //loop through the given room array to find the correct room to use
                Room data = null;
                for (int k = 0; k < roomArray.length; k++) {
                    if (roomArray[k].isRoom(readJson.getRoomArray().get(i).toString())) {
                        data = roomArray[k];
                        System.out.println(readJson.getRoomArray().get(i).toString() + " " + data.noOfOccupants);
                        break;
                    }
                }
                if (i < 4) {
                    drawRoomLeft(g, data.UpdatedOutput(readJson.getRoomArray().get(i).toString()), data);
                }
                if (i == 4) {
                    g.strokeRect(150, -400, 100, 400);
                    g.translate(250, -400);
                }
                if (i >= 4) {
                    drawRoomRight(g, data.UpdatedOutput(readJson.getRoomArray().get(i).toString()), data);
                }
            }
        }
    }
}
