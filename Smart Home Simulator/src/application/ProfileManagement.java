//ISSUE 1 CLEAR
package application;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ProfileManagement {

    
    final boolean ha = true;
    static JSONArray profileArray;

    public ProfileManagement() {
        profileArray = new JSONArray();
    }

    public Profile findProfileFromName(String name) {
        if (ha){
            switch(name){
                case "Parent":
                    return new Profile("Parent",true,true,true,true,true,true);
                case "Child":
                    return new Profile("Child",true,true,true,false,true,false);
                case "Guest":
                    return new Profile("Guest",true,true,false,false,false,false);
                case "Stranger":
                    return new Profile("Stranger",false,false,false,false,false,false);
            }
        }
        if (profileArray == null) {
            JSONParser parser = new JSONParser();
            try (FileReader reader = new FileReader("profiles.json")) {
                System.out.println("I found the file...");
                Object obj = parser.parse(reader);
                JSONObject jsonObject = (JSONObject) obj;
                profileArray = (JSONArray) jsonObject.get("Rooms");
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("I could not find the file, dumping some dummy values");
                profileArray.add(new Profile("Parent"));
                e.getMessage();
            } catch (IOException e) {
                e.getMessage();
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < profileArray.size(); i++) {
            if (((Profile) profileArray.get(i)).getProfileName().equalsIgnoreCase(name)) {
                return (Profile) profileArray.get(i);
            }
        }
        return null;
    }

    public void saveProfile(Profile parent, Profile children, Profile guest, Profile stranger)
    {
        JSONObject parentPermission = new JSONObject();
            parentPermission.put("profileName", parent.getProfileName());
            parentPermission.put("windowAccess", parent.getWindowAccess());
            parentPermission.put("doorAccess", parent.getDoorAccess());
            parentPermission.put("garageAccess", parent.getGarageAccess());
            parentPermission.put("lightAccess", parent.getLightAccess());
            parentPermission.put("houseAccess", parent.getHouseAccess());
            parentPermission.put("offPropertyAccess", parent.getOffPropertyAccess());

            JSONObject childrenPermission = new JSONObject();
            childrenPermission.put("profileName", children.getProfileName());
            childrenPermission.put("windowAccess", children.getWindowAccess());
            childrenPermission.put("doorAccess", children.getDoorAccess());
            childrenPermission.put("garageAccess", children.getGarageAccess());
            childrenPermission.put("lightAccess", children.getLightAccess());
            childrenPermission.put("houseAccess", children.getHouseAccess());
            childrenPermission.put("offPropertyAccess", children.getOffPropertyAccess());

            JSONObject guestPermission = new JSONObject();
            guestPermission.put("profileName", guest.getProfileName());
            guestPermission.put("windowAccess", guest.getWindowAccess());
            guestPermission.put("doorAccess", guest.getDoorAccess());
            guestPermission.put("garageAccess", guest.getGarageAccess());
            guestPermission.put("lightAccess", guest.getLightAccess());
            guestPermission.put("houseAccess", guest.getHouseAccess());
            guestPermission.put("offPropertyAccess", guest.getOffPropertyAccess());

            JSONObject strangerPermission = new JSONObject();
            strangerPermission.put("profileName", stranger.getProfileName());
            strangerPermission.put("windowAccess", stranger.getWindowAccess());
            strangerPermission.put("doorAccess", stranger.getDoorAccess());
            strangerPermission.put("garageAccess", stranger.getGarageAccess());
            strangerPermission.put("lightAccess", stranger.getLightAccess());
            strangerPermission.put("houseAccess", stranger.getHouseAccess());
            strangerPermission.put("offPropertyAccess", stranger.getOffPropertyAccess());


        JSONArray userPermission =  new JSONArray();
        userPermission.add(parentPermission);
        userPermission.add(childrenPermission);
        userPermission.add(guestPermission);
        userPermission.add(strangerPermission);

        JSONObject profile = new JSONObject();
        profile.put("Profile", userPermission);


        try (FileWriter file = new FileWriter("profiles.json",false)) {
 
            file.write(profile.toJSONString());
            file.flush();
            file.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
