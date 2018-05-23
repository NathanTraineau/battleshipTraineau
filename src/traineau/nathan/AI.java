package traineau.nathan;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public abstract class AI implements IPlayer {
	
	
	protected String name;
	protected HashSet<Ship> ships = new HashSet<Ship>();
	protected Map map = new Map();
	
	public AI() {
		setName("AI");
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName(){
		return this.name ;
	}
	
	public Set<Ship> getShips(){
		return this.ships ;
	}
	public void addShipPlayer(Ship ship) {
		this.ships.add(ship);
		this.getPlayerMap().addShipCoords(ship);
	};
	
	// Map
	
	// Could a map exists without a player ? I guess
	public Map getPlayerMap(){
		return this.map ;
	}
	
	public void setPlayerMap(  char lenght,int height){
		this.getPlayerMap().setMap( height, lenght);
		
	}
	 public Ship isPlayerHit(Coordinates missileCoord) {
		//Check if the player is hit and put the hit marker on the ship
		Iterator<Ship> it = getShips().iterator();
		while (it.hasNext()) {
			Ship ship = it.next();
			if (ship.isHit(missileCoord)){
				return ship;
			}
		};
		
		return null;
	}
	
	
	public boolean occupyCoordinates(Coordinates coords) {
		Iterator<Ship> it = getShips().iterator();
		while (it.hasNext()) {
			if (it.next().shipIsOnCoord(coords)){
				return true;
			}
		};
		return false;
	}

	public boolean playerIsDead() {
		Iterator<Ship> it = getShips().iterator();
		while (it.hasNext()) {
			if ((it.next().isDestroyed()) == false) {
				return false;
			}
			
		}
		
		return true;
	}
	
	
	public Coordinates chooseRandomCoordinates( IPlayer iPlayer) {
		// choose random coord on the player map
		
		Random r = new Random();
		
		int rand = (int) 65 + r.nextInt((int) (iPlayer.getPlayerMap().getLenght()+1)- 65);
		rand = rand - 65;
		//the rank of the chosen letter
		char letter1 = '0';
		// it count the incrementation of i
		for ( char i = 'A'; i<= iPlayer.getPlayerMap().getLenght(); i++) {
			if ( (int) i- (int)'A' == rand) {
				letter1 = i;
				
			};
		
		}
		int nb1 = 1 + r.nextInt(iPlayer.getPlayerMap().getHeight());
		
		return new Coordinates(letter1,nb1);
	}
	/*
	public Coordinates chooseShipCoordinates(Rules rules, Player opponentPlayer, int size) {
		return new Coordinates("E-4","E-9");
		
	}*/
	
		public Coordinates[] chooseShipCoordinates(Rules rules, IPlayer currentPlayer, int size) {
			
		int isBlocked = 0;
		
	    Coordinates startCoord = chooseRandomCoordinates( currentPlayer);
	
		int nb1 = startCoord.getNumber(); 
		char letter1 = startCoord.getLetter(); 
		
		int rand2;
		int nb2 = -1;
		char letter2 = '0';
		Coordinates endCoord = new Coordinates(letter2,nb2);
		
		
		// Now we will choose a endCoordinates considering the start one
		while ((!rules.verifAIShipCoordinates(currentPlayer, letter1+"-"+nb1,letter2+"-"+nb2, size))) {
		Random r = new Random();
		rand2 = 0 + r.nextInt(4);
		
		isBlocked = isBlocked +1;
		if (isBlocked > 10)
		{
			// if the ship is blocked because the startCoord is surrounded by ships
			return chooseShipCoordinates(rules, currentPlayer, size);
		}
		
			switch(rand2){
				case 0 :
					
					// we create the boat down
					nb2 = nb1 + (size-1);
					letter2 = letter1;	
					break;
						
				case 1 :
					
					// we create the ship to the up
					nb2 = nb1 - (size -1);
					letter2 = letter1;	
					break;
				case 2 :
					
					// we create the ship to the left
					nb2 = nb1 ;
					for ( char i = letter1; i>= 'A'; i--) {
						if ( (int) letter1 - (int)i == size-1 ) {
							letter2 = i;
						};
					}
					break;
				case 3 :
					// we create the ship to the right
					nb2 = nb1 ;
					for ( char i = letter1; i<= currentPlayer.getPlayerMap().getLenght() ; i++) {
						if ( (int) i - (int) letter1 == size-1 ) {
							letter2 = i;
						};
					}
					break;
					}
		}
		
		endCoord = new Coordinates(letter2,nb2);
		
		Coordinates[] coords= {startCoord,endCoord};
		
		return coords ;
		}
		
	
	
		public abstract Coordinates chooseShoot(Rules rules, IPlayer opponentPlayer);
		
		public Ship shoot(Rules rules, IPlayer opponentPlayer, Coordinates missileCoord ) {
			return opponentPlayer.isPlayerHit(missileCoord);
		}
		
		public void setHit(Ship ship, Coordinates missileCoord) {
			ship.setHit(missileCoord);
		}
		
		public boolean isDestroyed(Ship ship) {
			return ship.isDestroyed();
		}
		
		// Add the shots on the map
		
		public void addOwnMapMissCoords(Coordinates coord) {
			//Structure set do not accept twin elements
			getPlayerMap().addOwnMissCoords(coord);
		
		}
		
		
		public void  addOwnMapHitCoords(Coordinates coord){
			// add coordinates were a boat was hit 
			getPlayerMap().addOwnHitCoords(coord);
			
		}
		
		public void addOpponentMapMissCoords(Coordinates coord) {
			//Structure set do not accept twin elements
			getPlayerMap().addOpponentMissCoords(coord);
		
		}
		
		
		public void  addOpponentMapHitCoords(Coordinates coord){
			// add coordinates were a boat was hit 
			getPlayerMap().addOpponentHitCoords(coord);
			
		}
		
		public void  addShipMapCoords(Ship ship){
			// add coordinates were a boat was hit 
			getPlayerMap().addShipCoords(ship);
			}
}





