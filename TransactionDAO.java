package dev.riddle.dao;

import java.sql.*;
import java.util.*;

import dev.riddle.models.*;
import dev.riddle.utilities.*;

public class TransactionDAO implements TransactionDAOImpl {

	private Connection conn = JDBCConnection.getConnection();

	public Transaction save(Transaction transaction) {

		try {
			// insert to transaction table
			String sql = "insert into transaction values(default,?,?,?,?,?,? ) returning * ;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBoolean(1, transaction.getDeposit());
			ps.setBoolean(2, transaction.getWithdraw());
			ps.setBoolean(3, transaction.getTransfer());
			ps.setDouble(4, transaction.getTransAmount());
			ps.setInt(5, transaction.getSendId());
			ps.setInt(6, transaction.getReceiveId());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				transaction.setTransId(rs.getInt("id"));
				return transaction;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return null;
	}
	
	public List<Transaction> findAll() {
		Connection conn = JDBCConnection.getConnection();
		List<Transaction> listTransactionInfo = new ArrayList<Transaction>();

		try {
			String sql = "select * from transactions;";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Transaction t = new Transaction();
				t.setTransId(rs.getInt("id"));
				t.setDeposit(rs.getBoolean("deposit"));
				t.setWithdraw(rs.getBoolean("withdraw"));
				t.setTransfer(rs.getBoolean("transfer"));
				t.setTransAmount(rs.getDouble("amount"));
				t.setSendId(rs.getInt("sender"));
				t.setReceiveId(rs.getInt("receiver"));

				
				listTransactionInfo.add(t);
				return listTransactionInfo;
			}
		}catch (SQLException e) {
				e.printStackTrace();
				
			}
		return null;
	}

	// accept the money transfer
	public Boolean update(Transaction transaction) {
//		Connection conn = JDBCConnection.getConnection();
//		try {
//			String sql = "update ;";
//			PreparedStatement acceptTransaction = conn.prepareStatement(sql);
//			acceptTransaction.setInt(1, transaction.getTransId());
//
//			acceptTransaction.executeUpdate();
//			return true;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return false;
	}

	// repicient can find their pending transaction
	public List<Transaction> findRepicient(int userId) {
//		Connection conn = JDBCConnection.getConnection();
//		List<Transaction> list = new ArrayList<Transaction>();
//
//		try {
//
//			String sql = "select ;";
//			PreparedStatement getTransaction = conn.prepareStatement(sql);
//			getTransaction.setInt(1, userId);
//
//			ResultSet res = getTransaction.executeQuery();
//
//			while (res.next()) {
//				Transaction transaction = new Transaction();
//				transaction.setTransId(res.getInt("id"));
//				transaction.setSendId(res.getInt("sender"));
//				transaction.setReceiveId(res.getInt("receiver"));
//				transaction.setTransAmount(res.getDouble("amount"));
//				transaction.setDeposit(res.getBoolean("deposit"));
//				transaction.setWithdraw(res.getBoolean("withdraw"));
//				transaction.setTransfer(res.getBoolean("transfer"));
//				list.add(transaction);
//
//			}
//
//			return list;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				((JDBCConnection) conn).releaseConnection(conn);
//			} catch (SQLException e) {
//
//				e.printStackTrace();
//			}
//		}

		return null;
	}

}
