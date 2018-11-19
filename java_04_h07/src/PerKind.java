/**
 * Strategie "PerKind": Spielt periodisch die Folge kooperieren/kooperieren/verraten.
 * @author Jonas, Julia, Dominik
 * V01.00B01
 */
public class PerKind implements GefStrategie {
	boolean[] sequence;
	int pos;
	
	/**
	 * Setzt sequence auf {true, true, false} und pos auf 0.
	 */
	public PerKind() {
		sequence = new boolean[]{true, true, false};
		pos = 0;
	}

	@Override
	public boolean getNextDecision() {
		boolean ret = sequence[pos];
		
		pos++;
		if(pos >= sequence.length) {
			pos = 0;
		}
		
		return ret;
	}

	@Override
	public void setOpponentsLastDecision(boolean decision) {
	}

}
