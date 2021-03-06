package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Bei EMailAdressBuch handelt es sich um eine Klasse, die die E-Mail Adressen von Personen aus einer Datei sucht und ausgibt. 
 * @author Jonas, Dominik, Julia
 * V01.00B00
 */
public class EMailAdressBuch {
	// Byte Order Mark zur Verhinderung einer fehlerhaften Ausgabe.
	private static final String BOM = "\uFEFF";
	private HashMap<String, String> data;

	/**
	 * Konstruktor der HashMap "data".
	 */
	public EMailAdressBuch() {
		data = new HashMap<>();
	}

	/**
	 * Liest die Daten aus der Datei ein und speichert sie in der HashMap.
	 * @param dateiname String; Pfad der Datei, die eingelesen werden soll.
	 * @throws FileNotFoundException wird geschmissen, wenn die Datei nicht gefunden wird.
	 */
	public void einlesen(String dateiname) throws FileNotFoundException {
		File fi = new File(dateiname);
		Scanner sc = new Scanner(fi, "UTF-8");

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line.startsWith(BOM)) {
				line = line.replaceFirst(BOM, "");
			}
			String[] lineInfos = line.split(";");
			data.put(lineInfos[0], lineInfos[1]);
		}

		sc.close();
	}

	/**
	 * 
	 * @param url
	 * @throws IOException
	 */
	public void mitarbeiterEinlesen(URL url) throws IOException {
		URLConnection con = url.openConnection();
		InputStream content = (InputStream) con.getContent();
		Scanner sc = new Scanner(content, "UTF-8");

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line.startsWith(BOM)) {
				line = line.replaceFirst(BOM, "");
			}
			String[] lineInfos = line.split(";");
			data.put(lineInfos[0], lineInfos[1]);
		}

		sc.close();
	}

	/**
	 * 
	 * @param name String; enthaelt den Schluessel der gesuchten E-Mail Adresse. 
	 * @param email String; enthaelt die E-Mail Adresse zum eingegebenen Schluessel.
	 */
	public void einfuegen(String name, String email) {
		data.put(name, email);
	}

	/**
	 * Ueberprueft, ob name in der Datei vorhanden ist.
	 * Falls ja, gibt er die zugehoerige E-Mail Adresse zurueck.
	 * Falls nein, wirft er die selbstgeschriebene UnknownNameException.
	 * @param name String; enthaelt den Schluessel der gesuchten E-Mail Adresse. 
	 * @return gibt die E-Mail Adresse des angegebenen Schluessels name zurueck.
	 */
	public String abfrage(String name) {
		if (data.containsKey(name))
			return data.get(name);
		throw new UnknownNameException(name);
	}

	/**
	 * 
	 */
	public String toString() {
		StringBuilder ret = new StringBuilder("{");

		data.forEach((key, value) -> {
			ret.append(key + "=" + value + ", \n");
		});

		ret.delete(ret.length() - 2, ret.length());
		ret.append("}");

		return ret.toString();
	}

	/**
	 * Dies ist eine Test-Methode.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			URL intern = new URL(
					"https://doc.itc.rwth-aachen.de/download/attachments/5800183/mitarbeiter_matse_intern.txt");
			URL extern = new URL(
					"https://doc.itc.rwth-aachen.de/download/attachments/5800183/mitarbeiter_matse_extern.txt");
			EMailAdressBuch c = new EMailAdressBuch();
			c.mitarbeiterEinlesen(intern);
			c.mitarbeiterEinlesen(extern);
			System.out.println(c);

			String dateiName = "mein_adressbuch.txt";
			EMailAdressBuch b = new EMailAdressBuch();
			b.einlesen(dateiName);
			System.out.println(b);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
