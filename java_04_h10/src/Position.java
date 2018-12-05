
public final class Position {
	private final int m_x;
	private final int m_y;

	public Position(int _x, int _y) {
		m_x = _x;
		m_y = _y;
	}

	public int getX() {
		return m_x;
	}

	public int getY() {
		return m_y;
	}

	@Override
	public boolean equals(Object _other) {
		if (_other instanceof Position) {
			return ((Position) _other).m_x == m_x && ((Position) _other).m_y == m_y;
		}
		return false;
	}

	public boolean isValid() {
		return (m_x >= 1 && m_x <= 8 && m_y >= 1 && m_y <= 8);
	}

	public String toString() {
		return "(" + m_x + "/" + m_y + ")";
	}
}
