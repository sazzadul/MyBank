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
	private String CLUSTER_NAME;
	private String HOST_NAME;
	private int PORT;
	
	public void setCLUSTER_NAME(String cLUSTER_NAME) {
		CLUSTER_NAME = cLUSTER_NAME;
	}

    public void setHOST_NAME(String hOST_NAME) {
		HOST_NAME = hOST_NAME;
	}

	public void setPORT(int pORT) {
		PORT = pORT;
	}

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
