/**
 * Strategie "Spite": Kooperiert solange, bis der Mitspieler zum ersten Mal verraet.
 * @author Jonas, Julia, Dominik
 * V01.00B01
 */
public class Spite implements GefStrategie {
	private boolean betrayed;
	
	/**
	 * Setzt betrayed auf false.
	 */
	public Spite() {
		betrayed = false;
	}

	@Override
	public boolean getNextDecision() {
		return !betrayed;
	}

	@Override
	public void setOpponentsLastDecision(boolean decision) {
		if(!decision) {
			betrayed = true;
		}
	}

}
