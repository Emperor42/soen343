package houseLayout;
import java.awt.Graphics;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;


public class ShowGraphics{  

	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		
		private static void drawRoomLeft(GraphicsContext g, String s) {
			
			g.strokeRect(0, 0, 150, 100);
	        g.strokeRect(0, 0, 25, 25);
	        g.strokeText("W", 10, 15);          //Represents the windows
	        g.translate(25, 0);
	        g.strokeRect(95, 0, 30, 30);
	        g.strokeText("D", 105, 15);
	        g.strokeText(s, 20, 50);
	        g.translate(-25, 75);
	        g.strokeOval(0, 0, 50, 25);
	        g.strokeText("L", 25, 20);		    //Represents the lights
	        g.translate(0, 25);
			
		}
		
		//All bedrooms are on the right which contain a bed, door, window and two lights
		private static void drawRoomRight(GraphicsContext g, String s) {
			
			g.strokeRect(0, 0, 150, 100);
	        g.strokeRect(0, 0, 25, 25);
	        g.strokeText("D", 10, 15);
	        g.translate(25, 0);
	        g.strokeRect(100, 0, 25, 25);
	        g.strokeText("W", 105, 15); 
	        g.strokeText(s, 20, 50);
	        g.translate(-25, 75);
	        g.strokeOval(0, 0, 50, 25);
	        g.strokeText("L", 125, 20);
	        g.strokeRect(65, 0, 25, 25);
	        g.strokeText("B", 75, 15); 			//Represents Bed
	        g.strokeOval(100, 0, 50, 25);
	        g.strokeText("L", 25, 20);
	        g.translate(0, 25);	
			
		}
		
	    public static void paint(GraphicsContext g){
                g.setStroke(Color.GREEN);
                g.setLineWidth(1);
                g.strokeText("L", 25, 20);
	    	ReadJsonFile readJson = new ReadJsonFile("myJSON.json");
                if (readJson.getRoomArray()==null){
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
                }
                g.clearRect(0, 0, 600, 600);
                //g.setStroke(Color.GREEN);
                //g.setLineWidth(5);
                //g.strokeText("L", 25, 20);
	    	for (int i=0; i<readJson.getRoomArray().size(); i++) {
	    		if (i<4)
	    			drawRoomLeft(g, readJson.getRoomArray().get(i).toString());
	    		if (i==4)
	    		{
	    			g.strokeRect(150, -400, 100, 400);
	    			g.translate(250, -400);
	    		}
	    		if (i>=4)
	    			drawRoomRight(g, readJson.getRoomArray().get(i).toString());
	    	}
	    	
	    }
	    
            /*
	    public static void main(String[] args){
	        JFrame frame= new JFrame("JavaTutorial.net");    
	        frame.getContentPane().add(new ShowGraphics());
	        frame.setSize(600, 600);
	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setResizable(false);        
	    }
            */

	}