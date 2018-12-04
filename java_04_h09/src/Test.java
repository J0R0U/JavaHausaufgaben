/**
 * Testklasse
 * @author Jonas, Dominik, Julia
 *
 */
public class Test {
	public static void main(String[] args) {
		Schiebepuzzle puzzle = new Schiebepuzzle();

		puzzle.mische();
		
		System.out.println("Vorher:");
		System.out.println(puzzle);
		
		Loesungsalgorithmus alg1 = new SchiebAlg1();
		alg1.loese(puzzle);
		
		System.out.println();
		System.out.println("Nachher:");
		System.out.println(puzzle);
	}
}
