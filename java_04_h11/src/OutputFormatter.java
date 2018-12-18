import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Diese Klasse dient nur zur korrekten Bildschirmausgabe.
 * @author Jonas, Dominik, Julia
 * @version V01.00B01
 */
public class OutputFormatter {

	/**
	 * Diese Funktion wandelt die uebergebenen Parameter in einen String um.
	 * @param x ; double , X-Wert des Punktes.
	 * @param y ; double , Y-Wert des Punktes
	 * @return
	 */
	public static String formatToString(double x, double y) {
		return String.format("(%s/%s)", fomatToString(x), fomatToString(y));
	}

	/**
	 * Diese Funktion wandelt den uebergebenen Parameter in einen String um.
	 * @param d ; double , der umgewandelt werden soll.
	 * @return
	 */
	private static String fomatToString(double d) {
		if (d == (int) d) {
			return String.format("%d", (int) d);
		} else {
			return String.format("%s", floorForPrint(d)).replace(".",
					"" + new DecimalFormatSymbols(Locale.getDefault()).getDecimalSeparator());
		}
	}

	/**
	 * Schneidet den double-Wert bei einer Nachkommastelle ab.
	 * @param a ; double, der abgeschnitten werden soll.
	 * @return
	 */
	private static double floorForPrint(double a) {
		return ((int) (a * 10)) / 10.;
	}
}
