package main;

import java.security.InvalidParameterException;

/**
 * In der Klasse PositiveNumber koennen postive Zahlen in Dezimal-, Hexal- oder Binaerzahlen umgerechnet werden.
 * @author Jonas, Dominik, Julia
 * @version V01.00B01
 */
public class PositiveNumber {
	//
	private static final short BASE_BIN = 2;
	private static final short BASE_DEC = 10;
	private static final short BASE_HEX = 16;

	private int value;

	/**
	 * Konstruktor: Initialisiert den Wert mit 0.
	 */
	public PositiveNumber() {
		value = 0;
	}

	/**
	 * Setzt den Wert auf Wert s im Dezimalsystem.
	 * @param s String; der die zu verwendende Zahl enthaelt.
	 */
	public void setDecimal(String s) {
		value = toValue(s, BASE_DEC);
	}

	/**
	 * Setzt den Wert auf Wert s im Hexadezimalsystem.
	 * @param s String; der die zu verwendende Zahl enthaelt.
	 */
	public void setHexadecimal(String s) {
		value = toValue(s, BASE_HEX);
	}

	/**
	 * Setzt den Wert auf Wert s im Binaersystem.
	 * @param s String; der die zu verwendende Zahl enthaelt.
	 */
	public void setBinary(String s) {
		value = toValue(s, BASE_BIN);
	}

	/**
	 * Gibt den Wert als Dezimalzahl zurueck.
	 * @return Dezimalwert in String
	 */
	public String getDecimal() {
		return intToString(value, BASE_DEC);
	}

	/**
	 * Gibt den Wert als Hexadezimalzahl zurueck.
	 * @return Hexadezimalwert in String
	 */
	public String getHexadecimal() {
		return intToString(value, BASE_HEX);
	}

	/**
	 * Gibt den Wert als Binaerzahl zurueck.
	 * @return Binaerwert in String
	 */
	public String getBinary() {
		return intToString(value, BASE_BIN);
	}
	
	/**
	 * Konvertiert die als String uebergebene Zahl im gegebenen Stellenwertsystem zu einem Integerwert im Dezimalsystem.
	 * @param s String; der die zu verwendende Zahl enthaelt.
	 * @param base short; Base von newValue
	 * @return die umgerechnete Zahl newValue
	 * @throws ArithmeticException : wird geschmissen, wenn der Wert zu groﬂ ist.
	 * @throws NumberFormatException : wird geschmissen, wenn s keine Zahl darstellt.
	 */
	private static int toValue(String s, short base) throws ArithmeticException,NumberFormatException {
		int newValue = 0;
		for(char c : s.toCharArray()) {
			newValue *= base;
			newValue += charToShort(c, base);
			if (newValue < 0) {
				throw new ArithmeticException("Overflow detected. Could not save string '" + s + "' as integer.");
			}
		}
		return newValue;
	}

	/**
	 * Wandelt ein Zeichen in einem gegebenen Stellenwertsystem in seinen numerischen Wert um.
	 * @param c Zeichen welches in eine Zahl umgewandelt werden soll.
	 * @param base short; Basis des Stellenwertsystems
	 * @return numerischer Wert des Zeichens
	 * @throws NumberFormatException : wird geschmissen, wenn s negativ oder groesser als die Basis ist.
	 */
	private static short charToShort(char c, short base) throws NumberFormatException {
		short ret = -1;
		if(c >= '0' && c <= '9')
			ret = (short) (c - '0');
		else if(c >= 'A' && c <= 'F')
			ret = (short) (c - 'A' + 10);
		else if(c >= 'a' && c <= 'f')
			ret = (short) (c - 'a' + 10);

		if (ret < 0 || ret >= base) {
			throw new NumberFormatException(
					"The character '" + c + "' could not be converted to a number in the format with base '" + base + "'.");
		}

		return ret;
	}
	
	/**
	 * Konvertiert einen Integer Wert in einen String mit angegebener Basis (rekursiv).
	 * @param v int; Wert der konvertiert werden soll.
	 * @param base Basis des Stellenwertsystems in welchem der Wert als String ausgegeben werden soll.
	 * @return Stringrepraesentation der gegebenen Zahl im entsprechenden Stellenwertsystems
	 */
	private static String intToString(int v, short base) {
		String ret = "";
		if(v == 0) {
			return "0";
		}
		short mod = (short) (v%base);
		ret += shortToChar(mod);
		ret = intToString((v-mod)/base, base) + ret;
		return ret;
	}

	/**
	 * Wandelt eine Zahl in ein entsprechendes einstelliges Zeichen in einem beliebigen Stellenwertsystem um
	 * @param c Zahl welche umgewandelt werden soll
	 * @return einstelliges Zeichen f¸r Darstellung in Stellenwertsystemen (besonders f¸r Basis >10)
	 */
	private static char shortToChar(short c) {
		if(c >= 0 && c <= 9)
			return (char) ('0' + c);
		if(c >= 10 && c <= 16)
			return (char) ('A' + c - 10);
		throw new InvalidParameterException("The value '" + c + "' is not compatible with a base <= 16" );
	}
	
	/**
	 * Dies ist eine Test-Methode.
	 * @param args
	 */
	public static void main(String[] args) {
		PositiveNumber zs = new PositiveNumber();
		zs.setDecimal("144");
		System.out.println("Binaer: "      + zs.getBinary());
		zs.setHexadecimal("affe");
		System.out.println("Dezimal: "     + zs.getDecimal());
		zs.setBinary("1000101011");
		System.out.println("Hexadezimal: " + zs.getHexadecimal());
	}
}
