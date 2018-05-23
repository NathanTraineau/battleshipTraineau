package fr.battleship;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


import traineau.nathan.AI0;
import traineau.nathan.AI1;
import traineau.nathan.AI2;
import traineau.nathan.Game;
import traineau.nathan.IPlayer;
import traineau.nathan.Rules;
public class TestIA {
	
	int[] resultTestAI2vsAI1 ;
	int[] resultTestAI2vsAI0 ;
	int[] resultTestAI1vsAI0 ;
	
	public TestIA() {
		
		resultTestAI2vsAI1 = testAI2vsAI1();
		resultTestAI2vsAI0 = testAI2vsAI0();
		resultTestAI1vsAI0 = testAI1vsAI0();
		
	}
	
	public int[] testAI2vsAI1() {
		
		IPlayer winner;
		
		int p1Victories = 0;
		int p2Victories = 0 ;
		
		IPlayer p1 = new AI2();
		IPlayer p2 = new AI1();
		
		Game game = new Game();
		Rules rules = new Rules();
		
		for (int i=0; i<100; i++) {
			p1 = new AI2();
			p2 = new AI1();
			if (i%2 == 0) {
				winner = game.play(rules,p1,p2);
			}
			else {
				winner = game.play(rules,p2,p1);
			}
			if(p1 == winner) {
				p1Victories = p1Victories+1;
			}
			else {
				p2Victories = p2Victories +1;
			}
		}
		int[] results = {p1Victories,p2Victories};
		return results ;

	}
	
	public int[] testAI2vsAI0() {
		
		IPlayer winner;
		
		int p1Victories = 0;
		int p2Victories = 0 ;
		
		IPlayer p1 = new AI2();
		IPlayer p2 = new AI0();
		
		Game game = new Game();
		Rules rules = new Rules();
		
		for (int i=0; i<100; i++) {
			p1 = new AI2();
			p2 = new AI0();
			if (i%2 == 0) {
				winner = game.play(rules,p1,p2);
			}
			else {
				winner = game.play(rules,p2,p1);
			}
			if(p1 == winner) {
				p1Victories = p1Victories+1;
			}
			else {
				p2Victories = p2Victories +1;
			}
		}
		int[] results = {p1Victories,p2Victories};
		return results ;

	}
	
	
	public int[] testAI1vsAI0() {
		
		IPlayer winner;
		
		int p1Victories = 0;
		int p2Victories = 0 ;
		
		IPlayer p1 = new AI1();
		IPlayer p2 = new AI0();
		
		Game game = new Game();
		Rules rules = new Rules();
		
		for (int i=0; i<100; i++) {
			p1 = new AI1();
			p2 = new AI0();
			if (i%2 == 0) {
				winner = game.play(rules,p1,p2);
			}
			else {
				winner = game.play(rules,p2,p1);
			}
			if(p1 == winner) {
				p1Victories = p1Victories+1;
			}
			else {
				p2Victories = p2Victories +1;
			}
		}
		int[] results = {p1Victories,p2Victories};
		return results ;

	}



public static void main(String[] args) {
	TestIA t = new TestIA();
	List<String> lines = Arrays.asList("AI Name; score ; AI Name2 ; score2", "AI Level Beginner;"+ t.resultTestAI1vsAI0[1] +
			";Level Medium ;" + t.resultTestAI1vsAI0[0], "AI Level Beginner;"+ t.resultTestAI2vsAI0[1] +
			";Level Hard;" + t.resultTestAI2vsAI0[0],  "AI Level Medium;"+ t.resultTestAI2vsAI1[1] +
			";Level Hard;" + t.resultTestAI2vsAI1[0] );
	Path file = Paths.get("ai_proof.csv");
try {
	Files.write(file, lines, Charset.forName("UTF-8"));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
Desktop desk = Desktop.getDesktop();
try {
	desk.open(new File("ai_proof.csv"));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
}

