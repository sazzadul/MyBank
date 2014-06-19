package no.mybank.util;

import java.util.Random;

public class CurrencyTypeGenerator {
	private static String[] accountTypes =  new String[] {"NOK", "SEK", "USD", "GBP"};
	private static Random random = new Random();
	
	public static String getCurrencyType() {
		int index = random.nextInt(accountTypes.length);
		return accountTypes[index];
	}
	
	public static void main(String[] args) {
		System.out.println(CurrencyTypeGenerator.getCurrencyType());

	}
}
