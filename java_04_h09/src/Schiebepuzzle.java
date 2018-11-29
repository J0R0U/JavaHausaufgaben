import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Schiebepuzzle {
	static final int FIELD_SIZE = 4;
	int[][] field;

	public Schiebepuzzle() {
		field = new int[FIELD_SIZE][FIELD_SIZE];

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				field[i][j] = i * FIELD_SIZE + j + 1;
			}
		}

		field[FIELD_SIZE - 1][FIELD_SIZE - 1] = -1;
	}

	public void schiebe(int i) {
		if (i <= 0 || i >= FIELD_SIZE * FIELD_SIZE) {
			throw new WrongNumberException(i, 1, maxElement());
		} else if (!istVerschiebar(i)) {
			throw new WrongMoveException(i, getLocationOfField(i), getLocationOfField(-1));
		}

		Point pointOfEmptyField = getLocationOfField(-1);
		Point pointOfField = getLocationOfField(i);

		field[pointOfEmptyField.x][pointOfEmptyField.y] = i;
		field[pointOfField.x][pointOfField.y] = -1;
	}

	public boolean istVerschiebar(int i) {
		Point pointOfEmptyField = getLocationOfField(-1);
		Point pointOfField = getLocationOfField(i);

		if (pointOfEmptyField.x == pointOfField.x) {
			if (Math.abs(pointOfEmptyField.y - pointOfField.y) == 1) {
				return true;
			}
		} else if (pointOfEmptyField.y == pointOfField.y) {
			if (Math.abs(pointOfEmptyField.x - pointOfField.x) == 1) {
				return true;
			}
		}
		return false;
	}

	public void mische() {
		int valFromBefore = 0;
		for (int i = 0; i < 100; i++) {
			ArrayList<Integer> possibilities = onlyMoveableCells(valFromBefore); // Prevents the algorithm to revert a move, for better shuffle results
			int index = randInt(possibilities.size() - 1);
			valFromBefore = possibilities.get(index);
			schiebe(possibilities.get(index));
		}
	}
	
	public Point getLocationOfField(int value) {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[0].length; j++) {
				if (field[i][j] == value) {
					return new Point(i, j);
				}
			}
		}
		return null;
	}
	
	public int maxElement() {
		return FIELD_SIZE * FIELD_SIZE - 1;
	}

	public String toString() {
		StringBuilder ret = new StringBuilder();
		int widthOfCell = ("" + maxElement()).length();
		for (int i = 0; i < field.length; i++) {
			addSpacerLine(ret, (widthOfCell + 1) * field.length + 1);

			for (int j = 0; j < field[i].length; j++) {
				addCell(ret, widthOfCell, field[i][j]);
			}

			ret.append("|\n");
		}

		addSpacerLine(ret, (widthOfCell + 1) * field.length + 1);

		return ret.toString();
	}

	private static void addSpacerLine(StringBuilder sb, int length) {
		for (int i = 0; i < length; i++) {
			sb.append("-");
		}
		sb.append("\n");
	}

	private static void addCell(StringBuilder sb, int cellWidth, int value) {
		if (value != -1) {
			sb.append(String.format("|%" + cellWidth + "d", value));
		} else {
			sb.append(String.format("|%" + cellWidth + "s", ""));
		}
	}

	private ArrayList<Integer> onlyMoveableCells(int filterField) {
		ArrayList<Integer> ret = new ArrayList<>();
		Point pos = getLocationOfField(-1);
		if (pos.x > 0 && filterField != field[pos.x - 1][pos.y])
			ret.add(field[pos.x - 1][pos.y]);
		if (pos.y > 0 && filterField != field[pos.x][pos.y-1])
			ret.add(field[pos.x][pos.y - 1]);
		if (pos.x < FIELD_SIZE - 1 && filterField != field[pos.x + 1][pos.y])
			ret.add(field[pos.x + 1][pos.y]);
		if (pos.y < FIELD_SIZE - 1 && filterField != field[pos.x][pos.y + 1])
			ret.add(field[pos.x][pos.y + 1]);
		return ret;
	}

	private static int randInt(int max) {
		Random rand = new Random(System.currentTimeMillis());
		return rand.nextInt(max + 1);
	}
}
