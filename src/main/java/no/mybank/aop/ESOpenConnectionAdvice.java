package no.mybank.aop;

import java.lang.reflect.Method;

import no.mybank.integration.ESConnection;

import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Autowired;

public class ESOpenConnectionAdvice implements MethodBeforeAdvice {
	private static Logger log = Logger.getLogger(ESOpenConnectionAdvice.class);
	
	@Autowired
	private ESConnection esConnection;
	
	public void before(Method method, Object[] args, Object target) throws Throwable {
		esConnection.openConnection();
		log.debug("****SPRING AOP**** ESOpenConnectionAdvice : Executing before method!");
	}

	public void setEsConnection(ESConnection esConnection) {
		this.esConnection = esConnection;
	}

}
