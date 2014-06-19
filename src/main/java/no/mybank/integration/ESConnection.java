package no.mybank.integration;

import no.mybank.exception.ESException;

import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class ESConnection {
	private static Logger log = Logger.getLogger(ESConnection.class);
	private Client client;
	private static ESConnection singletonObject;
	private final String CLUSTER_NAME = "mycluster";
	private final String HOST_NAME = "localhost";
	private final int PORT = 9300;
	
	public void openConnection() throws ESException {
		try {
			Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", CLUSTER_NAME).build();
			client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress(HOST_NAME, PORT));
			if (client == null) {
				throw new ESException("Could not establish connection to Elasticsearch"); 
			}
		} catch (Exception e) {
			log.error("Could not establish connection to Elasticsearch");
			throw new ESException(e.getMessage());
		}
	}
	
	public static synchronized ESConnection getSingletonObject() throws ESException {
		if (singletonObject == null) {
			singletonObject = new ESConnection();
		}
		return singletonObject;
	}
	
	public Client getClient() {
		return this.client;
	}
	
	public void closeConnection() throws ESException {
		try {
			this.client.close();
		} catch (Exception e) {
			log.error("Could not close connection to Elasticsearch");
			throw new ESException(e.getMessage());
		}
	}
}
