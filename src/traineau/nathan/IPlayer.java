package traineau.nathan;
import java.util.Set;



public interface IPlayer {
	
	public void setName(String name);
	public String getName();
	
	public Set<Ship> getShips();
	public void addShipPlayer(Ship ship);
	
	// Map
	
	// Could a map exists without a player ? I guess
	public Map getPlayerMap();
	
	public void setPlayerMap( char lenght,int height);
	public Ship isPlayerHit(Coordinates missileCoord) ;

	public boolean playerIsDead();
	
	public boolean occupyCoordinates(Coordinates coord);

	public void addOwnMapMissCoords(Coordinates coord);
	
	public void  addOwnMapHitCoords(Coordinates coord);
	
	public void addOpponentMapMissCoords(Coordinates coord);
	public boolean isDestroyed(Ship ship);
	public void setHit(Ship ship, Coordinates missileCoord);
	public void  addOpponentMapHitCoords(Coordinates coord);
	
	public void  addShipMapCoords(Ship ship);
	public Ship shoot(Rules rules, IPlayer OpponentPlayer, Coordinates missileCoord);
		
	}
	
