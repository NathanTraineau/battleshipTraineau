package traineau.nathan;

public class AI0 extends AI{
	
	public AI0() {
		
	}
	
	public Coordinates chooseShoot(Rules rules, IPlayer opponentPlayer) {
		return chooseRandomCoordinates(opponentPlayer);
	}

}
