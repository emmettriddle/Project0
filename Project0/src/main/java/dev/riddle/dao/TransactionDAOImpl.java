package dev.riddle.dao;

import java.util.List;

import dev.riddle.models.Transaction;

public interface TransactionDAOImpl {

	public Transaction save(Transaction transaction);

	public Boolean update(Transaction transaction);

	public List<Transaction> findRepicient(int userId);

}
