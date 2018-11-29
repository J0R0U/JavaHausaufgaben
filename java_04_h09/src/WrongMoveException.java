import java.awt.Point;

@SuppressWarnings("serial")
public class WrongMoveException extends RuntimeException {
	public WrongMoveException(int i, Point fieldWithValueI, Point emptyField) {
		super(createMsg(i,fieldWithValueI, emptyField));
	}
	
	private static String createMsg(int i, Point fieldWithValueI, Point emptyField
			) {
		StringBuilder ret = new StringBuilder();
		ret.append("Das Feld mit dem Wert ");
		ret.append(i);
		ret.append(" an Position ");
		ret.append("(" + fieldWithValueI.x + "|" + fieldWithValueI.y + ")");
		ret.append(" kann nicht an Position ");
		ret.append("(" + emptyField.x + "|" + emptyField.y + ")");
		ret.append(" geschoben werden.");
		return ret.toString();
	}
}
