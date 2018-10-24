package main;
 /**
  * Bei UnknownNameException handelt es sich um eine RuntimeException, welche für einen Fehler durch einen unbekannten Namen steht.
  * @author Jonas, Dominik, Julia
  * V01.00B00
  */
public class UnknownNameException extends RuntimeException {
	
	private static final long serialVersionUID = 1896638113680402709L;

	public UnknownNameException(String name) {
		super("There is no data for name \"" + name + "\" in the current address book.");
	}

}
