
/**
 * Berechnet die Punkte der Kochkurve zu einer bestimmten Rekursionstiefe.
 * @author Jonas, Dominik, Julia
 * @version V01.00B01
 */
public class KochKurve {

	/**
	 * Ruft kochKurveRek auf und gibt anschliessend den letzten Punkt aus.
	 * @param ax ; double X-Koordinate des Startpunkts
	 * @param ay ; double Y-Koordinate des Startpunkts
	 * @param bx ; double X-Koordinate des Endpunkts
	 * @param by ; double Y-Koordinate des Endpunkts
	 * @param rek ; double , Die Genauigkeit (Rekursionstiefe) mit welcher die KochKurve bestimmt werden soll.
	 */
	public static void kochKurve(double ax, double ay, double bx, double by, int rek) {
		System.out.printf("Rekursionstiefe %d: ", rek);
		kochKurveRek(ax, ay, bx, by, rek);
		System.out.printf("%s%n", OutputFormatter.formatToString(bx, by));
	}

	/**
	 * Berechnet rekursiv die einzelnen Punkte der Kochkurve fuer die uebergebene Rekursionstiefe zwischen zwei gegebenen Punkten.
	 * @param ax ; double X-Koordinate des Startpunkts
	 * @param ay ; double Y-Koordinate des Startpunkts
	 * @param bx ; double X-Koordinate des Endpunkts
	 * @param by ; double Y-Koordinate des Endpunkts
	 * @param rek ; double , Die Genauigkeit (Rekursionstiefe) mit welcher die KochKurve bestimmt werden soll.
	 */
	private static void kochKurveRek(double ax, double ay, double bx, double by, int rek) {
		if (rek == 0) {
			System.out.printf("%s ", OutputFormatter.formatToString(ax, ay));
			return;
		}
		double oneThirdX = getWeightnedPoint(ax, bx, 1. / 3);
		double oneThirdY = getWeightnedPoint(ay, by, 1. / 3);

		double twoThirdX = getWeightnedPoint(ax, bx, 2. / 3);
		double twoThirdY = getWeightnedPoint(ay, by, 2. / 3);

		double newTriangleX = getTriangleX(oneThirdX, oneThirdY, twoThirdX, twoThirdY);
		double newTriangleY = getTriangleY(oneThirdX, oneThirdY, twoThirdX, twoThirdY);

		kochKurveRek(ax, ay, oneThirdX, oneThirdY, rek - 1);
		kochKurveRek(oneThirdX, oneThirdY, newTriangleX, newTriangleY, rek - 1);
		kochKurveRek(newTriangleX, newTriangleY, twoThirdX, twoThirdY, rek - 1);
		kochKurveRek(twoThirdX, twoThirdY, bx, by, rek - 1);
	}

	/**
	 * Berechnet einen Wert welcher gewichtet zwischen zwei weiteren Werten liegt.
	 * @param a ; double Wert Eins
	 * @param b ; double Wert Zwei
	 * @param weight ; double Gewicht welches die Position zwischen den Punkten bestimmt. (0.5 ist z.B. die Haelfte zweichen Wert Eins und Zwei)
	 * @return
	 */
	private static double getWeightnedPoint(double a, double b, double weight) {
		return a * (1 - weight) + b * weight;
	}
	
	/**
	 * Berechnet den Y Wert eines gleichseitigen Dreiecks.
	 * @param ax ; double X-Koordinate des ersten Eckpunkts
	 * @param ay ; double Y-Koordinate des ersten Eckpunkts
	 * @param bx ; double X-Koordinate des zweiten Eckpunkts
	 * @param by ; double Y-Koordinate des zweiten Eckpunkts
	 * @return Der berechnete Wert.
	 */
	private static double getTriangleX(double ax, double ay, double bx, double by) {
		return calculateNewPoint(ax, bx, ay, by);
	}
	
	/**
	 * Berechnet den Y Wert eines gleichseitigen Dreiecks.
	 * @param ax ; double X-Koordinate des ersten Eckpunkts
	 * @param ay ; double Y-Koordinate des ersten Eckpunkts
	 * @param bx ; double X-Koordinate des zweiten Eckpunkts
	 * @param by ; double Y-Koordinate des zweiten Eckpunkts
	 * @return Der berechnete Wert.
	 */
	private static double getTriangleY(double ax, double ay, double bx, double by) {
		return  calculateNewPoint(ay, by, bx, ax);
	}

	/**
	 * Berechnet den X oder Y Wert eines gleichseitigen Dreiecks.
	 * @param one ; double Wert Eins
	 * @param two ; double Wert Zwei
	 * @param three ; double Wert Drei
	 * @param four ; double Wert Vier
	 * @return Der berechnete Wert.
	 */
	private static double calculateNewPoint(double one, double two, double three, double four) {
		return (one + two - Math.sqrt(3) * (three - four)) / 2;
	}

	/**
	 * Test-Methode.
	 * @param args
	 */
	public static void main(String[] args) {
		kochKurve(0, 500, 500, 500, 2);
	}
}
