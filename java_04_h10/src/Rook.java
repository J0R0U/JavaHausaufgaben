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

		Position myPos = getPosition();
		Position temp = new Position(myPos.getX() + 1, myPos.getY());

		while (temp.isValid()) {
			ret.add(temp);
			temp = new Position(temp.getX() + 1, myPos.getY());
		}

		temp = new Position(myPos.getX() - 1, myPos.getY());

		while (temp.isValid()) {
			ret.add(temp);
			temp = new Position(temp.getX() - 1, myPos.getY());
		}

		temp = new Position(myPos.getX(), myPos.getY() + 1);

		while (temp.isValid()) {
			ret.add(temp);
			temp = new Position(myPos.getX(), temp.getY() + 1);
		}

		temp = new Position(myPos.getX(), myPos.getY() - 1);

		while (temp.isValid()) {
			ret.add(temp);
			temp = new Position(myPos.getX(), temp.getY() - 1);
		}

		return ret;
	}

	@Override
	public String toString() {
		return "Turm: " + super.getPosition();
	}
}
