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
	 * PositiveNumber setzt value auf 0.
	 */
	public PositiveNumber() {
		value = 0;
	}

	/**
	 * setDecimal setzt value auf eine Dezimalzahl mit Wert s.  
	 * @param s String; der die zu verwendende Zahl enthaelt.
	 * @throws 
	 */
	public void setDecimal(String s) {
		value = toValue(s, BASE_DEC);
	}

	/**
	 * setHexadecimal setzt value auf eine Hexadezimalzahl mit Wert s.
	 * @param s String; der die zu verwendende Zahl enthaelt.
	 * @throws
	 */
	public void setHexadecimal(String s) {
		value = toValue(s, BASE_HEX);
	}

	/**
	 * setBinary setzt value auf eine Binaerzahl mit Wert s.
	 * @param s String; der die zu verwendende Zahl enthaelt.
	 * @throws
	 */
	public void setBinary(String s) {
		value = toValue(s, BASE_BIN);
	}

	/**
	 * getDecimal gibt den Dezimalwert von value in einem String zurueck.
	 * @return Dezimalwert in String
	 */
	public String getDecimal() {
		return intToString(value, BASE_DEC);
	}

	/**
	 * getHexadecimal gibt den Hexadezimalwert von value in einem String zurueck.
	 * @return Hexadezimalwert in String
	 */
	public String getHexadecimal() {
		return intToString(value, BASE_HEX);
	}

	/**
	 * getBinary gibt den Binaerwert von value in einem String zurueck.
	 * @return Binaerwert in String
	 */
	public String getBinary() {
		return intToString(value, BASE_BIN);
	}
	
	/**
	 * toValue rechnet s in eine Zahl mit anderer Basis um, die dann in newValue gespeichert wird.
	 * @param s String; der die zu verwendende Zahl enthaelt.
	 * @param base short; Base von newValue
	 * @return die umgerechnete Zahl newValue
	 * @throws ArithmeticException : wird geschmissen, wenn s negativ ist.
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
	 * charToShort wandelt den char c in den short ret um.
	 * @param c 
	 * @param base short; Base der Zahl
	 * @return ret
	 * @throws NumberFormatException : wird geschmissen, wenn s negativ oder groesser als die Base ist.
	 */
	private static short charToShort(char c, short base) throws NumberFormatException {
		short ret = -1;
		switch (c) {
		case '0': ret = 0;  break;
		case '1': ret = 1;  break;
		case '2': ret = 2;  break;
		case '3': ret = 3;  break;
		case '4': ret = 4;  break;
		case '5': ret = 5;  break;
		case '6': ret = 6;  break;
		case '7': ret = 7;  break;
		case '8': ret = 8;  break;
		case '9': ret = 9;  break;
		case 'A': ret = 10;	break;
		case 'a': ret = 10;	break;
		case 'B': ret = 11;	break;
		case 'b': ret = 11;	break;	
		case 'C': ret = 12;	break;
		case 'c': ret = 12;	break;
		case 'D': ret = 13;	break;
		case 'd': ret = 13;	break;
		case 'E': ret = 14;	break;
		case 'e': ret = 14;	break;
		case 'F': ret = 15; break;
		case 'f': ret = 15;	break;
		}

		if (ret < 0 || ret >= base) {
			throw new NumberFormatException(
					"The character '" + c + "' could not be converted to a number in the format with base '" + base + "'.");
		}

		return ret;
	}
	
	/**
	 * konvertiert einen int Wert in einen String mit angegebener Basis (rekusiv).
	 * @param v int; Wert der konvertiert werden soll.
	 * @param base Basis in welcher der String ausgegeben werden soll.
	 * @return
	 */
	private static String intToString(int v, short base) {
		String ret = "";
		if(v == 0) {
			return ret;
		}
		short mod = (short) (v%base);
		ret += shortToChar(mod);
		ret = intToString((v-mod)/base, base) + ret;
		return ret;
	}

	/**
	 * shortToChar wandelt short c in einen Character.
	 * @param c wird von short zu char geaendert.
	 * @return char c
	 */
	private static char shortToChar(short c) {
		switch (c) {
		case 0:	 return '0';
		case 1:	 return '1';
		case 2:	 return '2';
		case 3:	 return '3';
		case 4:	 return '4';
		case 5:	 return '5';
		case 6:	 return '6';
		case 7:	 return '7';
		case 8:	 return '8';
		case 9:	 return '9';
		case 10: return 'A';
		case 11: return 'B';
		case 12: return 'C';
		case 13: return 'D';
		case 14: return 'E';
		case 15: return 'F';
		}
		throw new InvalidParameterException();
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
