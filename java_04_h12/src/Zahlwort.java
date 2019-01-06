import java.util.ArrayList;

public class Zahlwort {

	private static String onesAsInt(int x, boolean m) {
		switch (x) {
		case 1:
			return "ein" + (m ? "s" : "");
		case 2:
			return "zwei";
		case 3:
			return "drei";
		case 4:
			return "vier";
		case 5:
			return "fuenf";
		case 6:
			return "sech" + (m ? "s" : "");
		case 7:
			return "sieb" + (m ? "en" : "");
		case 8:
			return "acht";
		case 9:
			return "neun";
		}
		return "";
	}
	
	private static String tensAsInt(int x) {
		switch(x) {
		case 1:
			return "zehn";
		case 2:
			return "zwanzig";
		case 3:
			return "dreissig";
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			return onesAsInt(x, false) + "zig";
		}
		return "";
	}
	
	private static String hundretsAsInt(int x) {
		switch(x) {
		case 1:
			return "einhundert";
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			return onesAsInt(x, true) + "hundert";
		}
		return "";
	}
	
	private static String thousandsAsInt(int x) {
		switch(x) {
		case 1:
			return "eintausend";
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			return onesAsInt(x, true) + "tausend";
		}
		return "";
	}
	
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
				ret += hundretsAsInt(numbers.get(0));
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
	
	private static void flipLastTwoEnterys(ArrayList<Integer> numbers) {
		if(numbers.size() > 1) {
			Integer temp = numbers.get(numbers.size() - 1);
			numbers.set(numbers.size() - 1, numbers.get(numbers.size() - 2));
			numbers.set(numbers.size() - 2, temp);
		}
	}

	public static String getZahlwort(int x) {
		ArrayList<Integer> numbers = new ArrayList<>();
		for(char c : (x + "").toCharArray()) {
			numbers.add(Integer.parseInt("" + c));
		}
		
		flipLastTwoEnterys(numbers);		

		return toString(numbers);
	}

	public static void main(String[] args) throws java.io.IOException {
		for (int i = 1; i <= 9999; i++) {
			System.out.println(getZahlwort(i));
		}
	}
}
