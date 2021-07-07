package dev.riddle.application;

import dev.riddle.models.*;
import dev.riddle.services.*;
import dev.riddle.dao.*;
import dev.riddle.utilities.*;

import java.sql.*;
import java.util.*;

import dev.riddle.application.*;

public class UserMenu implements UserMenuImpl {

	private Connection conn = JDBCConnection.getConnection();

	private CustomerServices csi;
	private EmployeeServices esi;
	private TransactionDAO td = new TransactionDAO();
	private UserDAO ud = new UserDAO();
	private AccountDAO ad = new AccountDAO();

	Scanner userIn;

	public UserMenu(CustomerServices csi, EmployeeServices esi) {
		this.csi = csi;
		this.esi = esi;
		userIn = new Scanner(System.in);
	}

	public String display() {
		// TODO Auto-generated method stub
		return null;
	}

	// 0
	public void manageUserAccountInput() throws InvalidUser, InternalExceptions, SQLException, InputMismatchException {

		String email, password;
		int accountType;

		System.out.println("Your banking options:\n 1. New Customer \n 2. Existing Customer  \n 3. Employee \n");
		System.out.println("Please choose to start!");

		// this.userIn.nextLine();
		System.out.println("Your choice is: ");
		try {

			accountType = this.userIn.nextInt();
			if (accountType < 0 || accountType > 3) {
				System.out.println("Please make a selection 1, 2, or 3");
			}

			else {
				if (accountType == 1) {
					this.userIn.nextLine();
					// 1
					manageNewCustomer();
				}
				if (accountType == 2) {
					this.userIn.nextLine();
					System.out.println("Welcome back!\n ");
					System.out.println("Please enter your email: ");
					email = this.userIn.nextLine();
					System.out.println("Please enter your password: ");
					password = this.userIn.nextLine();
					User u = csi.userLogIn(email, password, false);
					if (!u.isEmployee()) {
						// Enter t menu
						// this.userIn.nextLine();
						manageCustomerAccount(u);
					}

				} else if (accountType == 3) {
					System.out.println("Welcome back!\n ");
					this.userIn.nextLine();
					System.out.println("Please enter your employee email: ");
					email = this.userIn.nextLine();
					System.out.println("Please enter your password: ");
					password = this.userIn.nextLine();
					User employee = esi.userLogIn(email, password, true);
					if (employee.isEmployee()) {

						manageEmployeeAccount();
					}
				}

			}

		} catch (InputMismatchException e) {
			System.out.println("Please make a selection!\n");
		} catch (InvalidUser u) {
			System.out.println("Wrong user or password! \n");
		}
	}

	// 1
	public void manageNewCustomer() {

		while (true) {
			String email, password, fName, lName;
			double initialDeposit;
			// boolean isEmployee = false;
			System.out.println("Choose the best option for you: \n");
			System.out.println("1. Apply for new Account\n" + "2. Return to main menu \n");
			System.out.println("Your choice is: ");
			int option = this.userIn.nextInt();
			if (option == 2)
				break;
			User u = new Customer();
			Account a = new Account();
			if (option == 1) {
				this.userIn.nextLine();
				// u.setUserId("id");
				System.out.println("Please enter your email: ");
				email = this.userIn.nextLine();
				u.setEmail(email);
				System.out.println("Please enter your password: ");
				password = this.userIn.nextLine();
				u.setPassword(password);
				System.out.println("Please enter your first name: ");
				fName = this.userIn.nextLine();
				u.setFName(fName);
				System.out.println("Please enter your last name: ");
				lName = this.userIn.nextLine();
				u.setLName(lName);
//				System.out.println("Please enter your initial deposit: ");
//				initialDeposit = this.userIn.nextDouble();
//				u.setInitialDeposit(initialDeposit);
				u = ud.createCustomerAccount(u);
				System.out.println(u);
			}

			// if (option == 1) {

			// this.csi.applyNewAcctWithBal(t, 0);

			else if (option == 2) {
				System.out.println("Please enter your first deposit: ");
				// double balance = this.userIn.nextDouble();
				// this.csi.applyNewAcctWithBal(t, balance);
			} else {
				this.userIn.nextLine();
				System.out.println("Please enter option 1 or 2! \n");
				System.out
						.println("Would you like to try again! \n " + "Type 'y' to continue 'n' to exit this menu:\n ");
				String type = this.userIn.nextLine();
				if (type.equals("n")) {
					break;
				}

			}
		}

		System.out.println("Please press enter to confirm to back to the main menu!");
		this.userIn.nextLine();
	}

	// 3
	public void manageEmployeeAccount() {
		while (true) {
			System.out.println("Please choose the option below!\n");
			System.out.println("1. View pending Customer account\n" + "2. View a Customer's bank account\n"
					+ "3. View a log of all transactions\n" + "4. Return to the main menu\n");

			System.out.println("Your choice is: ");
			int option = this.userIn.nextInt();
			if (option == 4)
				break;
			switch (option) {
			case 1:
				viewPendingAccount();
				break;
			case 2:
				viewCustomerBankAccount();
				break;
			case 3:
				viewAllTransactions();
				break;
			default: {
				this.userIn.nextLine();
				System.out.println("Please enter option 1, 2, or 3 ! \n");
				break;
			}

			}

//			System.out.println(" 1.Return to main menu\n 2. No\n ");
//			int type = this.userIn.nextInt();
//			if (type == 1) {
//				break;
//			}
		}

	}

	// 3.1
	public void viewPendingAccount() {

		List<Account> plist = ad.pendingAccounts();

		for (Account a : plist) {

			System.out.println(a);
			System.out.println("Would you like to aprove or deny this new account?\n" + "1.Aprove\n" + "2.Deny");
			int option = this.userIn.nextInt();
			this.userIn.nextLine();
			if (option == 1) {
				System.out.println("Account has been aproved");
				a.setAStatus("active");
				ad.updateBal(a);
			} else {
				System.out.println("Account has been denied");
				a.setAStatus("denied");
				ad.updateBal(a);
			}
		}

	}

	// 3.2
	public void viewCustomerBankAccount() {
		this.userIn.nextLine();
		System.out.println("Please enter the Customer's ID:\n");
		int userId = this.userIn.nextInt();
		User u = new Customer();
		u.setUserId(userId);
		List<User> list = esi.viewCustomerInfo(u);
		if (list.size() > 0)
			System.out.println(list);
		else {
			System.out.println("Customer is not found!\n");
		}
	}

	// 3.3
	public void viewLogOfTransaction() {

		List<Transaction> tlist = td.findAll();
		for (Transaction t : tlist) {
			System.out.println(tlist);
		}

	}

	// 2
	public void manageCustomerAccount(User user) {
		while (true) {
			List<User> list = csi.viewCustomerInfo(user);
			if (list == null) {
				System.out.println("Welcome " + user.getFName() + " " + user.getLName() + "!\n"
						+ "Your account is under review! Please wait!\n");
				System.out.println("Press enter to come back to the main menu!\n");
				this.userIn.nextLine();
				break;
			}

			System.out.println("Welcome " + user.getFName() + " " + user.getLName() + "!\n");

			System.out.println("Select the options below for your account:\n");
			System.out.println("1. View your account details\n" + "2. Deposit money to your account\n"
					+ "3. Withdraw money from your account\n" + "4. Send money to another account\n"
					+ "5. Apply for new Account\n" + "6. Return to the main menu\n");
			System.out.println("Your choice is: ");
			int option = this.userIn.nextInt();
			switch (option) {
			case 1:
				viewCustomerAccountDetail(user);
				break;
			case 2:
				depositMoney(user);
				break;
			case 3:
				withdrawMoney(user);
				break;
			case 4:
				sendMoney(user);
				break;
			case 5:
				applyNewAcctWithBal(user);
				break;
			default:
				break;
			}

			if (option == 6) {
				break;
			}

		}

	}

	// 2.1
	public void viewCustomerAccountDetail(User u) {
		Map<Integer, Account> accts = ad.getAllByCustomerId(u.getUserId());
		for (Account a : accts.values()) {
			System.out.println(a);
		}
	}

	// 2.2
	public void depositMoney(User u) {

		System.out.println("Please select your Account number:");

		Map<Integer, Account> accts = ad.getAllByCustomerId(u.getUserId());
		for (Account a : accts.values()) {
			System.out.println(a);
		}
		int option = this.userIn.nextInt();
		Account a = ad.findAcct(option);
		System.out.println("Please enter your amount:\n");

		double amount = this.userIn.nextDouble();
		if (amount < 0) {
			System.out.println("Your amount cannot deposit amount less than 0!\n");
		}

		this.userIn.nextLine();
		if (a != null) {
			if (!a.getAStatus().equals("active")) {
				System.out.println("Error account is not active.");
			} else if (csi.deposit(a.getBankId(), a, amount)) {
				System.out.println("You successfully deposited into your account!");
				Transaction t = new Transaction();
				t.setDeposit(true);
				t.setReceiveId(a.getBankId());
				t.setSendId(a.getBankId());
				t.setTransfer(false);
				t.setTransAmount(amount);
				t.setWithdraw(false);
				td.save(t);
			}
		} else {
			System.out.println("Please try again and enter a valid account number:\n");
		}
		System.out.println("Please enter to come bank the main menu!\n");
		this.userIn.nextLine();
	}

	// 2.3
	public double withdrawMoney(User u) {
		System.out.println("Please select your Account number:");

		Map<Integer, Account> accts = ad.getAllByCustomerId(u.getUserId());
		for (Account a : accts.values()) {
			System.out.println(a);
		}
		int option = this.userIn.nextInt();
		Account a = ad.findAcct(option);
		System.out.println("Please enter your amount:\n");

		double amount = this.userIn.nextDouble();
		if (amount > a.getBalance() || amount < 0) {
			System.out.println("Your amount cannot withdraw amount bigger than the Account balance or less than 0!\n");
		}

		this.userIn.nextLine();
		if (a != null) {
			if (!a.getAStatus().equals("active")) {
				System.out.println("Error account is not active.");
			} else if (csi.withdraw(a.getBankId(), a, amount)) {
				System.out.println("You successfully withdrew from your account!");
				Transaction t = new Transaction();
				t.setDeposit(false);
				t.setReceiveId(a.getBankId());
				t.setSendId(a.getBankId());
				t.setTransfer(false);
				t.setTransAmount(amount);
				t.setWithdraw(true);
				td.save(t);
				return amount;
			}
		} else {
			System.out.println("Please try again and enter a valid account number:");
		}
		System.out.println("Please enter to come bank the main menu!\n");
		this.userIn.nextLine();

		return 0;
	}

	// 2.5
	public void sendMoney(User u) {

		System.out.println("Please select the sending Account number:");

		Map<Integer, Account> accts = ad.getAllByCustomerId(u.getUserId());
		for (Account a : accts.values()) {
			System.out.println(a);
		}
		int option = this.userIn.nextInt();
		Account wa = ad.findAcct(option);
		System.out.println("Please select receiving Account number:");
		this.userIn.nextLine();
		option = this.userIn.nextInt();
		Account da = ad.findAcct(option);
		System.out.println("Please enter your amount:\n");

		double amount = this.userIn.nextDouble();
		this.userIn.nextLine();
		if (amount < 0) {
			System.out.println("Your amount cannot transfer an amount less than 0!\n");
		} else if (amount > wa.getBalance()) {
			System.out.println("You can not transfer an amount larger than the account balance!\n");
		} else {
			if (wa != null && da != null) {
				if (!wa.getAStatus().equals("active") && !da.getAStatus().equals("active")) {
					System.out.println("Error one of these accounts is not active.");
				} else {
					System.out.println("Please confirm your transfer!\n Y or N");
					if (this.userIn.nextLine().equalsIgnoreCase("Y")) {
						csi.withdraw(wa.getBankId(), wa, amount);
						csi.deposit(da.getBankId(), da, amount);
						Transaction t = new Transaction();
						t.setDeposit(false);
						t.setReceiveId(da.getBankId());
						t.setSendId(wa.getBankId());
						t.setTransfer(true);
						t.setTransAmount(amount);
						t.setWithdraw(false);
						td.save(t);
					}
				}
			}
		}

		System.out.println("Please enter to come bank the main menu!\n");
		this.userIn.nextLine();
	}

	public void viewAllTransactions() {

		List<Transaction> tlist = td.findAll();
		for (Transaction t : tlist) {
			System.out.println(t);
		}

	}

	public void applyNewAcctWithBal(User u) {

		System.out.println("Please enter your starting balance:\n");
		double amount = this.userIn.nextDouble();
		Account a = new Account();
		a.setAStatus("pending");
		a.setBalance(amount);
		a.setCustomerId(u.getUserId());
		a.setChecking(false);
		a.setSavings(false);
		ad.saveAcct(a);
		System.out.println("Your new account with starting balance of " + amount + " has been sent for aproval.");
	}

}
