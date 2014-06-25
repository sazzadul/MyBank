package no.mybank.aop;

import java.lang.reflect.Method;

import no.mybank.integration.ESConnection;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;

public class ESCloseConnectionAdvice implements AfterReturningAdvice {
	private static Logger log = Logger.getLogger(ESCloseConnectionAdvice.class);
	private ESConnection esConnection;
	
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		log.debug("****SPRING AOP**** ESCloseConnectionAdvice : Executing after method return!");
		esConnection.closeConnection();
	}
	
	public void setEsConnection(ESConnection esConnection) {
		this.esConnection = esConnection;
	}
	
}
