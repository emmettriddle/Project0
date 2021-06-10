package dev.riddle.models;

public class Employee extends User {

	public Employee(String email, String password, String fName, String lName) {
		super(email, password, fName, lName);
		super.setCustomer(false);
	}

	public Employee() {
		super.setCustomer(false);
	}

}
