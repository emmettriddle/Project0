package dev.riddle.dao;

import java.sql.*;
import java.util.*;

import dev.riddle.models.*;
import dev.riddle.utilities.*;

public interface UserDAOImpl {

	public User createCustomerAccount(User user, Double balance);

	public User findOne(String email, String password, Boolean isCustomer)
			throws InvalidUser, InternalExceptions, SQLException;

	public List<User> findPendingCustomer() throws InternalExceptions, SQLException;

	public List<User> findAll();

	public Boolean accept(User user, CheckingAccount ca, SavingsAccount sa);

	public Boolean deny(User user);

	List<Object> findCustomerById(User user) throws InternalExceptions;

}
