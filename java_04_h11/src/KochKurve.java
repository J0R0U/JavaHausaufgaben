
/**
 * 
 * @author
 * @version
 */
public class KochKurve {

	/**
	 * 
	 * @param ax
	 * @param ay
	 * @param bx
	 * @param by
	 * @param rek
	 */
	public static void kochKurve(double ax, double ay, double bx, double by, int rek) {
		System.out.printf("Rekursionstiefe %d: ", rek);
		kochKurveRek(ax, ay, bx, by, rek);
		System.out.printf("%s%n", OutputFormatter.formatToString(bx, by));
	}

	/**
	 * 
	 * @param ax
	 * @param ay
	 * @param bx
	 * @param by
	 * @param rek
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

		double newTriangleX = calculateNewPoint(oneThirdX, twoThirdX, oneThirdY, twoThirdY);
		double newTriangleY = calculateNewPoint(oneThirdY, twoThirdY, twoThirdX, oneThirdX);

		kochKurveRek(ax, ay, oneThirdX, oneThirdY, rek - 1);
		kochKurveRek(oneThirdX, oneThirdY, newTriangleX, newTriangleY, rek - 1);
		kochKurveRek(newTriangleX, newTriangleY, twoThirdX, twoThirdY, rek - 1);
		kochKurveRek(twoThirdX, twoThirdY, bx, by, rek - 1);
	}

	/**
	 * 
	 * @param ax
	 * @param bx
	 * @param weight
	 * @return
	 */
	private static double getWeightnedPoint(double ax, double bx, double weight) {
		return ax * (1 - weight) + bx * weight;
	}

	/**
	 * 
	 * @param one
	 * @param two
	 * @param three
	 * @param four
	 * @return
	 */
	private static double calculateNewPoint(double one, double two, double three, double four) {
		return (one + two - Math.sqrt(3) * (three - four)) / 2;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		kochKurve(0, 500, 500, 500, 2);
	}
}
