/**
 * Strategie "TitForTat": Kopiert in jeder Runde den letzten Zug des anderes Spielers.
 * Beginnt mit Kooperation.
 * @author Jonas, Julia, Dominik
 *
 */
public class TitForTat implements GefStrategie {
	private boolean lastDecission;
	
	public TitForTat() {
		lastDecission = true;
	}
	
	@Override
	public boolean getNextDecision() {
		return lastDecission;
	}

	@Override
	public void setOpponentsLastDecision(boolean decision) {
		lastDecission = decision;
	}

}
