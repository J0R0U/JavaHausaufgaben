/**
 * Strategie "TitForTat": Kopiert in jeder Runde den letzten Zug des anderes Spielers.
 * Beginnt mit Kooperation.
 * @author Jonas, Julia, Dominik
 * V01.00B01
 */
public class TitForTat implements GefStrategie {
	private boolean lastDecission;
	
	/**
	 * Setzt lastDecission auf true.  
	 */
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
