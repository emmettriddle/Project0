package dev.riddle.services;

import dev.riddle.models.Transaction;
import dev.riddle.models.User;

public interface CustomerServicesImpl {

	public void applyNewAcctWithBal(User t, double bal);

	public Boolean acceptMoneyTrans(Transaction transaction);

}
