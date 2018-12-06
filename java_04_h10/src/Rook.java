import java.util.ArrayList;

public class Rook extends Chessman {

	public Rook(Position _position) {
		super(_position);
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
