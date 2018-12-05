import java.util.ArrayList;

public abstract class Chessman {
	private Position m_position;

	public Chessman(Position _position) {
		if(!_position.isValid()) {
			throw new RuntimeException("Field does not exist.");
		}
		m_position = _position;
	}
	
	public Position getPosition() {
		return m_position;
	}
	
	public void moveTo(Position _position) {
		if(!canMoveTo(_position)) {
			throw new RuntimeException("Field does not exist.");
		}
		m_position = _position;
	}

	public abstract ArrayList<Position> getMoveList();

	public boolean canMoveTo(Position _position) {
		return getMoveList().contains(_position);
	}
}
