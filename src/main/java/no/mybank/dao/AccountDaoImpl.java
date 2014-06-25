package no.mybank.dao;

import java.io.IOException;
import java.util.ArrayList;

import no.mybank.dto.AccountInfo;
import no.mybank.exception.ESException;
import no.mybank.integration.ESConnection;

import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {
	private static Logger log = Logger.getLogger(AccountDaoImpl.class);
	
	@Autowired
	private ESConnection esConnection;
	
	private final String TYPE = "account_info";
	private final String INDEX_NAME = "mybank_v1";
	
	private ObjectMapper mapper = new ObjectMapper();
	
	public void setEsConnection(ESConnection esConnection) {
		this.esConnection = esConnection;
	}
	
	@Override
	public void createAccount(AccountInfo accountInfo) throws ESException {
		try {
			Client client = esConnection.getClient();
			
			String json = mapper.writeValueAsString(accountInfo);
			
			client.prepareIndex(INDEX_NAME, TYPE)
			        .setSource(json)
			        .execute()
			        .actionGet();
		
			log.debug("Successfully inserted account with nr: " + accountInfo.getAccountNumber() +" to Elasticsearch");
		} catch (IOException e) {
			log.error(e);
			throw new ESException(e.getMessage());
		}
	}

	@Override
	public void createAccounts(ArrayList<AccountInfo> accountInfos) throws ESException {
		log.debug("createAccounts() - entered");
		String json = null;
		try {
			Client client = esConnection.getClient();
			
			for (AccountInfo accountInfo : accountInfos) {
				json = mapper.writeValueAsString(accountInfo);
				
				client.prepareIndex(INDEX_NAME, TYPE)
				        .setSource(json)
				        .execute()
				        .actionGet();
			
				log.info("Successfully inserted account with nr: " + accountInfo.getAccountNumber() +" to Elasticsearch");
			}
		} catch (IOException e) {
			log.error(e);
			throw new ESException(e.getMessage());
		} 
	}
	
	@Override
	public void updateAccount(AccountInfo accountInfo) throws ESException {
		log.debug("updateAccount() - entered");
	}

	@Override
	public void deleteAccount(AccountInfo accountInfo) throws ESException {
		log.debug("deleteAccount() - entered");
	}

	@Override
	public AccountInfo retrieveAccount(String accountNr) throws ESException {
		log.debug("retrieveAccount() - entered, accountNr=" + accountNr);
		AccountInfo accountInfo = null;
		try {
			Client client = esConnection.getClient();
		
			SearchResponse response = client.prepareSearch(INDEX_NAME)
			        .setTypes(TYPE)
			        .setQuery(QueryBuilders.termQuery("accountNumber", accountNr))            
			        .execute()
			        .actionGet();
			
			if (response == null) {
				log.debug("Account not found, accountNr: " + accountNr);
			} else {
				log.debug("Successfully retrieved account, accountNr: " + accountNr);
				java.util.Iterator<SearchHit> hit_it = response.getHits().iterator();
				while(hit_it.hasNext()){
					SearchHit hit = hit_it.next();
					accountInfo = mapper.readValue(hit.getSourceAsString().getBytes(), AccountInfo.class);
				}
			}
			return accountInfo;
		} catch (JsonParseException e) {
			log.error(e);
			throw new ESException(e.getMessage());
		} catch (JsonMappingException e) {
			log.error(e);
			throw new ESException(e.getMessage());
		} catch (IOException e) {
			log.error(e);
			throw new ESException(e.getMessage());
		}
	}
}
