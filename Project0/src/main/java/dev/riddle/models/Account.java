package dev.riddle.models;

public class Account {
	private Integer customerId;
	private Integer acctNum;
	private String AStatus;
	//private boolean pendingTransaction;
	private Double balance;
	private Boolean checking;
	private Boolean savings;

	public Boolean getChecking() {
		return checking;
	}

	public void setChecking(Boolean checking) {
		this.checking = checking;
	}

	public Boolean getSavings() {
		return savings;
	}

	public void setSavings(Boolean savings) {
		this.savings = savings;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public int getBankId() {
		return acctNum;
	}

	public void setBankId(int bankId) {
		this.acctNum = bankId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getAStatus() {
		return AStatus;
	}

	public void setAStatus(String AStatus) {
		this.AStatus = AStatus;
	}

	@Override
	public String toString() {
		return "Account [customerId=" + customerId + ", acctNum=" + acctNum + ", AStatus=" + AStatus + ", balance="
				+ balance + ", checking=" + checking + ", savings=" + savings + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((AStatus == null) ? 0 : AStatus.hashCode());
		result = prime * result + acctNum;
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((checking == null) ? 0 : checking.hashCode());
		result = prime * result + customerId;
		result = prime * result + ((AStatus == null) ? 0 : AStatus.hashCode());
		//result = prime * result + (pendingTransaction ? 1231 : 1237);
		result = prime * result + ((savings == null) ? 0 : savings.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (AStatus != other.AStatus)
			return false;
		if (acctNum != other.acctNum)
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (checking == null) {
			if (other.checking != null)
				return false;
		} else if (!checking.equals(other.checking))
			return false;
		if (customerId != other.customerId)
			return false;

		if (savings == null) {
			if (other.savings != null)
				return false;
		} else if (!savings.equals(other.savings))
			return false;
		return true;
	}

}
