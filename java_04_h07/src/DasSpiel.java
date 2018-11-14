/**
 * Spielt verschiedene Partien mit jeweils zwei Strategien gegeneinander (Jeder gegen Jeden)
 * @author Jonas, Julia, Dominik
 *
 */
public class DasSpiel {
	public static void main(String[] args) {
		GefDilemma gd = new GefDilemma(new Pavlov(), new PerKind());
		gd.spiele(100);
		System.out.println();
		
		gd = new GefDilemma(new Pavlov(), new Random());
		gd.spiele(100);
		System.out.println();
		
		gd = new GefDilemma(new Pavlov(), new Spite());
		gd.spiele(100);
		System.out.println();
		
		gd = new GefDilemma(new Pavlov(), new TitForTat());
		gd.spiele(100);
		System.out.println();
		
		gd = new GefDilemma(new PerKind(), new Random());
		gd.spiele(100);
		System.out.println();
		
		gd = new GefDilemma(new PerKind(), new Spite());
		gd.spiele(100);
		System.out.println();
		
		gd = new GefDilemma(new PerKind(), new TitForTat());
		gd.spiele(100);
		System.out.println();
		
		gd = new GefDilemma(new Random(), new Spite());
		gd.spiele(100);
		System.out.println();
		
		gd = new GefDilemma(new Random(), new TitForTat());
		gd.spiele(100);
		System.out.println();
		
		gd = new GefDilemma(new Spite(), new TitForTat());
		gd.spiele(100);
	}
}
