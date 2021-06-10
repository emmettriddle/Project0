package dev.riddle.services;

import java.sql.*;
import java.util.*;

import dev.riddle.models.*;
import dev.riddle.utilities.*;
import dev.riddle.dao.*;
import dev.riddle.application.*;

public class CustomerServices implements UserServices, CustomerServicesImpl {

	private AccountDAO adi;
	private TransactionDAO ti;
	private UserDAO udi;

	public CustomerServices(UserDAO udi, AccountDAO adi, TransactionDAO ti) {
		super();
		this.adi = (AccountDAO) adi;
		this.ti = (TransactionDAO) ti;
		this.udi = (UserDAO) udi;
	}

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

	public void applyNewAcctWithBal(User t, double balance) {
		User user = udi.createCustomerAccount(t, balance);
		if (user != null) {
			System.out.println("Welcome, " + user.getFName() + " " + user.getLName());
			System.out.println("Thank you for registering new account! Your account is reviewed!");
		} else {
			System.out.println("Creation is not successful!");
		}

	}

	public boolean deposit(int bankId, Account existingAccount, double amount) {
		if(amount<0) {return false;}
		existingAccount.setBalance(existingAccount.getBalance()+amount);
		
		AppLogger.logger.info("Banking Account with Id: " + bankId + " just got deposit: " + amount + "$!");
		return adi.updateBal(existingAccount);

	}

	public boolean withdraw(int bankId, Account existingAccount, double amount) {
		if(amount<0 || amount > existingAccount.getBalance()) {return false;}
		existingAccount.setBalance(existingAccount.getBalance()-amount);		
		return adi.updateBal(existingAccount);
	}

	public boolean transferMoney(String email, int acctNum, Account existingAccount, double amount) {
//		Transaction t = new Transaction();
//		t.setSendId(acctNum);
//
//		if (existingAccount instanceof CheckingAccount) {
//			t.setSendAcctNum(((CheckingAccount) existingAccount).getAccountNumber());
//		} else if (existingAccount instanceof SavingsAccount) {
//			t.setSendAcctNum(((SavingsAccount) existingAccount).getAcctNum());
//		}
//		t.setReceiveId(acctNum);
//		t.setTransAmount(amount);
//		Transaction newTransaction = ti.save(t);
//		AppLogger.logger.info("User with Id: " + userId + "just send " + amount + "$!");
		return 
				false;
	}
	
	public boolean transferMoney(int sender,int receiver,double amount) {
		Transaction t = new Transaction();
		t.setSendId(sender);
		t.setTransfer(true);
		t.setDeposit(false);
		t.setWithdraw(false);	
		t.setReceiveId(receiver);		
		t.setTransAmount(amount);
		
		ti.save(t);
		
		return true;
		}

	public Boolean acceptMoneyTrans(Transaction transaction) {

		return ti.update(transaction);
	}

	public List<Transaction> findRepicient(int repicientId) {

		return ti.findRepicient(repicientId);

	}

	// Customer Login
	public User userLogIn(String email, String password, Boolean isEmployee)
			throws InvalidUser, InternalExceptions, SQLException {
		User user = udi.findOne(email, password, isEmployee);
		AppLogger.logger.info(user.getFName() + " " + user.getLName() + " logged in!");
		if (user != null) {
			// System.out.println("Welcome, "+ user.getFirstName()+" "+user.getLastName());
			return user;
		}

		return null;
	}

}
