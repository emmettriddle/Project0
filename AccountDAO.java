package dev.riddle.dao;

import java.sql.*;

import dev.riddle.models.*;
import dev.riddle.utilities.*;

public class AccountDAO implements AccountDAOImpl {

	private Connection conn = JDBCConnection.getConnection();

	public Account saveAcct(Account newAccount) {
		String sql = "insert into account values(default,?,?,?,?,?) returning *;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBoolean(1, newAccount.getSavings());
			ps.setBoolean(2, newAccount.getChecking());
			ps.setDouble(3, newAccount.getBalance());
			ps.setInt(4, newAccount.getCustomerId());
			ps.setString(5, newAccount.getAStatus());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				newAccount.setBankId(rs.getInt("id"));
				return newAccount;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

	public Account findAcct(int getById) {
		String sql = "select * from account where id =?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, getById);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Account a = new Account();
				a.setBankId(rs.getInt("id"));
				a.setSavings(rs.getBoolean("savings"));
				a.setChecking(rs.getBoolean("checking"));
				a.setBalance(rs.getDouble("balance"));
				a.setCustomerId(rs.getInt("userid"));
				a.setAStatus(rs.getString("status"));
				
				return a;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Boolean updateBal(Account account) {
		//Connection conn = JDBCConnection.getConnection();
		
		String sql = "update account set balance = ?, status = ? where id = ? returning *;";
//		Double newBalance = 0;

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, account.getBalance());
			ps.setString(2, account.getAStatus());
			ps.setInt(3, account.getBankId());
			return ps.execute();
			
//			if (account.getChecking()) {
//				sql = "update account where checking = true;";
//				ca_account_number = ((CheckingAccount) existingAccount).getAccountNumber();
//				newBalance = ((CheckingAccount) existingAccount).getBalance();
//
//			} else if (existingAccount instanceof SavingsAccount) {
//				sql = "update account where savings = true;";
//				sa_account_number = ((SavingsAccount) existingAccount).getAcctNum();
//				newBalance = ((SavingsAccount) existingAccount).getBal();
//			}

//			if (ca_account_number != null) {
//				PreparedStatement updateBalance = conn.prepareStatement(sql);
//				updateBalance.setDouble(1, newBalance);
//				updateBalance.setString(2, ca_account_number);
//				updateBalance.setInt(3, bankId);
//				updateBalance.execute();
//
//				return true;
//			} else if (sa_account_number != null) {
//				PreparedStatement updateBalance = conn.prepareStatement(sql);
//				updateBalance.setDouble(1, newBalance);
//				updateBalance.setString(2, a.getAcctNum);
//				updateBalance.setInt(3, bankId);
//				updateBalance.execute();
//
//				return true;
//			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return false;
	}

	@Override
	public Boolean delete(Account account) {

		String sql = "call deleteAcct(?);";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, account.getBankId());
			return cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
