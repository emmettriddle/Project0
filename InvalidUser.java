package dev.riddle.utilities;

public class InvalidUser extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidUser() {
		super("User not found");
	}

}
