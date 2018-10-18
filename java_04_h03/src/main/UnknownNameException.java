package main;

public class UnknownNameException extends RuntimeException {
	
	private static final long serialVersionUID = 1896638113680402709L;

	public UnknownNameException(String name) {
		super("There is no data for name \"" + name + "\" in the current address book.");
	}

}