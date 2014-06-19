package no.mybank.dao;

import java.io.IOException;
import java.util.ArrayList;

import no.mybank.dto.AccountInfo;
import no.mybank.exception.ESException;
import no.mybank.integration.ESConnection;

import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {
	private static Logger log = Logger.getLogger(AccountDaoImpl.class);
	private final String indexName = "account_info";
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void createAccount(AccountInfo accountInfo) throws ESException {
		try {
			Client client = ESConnection.getSingletonObject().getClient();
			
			String json = mapper.writeValueAsString(accountInfo);
			
			client.prepareIndex("mybank_v1", indexName)
			        .setSource(json)
			        .execute()
			        .actionGet();
		
			log.debug("Successfully inserted account with nr: " + accountInfo.getAccountNumber() +" to Elasticsearch");
		} catch (IOException e) {
			log.error(e);
			throw new ESException(e.getMessage());
		} catch (ESException e) {
			log.error(e);
			throw e;
		}
	}

	@Override
	public void createAccounts(ArrayList<AccountInfo> accountInfos) throws ESException {
		log.debug("createAccounts() - entered");
		String json = null;
		try {
			Client client = ESConnection.getSingletonObject().getClient();
			
			for (AccountInfo accountInfo : accountInfos) {
				json = mapper.writeValueAsString(accountInfo);
				
				client.prepareIndex("mybank_v1", indexName)
				        .setSource(json)
				        .execute()
				        .actionGet();
			
				log.debug("Successfully inserted account with nr: " + accountInfo.getAccountNumber() +" to Elasticsearch");
			}
		} catch (IOException e) {
			log.error(e);
			throw new ESException(e.getMessage());
		} catch (ESException e) {
			log.error(e);
			throw e;
		}
	}
	
	@Override
	public void updateAccount(AccountInfo accountInfo) throws ESException {
		log.debug("updateAccount() - entered");
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteAccount(AccountInfo accountInfo) throws ESException {
		log.debug("deleteAccount() - entered");
		// TODO Auto-generated method stub
	}
}
