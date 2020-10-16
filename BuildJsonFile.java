package jsonPackage;


import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class BuildJsonFile {


	public static void main(String[] args) {
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

		try 
		{
			PrintWriter pw = new PrintWriter("myJson.json");
			pw.write(container.toString());
			pw.flush();
			pw.close();
	
		}
		
		catch (IOException e) {
			e.getMessage();
		}
 
	}

}
