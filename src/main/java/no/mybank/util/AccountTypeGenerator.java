package no.mybank.util;

import java.util.Random;

public class AccountTypeGenerator {
	private static String[] accountTypes =  new String[] {"Deposit", "Savings", "Loan", "Joint"};
	private static Random random = new Random();
	
	public static String getAccountType() {
		int index = random.nextInt(accountTypes.length);
		return accountTypes[index];
	}
	
	public static void main(String[] args) {
		System.out.println(AccountTypeGenerator.getAccountType());

	}
}
