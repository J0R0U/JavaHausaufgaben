import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Diese Klasse entschluesselt einen Text, der zuvor mittels Huffman-Codierung verschluesselt wurde.
 * @author Jonas, Domink, Julia
 * V01.00B01
 */
public class Huffman {
	/**
	 * Alle Symbole die in einer Datei codiert sein koennen.
	 */
	private static final ArrayList<Character> SYMBOLS = new ArrayList<>();
	static {
		for (char i = 'A'; i <= 'Z'; i++) {
			SYMBOLS.add(i);
		}
		SYMBOLS.add(' ');
	}
	
	/**
	 * Die erwartete Laenge einer Datei (erste Zeile + alle Symbole).
	 */
	private static final int EXPECTED_LINE_COUNT = SYMBOLS.size() + 1;
	
	/**
	 * Jede Zeile muss aus Nullen und Einsen bestehen, oder leer sein.
	 */
	private static final String LINE_REG_EX = "(0|1)*";
	
	/**
	 * Der Fehlertext der ausgegeben werden soll wenn 
	 */
	private static final String LINE_COUNT_ERROR_TEXT = "Expected " + EXPECTED_LINE_COUNT
			+ " lines in file, but got %d.";
	
	/**
	 * Der Fehlertext der ausgegeben werden soll wenn 
	 */
	private static final String PARSING_ERROR_TEXT = "Parsing Error in line \"%s\" (must match \"" + LINE_REG_EX
			+ "\")";

	/**
	 * Decodiert die uebergebene Datei.
	 * @param f ; Datei, die decodiert werden soll.
	 * @return die decodierte Datei als String
	 */
	public static String decode(File f) {
		String encodedMessage = null;
		String decodedMessage = null;
		HashMap<String, Character> mapping = new HashMap<>();

		int lineCount = lineCount(f);
		if (lineCount != EXPECTED_LINE_COUNT) {
			throw new IllegalArgumentException(String.format(LINE_COUNT_ERROR_TEXT, lineCount));
		}

		encodedMessage = loadInformationFromFile(f, mapping);
		decodedMessage = decodeWithMapping(encodedMessage, mapping);

		return decodedMessage;
	}

	/**
	 * Diese Methode zaehlt die Anzahl aller vorhandenen Zeilen in einer Datei. Es
	 * kann leider keine Systemfunktion genutzt werden, da die letzte Zeile, sollte
	 * sie keinen Inhalt haben, nicht als eigene Zeile angesehen wird.
	 * 
	 * @param f Die Datei, deren Zeilen gezaehlt werden sollen.
	 * @return Die Anzahl an Zeilen in der Datei.
	 */
	private static int lineCount(File f) {
		try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(f))) {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			while ((readChars = is.read(c)) != -1) {
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return count + 1;
		} catch (IOException e) {
			return -1;
		}
	}
	
	/**
	 * Diese Funktion liest die Informationen aus der uebergebenen Datei aus.
	 * @param f ; Datei, deren Informationen geladen werden sollen.
	 * @param m ; HashMap, die eine Huffman-Codierung ihrem entsprechenden Character zuweist.
	 * @return die erste Zeile der Datei
	 */
	private static String loadInformationFromFile(File f, HashMap<String, Character> m) {
		try (Scanner sc = new Scanner(f)) {
			int symbolIndex = 0;
			String ret = null;
			m.clear();

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (!line.matches(LINE_REG_EX)) {
					throw new IllegalArgumentException(String.format(PARSING_ERROR_TEXT, line));
				} else if (ret == null) {
					ret = line;
				} else {
					if (!line.isEmpty()) {
						m.put(line, SYMBOLS.get(symbolIndex));
					}
					symbolIndex++;
				}
			}

			return ret;
		} catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * Diese Funktion entschluesselt die Datei.
	 * @param encodedMessage ; enthaelt den Text, der decodiert werden soll.
	 * @param mapping
	 * @return decodierten String.
	 */
	private static String decodeWithMapping(String encodedMessage, HashMap<String, Character> mapping) {
		int pos = 0;
		int len = 0;

		String ret = "";

		while (encodedMessage != null && pos < encodedMessage.length()) {
			if (mapping.containsKey(encodedMessage.substring(pos, pos + len))) {
				ret += mapping.get(encodedMessage.substring(pos, pos + len));
				pos += len;
				len = 0;
			} else {
				len++;
			}
		}

		return ret;
	}

	/**
	 * Test-Methode
	 * @param args
	 */
	public static void main(String args[]) {
		System.out.println(decode(new File("message.txt")));
	}
}
