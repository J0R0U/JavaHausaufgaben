/**
 * Stellt das Spielprinzip und den genauen Spielablauf zweier Strategien dar.
 * @author Jonas, Julia, Dominik
 *
 */
public class GefDilemma {
	private GefStrategie playerOne;
	private GefStrategie playerTwo;

	/**
	 * Konstruktor: Initialisiert die Spielstrategien, die gegeneinander spielen
	 * @param playerOne erste Gefangenenstrategie
	 * @param playerTwo zweite Gefangenenstrategie
	 */
	public GefDilemma(GefStrategie playerOne, GefStrategie playerTwo) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
	}

	/**
	 * Spielt das Spiel zwischen den beiden Spielern mit gegebener Anzahl von Runden
	 * @param n Anzahl zu spielender Runden
	 */
	public void spiele(int n) {
		int pointsOne = 0;
		int pointsTwo = 0;

		for (int i = 0; i < n; i++) {
			boolean decOne = playerOne.getNextDecision();
			boolean decTwo = playerTwo.getNextDecision();

			if (decOne && decOne == decTwo) {
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

		String headline;
		{
			int length = Math.min(playerOne.getClass().getName().length(), playerTwo.getClass().getName().length());
			headline = String.format(" Spieler 1 (%" + length + "s) : Spieler 2 (%" + length + "s)",
					playerOne.getClass().getName(), playerTwo.getClass().getName());
		}

		System.out.println(headline);

		System.out.printf("%" + (headline.indexOf(":") - 1) + "d :%" + (headline.length() - headline.indexOf(":") - 1) + "d%n", pointsOne, pointsTwo);

		if (pointsOne > pointsTwo) {
			System.out.printf(" %" + (headline.length() - 1) + "s%n", "Spieler 2 hat gewonnen");
		} else if (pointsOne < pointsTwo) {
			System.out.printf(" %" + (headline.length() - 1) + "s%n", "Spieler 1 hat gewonnen");
		} else {
			System.out.printf(" %" + (headline.length() - 1) + "s%n", "Unentschieden");
		}
	}
}
