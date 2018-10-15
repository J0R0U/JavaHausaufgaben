package main;


import java.util.Arrays;

/**
 * Die Klasse MessreiheTest ist eine Klasse welche eine Methode zum testen der
 * Messreihe Klasse besitzt.
 * 
 * @author Julia, Dominik, Jonas
 * @version V01.00B00
 */
public class MessreiheTest {

	/**
	 * Die Methode main, wird zu Beginn des Programms ausgefuehrt und beinhaltet die
	 * Testaufrufe.
	 * 
	 * @param args Die von der Umgebung uebergebenen Parameter.
	 */
	public static void main(String[] args) {
		try {
			Messreihe messreihe = new Messreihe("Messwerte.txt");
			System.out.println("Maximalwert: " + messreihe.getMax());
			System.out.println("Minimalwert: " + messreihe.getMin());
			System.out.println("Einheitliche Werte: " + messreihe.isEinheitlich());
			System.out.println("Anzahl Werte groesser als 50: " + messreihe.zaehleGroessere(50));
			System.out.println("Index Maximalwert: " + messreihe.getMaxIndex());
			System.out.println("Wertebereich: " + Arrays.toString(messreihe.getBereich()));
		} catch (ArithmeticException e) {
			System.out.println("Anzahl der Messwerte war zu gross/ zu klein!");
		}
	}
}
