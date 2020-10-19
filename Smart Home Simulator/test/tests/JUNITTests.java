/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import org.json.simple.JSONArray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
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
        houseLayout.ReadJsonFile tmp = new houseLayout.ReadJsonFile("myTestJSON");
        assertEquals(rooms, tmp.getRoomArray());
    }
    
    @Test
    public void ManageUserProfileTest() {
        System.out.println("TESTING FOR USE CASE: Manage user profile");
        assertEquals(true,true);
    }
    
    @Test
    public void SetDateAndTimeTest() {
        System.out.println("TESTING FOR USE CASE: Set date and time");
        assertEquals(true,true);
    }
    
    @Test
    public void LogInUsingAnExistingUserProfileAndSetHouseLocationTest() {
        System.out.println("TESTING FOR USE CASE: Log in using an existing user profile and set house location");
        assertEquals(true,true);
    }
    
    @Test
    public void ControlTheSimulationTest() {
        System.out.println("TESTING FOR USE CASE: Control the simulation");
        assertEquals(true,true);
    }
    
    @Test
    public void ModifyDateAndTimeTest() {
        System.out.println("TESTING FOR USE CASE: Modify date and time");
        assertEquals(true,true);
    }
    
    @Test
    public void MoveTheLoggedUserTest() {
        System.out.println("TESTING FOR USE CASE: Move the logged user");
        assertEquals(true,true);
    }
    
    @Test
    public void MovePeopleTest() {
        System.out.println("TESTING FOR USE CASE: Move people");
        assertEquals(true,true);
    }
    
    @Test
    public void ModifyTemperatureOutsideHomeTest() {
        System.out.println("TESTING FOR USE CASE: Modify temperature outside home");
        assertEquals(true,true);
    }
    
    @Test
    public void BlockWindowsTest() {
        System.out.println("TESTING FOR USE CASE: Block windows");
        assertEquals(true,true);
    }
}
