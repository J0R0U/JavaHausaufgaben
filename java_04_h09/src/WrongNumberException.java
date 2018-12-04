
/**
 * Exception fuer fehlerhafte Plaettchenwerte.
 * @author Jonas, Dominik, Julia
 *
 */
@SuppressWarnings("serial")
public class WrongNumberException extends RuntimeException {
	String msg;
	
	public WrongNumberException(int i, int min, int max) {
		super(createMsg(i, min, max));
	}	

	private static String createMsg(int i, int min, int max) {
		StringBuilder ret = new StringBuilder()
			.append("Der Angegebene Wert ")
			.append(i)
			.append(" ist nicht Element des Intervalls [")
			.append(min)
			.append(",")
			.append(max)
			.append("].");
		return ret.toString();
	}
}
