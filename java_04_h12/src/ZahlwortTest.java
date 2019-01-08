import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ZahlwortTest {

	@Test
	public void test01_innerValues() {
		int[] testtabelle = {1, 10, 11, 12, 16, 17, 20, 38, 69, 70, 131, 195, 235};
		String[] erwartungswerte = {"eins", "zehn", "elf", "zwoelf", "sechzehn", "siebzehn", "zwanzig", "achtunddreissig", "neunundsechzig",
				"siebzig", "einhunderteinunddreissig", "einhundertfuenfundneunzig", "zweihundertfuenfunddreissig"};
		for(int i = 0; i < testtabelle.length; i++) {
			assertEquals(erwartungswerte[i], Zahlwort.getZahlwort(testtabelle[i]));
		}
	}

	@Test(expected=ArithmeticException.class)
	public void test02_lowerBorder() {
		Zahlwort.getZahlwort(0);
	}

	@Test(expected=ArithmeticException.class)
	public void test03_upperBorder() {
		Zahlwort.getZahlwort(10000);
	}

	@Test
	public void test04_stream() {
		assertArrayEquals(new String[] {"acht", "neun", "zehn", "elf", "zwoelf"}, Zahlwort.getZahlStream(8, 12).toArray(String[]::new));
	}

}
