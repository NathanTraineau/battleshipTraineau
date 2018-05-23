package traineau.nathan;
import java.util.Scanner;



public class battleship {
	
	public static void main(String[] args) {
		
		System.out.println("Which mode do you want to play ? HumanVsHuman 0 / HumanVsAI 1  ");
		Scanner sc = new Scanner(System.in);
		int answer1 = sc.nextInt();
		Game game = new Game();
		Rules rules = new Rules();
		
		switch (answer1) {
			case 0 :
				System.out.println(((Human) game.play(rules, new Human(), new Human())).getName()+" has won");
				break;
			case 1 :
				System.out.println("Which AI do you want to play against ? AI0 0 / AI1 1  / AI2 2");
				int answer2 = sc.nextInt();
				switch (answer2) {
				case 0 :
					System.out.println(game.play(rules, new Human(), new AI0()).getName()+" has won");
					break;
				case 1 : 
					System.out.println(game.play(rules, new Human(), new AI1()).getName()+" has won");
					break;
				case 2 : 
					System.out.println(game.play(rules, new Human(), new AI2()).getName()+" has won");
					
				}break;
			
	}
		sc.close();
}
}
		
			
		// Demander dans varia StartCoord et EndCoord
		
		



