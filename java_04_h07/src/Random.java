/**
 * Strategie "Random": Trifft immer eine zufaellige Entscheidung.
 * @author Jonas, Julia, Dominik
 * V01.00B01
 */
public class Random implements GefStrategie {

	@Override
	public boolean getNextDecision() {
		return ((int) Math.round( Math.random() )) == 0 ? false : true;
	}

	@Override
	public void setOpponentsLastDecision(boolean decision) {
	}

}
