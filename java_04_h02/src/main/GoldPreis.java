package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Bei GoldPreis handelt es sich um eine Klasse, die die Goldtagespreise aus einer Datei einliest, speichert und verwaltet.
 * @author Jonas, Dominik, Julia
 * @version V01.00B00
 */
public class GoldPreis {
	private ArrayList<GoldTagespreis> list;

	/**
	 * Liest die uebergebene Datei ein und speichert die Daten in einer Liste.
	 * @param dateiname String; Pfad der Datei, die eingelesen werden soll.
	 * @throws FileNotFoundException wird geschmissen, wenn die Datei nicht gefunden wird.
	 */
	public GoldPreis(String dateiname) throws FileNotFoundException {
		File fi = new File(dateiname);
		Scanner sc = new Scanner(fi);

		list = new ArrayList<>();

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			list.add(new GoldTagespreis(line));
		}

		sc.close();
	}

	/**
	 * Get-Methode fuer den Preis an einem bestimmten Datum.
	 * @param datum String; Datum fuer welches der Preis gesucht wird.
	 * @return double Preis an datum.
	 */
	public double getPreis(String datum) {
		for (GoldTagespreis entry : list) {
			if (entry.datum.equals(datum)) {
				return entry.preis;
			}
		}

		throw new NumberFormatException("Datum konnte nicht gefunden werden.");
	}

	/**
	 * Gibt den hoechsten und den niedrigsten Goldpreis mit zugehoerigem Datum in der Konsole aus.
	 */
	public void printMinMax() {
		Collections.sort(list, (gt1, gt2) -> Double.compare(gt1.preis, gt2.preis));

		int min = 0;
		while (list.get(min).preis == -1) {
			min++;
		}

		System.out.print("Den niedrigsten Goldpreis von ");
		System.out.print(list.get(min).preis);
		System.out.println(" gab es an folgenden Tagen:");

		int next=min;
		while(list.get(min).preis == list.get(next).preis) {
			System.out.println(list.get(next).datum);
			next++;
		}

		System.out.print("Den hoechsten Goldpreis von ");
		System.out.print(list.get(list.size() - 1).preis);
		System.out.println(" gab es an folgenden Tagen:");

		next=list.size() - 1;
		while(list.get(list.size() - 1).preis == list.get(next).preis) {
			System.out.println(list.get(next).datum);
			next--;
		}
	}

	/**
	 * Dies ist eine Test-Methode.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GoldPreis test = new GoldPreis("gold.txt");
			System.out.println(test.getPreis("2009-10-20"));
			System.out.println(test.getPreis("2009-02-07"));
			test.printMinMax();
		} catch (FileNotFoundException e) {
			System.out.println("Datei nicht gefunden");
		}
	}

}
