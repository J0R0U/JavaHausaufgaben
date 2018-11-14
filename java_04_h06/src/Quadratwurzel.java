/**
 * Repraesentiert das ziehen der Quadratwurzel einer Zahl als Rechenoperation
 * @author Jonas, Julia, Dominik
 */
public class Quadratwurzel implements Rechenoperation {

	/**
	 * Gibt die Quadradwurzel des uebergebenen Wertes zurueck
	 * @return Quadratwurzel
	 */
	@Override
	public double berechne(double x) {
		return Math.sqrt(x);
	}

}
