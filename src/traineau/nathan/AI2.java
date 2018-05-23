
package traineau.nathan;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class AI2 extends AI {
	
	HashSet<Coordinates> shotCoords = new HashSet<Coordinates>();;
	ArrayList<Coordinates> currentHitShots = new ArrayList<Coordinates>();
	int NumberOfMissShots=0;
	int isBlocked = 0;
	// We save the lasts hit shots until we have sinked the ship targeted
	
	public AI2() {
		
	}
	
	
	
	
public Coordinates chooseShoot(Rules rules ,IPlayer opponentPlayer) {
	// This ai will save the 2 lasts HitShots
		Coordinates coordFromWhereWeHit;
		Coordinates shot;
		int nbHitShot = getCurrentHitShots().size() ;
		ArrayList<Coordinates> hitShots = getCurrentHitShots();	
		
		
		switch(nbHitShot) {
		
		case 0 : 
			 shot = chooseRandomCoordinates(opponentPlayer);
			if (shot.containsCoord(getShotCoords()) || !rules.inMap(this,shot)) {;
				return chooseShoot(rules, opponentPlayer);
			}
			else {
				addShotCoords( shot);
				return shot;
			}
			
		
		
		case 1 :
			shot = chooseCoordinatesAroundHitShot(rules, hitShots.get(0));
			if (shot.containsCoord(getShotCoords()) || !rules.inMap(this,shot) ) {
				isBlocked = isBlocked +1;
				if (isBlocked >8) {
					currentHitShotsClear();
					isBlocked = 0;
				}
				return chooseShoot(rules, opponentPlayer);
			}
			else {
				addShotCoords( shot);
				
				return shot;
			}
			
		default :
		
			if (getCurrentNumberOfMissShots()>1) {
				// it mean that there is others ships so we 
				//will shot around all of the current hit shots
				// because if we found 2 hit coordinates we should be able to hit at least in 2 times
			
				Random r = new Random();
				int index = 0 + r.nextInt(nbHitShot-1);
				shot = chooseCoordinatesAroundHitShot(rules, getCurrentHitShots().get(index));
				if (shot.containsCoord(getShotCoords()) || !rules.inMap(this, shot) ) {
					isBlocked = isBlocked +1;
					if (isBlocked >8) {
						currentHitShotsClear();
						isBlocked = 0;
					}
					return chooseShoot(rules, opponentPlayer);
				}
				else {
					addShotCoords( shot);
					return shot;
				}
			}
			 if (isShipVertical(getCurrentHitShots())){
				 coordFromWhereWeHit = getCoordinatesWithHighestNumber(getCurrentHitShots());
					Coordinates ds = downHitShot(coordFromWhereWeHit);
					if (ds.containsCoord(getShotCoords()) || !rules.inMap(this, ds)){
						coordFromWhereWeHit = getCoordinatesWithSmallestNumber(getCurrentHitShots());
						Coordinates us = upHitShot(coordFromWhereWeHit);
						addShotCoords( us);
						return us;
				}
					if (!rules.inMap(this,ds)){
						currentHitShotsClear();
						return chooseShoot(rules, opponentPlayer);
					}
					addShotCoords( ds);
					return ds;
			}
			else if (isShipHorizontal(getCurrentHitShots())){
				
				 coordFromWhereWeHit = getCoordinatesWithHighestLetter(getCurrentHitShots());
				 Coordinates rs = rightHitShot(coordFromWhereWeHit);
				 
				if (rs.containsCoord(getShotCoords()) || !rules.inMap(this,rs)){
					coordFromWhereWeHit = getCoordinatesWithSmallestLetter(getCurrentHitShots());
					Coordinates ls = leftHitShot(coordFromWhereWeHit);
					addShotCoords(ls);
					return ls;
				}
				if (!rules.inMap(this,rs)){
					currentHitShotsClear();
					return chooseShoot(rules, opponentPlayer);
				}
				addShotCoords( rs);
				return rs;
			}
		}
		return null;// chooseCoordinatesAroundHitShot(rules, hitShots.get(0));
		
	}

public void setCurrentNumberOfMissShots(int nb) {
	this.NumberOfMissShots = nb;
	
}

public int getCurrentNumberOfMissShots() {
	return this.NumberOfMissShots ;
	
}
public boolean isThereSeveralShips(ArrayList<Coordinates> coords) {
	Iterator<Coordinates> it = coords.iterator();
	int number = it.next().getNumber();
	char letter = it.next().getLetter();
	while (it.hasNext()) {
		Coordinates i = it.next();
		if (i.getLetter() != letter && i.getNumber() != number) {
			return true;
		}
	}
		
	return false;
}

public Coordinates getCoordinatesWithHighestLetter(ArrayList<Coordinates> coords) {
	Iterator<Coordinates> it = coords.iterator();
	Coordinates max = new Coordinates('A',1);
	while (it.hasNext()) {
		Coordinates i = it.next();
	
		if (i.getLetter()> max.getLetter()) {
			max = i;
		}
	}
	return max;
}


public Coordinates getCoordinatesWithSmallestLetter(ArrayList<Coordinates> coords) {
	Iterator<Coordinates> it = coords.iterator();
	Coordinates min = new Coordinates(getPlayerMap().getLenght(),getPlayerMap().getHeight());
	while (it.hasNext()) {
		Coordinates i = it.next();
	
		if (i.getLetter()< min.getLetter()) {
			min = i;
		}
	}
	return min;
}

    public Coordinates getCoordinatesWithHighestNumber(ArrayList<Coordinates> coords) {
    	Iterator<Coordinates> it = coords.iterator();
    	Coordinates max = new Coordinates('A',1);
		while (it.hasNext()) {
			Coordinates i = it.next();
	
			if (i.getNumber()> max.getNumber()) {
				max = i;
			}
		}
		return max;
    }
    
    
    public Coordinates getCoordinatesWithSmallestNumber(ArrayList<Coordinates> coords) {
    	Iterator<Coordinates> it = coords.iterator();
    	Coordinates min = new Coordinates(getPlayerMap().getLenght(),getPlayerMap().getHeight());
		while (it.hasNext()) {
			Coordinates i = it.next();
			
			if (i.getNumber() < min.getNumber()) {
				min = i;
			}
		}
		return min;
    }
    

	public void addShotCoords(Coordinates coord) {
		//Structure set do not accept twin elements
		 this.shotCoords.add(coord);
	
	}
	
	public Set<Coordinates> getShotCoords() {
		//Structure set do not accept twin elements
		 return this.shotCoords;
	
	}
	
	
	public void addHitShots(Coordinates shot) {
		//Structure set do not accept twin elements
		 this.currentHitShots.add(shot);
	
	}
	
	public ArrayList<Coordinates> getCurrentHitShots() {
		//Structure set do not accept twin elements
		 return this.currentHitShots;
	
	}
	
	public void currentHitShotsClear() {
		getCurrentHitShots().removeAll(getCurrentHitShots());
		
	}
	
	public boolean isShipHorizontal(ArrayList<Coordinates> coords) {
		//takes an array of 2 coordinates and return true if they are vertical
		return coords.get(0).getNumber() == coords.get(1).getNumber();
	}

	public boolean isShipVertical(ArrayList<Coordinates> coords) {
		//takes an array of 2 coordinates and return true if they are Horizontal
		return coords.get(0).getLetter() == coords.get(1).getLetter();
	}


	// methods which return coordinates around a shot, randomly or targeted
	
	public Coordinates chooseCoordinatesAroundHitShot(Rules rules, Coordinates shot) {
	
	int nb1 = shot.getNumber(); 
	char letter1 = shot.getLetter(); 
		
	int rand;
	int nb2 = -1;
	char letter2 = '0';
	Random r = new Random();
	rand = 0 + r.nextInt(4);
		
	
	switch(rand){
	case 0 :
		
		// coord down
		nb2 = nb1 + 1;
		letter2 = letter1;	
		break;
			
	case 1 :
		
		// coord to the up
		nb2 = nb1 -1;
		letter2 = letter1;	
		break;
	case 2 :
		
		// coord to the left
		nb2 = nb1 ;
		for ( char i = letter1; i>= 'A'; i--) {
			if ( (int) letter1 - (int)i == 1 ) {
				letter2 = i;
			};
		}
		break;
	case 3 :
		// coord to the right
		nb2 = nb1 ;
		for ( char i = letter1; i<= getPlayerMap().getLenght() ; i++) {
			if ( (int) i - (int) letter1 == 1 ) {
				letter2 = i;
			};
		}
		break;
		}
	
	Coordinates coord = new Coordinates(letter2,nb2);
	if (!rules.inMap(this, coord)) {
		return chooseCoordinatesAroundHitShot(rules, shot);
	}
	return coord ;
}

	
	public Coordinates leftHitShot(Coordinates coord) {
		// return the coordinates at the left of the one in parameter
		int nb1 = coord.getNumber(); 
		char letter1 = coord.getLetter(); 
		
		char letter2 = '0';
		for ( char i = letter1; i>= 'A'; i--) {
			if ( (int) letter1 - (int)i == 1 ) {
				letter2 = i;
			};
		}
		return new Coordinates(letter2,nb1);
}
	
	public Coordinates rightHitShot(Coordinates coord) {
		// return the coordinates at the right of the one in parameter 
		int nb1 = coord.getNumber(); 
		char letter1 = coord.getLetter(); 
		char letter2 = '0';
		for ( char i = letter1; i<= getPlayerMap().getLenght() ; i++) {
			if ( (int) i - (int) letter1 == 1 ) {
				letter2 = i;
			};
		}
		return new Coordinates(letter2,nb1);
}
	
	
	public Coordinates upHitShot(Coordinates coord) {
		
		int nb1 = coord.getNumber(); 
		char letter1 = coord.getLetter(); 
		
	
		int nb2 = 0;
			
				
				// we create the ship to the up
				nb2 = nb1 - 1;
		
				
			
		return new Coordinates(letter1,nb2);
}
	
	public Coordinates downHitShot(Coordinates coord) {
		
		int nb1 = coord.getNumber(); 
		char letter1 = coord.getLetter(); 
		
		int nb2 = nb1 +1;		
		// we create the ship to the down
		
			
		return new Coordinates(letter1,nb2);
}
}


