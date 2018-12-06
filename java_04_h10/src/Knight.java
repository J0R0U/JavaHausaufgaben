import java.util.ArrayList;

/**
 * Stellt die Schachfigur "Springer" dar.
 * @author Jonas, Dominik, Julia
 *
 */
public class Knight extends Chessman {

	/**
	 * Erzeugt ein neues Springer Objekt an gegebener Position.
	 * @param position Startposition der Figur
	 */
	public Knight(Position position) {
		super(position);
	}

	@Override
	public ArrayList<Position> getMoveList() {
		ArrayList<Position> ret = new ArrayList<>();
		Position myPos = getPosition();
		ArrayList<Position> temp = new ArrayList<>();

		
		temp.add(new Position(myPos.getX() + 1, myPos.getY() + 2));
		temp.add(new Position(myPos.getX() - 1, myPos.getY() + 2));
		temp.add(new Position(myPos.getX() + 1, myPos.getY() - 2));
		temp.add(new Position(myPos.getX() - 1, myPos.getY() - 2));
		temp.add(new Position(myPos.getX() + 2, myPos.getY() + 1));
		temp.add(new Position(myPos.getX() - 2, myPos.getY() + 1));
		temp.add(new Position(myPos.getX() + 2, myPos.getY() - 1));
		temp.add(new Position(myPos.getX() - 2, myPos.getY() - 1));

		for(Position i : temp) {
			if(i.isValid()) {
				ret.add(i);
			}
		}

		return ret;
	}

	@Override
	public String toString() {
		return "Springer: " + super.getPosition();
	}

}
