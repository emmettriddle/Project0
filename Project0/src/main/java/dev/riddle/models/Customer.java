package dev.riddle.models;

import dev.riddle.models.*;

public class Customer extends User {

	//private CStatus cStatus;

	public Customer(String email, String password, String fName, String lName) {
		super(email, password, fName, lName);
		super.setCustomer(true);

	}

	public Customer() {
		super.setCustomer(true);
	}

//	public Customer(String cStatus) {
//		super.setCustomer(true);
//		for (CStatus cs : CStatus.values()) {
//			if (cs.toString().equals(cStatus)) {
//				this.cStatus = cs;
//			}
//		}
//	}

//	public String getCustomerStatus() {
//		return cStatus.toString();
//	}

//	public void setCustomerStatus(String cStatus) {
//
//		for (CStatus cs : CStatus.values()) {
//			if (cs.toString().equals(cStatus)) {
//				this.cStatus = cs;
//			}
//		}
//
//	}

}
