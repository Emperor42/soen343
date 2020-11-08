//ISSUE 1 CLEAR
package houseLayout;

/**
 *
 * @author Justin Loh 40073776
 */
public class Room {

    String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    /**
     * A function to see if the given name from the Room JSON is the same as
     * this room
     *
     * @param given
     * @return true if the names are the same
     * @author Matthew Giancola (40019131)
     */
    public boolean isRoom(String given) {
        return Name.equals(given);
    }

    public String UpdatedOutput(String given) {
        return given + "\n" + " (" + noOfOccupants + ")";
    }

    public boolean doorBlocked() {
        return doorBlocked;
    }

    public boolean windowBlocked() {
        return windowBlocked;
    }

    int windows;
    int doors;
    Person[] occupants = new Person[10]; // limit of 10 people per room
    int noOfOccupants = 0;
    //simple boolean for a blocked window/door
    public boolean windowBlocked = false;
    public boolean doorBlocked = false;
    public boolean lightBlocked = false;

    public void addOccupants(Person p) {

        for (int i = 0; i < 11; i++) {
            if (occupants[i] == null) {
                occupants[i] = p;
                noOfOccupants++;
                break;
            }
        }
    }

    public void removeOccupants(Person p) {

        for (int i = 0; i < 11; i++) {
            if (occupants[i].name.equals(p.name)) {
                occupants[i] = null;
                noOfOccupants--;
                break;
            }
        }
    }

    public int getWindows() {
        return windows;
    }

    public void setWindows(int windows) {
        this.windows = windows;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public Person[] getOccupants() {
        return occupants;
    }

    public void setOccupants(Person[] occupants) {
        this.occupants = occupants;
    }

    public int getNoOfOccupants() {
        return noOfOccupants;
    }

    public void setNoOfOccupants(int noOfOccupants) {
        this.noOfOccupants = noOfOccupants;
    }

    void printOccupants() {
        for (int i = 0; i < 11; i++) {
            System.out.println(occupants[i].name);
        }
    }

    public void blockWindows() {
        System.out.println("window "+windowBlocked);
        windowBlocked = !windowBlocked;
    }
    
    public void blockLight() {
        System.out.println("light "+lightBlocked);
        lightBlocked = !lightBlocked;
    }
    
    public void blockDoor() {
        System.out.println("door "+doorBlocked);
        doorBlocked = !doorBlocked;
    }
}
