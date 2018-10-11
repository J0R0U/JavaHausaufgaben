package Hausaufgabe2;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author
 * @version V00.00B01
 */
public class GoldTagespreis {
	public String datum;
	public double preis;

	public GoldTagespreis(String infos) {
		String[] splittedInfo = infos.split("\t");
		this.datum = splittedInfo[0];

		if (splittedInfo[1].equals("kein Nachweis")) {
			preis = -1;
		} else {
			try {
				NumberFormat format = NumberFormat.getInstance(Locale.GERMAN);
				Number number = format.parse(splittedInfo[1]);
				preis = number.doubleValue();
			} catch (Exception e) {
				String error = "\"" + splittedInfo[1] + "\"";
				error += "konnte nicht zu einem Doublewert ueberfuehrt werden.";
				throw new IllegalArgumentException(error);
			}
		}
	}

	@Override
	public String toString() {
		return datum + " " + preis;
	}
}
