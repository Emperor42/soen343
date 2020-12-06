//ISSUE 1 CLEAR
package houseLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.lang.model.SourceVersion;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import shc.SmartHomeCore;

public class ReadJsonFile {

    private JSONArray roomArray;

    public ReadJsonFile(String fileName) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)) {
            System.out.println("I found the file...");
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            roomArray = (JSONArray) jsonObject.get("Rooms");
            System.out.println("Here is "+roomArray.toString());

//            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("I could not find the file");
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

    }

    public JSONArray getRoomArray() {
        return roomArray;
    }

    public void setRoomArray(JSONArray roomArray) {
        this.roomArray = roomArray;
    }

}
