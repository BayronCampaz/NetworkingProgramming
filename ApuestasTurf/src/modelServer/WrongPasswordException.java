package modelServer;

public class WrongPasswordException extends Exception {
	
	public WrongPasswordException() {
		super("Contrase�a incorrecta");
	}

}
