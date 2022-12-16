package ooptaxi;

import ooptaxi.person.Customer;

public class Application {

	public static void main(String[] args) throws InterruptedException {

		Customer unknown = TaxiManager.getInstance().getCustomers().get(0);

		System.out.println("[Customer]");
		System.out.println(unknown.toString());

		Thread.sleep(2000);
		;

		unknown.callTaxi();

	}
}
