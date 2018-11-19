/**
 * Strategie "Pavlov": Kooperiert dann, wenn beide Spieler in der letzten Runde die gleiche Entscheidung getroffen haben.
 * Beginnt mit Kooperation.
 * @author Jonas, Julia, Dominik
 * V01.00B01
 */
public class Pavlov implements GefStrategie {
	private boolean kooperate;
	
	/**
	 * Setzt koorperate auf true.
	 */
	public Pavlov() {
		kooperate = true;
	}

	@Override
	public boolean getNextDecision() {
		return kooperate;
	}

	@Override
	public void setOpponentsLastDecision(boolean decision) {
		kooperate = kooperate == decision;
	}

}
