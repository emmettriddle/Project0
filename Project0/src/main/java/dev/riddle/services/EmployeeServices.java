package dev.riddle.services;

import java.sql.*;
import java.util.*;

import dev.riddle.models.*;
import dev.riddle.utilities.*;
import dev.riddle.dao.*;
import dev.riddle.application.*;

public class EmployeeServices {

	// private UserImplementationDAO uid;
	private UserDAO udi;

	public EmployeeServices(UserDAO udi) {
		this.udi = udi;
	}

	//
	public User userLogIn(String email, String password, boolean isEmployee)
			throws InvalidUser, InternalExceptions, SQLException {
		User user = udi.findOne(email, password, isEmployee);
		if (user != null) {
			System.out.println("Welcome, " + user.getFName() + " " + user.getLName());
			return user;
		} else {
			System.out.println(" User is not found");
		}
		return null;

	}

	public boolean setCustomerStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	public void viewCustomerAccount(User t) {
		// TODO Auto-generated method stub

	}

	public List<User> viewListPendingUser() throws InternalExceptions, SQLException {
		List<User> list = udi.findPendingCustomer();
		if (list.size() != 0) {
			return list;
		}

		return null;
	}

	public boolean approveCustomer(User user) {

		

		return false;
	}

	public boolean rejectCustomer(User user) {
		return false;
	}

	

	// Employee can view t account using their email
	public List<User> viewCustomerInfo(User t) {
		List<User> listInfo;
		try {
			listInfo = udi.findCustomerById(t);
			if (listInfo.size() != 0) {
				return listInfo;
			}
		} catch (InternalExceptions e) {
			e.printStackTrace();
			System.out.println(e);
		}

		return null;
	}
}
