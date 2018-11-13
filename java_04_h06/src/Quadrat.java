/**
 * Repraesentiert das zum Quadrat nehmen einer Zahl als Rechenoperation
 * @author Jonas, Julia, Dominik
 */
public class Quadrat implements Rechenoperation {

	/**
	 * Gibt das Quadrat des uebergebenen Wertes zurueck
	 * @return Quadrat
	 */
	@Override
	public double berechne(double x) {
		return x * x;
	}

}
