package no.mybank.service;

import java.util.ArrayList;

import no.mybank.dao.AccountDao;
import no.mybank.dto.AccountInfo;
import no.mybank.exception.ESException;
import no.mybank.integration.ESConnection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("accountService")
public class AccountServiceImpl implements AccountService {
	private static Logger log = Logger.getLogger(AccountServiceImpl.class);
	
	private AccountDao accountDao;
	
	@Override
	public void createAccount(AccountInfo accountInfo) throws ESException {
		log.debug("createAccount() - entered");
		ESConnection.getSingletonObject().openConnection();
		accountDao.createAccount(accountInfo);
		ESConnection.getSingletonObject().closeConnection();
	}

	@Override
	public void createAccounts(ArrayList<AccountInfo> accountInfos) throws ESException {
		log.debug("createAccounts() - entered");
		ESConnection.getSingletonObject().openConnection();
		accountDao.createAccounts(accountInfos);
		ESConnection.getSingletonObject().closeConnection();
	}

	@Override
	public void updateAccount(AccountInfo accountInfo) throws ESException {
		log.debug("updateAccount() - entered");
		ESConnection.getSingletonObject().openConnection();
		accountDao.updateAccount(accountInfo);
		ESConnection.getSingletonObject().closeConnection();
	}

	@Override
	public void deleteAccount(AccountInfo accountInfo) throws ESException {
		log.debug("deleteAccount() - entered");
		ESConnection.getSingletonObject().openConnection();
		accountDao.deleteAccount(accountInfo);
		ESConnection.getSingletonObject().closeConnection();
	}

	@Autowired
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}	
}
