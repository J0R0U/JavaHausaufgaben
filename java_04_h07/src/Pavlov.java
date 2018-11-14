
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
