import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * Ein Puzzle, bei dem 15 Plaettchen auf 16 Feldern angeordnet werden und durch Verschiebung in eine
 * richtige Anordnung ueberfuehrt werden muessen.
 * @author Jonas, Dominik, Julia
 *
 */
public class Schiebepuzzle {
	static final int FIELD_SIZE = 4;
	int[][] field;

	/**
	 * Konstruktor: Initialisiert das Spielfeld spalten- und zeilenweise mit 15 durchnummerierten Plaettchen.
	 * Das Feld rechts unten bleibt leer.
	 */
	public Schiebepuzzle() {
		field = new int[FIELD_SIZE][FIELD_SIZE];

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				field[i][j] = i * FIELD_SIZE + j + 1;
			}
		}

		field[FIELD_SIZE - 1][FIELD_SIZE - 1] = -1;
	}

	/**
	 * Verschiebt das Plaettchen mit gegebenem Wert auf das freie Feld, wenn dieses direkt daneben liegt.
	 * @param i Wert das zu verschiebenden Plaettchens
	 * @throws WrongNumberException wenn kein Plaettchen mit uebergebem Wert existiert
	 * @throws WrongMoveException wenn das gewaehlte Plaettchen nicht neben dem freien Feld liegt
	 */
	public void schiebe(int i) throws WrongNumberException, WrongMoveException {
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

	/**
	 * Gibt zurueck, ob das Plaettchen mit gegebenem Wert verschiebbar ist (neben dem freien Feld liegt)
	 * @param i Wert des gewaehlten Plaettchens
	 * @return ob das Plaettchen verschiebbar ist
	 */
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

	/**
	 * Fuehrt 100 zufaellige valide Zuege durch und mischt somit das Spielfeld.
	 * Verhindert zwei aufeinanderfolge Zuege die sich gegenseitig negieren.
	 */
	public void mische() {
		int valFromBefore = 0;
		for (int i = 0; i < 100; i++) {
			ArrayList<Integer> possibilities = onlyMoveableCells(valFromBefore); // Prevents the algorithm to revert a move, for better shuffle results
			int index = randInt(possibilities.size() - 1);
			valFromBefore = possibilities.get(index);
			schiebe(possibilities.get(index));
		}
	}
	
	/**
	 * Gibt die Position des Plaettchens mit dem gegeben Wert zurueck.
	 * @param value Wert des Plaettchens
	 * @return Position des Plaettchen
	 */
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
	
	/**
	 * Gibt den maximal moeglichen Wert eines Plaettchens zurueck.
	 * @return maximal moeglicher Wert
	 */
	public int maxElement() {
		return FIELD_SIZE * FIELD_SIZE - 1;
	}
	
	/**
	 * Gibt die textuelle Repraesentation des Spielfeldes zurueck.
	 * @return Spielfeld in Textform
	 */
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

	/**
	 * Fuegt eine horizontale Trennlinie mit gebener Anzahl an Trennzeichen zum StringBuilder hinzu.
	 * @param sb StringBuilder an den die Trennlinie angehangen werden soll
	 * @param length Anzahl an Trennzeichen
	 */
	private static void addSpacerLine(StringBuilder sb, int length) {
		for (int i = 0; i < length; i++) {
			sb.append("-");
		}
		sb.append("\n");
	}

	/**
	 * Fuegt eine Spielfeldzelle mit gegebenen Wert an den StringBuilder an.
	 * @param sb StringBuilder an den die Zelle angehangen werden soll
	 * @param cellWidth Breite der Zelle
	 * @param value Wert der Zelle
	 */
	private static void addCell(StringBuilder sb, int cellWidth, int value) {
		if (value != -1) {
			sb.append(String.format("|%" + cellWidth + "d", value));
		} else {
			sb.append(String.format("|%" + cellWidth + "s", ""));
		}
	}

	/**
	 * Gibt eine Liste der bewegbaren Plaettchen bzw. die Werte der bewegbaren Plaettchen zurueck.
	 * @param filterField Feld, welches grundsaetzlich aus der Liste ausgenommen werden soll
	 * @return Liste der bewegbaren Plaettchen
	 */
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

	/**
	 * Gibt einen zufaelligen Wert zwischen 0 und max zurueck (inklusive).
	 * @param max maximaler Wert
	 * @return zufaelliger Wert
	 */
	private static int randInt(int max) {
		Random rand = new Random(System.currentTimeMillis());
		return rand.nextInt(max + 1);
	}
}
