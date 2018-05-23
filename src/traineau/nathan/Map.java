package traineau.nathan;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Map {
	
	
	
	private int height;
	private char lenght;
	private HashSet<Coordinates> ownMissCoords = new HashSet<Coordinates>();
	private HashSet<Coordinates> ownHitCoords = new HashSet<Coordinates>();
	private HashSet<Coordinates> shipCoords = new HashSet<Coordinates>();
	
	// Shot of the opponent on your map
	private HashSet<Coordinates> opponentMissCoords = new HashSet<Coordinates>();
	private HashSet<Coordinates> OpponentHitCoords = new HashSet<Coordinates>();
	 
	Map() {
		
	}

	 void setMap( int height, char lenght){
		//So we can decide how tall the map is, in the main
		setHeight(height);
		setLenght(lenght);
		
	}
	
	public void opponentMapDisplay() {
		//Print the map on the console
		for (int i = 0; i <= getHeight(); i++) {
			System.out.print(i);
		    for (char j = 'A'; j <= getLenght(); j++) {
		    	Coordinates coord = new Coordinates(j,i);
		    	if (i == 0) {
		    		System.out.print("  |"+j);
		    	}
		    	else if (coord.containsCoord(getOwnMissCoords())) {
		    		System.out.print("   O");
		    	}
		    	 else if (coord.containsCoord(getOwnHitCoords())) {
		    		System.out.print("   !");
		    	}
		    	
		    	else {
		    		System.out.print("   .");
		    	}
		    		
		        
		    }
		    System.out.println(" |\n");
		    
		}
		
	}
	
	
	 public void ownMapDisplay() {
		//Print the map on the console
		for (int i = 0; i <= getHeight(); i++) {
			System.out.print(i);
		    for (char j = 'A'; j <= getLenght(); j++) {
		    	Coordinates coord = new Coordinates(j,i);
		    	if (i == 0) {
		    		System.out.print("  |"+j);
		    	}
		    	else if (coord.containsCoord(getOpponentMissCoords())) {
		    		System.out.print("   O");
		    	}
		    	 else if (coord.containsCoord(getOpponentHitCoords())) {
		    		System.out.print("   !");
		    	}
		    	else if (coord.containsCoord(getShipCoords())) {
		    		System.out.print( "   S");
		    	}
		    	
		    	else {
		    		System.out.print("   .");
		    	}
		    		
		        
		    }
		    System.out.println(" |\n");
		    
		}
		
	}
	
	 public int getHeight() {
			return this.height;
		}
		
	public void setHeight(int height) {
			this.height = height;
		}
		
	 public char getLenght() {
			return this.lenght;
		}
		
	 public void setLenght(char lenght) {
			this.lenght = lenght;
		}
		
		
	 void addOwnMissCoords(Coordinates coord) {
			//Structure set do not accept twin elements
			 this.ownMissCoords.add(coord);
		
		}
		
		
	 void  addOwnHitCoords(Coordinates coord){
			// add coordinates were a boat was hit 
			this.ownHitCoords.add(coord);
			
		}
		
	 void addOpponentMissCoords(Coordinates coord) {
			//Structure set do not accept twin elements
			 this.opponentMissCoords.add(coord);
		
		}
		
		
	 void  addOpponentHitCoords(Coordinates coord){
			// add coordinates were a boat was hit 
			this.OpponentHitCoords.add(coord);
			
		}
		
	 void  addShipCoords(Ship ship){
			// add coordinates were a boat was hit 
			Iterator<Coordinates> it = ship.getCoordShip().iterator();
			while (it.hasNext()) {
				this.shipCoords.add(it.next());
			}
			
		}



		
	 Set<Coordinates> getOwnMissCoords() {
		//Structure set do not accept twin elements
		 return this.ownMissCoords;
	
	}
	
	
	 Set<Coordinates>  getOwnHitCoords(){
		// Return all the coordinates were a boat was hit but not yet sinked
		return this.ownHitCoords;
		
	}
	
	 Set<Coordinates> getOpponentMissCoords() {
		//Structure set do not accept twin elements
		 return this.opponentMissCoords;
	
	}
	
	
	 Set<Coordinates>  getOpponentHitCoords(){
		// Return all the coordinates were a boat was hit but not yet sinked
		return this.OpponentHitCoords;
		
	}
	
	 Set<Coordinates> getShipCoords() {
		//Structure set do not accept twin elements
		 return this.shipCoords;
	
	}
	


	
	
	


}
