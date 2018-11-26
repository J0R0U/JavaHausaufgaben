
import java.security.InvalidParameterException;
import java.util.HashMap;

/**
 * Erstellt ein Blatt fuer einen Spieler.
 * @author Jonas, Dominik, Julia
 * V01.00B01
 */
public class Blatt {
	private static final int CARD_COUNT = 3;
	private static final int TRIPLE = 3;
	private static final int DOUBLE = 2;
	private int[] data;

	/**
	 * Konstruktor: Initialisiert das Integer-Feld data
	 */
	public Blatt() {
		data = new int[CARD_COUNT];
	}

	/**
	 * Prueft, ob das Integer-Feld die richtige Laenge und gueltige Werte besitzt.
	 * Falls die Laenge nicht 3 ist, wird eine InvalidParameterException geworfen.
	 * @param data
	 */
	public Blatt(int[] data) {
		this.data = new int[CARD_COUNT];
		if (data.length != 3) {
			throw new InvalidParameterException("Size of given array is not 3");
		}
		for (int i = 0; i < data.length; i++) {
			if (data[i] >= 2 && data[i] <= 14) {
				this.data[i] = data[i];
			}
		}
	}

	/**
	 * Gibt zurueck, ob trippleValue ungleich an -1 ist.
	 * @return
	 */
	public boolean isTripple() {
		return trippleValue() != -1;
	}

	/**
	 * Gibt den valueOfOccurence von trippleValue zurueck.
	 * @return
	 */
	public int trippleValue() {
		return valueOfOccurence(TRIPLE);
	}

	/**
	 * Gibt zurueck, ob doubleValue ungleich an -1 ist.
	 * @return
	 */
	public boolean isDouble() {
		return doubleValue() != -1;
	}

	/**
	 * Gibt den valueOfOccurence von doubleValue zurueck.
	 * @return
	 */
	public int doubleValue() {
		return valueOfOccurence(DOUBLE);
	}

	/**
	 * Gibt den Wert der dritten Karte zurueck, wenn die anderen beiden ein Paar bilden.
	 * @return
	 */
	public int thirdValue() {
		int temp = doubleValue();
		for (int i = 0; i < data.length; i++) {
			if (data[i] != temp) {
				return data[i];
			}
		}
		return -1;
	}

	/**
	 * Summiert die Daten.
	 * @return int; Ergebnis der Summe
	 */
	public int sum() {
		int ret = 0;
		for (int i = 0; i < data.length; i++) {
			ret += data[i];
		}
		return ret;
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < data.length; i++)
			ret.append(data[i] + ", ");
		ret.setLength(ret.length() - 2);
		return ret.toString();
	}

	/**
	 * Gibt den Wert zurueck, der Karte, die so oft vorkommt wie der uebergebene Parameter.
	 * @param occurence
	 * @return
	 */
	private int valueOfOccurence(int occurence) {
		HashMap<Integer, Integer> temp = new HashMap<>();
		int ret = -1;

		for (int i = 0; i < data.length; i++) {
			if (!temp.containsKey(data[i])) {
				temp.put(data[i], 1);
			} else {
				temp.put(data[i], temp.get(data[i]) + 1);
				if (temp.get(data[i]) == occurence) {
					ret = data[i];
				} else if (temp.get(data[i]) > occurence) {
					ret = -1;
				}
			}
		}

		return ret;
	}
}
