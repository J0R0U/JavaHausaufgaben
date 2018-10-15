package Hausaufgabe2;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Der GoldTagesPreis handelt sich um eine Klasse, die den Goldtagespreis mit dem jeweiligen Datum verknuepft.
 * @author Jonas, Dominik, Julia
 * @version V01.00B00
 */
public class GoldTagespreis {
	public String datum;
	public double preis;

	/**
	 * Konstruktor, der aus einem String das Datum und den Preis einliest.
	 * @param infos bekommt in einem String die Textzeile, die das Datum und den Preis enthaelt, uebergeben.
	 */
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
	 /**
	  * Ueberschreibt die toString Methode und gibt Datum und Preis zurueck.
	  * @return String; gibt Datum und Preis in einem String zurueck.
	  */
	@Override
	public String toString() {
		return datum + " " + preis;
	}
}
