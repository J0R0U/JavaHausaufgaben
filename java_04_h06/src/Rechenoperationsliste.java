import java.util.ArrayList;
import java.util.Arrays;

public class Rechenoperationsliste {
	ArrayList<Rechenoperation> data;
	
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

	public Rechenoperationsliste () {
		data = new ArrayList<>();
	}
	
	public void add(Rechenoperation operation) {
		data.add(operation);
	}
	
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
