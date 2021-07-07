package dev.riddle.services;

import java.sql.*;
import java.util.*;

import dev.riddle.models.User;
import dev.riddle.utilities.InternalExceptions;
import dev.riddle.utilities.InvalidUser;

public interface UserServices {
	
	public User userLogIn(String email,String password, Boolean isEmployee)
		      throws InvalidUser,InternalExceptions,SQLException;
		      
		      public List<User> viewCustomerInfo(User u);

}
