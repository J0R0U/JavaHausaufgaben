/**
 * Strategie "Pavlov": Kooperiert dann, wenn beide Spieler in der letzten Runde die gleiche Entscheidung getroffen haben.
 * Beginnt mit Kooperation.
 * @author Jonas, Julia, Dominik
 *
 */
public class Pavlov implements GefStrategie {
	private boolean kooperate;
	
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
