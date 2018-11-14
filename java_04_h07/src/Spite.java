
public class Spite implements GefStrategie {
	private boolean betrayed;
	
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
