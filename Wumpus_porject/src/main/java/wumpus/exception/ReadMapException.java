package wumpus.exception;

public class ReadMapException extends RuntimeException {

	private static final long serialVersionUID = -4785811020819055351L;

	public ReadMapException() {
		super("Nem sikerült betölteni a pályát!");
	}
	
	public ReadMapException(String message) {
		super(message);
	}
	
}
