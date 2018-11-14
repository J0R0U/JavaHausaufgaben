
public class PerKind implements GefStrategie {
	boolean sequence[] = {true, true, false};
	int pos = 0;

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
