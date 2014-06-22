package no.mybank.dto;

import java.io.Serializable;
import java.util.Date;

public class AccountInfo implements Serializable {

	private static final long serialVersionUID = 4101071116377801276L;

	private String accountNumber;
	private String accountOwner;
	private String accountType;
	private String currencyType;
	private String status;
	private double balance;
	private Date created;
	private Date lastupdated;
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountOwner() {
		return accountOwner;
	}
	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getLastupdated() {
		return lastupdated;
	}
	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "AccountInfo [accountNumber=" + accountNumber
				+ ", accountOwner=" + accountOwner + ", accountType="
				+ accountType + ", currencyType=" + currencyType + ", status="
				+ status + ", balance=" + balance + ", created=" + created
				+ ", lastupdated=" + lastupdated + "]";
	}
}
