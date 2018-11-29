import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class SchiebAlg1 implements Loesungsalgorithmus {

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
	
	private static int randInt(int max) {
		Random rand = new Random(System.currentTimeMillis());
		return rand.nextInt(max + 1);
	}
}
