/**
 * Allgemeine Definition einer Gefangenenstrategie.
 * @author Jonas, Julia, Dominik
 *
 */
public interface GefStrategie {
	boolean getNextDecision();
	
	void setOpponentsLastDecision(boolean decision);
}
