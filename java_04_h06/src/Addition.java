
public class Addition implements Rechenoperation {
	private double addMePls;
	
	public Addition(double d) {
		addMePls = d;
	}

	@Override
	public double berechne(double x) {
		return x + addMePls;
	}

}
