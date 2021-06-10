package dev.riddle.models;

public class Transaction {
	
	private int transId;	
	private int receiveId;
	private int sendId;
	private Boolean deposit;
	private Boolean withdraw;
	private Boolean transfer;
	private double transAmount;
	public int getTransId() {
		return transId;
	}
	public void setTransId(int transId) {
		this.transId = transId;
	}
	public int getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(int receiveId) {
		this.receiveId = receiveId;
	}

	public int getSendId() {
		return sendId;
	}
	public void setSendId(int sendId) {
		this.sendId = sendId;
	}
	
	public double getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(double transAmount) {
		this.transAmount = transAmount;
	}
	
	
	
	public Boolean getDeposit() {
		return deposit;
	}
	public void setDeposit(Boolean deposit) {
		this.deposit = deposit;
	}
	public Boolean getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(Boolean withdraw) {
		this.withdraw = withdraw;
	}
	public Boolean getTransfer() {
		return transfer;
	}
	public void setTransfer(Boolean transfer) {
		this.transfer = transfer;
	}
	@Override
	public String toString() {
		return "Transaction [transId=" + transId + ", receiveId=" + receiveId + ", sendId=" + sendId + ", deposit="
				+ deposit + ", withdraw=" + withdraw + ", transfer=" + transfer + ", transAmount=" + transAmount + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deposit == null) ? 0 : deposit.hashCode());
		result = prime * result + receiveId;
		result = prime * result + sendId;
		long temp;
		temp = Double.doubleToLongBits(transAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + transId;
		result = prime * result + ((transfer == null) ? 0 : transfer.hashCode());
		result = prime * result + ((withdraw == null) ? 0 : withdraw.hashCode());
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
		Transaction other = (Transaction) obj;
		if (deposit == null) {
			if (other.deposit != null)
				return false;
		} else if (!deposit.equals(other.deposit))
			return false;
		if (receiveId != other.receiveId)
			return false;
		if (sendId != other.sendId)
			return false;
		if (Double.doubleToLongBits(transAmount) != Double.doubleToLongBits(other.transAmount))
			return false;
		if (transId != other.transId)
			return false;
		if (transfer == null) {
			if (other.transfer != null)
				return false;
		} else if (!transfer.equals(other.transfer))
			return false;
		if (withdraw == null) {
			if (other.withdraw != null)
				return false;
		} else if (!withdraw.equals(other.withdraw))
			return false;
		return true;
	}
	
	
	

}
