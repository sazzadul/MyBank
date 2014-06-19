package no.mybank.service;

import java.util.ArrayList;

import no.mybank.dto.AccountInfo;
import no.mybank.exception.ESException;

public interface AccountService {

public void createAccount(AccountInfo accountInfo) throws ESException;
	
	public void createAccounts(ArrayList<AccountInfo> accountInfos) throws ESException;
	
	public void updateAccount(AccountInfo accountInfo) throws ESException;
	
	public void deleteAccount(AccountInfo accountInfo) throws ESException;
}
