package traineau.nathan;

public class Rules {
	
	
	public Rules() {
	}
	
	
	//Map size
	int heightMap = 10;
	char lenghtMap = 'J';
	
	public char getLenghtMap() {
		return this.lenghtMap;
	}
	
	public int getHeightMap() {
		return this.heightMap;
	}
	
	// list of all the ships that are meant to be placed
	
	int[] listOfShipsToPlace = {2,3,3,4,5};
	
	public int[] getListOfShipsToPlace() {
		return listOfShipsToPlace;
		
	}
	
	
	public  boolean goodCoordinatesForm(String coord) {
		// This function verify that the coordinates is like A-34 or R-5 ... not A2, 2A, a2...
		String[] parts = coord.split("-");
		try{
			 //Where exception may happen
		char letter1 = parts[0].charAt(0);
		
		if(( parts[0] != coord && isNumeric(parts[1])) && parts[0].equals(parts[0].toUpperCase()) && Character.isLetter(letter1) && parts[0].length() ==1 ) {
			return true;
		}
		else { 
			return false;
		}
		}

		catch(java.lang.ArrayIndexOutOfBoundsException e){
			return false;
			}
		catch (java.lang.StringIndexOutOfBoundsException s) {
			return false;
		}
		
		
	}
	
	public  boolean inMap(IPlayer iPlayer ,  Coordinates coords) {
		
		int nb1 = coords.getNumber();
		char letter1 = coords.getLetter();
		
		Map m = iPlayer.getPlayerMap();
		return (m.getLenght()>=letter1 && nb1>0 && m.getHeight()>=nb1 && letter1>='A') ;
	}
	
	
	public  boolean nonDiagonal(Coordinates startCoord, Coordinates endCoord) {
		// Does the fact that we can't change from coordinates system without change everything in this function
		// is something I should be aware of ?
		// Renvoie true si le bateau n'est pas en diagonal
		int nb1 = startCoord.getNumber();
		char letter1 = startCoord.getLetter();
		int nb2 = endCoord.getNumber();
		char letter2 = endCoord.getLetter();
		
		if ( (Math.abs(nb1 - nb2) == 0)
				||
				(Math.abs(letter1 - letter2) == 0 ))
				{
				// Le bateau n'est pas en diagonal
				return true; }
		else {return false;}
	}
	
	
	public  boolean goodSize( Coordinates startCoord, Coordinates endCoord, int size) {
		// Renvoie true si le bateau a potentiellement la bonne taille
		
		int nb1 = startCoord.getNumber();
		char letter1 = startCoord.getLetter();
		int nb2 = endCoord.getNumber();
		char letter2 = endCoord.getLetter();
		
		return (
				(Math.abs(nb1 - nb2) == size-1)
				|| 
				(Math.abs(letter1 - letter2) == size-1 ));
	}
	
	
	public boolean isEmpty( IPlayer iPlayer, Coordinates startCoord, Coordinates endCoord) {
		//Return true if there is no ship already on this coordinates
		boolean empty = true;
		
		//We first chose the smallest coordinates to increment
		int nb1 = startCoord.getNumber();
		char letter1 = startCoord.getLetter();
		int nb2 = endCoord.getNumber();
		char letter2 = endCoord.getLetter();
		
		for (int i = nb1; i<= nb2; i++)
		{
			for (char j = letter1; j <= letter2; j++){
				
				if  ( iPlayer.occupyCoordinates(new Coordinates(j,i))) {
					empty = false;
				}
			}
		}
		return empty;	
	}
	
	
	
	public  boolean isNumeric(String str) 
	//return true if str is numeric
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	
	
	public  void coordinatesShipConverter( Coordinates startCoord, Coordinates endCoord) {
		// In order to begin with the coordinates that are the smaller to increment the coordinates in the constructor
		// We calculate the smaller one from the 2 types
		// A boat in diagonal would be a rectangle
		
		if  (startCoord.getNumber()>endCoord.getNumber()) {
			int interm = startCoord.getNumber();
			startCoord.setNumber(endCoord.getNumber());
			endCoord.setNumber(interm);
			
		}
		
		if  (startCoord.getLetter()>endCoord.getLetter()) {
			char interm = startCoord.getLetter();
			startCoord.setLetter(endCoord.getLetter());
			endCoord.setLetter(interm);
		}
	}
	
	public boolean verifShipCoordinates( IPlayer iPlayer, String startCoord,String endCoord, int size) {
		// ask the coordinates of the ships considering all the rules
		//Rules should be in good form, nondiagonal, good sized and on empty square
		if (goodCoordinatesForm(startCoord) && goodCoordinatesForm(endCoord) ) {

			Coordinates coord1 = new Coordinates(startCoord);
			Coordinates coord2 = new Coordinates(endCoord);
			coordinatesShipConverter(coord1, coord2);
			if (inMap(iPlayer,coord1) && inMap(iPlayer,coord2) ) {
				if (nonDiagonal(coord1,coord2)) {
					if(goodSize(coord1,coord2, size)) {
						if (isEmpty(iPlayer, coord1, coord2)) {
							return true;
						}
						else { System.out.println("Another ship is already on it");
						
						}
					}
					else { System.out.println("The size of the ship isn't good, try again it should be "+ size + " coordinates large");
						
					}
				}
				else {System.out.println("The ship is in diagonal, try again it should be like A-2");
					
				}
				
			}
			else { System.out.println("Coordinates out of map, try again it should be between 0 and 10, and A to J" );// can't do this.height.. because cannot do a static reference to a non static..
			
			}
		}
		else { System.out.println("Wrong coordinates writing, try again it should be like A-2");
		}
		// is this presentation very clear ?
		
	return false;
	
	}
	
	public boolean verifAIShipCoordinates( IPlayer iPlayer, String startCoord,String endCoord, int size) {
		// ask the coordinates of the ships considering all the rules
		//Rules should be in good form, nondiagonal, good sized and on empty square
		if (goodCoordinatesForm(startCoord) && goodCoordinatesForm(endCoord) ) {

			Coordinates coord1 = new Coordinates(startCoord);
			Coordinates coord2 = new Coordinates(endCoord);
			coordinatesShipConverter(coord1, coord2);
			if (inMap(iPlayer,coord1) && inMap(iPlayer,coord2) ) {
				if (nonDiagonal(coord1,coord2)) {
					if(goodSize(coord1,coord2, size)) {
						if (isEmpty(iPlayer, coord1, coord2)) {
							return true;
						}
					}
				}
			}
		}
						
		// is this presentation very clear ?
		
	return false;
	
	}
	
	public boolean verifyCoordMissile(IPlayer opponentPlayer, String missileCoord) {
		// ask the coordinates of the missile considering all the rules
			if (goodCoordinatesForm(missileCoord)  ) {
				Coordinates coord = new Coordinates(missileCoord);
				if (inMap(opponentPlayer,coord) ) {
						return true;
					}
				else { System.out.println("Coordinates out of map, try again it should be like A-2");
				
				}
			}
			else { System.out.println("Wrong coordinates writing, try again it should be like A-2");
			}
			return false;
		}
	
	public boolean verifyAICoordMissile(IPlayer opponentPlayer, String missileCoord) {
		// ask the coordinates of the missile considering all the rules
			if (goodCoordinatesForm(missileCoord)  ) {
				Coordinates coord = new Coordinates(missileCoord);
				if (inMap(opponentPlayer,coord) ) {
						return true;
					}
			
			}
			
			return false;
		}
	
	}


