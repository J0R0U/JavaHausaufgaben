/**
 * Allgemeine Definition einer Gefangenenstrategie.
 * @author Jonas, Julia, Dominik
 * V01.00B01
 */
public interface GefStrategie {
	
	/**
	 * Gibt die naechste Entscheidung zurueck.
	 * @return
	 */
	boolean getNextDecision();
	
	/**
	 * Vergleicht die neue Entscheidung mit der vorherigen Entscheidung.
	 * @param decision ; boolean
	 */
	void setOpponentsLastDecision(boolean decision);
}
