import java.util.ArrayList;

/**
 * Abstrakte Definition einer Schachfigur
 * @author Jonas, Dominik, Julia
 *
 */
public abstract class Chessman {
	private Position position;

	/**
	 * Konstruktor: Erzeugt eine neue Schachfigur an gegebener Position.
	 * @param position Position an der die Figur zu Beginn stehen soll
	 * @throws RuntimeException wenn das Feld nicht existiert
	 */
	public Chessman(Position position) throws RuntimeException {
		if(!position.isValid()) {
			throw new RuntimeException("Field does not exist.");
		}
		this.position = position;
	}
	
	/**
	 * Gibt die Position der Schachfigur zurueck.
	 * @return Position der Schachfigur
	 */
	public Position getPosition() {
		return this.position;
	}
	
	/**
	 * Bewegt die Schachfigur an die gegebene Position, wenn moeglich.
	 * @param position Position, an die die Schachfigur bewegt werden soll
	 * @throws RuntimeException wenn das Feld nicht in einem Zug erreichbar ist
	 */
	public void moveTo(Position position) throws RuntimeException {
		if(!canMoveTo(position)) {
			throw new RuntimeException("Field does not exist.");
		}
		this.position = position;
	}

	/**
	 * Gibt eine Liste von Positionen zurueck, an die sich die Schachfigur bewegen kann
	 * @return Liste von moeglichen Positionen
	 */
	public abstract ArrayList<Position> getMoveList();

	/**
	 * Gibt zurueck, ob die Figur sich an die gegebene Position bewegen kann.
	 * @param position Position, die ueberprueft werden soll
	 * @return ob das Feld erreichbar ist
	 */
	public boolean canMoveTo(Position position) {
		return getMoveList().contains(position);
	}
}
