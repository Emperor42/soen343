package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ProfileManagement {
    
    static JSONArray profileArray;
    public ProfileManagement(){
        profileArray = new JSONArray();
    }
    
    public Profile findProfileFromName(String name){
        if (profileArray == null) {
            JSONParser parser = new JSONParser();	
            try (FileReader reader = new FileReader("profiles.json"))
            {
                System.out.println("I found the file...");
                Object obj = parser.parse(reader);
                JSONObject jsonObject = (JSONObject) obj;
                profileArray = (JSONArray) jsonObject.get("Rooms");
                reader.close();
            }
            catch (FileNotFoundException e) {
                System.out.println("I could not find the file, dumping some dummy values");
                profileArray.add(new Profile("Parent"));
                e.getMessage();
            }
            catch (IOException e) {
                e.getMessage();
                }
            catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0 ;i<profileArray.size();i++){
            if (((Profile)profileArray.get(i)).getProfileName().equalsIgnoreCase(name)){
                return (Profile)profileArray.get(i);
            }
        }
        return null;
    }
}
