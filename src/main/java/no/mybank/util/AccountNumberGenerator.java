package no.mybank.util;

import org.apache.commons.lang3.RandomStringUtils;

public class AccountNumberGenerator {
	
	public static String getAccountNumber()  {
		String randomNum = RandomStringUtils.randomNumeric(11);
		String accountNr = randomNum.substring(0, 4) + "." + randomNum.substring(4, 6) + "." + randomNum.substring(6);
		return accountNr;
   }
	
	public static void main(String[] args) {
		for (int i=0; i<10; i++) {
			System.out.println(AccountNumberGenerator.getAccountNumber());
		} 
    }
}
