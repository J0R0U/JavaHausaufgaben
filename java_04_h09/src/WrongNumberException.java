

@SuppressWarnings("serial")
public class WrongNumberException extends RuntimeException {
	String msg;
	
	public WrongNumberException(int i, int min, int max) {
		super(createMsg(i, min, max));
	}	

	private static String createMsg(int i, int min, int max) {
		StringBuilder ret = new StringBuilder();
		ret.append("Der Angegebene Wert ");
		ret.append(i);
		ret.append(" ist nicht Element des Intervalls [");
		ret.append(min);
		ret.append(",");
		ret.append(max);
		ret.append("].");
		return ret.toString();
	}
}
