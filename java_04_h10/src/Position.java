
/**
 * Stellt eine Position auf ein Schachfeld dar.
 * @author Jonas, Dominik, Julia
 *
 */
public final class Position {
	private final int x;
	private final int y;

	/**
	 * Konstruktor: Erzeugt ein neues Positions Objekt mit gegebenen Koordinaten
	 * @param x x-Koordinate
	 * @param y y-Koordinate
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Gibt die x-Koordinate zurueck.
	 * @return x-Koordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gibt die y-Koordinate zurueck.
	 * @return y-Koordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Gibt zurueck, ob das uebergebene Objekt inhaltlich identisch ist.
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Position) {
			return ((Position) other).x == x && ((Position) other).y == y;
		}
		return false;
	}

	/**
	 * Gibt zurueck, ob die Position gueltig ist.
	 * @return ob die Position auf dem Feld liegt.
	 */
	public boolean isValid() {
		return (x >= 1 && x <= 8 && y >= 1 && y <= 8);
	}

	@Override
	public String toString() {
		return "(" + x + "/" + y + ")";
	}
}
