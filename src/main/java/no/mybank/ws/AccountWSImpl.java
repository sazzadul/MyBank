package no.mybank.ws;

import java.util.ArrayList;
import java.util.Date;

import no.mybank.dto.AccountInfo;
import no.mybank.exception.ESException;
import no.mybank.service.AccountService;
import no.mybank.util.AccountNumberGenerator;
import no.mybank.util.AccountTypeGenerator;
import no.mybank.util.BalanceGenerator;
import no.mybank.util.CurrencyTypeGenerator;
import no.mybank.util.NameGenerator;
import no.mybank.util.StopWatch;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class AccountWSImpl implements AccountWS {
	private static Logger log = Logger.getLogger(AccountWSImpl.class);
	
	@Autowired
	private AccountService accountService;

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public String createAccounts(int numberofaccounts) {
		log.debug("createAccounts() - entered, numberofaccounts = " + numberofaccounts);
		if (numberofaccounts <= 0) {
			return "You must specify number of accounts to be created";
		}
		String msg = null;
		
		try {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			accountService.createAccounts(createAccountsList(numberofaccounts));
			stopWatch.stop();
			msg = "Successfully created '" + numberofaccounts + "' Accounts in '" + stopWatch.getElapsedTimeSecs() + "' seconds";
		} catch (ESException e) {
			msg = "Could not create accounts";
			log.error(msg, e);
		}
		return msg;
	}

	private ArrayList<AccountInfo> createAccountsList(int numberofaccounts) {
		ArrayList<AccountInfo> accountInfos = new ArrayList<AccountInfo>();
		AccountInfo accountInfo = new AccountInfo();
		
		for (int i = 0; i < numberofaccounts; i++) {
			accountInfo = new AccountInfo();
			accountInfo.setAccountNumber(AccountNumberGenerator.getAccountNumber());
			accountInfo.setAccountOwner(new NameGenerator().getName());
			accountInfo.setAccountType(AccountTypeGenerator.getAccountType());
			accountInfo.setBalance(BalanceGenerator.getBalance());
			accountInfo.setCreated(new Date());
			accountInfo.setCurrencyType(CurrencyTypeGenerator.getCurrencyType());
			accountInfo.setLastupdated(new Date());
			accountInfo.setStatus("Active");
			accountInfos.add(accountInfo);
		}
		return accountInfos;
	}
	
	@Override
	public String ping() {
		log.debug("ping() - entered");
		return "pong";
	}

	@Override
	public String retrieveAccount(String accountNr) {
		log.debug("retrieveAccount() - entered, accountNr = " + accountNr);
		String jsonString = null;
		String msg = null;
		
		try {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			jsonString = accountService.retrieveAccount(accountNr);
			stopWatch.stop();
		} catch (ESException e) {
			msg = "Could not create accounts";
			log.error(msg, e);
		}
		if (jsonString == null) {
			return "Account not found";
		} else {
			JsonParser parser = new JsonParser();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			JsonElement el = parser.parse(jsonString);
			jsonString = gson.toJson(el);
			return jsonString;
		}
	}

}
