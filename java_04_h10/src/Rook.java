import java.util.ArrayList;

/**
 * Stellt die Schachfigur "Turm" dar
 * @author Jonas, Dominik, Julia
 *
 */
public class Rook extends Chessman {

	/**
	 * Erzeugt ein neues Turm Objekt an gegebener Position.
	 * @param position Startposition der Figur
	 */
	public Rook(Position position) {
		super(position);
	}

	@Override
	public ArrayList<Position> getMoveList() {
		ArrayList<Position> ret = new ArrayList<>();
		Position currentPosition = getPosition();

		for (int i = 1; i <= 8; i++) {
			ret.add(new Position(i, currentPosition.getY()));
		}

		for (int i = 1; i <= 8; i++) {
			ret.add(new Position(currentPosition.getX(), i));
		}

		ret.remove(currentPosition);

		return ret;
	}

	@Override
	public String toString() {
		return "Turm: " + super.getPosition();
	}
}
