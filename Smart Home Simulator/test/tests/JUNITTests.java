/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.Calendar;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author emperor
 */
public class JUNITTests {

    public JUNITTests() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void ReadAndLoadAHouseLayoutFileTest() {
        System.out.println("TESTING FOR USE CASE: Read and load a house-layout file");
        JSONArray rooms = new JSONArray();
        rooms.add("Kitchen");
        rooms.add("Living RM");
        rooms.add("Bath RM");
        rooms.add("Garage");
        rooms.add("Master BR");
        rooms.add("Child BR");
        rooms.add("Guest BR");
        houseLayout.BuildJsonFile.Prep();
        houseLayout.ReadJsonFile tmp = new houseLayout.ReadJsonFile("myTestJSON");
        assertEquals(rooms, tmp.getRoomArray());
    }

    @Test
    public void ManageUserProfileTest() {
        System.out.println("TESTING FOR USE CASE: Manage user profile");
        String profileName = ("Parent");
        Profile p1 = application.ProfileManagement.findProfileFromName(profileName);
        Profile p2 = new Profile("Parent",true,true,true,true,true,true);

        String profileName = ("Child");
        Profile p3 = application.ProfileManagement.findProfileFromName(profileName);
        Profile p4 = new Profile("Child",true,true,true,false,true,false);

        String profileName = ("Guest");
        Profile p5 = application.ProfileManagement.findProfileFromName(profileName);
        Profile p6 = new Profile("Guest",true,true,false,false,false,false);

        String profileName = ("Stranger");
        Profile p7 = application.ProfileManagement.findProfileFromName(profileName);
        Profile p8 = new Profile("Stranger",false,false,false,false,false,false);

        assertEquals(p1, p2);
        assertEquals(p3, p4);
        assertEquals(p5, p6);
        assertEquals(p8, p8);

    }

    @Test
    public void SetDateAndTimeTest() {
        System.out.println("TESTING FOR USE CASE: Set date and time");
        application.DigitalClock tick = new application.DigitalClock();
        tick.bindToCurrentTime();
        assertEquals(Calendar.getInstance(), tick.getTime());
    }

    @Test
    public void LogInUsingAnExistingUserProfileAndSetHouseLocationTest() {
        System.out.println("TESTING FOR USE CASE: Log in using an existing user profile and set house location");
        System.out.println("Not Fully Implemented!");
        assertEquals(true, true);
    }

    @Test
    public void ControlTheSimulationTest() {
        System.out.println("TESTING FOR USE CASE: Control the simulation");
        System.out.println("Not Fully Implemented!");
        assertEquals(true, false);
    }

    @Test
    public void ModifyDateAndTimeTest() {
        System.out.println("TESTING FOR USE CASE: Modify date and time");
        application.DigitalClock tick = new application.DigitalClock();
        tick.bindToCurrentTime();
        assertEquals(Calendar.getInstance(), tick.getTime());
        assertEquals(true, true);
    }

    @Test
    public void MoveTheLoggedUserTest() {
        System.out.println("TESTING FOR USE CASE: Move the logged user");
        System.out.println("Not Fully Implemented!");
        assertEquals(true, false);
    }

    @Test
    public void MovePeopleTest() {
        System.out.println("TESTING FOR USE CASE: Move people");
        System.out.println("Not Fully Implemented!");
        assertEquals(true, false);
    }

    @Test
    public void ModifyTemperatureOutsideHomeTest() {
        System.out.println("TESTING FOR USE CASE: Modify temperature outside home");
        int oldTemperature = 20;
        assertEquals(main.smartHomeDashboard.newTemp ,oldTemperature);
    }

    @Test
    public void BlockWindowsTest() {
        System.out.println("TESTING FOR USE CASE: Block windows");
        System.out.println("Not Fully Implemented!");
        assertEquals(true, false);
    }

    @Test
    public void UpdateHouseLayoutTest() {
        System.out.println("TESTING FOR USE CASE: Block windows");
        System.out.println("Not Fully Implemented!");
        assertEquals(true, false);
    }

    @Test
    public void SetElapsedTimeForAlertingAuthoritiesTest() {
        System.out.println("TESTING FOR USE CASE: Block windows");

        assertEquals(true, false);
    }

    @Test
    public void SentNotificationWhenInAwayModeTest() {
        System.out.println("TESTING FOR USE CASE: Block windows");
        System.out.println("Not Fully Implemented!");
        assertEquals(true, false);
    }

    @Test
    public void SetAwayModeTest() {
        System.out.println("TESTING FOR USE CASE: Block windows");
        System.out.println("Not Fully Implemented!");
        assertEquals(true, false);
    }

    @Test
    public void AutoModeSetTest() {
        System.out.println("TESTING FOR USE CASE: Block windows");
        System.out.println("Not Fully Implemented!");
        assertEquals(true, false);
    }

    @Test
    public void TurnOnAndOffLightsTest() {
        System.out.println("TESTING FOR USE CASE: Block windows");
        System.out.println("Not Fully Implemented!");
        assertEquals(true, false);
    }

    @Test
    public void OpenAndCloseWindowTest() {
        System.out.println("TESTING FOR USE CASE: Block windows");
        System.out.println("Not Fully Implemented!");
        assertEquals(true, false);
    }

    @Test
    public void OpenAndCloseDoorTest() {
        System.out.println("TESTING FOR USE CASE: Block windows");
        System.out.println("Not Fully Implemented!");
        assertEquals(true, false);
    }

    @Test
    public void ChangeTimeSpeedTest() {
        System.out.println("TESTING FOR USE CASE: Block windows");
        System.out.println("Not Fully Implemented!");
        assertEquals(true, false);
    }

    @Test
    public void SavingProfileTest() {
        System.out.println("TESTING FOR USE CASE: Block windows");
        System.out.println("Not Fully Implemented!");
        assertEquals(true, false);
    }

}
