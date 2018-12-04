import java.awt.Point;

/**
 * Exception fuer fehlerhafte Zuege.
 * @author Jonas, Dominik, Julia
 *
 */
@SuppressWarnings("serial")
public class WrongMoveException extends RuntimeException {
	public WrongMoveException(int i, Point fieldWithValueI, Point emptyField) {
		super(createMsg(i,fieldWithValueI, emptyField));
	}
	
	private static String createMsg(int i, Point fieldWithValueI, Point emptyField) {
		StringBuilder ret = new StringBuilder()
			.append("Das Feld mit dem Wert ")
			.append(" an Position ")
			.append("(" + fieldWithValueI.x + "|" + fieldWithValueI.y + ")")
			.append(" kann nicht an Position ")
			.append("(" + emptyField.x + "|" + emptyField.y + ")")
			.append(" geschoben werden.");
		return ret.toString();
	}
}
