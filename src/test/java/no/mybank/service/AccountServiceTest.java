package no.mybank.service;

import java.util.ArrayList;
import java.util.Date;

import no.mybank.dto.AccountInfo;
import no.mybank.exception.ESException;
import no.mybank.util.AccountNumberGenerator;
import no.mybank.util.AccountTypeGenerator;
import no.mybank.util.BalanceGenerator;
import no.mybank.util.CurrencyTypeGenerator;
import no.mybank.util.NameGenerator;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class AccountServiceTest {
	private final static int NUM_OF_ACCOUNTS = 5;

	@Autowired
	private AccountService service;
	
	@Test
	public void retrieveAccount() {
		String accountNr = "4817.39.89917";
		try {
			AccountInfo accountInfo = service.retrieveAccount(accountNr);
		} catch (ESException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void createAccounts() {
		ArrayList<AccountInfo> accountInfos = new ArrayList<AccountInfo>();
		AccountInfo accountInfo = new AccountInfo();
		
		for (int i = 0; i < NUM_OF_ACCOUNTS; i++) {
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
		try {
			service.createAccounts(accountInfos);
		} catch (ESException e) {
			e.printStackTrace();
		}
	}
}
