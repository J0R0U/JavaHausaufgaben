
import java.security.InvalidParameterException;
import java.util.HashMap;

public class Blatt {
	private static final int CARD_COUNT = 3;
	private static final int TRIPLE = 3;
	private static final int DOUBLE = 2;
	private int[] data;

	public Blatt() {
		data = new int[CARD_COUNT];
	}

	public Blatt(int[] data) {
		this.data = new int[CARD_COUNT];
		if (data.length != 3) {
			throw new InvalidParameterException("Size of given array is not 3");
		}
		for (int i = 0; i < data.length; i++) {
			if (data[i] >= 2 && data[i] <= 14) {
				this.data[i] = data[i];
			}
		}
	}

	public boolean isTripple() {
		return trippleValue() != -1;
	}

	public int trippleValue() {
		return valueOfOccurence(TRIPLE);
	}

	public boolean isDouble() {
		return doubleValue() != -1;
	}

	public int doubleValue() {
		return valueOfOccurence(DOUBLE);
	}

	public int thirdValue() {
		int temp = doubleValue();
		for (int i = 0; i < data.length; i++) {
			if (data[i] != temp) {
				return data[i];
			}
		}
		return -1;
	}

	public int sum() {
		int ret = 0;
		for (int i = 0; i < data.length; i++) {
			ret += data[i];
		}
		return ret;
	}

	public String toString() {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < data.length; i++)
			ret.append(data[i] + ", ");
		ret.setLength(ret.length() - 2);
		return ret.toString();
	}

	private int valueOfOccurence(int occurence) {
		HashMap<Integer, Integer> temp = new HashMap<>();
		int ret = -1;

		for (int i = 0; i < data.length; i++) {
			if (!temp.containsKey(data[i])) {
				temp.put(data[i], 1);
			} else {
				temp.put(data[i], temp.get(data[i]) + 1);
				if (temp.get(data[i]) == occurence) {
					ret = data[i];
				} else if (temp.get(data[i]) > occurence) {
					ret = -1;
				}
			}
		}

		return ret;
	}
}
