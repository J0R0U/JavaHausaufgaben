import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Zahlwort {

	/**
	 * Wandelt eine einfache Ziffer in das zugehoerige Zahlwort um.
	 * Gibt die Moeglichkeit, die Endung wegzulassen.
	 * @param x Ziffer
	 * @param ending ob die Endung angehaengt werden soll oder nicht
	 * @return 
	 */
	private static String onesAsInt(int x, boolean ending) {
		switch (x) {
			case 1:
				return "ein" + (ending ? "s" : "");
			case 2:
				return "zwei";
			case 3:
				return "drei";
			case 4:
				return "vier";
			case 5:
				return "fuenf";
			case 6:
				return "sech" + (ending ? "s" : "");
			case 7:
				return "sieb" + (ending ? "en" : "");
			case 8:
				return "acht";
			case 9:
				return "neun";
		}
		return "";
	}

	/**
	 * Wandelt eine Ziffern in das zugehoerige Zahlwort im Zehner-Bereich um
	 * @param x Ziffer an Zehnerstelle
	 * @return Zahlwort
	 */
	private static String tensAsInt(int x) {
		switch(x) {
			case 1:
				return "zehn";
			case 2:
				return "zwanzig";
			case 3:
				return "dreissig";
			default:
				if(x > 3 && x < 10)
					return onesAsInt(x, false) + "zig";
		}
		return "";
	}

	/**
	 * Wandelt eine Ziffern in das zugehoerige Zahlwort im Hunderter-Bereich um
	 * @param x Ziffer an Hunderterstelle
	 * @return Zahlwort
	 */
	private static String hundredsAsInt(int x) {
		return onesAsInt(x, !(x==1)) + "hundert";
	}

	/**
	 * Wandelt eine Ziffern in das zugehoerige Zahlwort im Tausender-Bereich um
	 * @param x Ziffer an Tausenderstelle
	 * @return Zahlwort
	 */
	private static String thousandsAsInt(int x) {
		return onesAsInt(x, !(x==1)) + "tausend";
	}
	
	/**
	 * Wandelt eine Liste von Ziffern in das zugehoerige Zahlwort um
	 * @param numbers Liste von Ziffern
	 * @return Zahlwort
	 */
	private static String toString(ArrayList<Integer> numbers) {
		String ret = "";
		switch(numbers.size()) {
			case 1:
				ret = onesAsInt(numbers.get(0), true);
				break;
			case 2:
				if(numbers.get(1) == 0) {
					return onesAsInt(numbers.get(0), true);
				}
				if(numbers.get(0) != 0) {
					if(numbers.get(1) == 1) {
						if(numbers.get(0) == 1) {
							return "elf";
						} else if(numbers.get(0) == 2) {
							return "zwoelf";
						} else {
							ret = onesAsInt(numbers.get(0), false);
						}
					} else {
						if(numbers.get(0) == 1)
							ret = "ein";
						else {
							ret = onesAsInt(numbers.get(0), true);							
						}
						ret += "und";
					}
				}
				ret += tensAsInt(numbers.get(1));
				break;
			case 3:
				ret += hundredsAsInt(numbers.get(0));
				numbers.remove(0);
				ret +=toString(numbers);
				break;
			case 4:
				ret += thousandsAsInt(numbers.get(0));
				numbers.remove(0);
				ret += toString(numbers);
				break;
		}
		return ret;
	}
	
	/**
	 * Dreht die hinteren zwei Elemente der Liste um
	 * @param numbers Liste bei der die Elemente gewechselt werden sollen
	 */
	private static void flipLastTwoEntries(ArrayList<Integer> numbers) {
		if(numbers.size() > 1) {
			Integer temp = numbers.get(numbers.size() - 1);
			numbers.set(numbers.size() - 1, numbers.get(numbers.size() - 2));
			numbers.set(numbers.size() - 2, temp);
		}
	}

	/**
	 * Gibt das Zahlwort der uebergebenen Zahl zurueck
	 * @param x numerische Zahl
	 * @return Zahlwort
	 * @throws ArithmeticException wenn die Zahl außerhalb des gueltigen Wertebereichs liegt
	 */
	public static String getZahlwort(int x) throws ArithmeticException {
		if(x < 1 || x > 9999)
			throw new ArithmeticException("Zahl liegt nicht im gültigen Wertebereich");
		ArrayList<Integer> numbers = new ArrayList<>();
		for(char c : (x + "").toCharArray()) {
			numbers.add(Integer.parseInt("" + c));
		}
		
		flipLastTwoEntries(numbers);		

		return toString(numbers);
	}
	
	/**
	 * Gibts einen Stream der Zahlwoerter von <code>start</code> bis <code>stop</code> (beides inklusive)
	 * @param start Anfang
	 * @param stop Ende
	 * @return Stream der Zahlwoerter
	 */
	public static Stream<String> getZahlStream(int start, int stop) {
		return IntStream.iterate(start, i -> i+1).limit(stop-start+1).mapToObj(Zahlwort::getZahlwort);
	}

	public static void main(String[] args) throws java.io.IOException {
		for (int i = 1; i < 10000; i++) {
			System.out.println(getZahlwort(i));
		}
	}
}
