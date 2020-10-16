package jsonPackage;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.simple.JSONObject;

  
public class ShowGraphics extends JPanel{  

	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		
		public void drawRoomLeft(Graphics g, String s) {
			
			g.drawRect(0, 0, 150, 100);
	        g.drawRect(0, 0, 25, 25);
	        g.drawString("W", 10, 15);          //Represents the windows
	        g.translate(25, 0);
	        g.drawRect(95, 0, 30, 30);
	        g.drawString("D", 105, 15);
	        g.drawString(s, 20, 50);
	        g.translate(-25, 75);
	        g.drawOval(0, 0, 50, 25);
	        g.drawString("L", 25, 20);		    //Represents the lights
	        g.translate(0, 25);
			
		}
		
		//All bedrooms are on the right which contain a bed, door, window and two lights
		public void drawRoomRight(Graphics g, String s) {
			
			g.drawRect(0, 0, 150, 100);
	        g.drawRect(0, 0, 25, 25);
	        g.drawString("D", 10, 15);
	        g.translate(25, 0);
	        g.drawRect(100, 0, 25, 25);
	        g.drawString("W", 105, 15); 
	        g.drawString(s, 20, 50);
	        g.translate(-25, 75);
	        g.drawOval(0, 0, 50, 25);
	        g.drawString("L", 125, 20);
	        g.drawRect(65, 0, 25, 25);
	        g.drawString("B", 75, 15); 			//Represents Bed
	        g.drawOval(100, 0, 50, 25);
	        g.drawString("L", 25, 20);
	        g.translate(0, 25);	
			
		}
		
	    public void paint(Graphics g){
	    	ReadJsonFile readJson = new ReadJsonFile("myJSON.json");
	    	for (int i=0; i<readJson.getRoomArray().size(); i++) {
	    		if (i<4)
	    			this.drawRoomLeft(g, readJson.getRoomArray().get(i).toString());
	    		if (i==4)
	    		{
	    			g.drawRect(150, -400, 100, 400);
	    			g.translate(250, -400);
	    		}
	    		if (i>=4)
	    			this.drawRoomRight(g, readJson.getRoomArray().get(i).toString());
	    	}
	    	
	    }
	    
	    public static void main(String[] args){
	        JFrame frame= new JFrame("JavaTutorial.net");    
	        frame.getContentPane().add(new ShowGraphics());
	        frame.setSize(600, 600);
	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setResizable(false);        
	    }
	}