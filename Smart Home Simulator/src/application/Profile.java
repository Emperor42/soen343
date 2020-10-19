package application;

public class Profile {

	String  profileName;
	boolean windowAccess = false, 
	doorAccess = false, 
	garageAccess = false, 
	lightAccess = false,
	houseAccess = false,
	offPropertyAccess = false;
	
	public Profile(String profileName) {
		this.profileName = profileName;
	}
	
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	
	public String getProfileName() {
		return profileName;
	}
	
	public void setWindowAccess(boolean windowAccess) {
		this.windowAccess = windowAccess;
	}
	
	public boolean getWindowAccess() {
		return windowAccess;
	}
	
	public void setDoorAccess(boolean doorAccess) {
		this.doorAccess = doorAccess;
	}
	
	public boolean getDoorAccess() {
		return doorAccess;
	}
	
	public void setGarageAccess(boolean garageAccess) {
		this.garageAccess = garageAccess;
	}
	
	public boolean getGarageAccess() {
		return garageAccess;
	}
	
	public void setLightAccess(boolean lightAccess) {
		this.lightAccess = lightAccess;
	}
	
	public boolean getLightAccess() {
		return lightAccess;
	}
	
	public void setHouseAccess(boolean houseAccess) {
		this.houseAccess = houseAccess;
	}
	
	public boolean getHouseAccess() {
		return houseAccess;
	}
	
	public void setOffPropertyAccess(boolean offPropertyAccess) {
		this.offPropertyAccess = offPropertyAccess;
	}
	
	public boolean getOffPropertyAccess() {
		return offPropertyAccess;
	}
}
