package dev.riddle.dao;

import java.util.*;
import java.sql.*;

import dev.riddle.application.*;
import dev.riddle.dao.*;
import dev.riddle.models.*;
import dev.riddle.services.*;
import dev.riddle.utilities.*;

public class UserDAO {

//private Connection conn = JDBCConnection.getConnection();

	// create new t's account
	public User createCustomerAccount(User user, Double balance) {
		// TODO Auto-generated method stub
		Connection conn = JDBCConnection.getConnection();
		try {

			conn.setAutoCommit(false);
			String sql = "insert into users (default,?,?,?,?,false);";

			PreparedStatement createCustomerAccount = conn.prepareStatement(sql);
			createCustomerAccount.setString(2, user.getFName());
			createCustomerAccount.setString(3, user.getLName());
			createCustomerAccount.setString(4, user.getEmail());
			createCustomerAccount.setString(5, user.getPassword());

			ResultSet res = createCustomerAccount.executeQuery();

			int newCustomerId;
			if (res.next()) {
				newCustomerId = res.getInt("id");

			} else {
				throw new SQLException();
			}

			// do st with banking account
			String bankingSql = "insert into ;";

			PreparedStatement applyNewBanking = conn.prepareStatement(bankingSql);
			applyNewBanking.setInt(1, newCustomerId);
			applyNewBanking.setDouble(2, balance);

			applyNewBanking.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {

			try {
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}

//			 try {
//				((JDBCConnection) conn).releaseConnection(conn);
//			} catch (SQLException e) {
//				
//				e.printStackTrace();
//			}
		}

		return user;
	}

	public boolean accept(Account account) {
		Connection conn = JDBCConnection.getConnection();
		try {
			
			String sql = "update account set status = 'accepted' where id + ?  ;";
			PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setString(1, account.getAStatus());
			ps.setInt(1, account.getBankId());

			//ResultSet rs = ps.executeQuery();
			return ps.execute();
			

			// update user and account table
//			String userSql = "update ;";
//			PreparedStatement updateUser = conn.prepareStatement(userSql);
//			updateUser.setInt(1, user.getUserId());
//			updateUser.executeUpdate();
//
//			String bankAccountSql = "update ;";
//			PreparedStatement updateBankingAccount = conn.prepareStatement(bankAccountSql);
//			updateBankingAccount.setInt(1, bankId);
//			updateBankingAccount.executeUpdate();
//
//			// insert checking account
//			String insertCheckingAccountSql = "insert into ;";
//			PreparedStatement insertCheckingAccount = conn.prepareStatement(insertCheckingAccountSql);
//			insertCheckingAccount.setInt(1, bankId);
//			insertCheckingAccount.setString(2, ca.getAccountNumber());
//			insertCheckingAccount.setDouble(3, ca.getBalance());
//			insertCheckingAccount.execute();
//			// insert savings account
//			String insertSavingAccountSql = "insert into ;";
//			PreparedStatement insertSavingAccount = conn.prepareStatement(insertSavingAccountSql);
//			insertSavingAccount.setInt(1, bankId);
//			insertSavingAccount.setString(2, sa.getAcctNum());
//			insertSavingAccount.setDouble(3, sa.getBal());
//			insertSavingAccount.execute();
//			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return false;
	}

	public boolean deny(Account account) {
		Connection conn = JDBCConnection.getConnection();
		try {
			String sql = "update account set status = 'denied' where id + ?  ;";
			PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setString(1, account.getAStatus());
			ps.setInt(1, account.getBankId());

			//ResultSet rs = ps.executeQuery();
			return ps.execute();
			// update user and account table
			

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return false;
	}

	public List<User> findPendingCustomer() throws InternalExceptions, SQLException {
		Connection conn = JDBCConnection.getConnection();
		List<User> listPendingUser = new ArrayList<User>();

		try {
			String sql = "select * from custome where status = 'pending' ;";
			PreparedStatement getPendingUser = conn.prepareStatement(sql);
			ResultSet res = getPendingUser.executeQuery();

			while (res.next()) {
				User u = new Customer();
				u.setUserId(res.getInt("id"));
				u.setFName(res.getString("fname"));
				u.setLName(res.getString("lname"));
				u.setEmail(res.getString("email"));
				u.setPassword(res.getString("password"));
				u.setEmployee(res.getBoolean("isEmployee"));
				u.setInitialDeposit(res.getDouble("initial_deposit"));

				listPendingUser.add(u);
				
			}
			return listPendingUser;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new InternalExceptions();

		}
	}

	public User findOne(String email, String password, boolean isEmployee)
			throws InvalidUser, InternalExceptions, SQLException {

		Connection conn = JDBCConnection.getConnection();

		String sql = "select * from users where email = ? and \"password\" = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Customer c = new Customer();
                c.setUserId(rs.getInt("id"));
                c.setFName(rs.getString("fname"));
                c.setLName(rs.getString("lname"));
                c.setEmail(rs.getString("email"));
                c.setPassword(rs.getString("password"));
                c.setEmployee(rs.getBoolean("isEmployee"));
                //c.setAccounts(AccountDAO.getInstance().getAllByCustomerId(c.getId()));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
		
//		try {
//			String sql = "select * from users where email = ? and password = ?;";
//			PreparedStatement getUser = conn.prepareStatement(sql);
//
//			getUser.setString(1, email);
//			getUser.setString(2, password);
//
//			ResultSet res = getUser.executeQuery();
//
//			if (res.next()) {
//				User u;
//				if (!isEmployee) {
//					u = new Customer();
//				} else {
//					u = new Employee();
//				}
//				u.setUserId(res.getInt("id"));
//				u.setFName(res.getString("fname"));
//				u.setLName(res.getString("lname"));
//				u.setEmail(res.getString("email"));
//				u.setPassword(res.getString("password"));
//				u.setEmployee(isEmployee);
//
//				return u;
//			} else {
//				throw new InvalidUser();
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new InternalExceptions();
//		} // finally {
//			((JDBCConnection) conn).releaseConnection(conn);
//			
//		}
		//return null;
	}

	public List<User> findAll() {
		Connection conn = JDBCConnection.getConnection();
		List<User> listCustomerInfo = new ArrayList<User>();

		try {
			String sql = "select * from users;";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				User t = new Customer();
				t.setUserId(rs.getInt("id"));
				t.setFName(rs.getString("fname"));
				t.setLName(rs.getString("lname"));
				t.setEmail(rs.getString("email"));
				t.setPassword(rs.getString("password"));
				t.setEmployee(rs.getBoolean("isEmployee"));
				
				listCustomerInfo.add(t);
				return listCustomerInfo;
			}
		}catch (SQLException e) {
				e.printStackTrace();
				
			}
		return null;
	}

	public List<User> findCustomerById(User user) throws InternalExceptions {
		Connection conn = JDBCConnection.getConnection();
		List<User> listCustomerInfo = new ArrayList<User>();

		try {
			String sql = "select * from users where id = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, user.getUserId());

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				User t = new Customer();
				t.setUserId(rs.getInt("id"));
				t.setFName(rs.getString("fname"));
				t.setLName(rs.getString("lname"));
				t.setEmail(rs.getString("email"));
				t.setPassword(rs.getString("password"));
				t.setEmployee(rs.getBoolean("isEmployee"));
				
				listCustomerInfo.add(t);
				return listCustomerInfo;
			}

//			while (rs.next()) {
//				User t = new Customer();
//				Account account = new Account();
//				
//				
////				CheckingAccount checkingAccount = new CheckingAccount();
////				SavingsAccount savingsAccount = new SavingsAccount();
////				t.setUserId(res.getInt("id"));
////				t.setFName(res.getString("fname"));
////				t.setLName(res.getString("lname"));
////				t.setEmail(res.getString("email"));
////
////				listCustomerInfo.add(t);
////				bankingAccount.setBankId(res.getInt("id"));
////				bankingAccount.setCustomerId(res.getInt("id"));
////				bankingAccount.setBankingStatus(res.getString("banking_status"));
////				bankingAccount.setPendingTransaction(res.getBoolean("pending_transaction"));
////
////				listCustomerInfo.add(bankingAccount);
////				checkingAccount.setAcctNum(res.getString("ca_account_number"));
////				checkingAccount.setBal(res.getDouble("ca_balance"));
////
////				listCustomerInfo.add(checkingAccount);
////				savingsAccount.setAcctNum(res.getString("sa_account_number"));
////				savingsAccount.setBal(res.getDouble("sa_balance"));
////				listCustomerInfo.add(savingsAccount);
////
//			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new InternalExceptions();

		} 

		return listCustomerInfo;
	}

}
