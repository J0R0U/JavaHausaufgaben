import java.util.ArrayList;

public class Rook extends Chessman {

	public Rook(Position _position) {
		super(_position);
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
