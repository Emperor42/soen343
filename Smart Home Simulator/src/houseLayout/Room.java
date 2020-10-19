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



	int windows;
	int doors;
	Person[] occupants = new Person[10]; // limit of 10 people per room
	int noOfOccupants = 0;
	
	public void addOccupants(Person p) {
		for(int i = 0;i<11;i++) {
			if (occupants[i]==null) {
				occupants[i]=p;
				noOfOccupants++;
				break;
			}	
		}
	}
	
	void removeOccupants(Person p) {
		for(int i = 0;i<11;i++) {
			if (occupants[i].name.equals(p.name)) {
				occupants[i]=null;
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
		for(int i = 0;i<11;i++) {
			System.out.println(occupants[i].name);
		}
	}
}
