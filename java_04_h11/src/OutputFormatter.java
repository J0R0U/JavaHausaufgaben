import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * 
 * @author
 * @version
 */
public class OutputFormatter {

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static String formatToString(double x, double y) {
		return String.format("(%s/%s)", fomatToString(x), fomatToString(y));
	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	private static String fomatToString(double d) {
		if (d == (int) d) {
			return String.format("%d", (int) d);
		} else {
			return String.format("%s", roundForPrint(d)).replace(".",
					"" + new DecimalFormatSymbols(Locale.US).getDecimalSeparator());
		}
	}

	/**
	 * 
	 * @param a
	 * @return
	 */
	private static double roundForPrint(double a) {
		return ((int) (a * 10)) / 10.;
	}
}
