package traineau.nathan;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

public class Ship {
	
	private HashMap <Coordinates,Integer> shipCoords = new HashMap<>(); //We create a ghost ship in order to return a ship object in the function "isPlayerHit" 
	
	 Rules rules = new Rules();
	
	public Ship(Coordinates startCoord, Coordinates endCoord) {
		
		rules.coordinatesShipConverter(startCoord, endCoord);		
		// this function put in letter and nb the smaller in the "first"
		// the bigger in the "end"
		for (int i = startCoord.getNumber(); i<= endCoord.getNumber(); i++)
		{
			for (char j = startCoord.getLetter(); j <= endCoord.getLetter(); j++)
				// https://stackoverflow.com/questions/2047228/auto-increment-alphabet-in-java
			{
				Coordinates key = new Coordinates(j,i);
				
				this.shipCoords.put(key, 0);
			}
		
		}
		
		
	}
	
	
	
	 Set<Coordinates> getCoordShip(){

		return this.shipCoords.keySet();
	} 
	
	 boolean isHit(Coordinates missileCoord) {
		return missileCoord.containsCoord(getCoordShip());
	}
	
	 boolean shipIsOnCoord(Coordinates coords) {
		return coords.containsCoord(getCoordShip());
	}
	
	 void setHit(Coordinates missileCoord) {
		Iterator<Coordinates> it = getCoordShip().iterator();
		while (it.hasNext()) {
			Coordinates coord = it.next();
			if (coord.compareCoordinates(missileCoord)){
				this.shipCoords.put(coord, 1);
			}
		
		}
			
	}
	
	
	 Set<Coordinates> getHitCoordinates() {
		//Structure set do not accept twin elements
		// I think this is more pratical to stock it than to go through all of the hit coordinates of the map
		// to know if all of the coordinates have been hit and then tell that this ship is destroyed
	    Set<Coordinates> keys = new HashSet<Coordinates>();
	    for (Entry<Coordinates, Integer> entry : this.shipCoords.entrySet()) {
	        if (Objects.equals(1, entry.getValue())) {
	            keys.add(entry.getKey());
	        }
	    }
	    return keys;
	}
	
	
	 boolean isDestroyed() {
	
		//If we wanted to change the data structur of the hitCoordinates we may need to change this too
		// 
		return getHitCoordinates().size() == this.shipCoords.size();
	}

	
	

}
