package dev.riddle.application;

import java.sql.*;
import java.util.*;

import org.apache.logging.log4j.*;

import dev.riddle.dao.*;
import dev.riddle.utilities.AppLogger;
import dev.riddle.utilities.*;
import dev.riddle.services.*;

public class Main {

	public static void main(String[] args)
			throws InvalidUser, InternalExceptions, SQLException, InputMismatchException {

		UserDAO uid = new UserDAO();
		AccountDAO ad = new AccountDAO();
		TransactionDAO ti = new TransactionDAO();
		CustomerServices csi = new CustomerServices(uid, ad, ti);
		EmployeeServices esi = new EmployeeServices(uid);
		UserMenu userMenu = new UserMenu(csi, esi);

		AppLogger.logger.info("BankApp has Started!");
		System.out.println("Welcome to the Bank of Revature:\n");
		while (true) {

			userMenu.manageUserAccountInput();
			System.out.println("\n");
			
		}
		//for()
	}

}
