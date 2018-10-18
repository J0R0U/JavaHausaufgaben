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

public class EMailAdressBuch {

	private static final String BOM = "\uFEFF";

	private HashMap<String, String> data;

	public EMailAdressBuch() {
		data = new HashMap<>();
	}

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

	public void einfuegen(String name, String email) {
		data.put(name, email);
	}

	public String abfrage(String name) {
		if (data.containsKey(name))
			return data.get(name);
		throw new UnknownNameException(name);
	}

	public String toString() {
		StringBuilder ret = new StringBuilder("{");

		data.forEach((key, value) -> {
			ret.append(key + "=" + value + ", ");
		});

		ret.delete(ret.length() - 2, ret.length());
		ret.append("}");

		return ret.toString();
	}

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
