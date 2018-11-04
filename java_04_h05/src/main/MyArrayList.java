package main;

public class MyArrayList {
	private int[] arr;
	private int size;

	public static void main(String[] args) {
		MyArrayList myArrayList = new MyArrayList();
		// fuege die Zahlen 0-9 zur Liste hinzu
		for (int i = 0; i < 10; i++) {
			myArrayList.add(i);
		}
		System.out.println(myArrayList);//[0,1,2,3,4,5,6,7,8,9]
		//die Liste hat nun 10 Elemente
		System.out.println(myArrayList.size()); // 10
		//fuege die Zahl 5 an der Position 2 der Liste hinzu
		myArrayList.add(5, 2);
		System.out.println(myArrayList);//[0,1,5,2,3,4,5,6,7,8,9]
		//entferne die Elemente 1-6
		for(int i = 6; i > 0; i--){
			myArrayList.delete(i);
			System.out.print(myArrayList.capacity()+"/");
			System.out.println(myArrayList.size());
		}
		System.out.println(myArrayList);//[0,6,7,8,9]
		//gebe das Element an der Position 2 aus
		System.out.println(myArrayList.get(2));//7
		//erzeugt einen Klon der Liste
		MyArrayList myArrayList2= myArrayList.clone();
		//Loesche die Liste
		myArrayList.clear();
		System.out.println(myArrayList);//[]
		System.out.println(myArrayList2);//[0,6,7,8,9]
	}

	/*
	 * Konstruktor von arr und size
	 */
	public MyArrayList() {
		arr = new int[10];
		size = 0;
	}

	/*
	 * get gibt das Element an der angegebenen Stelle zurueck. 
	 * Wirft eine ArrayIndexOutOfBoundsException, falls pos nicht zwischen 0 und size liegt.
	 */
	public int get(int pos) {
		if(pos >= size) {
			throw new ArrayIndexOutOfBoundsException("Index " + pos + " ist kein valider Index.");
		}
		return arr[pos];
	}

	/*
	 * add haengt ein Element hinten an die Liste an.
	 */
	public void add(int i) {
		if (size >= arr.length) {
			int[] temp = new int[arr.length * 2];
			System.arraycopy(arr, 0, temp, 0, arr.length);
			arr = temp;
		}

		arr[size] = i;
		size++;
	}

	/*
	 * Fuegt ein Element an der Position pos ein. 
	 * Verschiebt alle folgenden Elemente eine Position nach hinten. 
	 * Wirft eine ArrayIndexOutOfBoundsException, falls pos nicht zwischen 0 und size liegt.
	 */
	public void add(int i, int pos) {
		if (pos > size || pos < 0)
			throw new ArrayIndexOutOfBoundsException("Index " + pos + " ist kein valider Index.");

		if (size >= arr.length) {
			int[] temp = new int[arr.length * 2];
			System.arraycopy(arr, 0, temp, 0, arr.length);
			arr = temp;
		}

		System.arraycopy(arr, pos, arr, pos + 1, size - pos);

		arr[pos] = i;
		size++;
	}

	/*
	 * Loescht das Element an der Position pos. 
	 * Verschiebt alle folgenden Elemente eine Position nach vorne. 
	 * Wirft eine ArrayIndexOutOfBoundsException, falls pos nicht zwischen 0 und size liegt.
	 */
	public void delete(int pos) {
		if (pos > size || pos < 0)
			throw new ArrayIndexOutOfBoundsException("Index " + pos + " ist kein valider Index.");

		for (int i = 0; i < size - 1; i++) {
			if (i < pos) {
				arr[i] = arr[i];
			} else if (i >= pos) {
				arr[i] = arr[i + 1];
			}
		}
		arr[size] = 0;
		size--;

		int usedSize = arr.length / 3;
		if (usedSize >= size) {
			int[] temp = new int[arr.length / 2];
			System.arraycopy(arr, 0, temp, 0, size);
			arr = temp;
		}
	}

	/*
	 * Gibt die Anzahl der aufgenommenen Elemente zurueck.
	 */
	public int size() {
		return size;
	}

	/*
	 * Gibt die Groesse des Feldes (einschliesslich freier Elemente) zurueck.
	 */
	public int capacity() {
		return arr.length;
	}

	/*
	 * clear loescht das Feld.
	 */
	public void clear() {
		arr = new int[10];
		size = 0;
	}

	/*
	 * Erzeugt einen Klon der this-Liste.(non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public MyArrayList clone() {
		MyArrayList ret = new MyArrayList();
		ret.arr = arr.clone();
		ret.size = size;
		return ret;
	}
	/*
	 * Gibt eine Stringdarstellung in der Form [1,2,3,4,7,9] zurueck.(non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder("[");

		for (int i = 0; i < size; i++) {
			b.append(arr[i]);
			b.append(",");
		}

		if (b.length() != 1) {
			b.setLength(b.length() - 1);
		}
		b.append("]");

		return b.toString();
	}
}
