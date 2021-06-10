package dev.riddle.dao;

import dev.riddle.models.Account;
import dev.riddle.models.User;

public interface AccountDAOImpl {

	public Account saveAcct(Account newAccount);

	public Account findAcct(int userId);

	public Boolean updateBal(Account account);
	
	public Boolean delete(Account account);

	//public void updateAcct(Account account);

}
