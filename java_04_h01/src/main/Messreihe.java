package main;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Die Klasse Messreihe erweitert ein Double-Array um weitere Methoden und
 * bietet die Moeglichkeit ein Double-Array aus einer Textdatei einzulesen.
 * 
 * @author Julia, Dominik, Jonas
 * @version V01.00B00
 */
public class Messreihe {
	// Das repraesentierte Double-Array
	private double[] liste;

	/**
	 * Konstruktor der Klasse Messreihe, welcher das Double-Array mit dem Klon eines
	 * anderen initialiesiert.
	 * 
	 * @param liste Das Array welches geklont werden soll.
	 */
	public Messreihe(double[] liste) {
		this.liste = liste.clone();
	}

	/**
	 * Konstruktor der Klasse Messreihe, welcher das Double-Array mit den Werten
	 * einer Textdatei initialiesiert.
	 * 
	 * @param dateiname Der Name der Datei welche ausgelesen werden soll.
	 */
	public Messreihe(String dateiname) {
		// Oeffnen der Datei
		FileReader fr = null;
		try {
			fr = new FileReader(dateiname);
		} catch (FileNotFoundException e) {
			System.out.println("Datei wurde nicht gefunden");
		}

		// Nur wenn die Datei existiert
		if (fr != null) {
			Scanner sc = new Scanner(fr);

			boolean firstLine = true;
			int index = 0;

			while (sc.hasNext()) {
				String line = sc.nextLine();
				if (!line.isEmpty() && !line.startsWith("%")) { // Kommentare und leere Zeilen ignorieren
					if (firstLine) { // In line steht nun die laenge der Messwerte.
						try {
							int length = Integer.parseInt(line);
							liste = new double[length];
							firstLine = false;
						} catch (NumberFormatException e) {
							sc.close();
							throw new IllegalArgumentException("Groesse konnte nicht gelesen werden.");
						}
					} else { // In line steht nun ein Messwert Messwerte.
						if (index >= liste.length) {
							sc.close();
							throw new ArithmeticException("Angegebene Laenge ist fehlerhaft. (zu klein)");
						}

						try {
							liste[index] = Double.parseDouble(line);
						} catch (NumberFormatException e) {
							sc.close();
							throw new IllegalArgumentException("Messwert konnte nicht gelesen werden.");
						}
						index++;
					}
				}
			}

			sc.close();

			if (index + 1 == liste.length) {
				throw new ArithmeticException("Angegebene Laenge ist fehlerhaft. (zu gross)");
			}
		}
	}

	/**
	 * Diese Methode ermittelt den Wert des groessten Elements.
	 * 
	 * @return Der Wert des groessten Elements.
	 */
	public double getMax() {
		int index = getMaxIndex();
		if (index == -1) {
			return Double.NaN;
		}
		return liste[index];
	}

	/**
	 * Diese Methode ermittelt den Wert des kleinstes Elements.
	 * 
	 * @return Der Wert des kleinen Elements.
	 */
	public double getMin() {
		double min = liste.length >= 1 ? liste[0] : Double.NaN;
		for (int i = 1; i < liste.length; i++) {
			if (Double.compare(min, liste[i]) > 0) {
				min = liste[i];
			}
		}
		return min;
	}

	/**
	 * Diese Methode prueft ob alle Werte des Arrays gleich sind.
	 * 
	 * @return {@code true}, wenn Werte gleich sind, sonst {@code false}.
	 */
	public boolean isEinheitlich() {
		return getMin() == getMax();
	}

	/**
	 * Diese Methode zaehlt, wieviele Zahlen im Array vorhanden sind die Groesser als
	 * ein Schwellenwert sind.
	 * 
	 * @param lim Der Schwellenwert welcher ueberschritten werden muss.
	 * @return Anzahl der Zahlen welche groesser als der Schwellenwert sind.
	 */
	public int zaehleGroessere(double lim) {
		int count = 0;
		for (int i = 0; i < liste.length; i++) {
			if (Double.compare(lim, liste[i]) < 0) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Diese Methode gibt den Index des ersten Elements mit dem groessten Wert zurueck.
	 * 
	 * @return Der Index des ersten Groessten Einrags.
	 */
	public int getMaxIndex() {
		double max = liste.length >= 1 ? liste[0] : Double.NaN;
		int ret = -1;
		for (int i = 1; i < liste.length; i++) {
			if (Double.compare(max, liste[i]) < 0) {
				max = liste[i];
				ret = i;
			}
		}
		return ret;
	}

	/**
	 * Diese Methode gibt den Bereich an in welchem die Werte liegen.
	 * 
	 * @return Ein Array mit folgendem Aufbau: {@code [getMin(),getMax()]};
	 */
	public double[] getBereich() {
		return new double[] { getMin(), getMax() };
	}

	/**
	 * Diese Methode gibt die Groesse der verwalteten Liste zurueck.
	 * 
	 * @return Die Groesse der Liste.
	 */
	public int getAnzahl() {
		return liste.length;
	}
}
