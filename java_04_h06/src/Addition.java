/**
 * Repraesentiert die Addition als Rechenoperation
 * @author Jonas, Julia, Dominik
 */
public class Addition implements Rechenoperation {
	private double addMePls;
	
	/**
	 * Konstruktor: Initialisiert den zu addierenden Wert mit dem uebergebenen Wert
	 * @param d zu addierender Wert
	 */
	public Addition(double d) {
		addMePls = d;
	}
	
	/**
	 * Fuehrt die Addition durch und gibt die Summe zurueck
	 * @return Summe
	 */
	@Override
	public double berechne(double x) {
		return x + addMePls;
	}

}
