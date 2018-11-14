
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
