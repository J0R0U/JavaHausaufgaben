package Hausaufgabe2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author
 * @version V00.00B01
 */
public class GoldPreis {
	private ArrayList<GoldTagespreis> list;

	/**
	 *
	 * @param dateiname 3
	 * @throws FileNotFoundException
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
	 *
	 * @param datum
	 * @return
	 */
	public double getPreis(String datum) {
		for (GoldTagespreis entery : list) {
			if (entery.datum.equals(datum)) {
				return entery.preis;
			}
		}

		throw new NumberFormatException("Datum konnte nicht gefunden werden.");
	}

	/**
	 *
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
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GoldPreis test = new GoldPreis("C:\\Java-Vorkurs\\JavaHausaufgaben\\src\\Hausaufgabe2\\goldpreise.txt");
			System.out.println(test.getPreis("2009-10-20"));
			System.out.println(test.getPreis("2009-02-07"));
			test.printMinMax();
		} catch (FileNotFoundException e) {
			System.out.println("Datei nicht gefunden");
		}
	}

}
