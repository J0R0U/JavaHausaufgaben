import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * Ein Loesungsalgorithmus, der so lange ein zufaelliges Plaettchen verschiebt, bis das Plaettchen 
 * mit der 1 oben links liegt.
 * @author Jonas, Dominik, Julia
 *
 */
public class SchiebAlg1 implements Loesungsalgorithmus {
	
	/**
	 * Verschiebt so lange ein zufaelliges der verschiebbaren Plaettchen, bis die 1 oben links liegt.
	 * @param p Puzzle auf dem gespielt wird
	 */
	@Override
	public void loese(Schiebepuzzle p) {
		Point endPos = new Point(0,0);
		while(!p.getLocationOfField(1).equals(endPos)) {
			ArrayList<Integer> temp = new ArrayList<>();
			for(int i = 1; i <= p.maxElement(); i++) {
				if(p.istVerschiebar(i)) {
					temp.add(i);
				}
			}
			int index = randInt(temp.size() - 1);
			p.schiebe(temp.get(index));
		}		
	}
	
	/**
	 * Gibt eine Zufallszahl zwischen 0 und max zurueck (inklusive).
	 * @param max maximaler Wert
	 * @return zufaellige Zahl
	 */
	private static int randInt(int max) {
		Random rand = new Random(System.currentTimeMillis());
		return rand.nextInt(max + 1);
	}
}
