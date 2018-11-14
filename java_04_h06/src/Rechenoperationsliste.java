import java.util.ArrayList;
import java.util.Arrays;

/**
 * Repraesentiert eine Liste von Rechenoperationen die auf Elemente eines Arrays angwendet werden koennen
 * @author Jonas, Julia, Dominik
 */
public class Rechenoperationsliste {
	ArrayList<Rechenoperation> data;
	
	/*
	 * Test-Methode
	 */
	public static void main(String[] args) {
		double[] ausgangsfeld = { 1, 3, 5, 6 };
		Rechenoperationsliste r = new Rechenoperationsliste();
		r.add(new Quadrat());
		r.add(new Addition(2));
		double[] test1 = r.transform(ausgangsfeld);
		System.out.println(Arrays.toString(test1));
		r.add(new Quadratwurzel());
		double[] test2 = r.transform(ausgangsfeld);
		System.out.println(Arrays.toString(test2));
	}

	/**
	 * Default-Konstruktor: Initialisiert die Liste der Rechenoperationen (leer)
	 */
	public Rechenoperationsliste () {
		data = new ArrayList<>();
	}
	
	/**
	 * Fuegt eine neue Rechenoperation an die Liste an
	 * @param operation zu anhaengende Rechenoperation
	 */
	public void add(Rechenoperation operation) {
		data.add(operation);
	}
	
	/**
	 * Fuehrt jede Rechenoperation der Liste auf jedem Element des uebergebenen Arrays aus und gibt das Ergebnis zurueck
	 * @param feld Array der zu verandernden Werte
	 * @return Array mit entsprechend veraenderten Werten
	 */
	public double[] transform(double[] feld) {
		double[] ret = feld.clone();
		for (Rechenoperation operation : data) {
			for(int i = 0; i < ret.length; i++) {
				ret[i] = operation.berechne(ret[i]);
			}
		}
		return ret;
	}
}
