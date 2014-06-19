package no.mybank.util;

import org.apache.commons.lang3.RandomUtils;

public class BalanceGenerator {
	
	public static double getBalance() {
		double balance = RandomUtils.nextDouble(1.0, 10000.0);
		return (double) Math.round(balance * 100) / 100;
	}
	
	public static void main(String[] args) {
		System.out.println(BalanceGenerator.getBalance());

	}
}
