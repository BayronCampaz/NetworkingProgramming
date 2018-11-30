package modelServer;

public class NotExistUserException extends Exception{
	
	public NotExistUserException() {
		super("el usuario no esta registrado");
	}

}
