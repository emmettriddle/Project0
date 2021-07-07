package dev.riddle.daotest;

import org.junit.*;

import dev.riddle.dao.AccountDAO;
import dev.riddle.models.Account;

public class JunitTestCase {
	
	@Test
	public void deleteTest() {
		
		AccountDAO adi = new AccountDAO();
		
		Account account = new Account();
		account.setBankId(300);
		Boolean result = adi.delete(account);
		Assert.assertEquals(false, result);
		
	}

}
