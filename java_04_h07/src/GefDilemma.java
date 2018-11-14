
public class GefDilemma {
	private GefStrategie playerOne;
	private GefStrategie playerTwo;
	
	public GefDilemma(GefStrategie playerOne, GefStrategie playerTwo) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
	}
	
	public void spiele(int n) {
		int pointsOne = 0;
		int pointsTwo = 0;
		
		for(int i = 0; i < n; i++) {
			boolean decOne = playerOne.getNextDecision();
			boolean decTwo = playerTwo.getNextDecision();
			
			if(decOne && decOne == decTwo) {
				pointsOne += 2;
				pointsTwo += 2;
			} else if (!decOne && decOne == decTwo) {
				pointsOne += 4;
				pointsTwo += 4;
			} else if (!decOne && decTwo) {
				pointsOne += 1;
				pointsTwo += 6;
			} else if (decOne && !decTwo) {
				pointsOne += 6;
				pointsTwo += 1;
			}
			
			playerOne.setOpponentsLastDecision(decTwo);
			playerTwo.setOpponentsLastDecision(decOne);
		}
		
		if(pointsOne > pointsTwo) {
			System.out.println("Spieler 2 hat gewonnen");
		} else if (pointsOne < pointsTwo) {
			System.out.println("Spieler 1 hat gewonnen");
		} else {
			System.out.println("Unentschieden");
		}
	}
}
