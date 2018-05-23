package traineau.nathan;
import java.util.Iterator;
import java.util.Set;

public class Coordinates {
	
	int number;
	char letter;
	
	public char getLetter() {
		return this.letter;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public void setLetter(char letter) {
		this.letter = letter;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void setCoordinates(char letter, int number ) {
		this.letter = letter;
		this.number = number;
	}
	
	public void setCoordinates(String coord) {
		String[] parts = coord.split("-");
		this.letter = parts[0].charAt(0);
		this.number = Integer.parseInt(parts[1]);
	}
	
	
	public Coordinates() {};
	
	public Coordinates(char letter, int number ) {
		this.letter = letter;
		this.number = number;
	}
	
	public Coordinates(String coord) {
		String[] parts = coord.split("-");
		this.letter = parts[0].charAt(0);
		this.number = Integer.parseInt(parts[1]);
	}
	
	public String coordinatesToString() {
		return this.letter+""+this.number;
	}
	
	public boolean compareCoordinates(Coordinates coord) {
		// return true if the coord is equals with the coordinates this
		return ( coord.getLetter() == getLetter()) && (coord.getNumber() == getNumber());
		}
	
	public Boolean containsCoord(Set<Coordinates> coords) {
		Iterator<Coordinates> it = coords.iterator();
		while (it.hasNext()) {
			Coordinates i = it.next();
			//System.out.println(i.coordinatesToString());;
			if (compareCoordinates(i)) {
				return true;
			}
		}
		return false;
		
	}

}
