package no.mybank.service;

import java.util.ArrayList;

import no.mybank.dao.AccountDao;
import no.mybank.dto.AccountInfo;
import no.mybank.exception.ESException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceImpl implements AccountService {
	private static Logger log = Logger.getLogger(AccountServiceImpl.class);
	
	private AccountDao accountDao;
	
	@Override
	public void createAccount(AccountInfo accountInfo) throws ESException {
		log.debug("createAccount() - entered");
		accountDao.createAccount(accountInfo);
	}

	@Override
	public void createAccounts(ArrayList<AccountInfo> accountInfos) throws ESException {
		log.debug("createAccounts() - entered");
		accountDao.createAccounts(accountInfos);
	}

	@Override
	public void updateAccount(AccountInfo accountInfo) throws ESException {
		log.debug("updateAccount() - entered");
		accountDao.updateAccount(accountInfo);
	}

	@Override
	public void deleteAccount(AccountInfo accountInfo) throws ESException {
		log.debug("deleteAccount() - entered");
		accountDao.deleteAccount(accountInfo);
	}

	@Autowired
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public String retrieveAccount(String accountNr) throws ESException {
		log.debug("retrieveAccount() - entered");
		return accountDao.retrieveAccount(accountNr);
	}	
}
