package dev.riddle.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.riddle.models.Account;
import dev.riddle.utilities.JDBCConnection;

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
				a.setSavings(rs.getBoolean("saving"));
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
	
	public Map<Integer, Account> getAllByCustomerId(Integer customer_id) {
        String sql = "select * from account where userid = ?;";
        try {
            Map<Integer, Account> map = new HashMap<Integer, Account>();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customer_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setBankId(rs.getInt("id"));
                a.setSavings(rs.getBoolean("saving"));
                a.setChecking(rs.getBoolean("checking"));
                a.setBalance(rs.getDouble("balance"));
                a.setCustomerId(rs.getInt("userid"));
                a.setAStatus(rs.getString("status"));
                map.put(a.getBankId(), a);
            }
            
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

	public Boolean updateBal(Account account) {
		//Connection conn = JDBCConnection.getConnection();
		
		String sql = "update account set balance = ?, status = ? where id = ? returning *;";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, account.getBalance());
			ps.setString(2, account.getAStatus());
			ps.setInt(3, account.getBankId());
			return ps.execute();

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
	
	public List<Account> pendingAccounts() {
		
		String sql = "select * from account where status = 'pending';";
        try {
            List<Account> list = new ArrayList<Account>();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setBankId(rs.getInt("id"));
                a.setBalance(rs.getDouble("balance"));
                a.setCustomerId(rs.getInt("userid"));
                a.setAStatus(rs.getString("status"));
                a.setChecking(false);
                a.setSavings(false);
                list.add(a);
            }
            
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
		
	}

}
