package ooptaxi;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import ooptaxi.person.Customer;
import ooptaxi.person.Driver;

public class TaxiManagerSystem {

	private static List<Driver> drivers = TaxiManager.getInstance().getDrivers();
	private static List<Customer> customers = TaxiManager.getInstance().getCustomers();
	private static int tmp;

	// public static Customer sortDriverList(Customer customer) {
	// List<String> sortedTaxiList = new ArrayList<>();

	// TaxiManager.getInstance().getCustomers().
	// }

	static Random random = new Random();
	static int seed = random.nextInt(customers.size());

	public static int findCusLocation(Customer customer) {
		int cusLoc = -1;

		HashMap<String, Integer> maps = TaxiManager.getInstance().getLocation();

		Iterator<Map.Entry<String, Integer>> entries = maps.entrySet().iterator();

		while (entries.hasNext()) {
			Map.Entry<String, Integer> entry = entries.next();

			if (customer.getLocation() == entry.getKey()) {
				cusLoc = entry.getValue();
			}
		}
		return cusLoc;
	}

	public static int findCusDestination(Customer customer) {
		int desLoc = -1;

		HashMap<String, Integer> maps = TaxiManager.getInstance().getLocation();

		Iterator<Map.Entry<String, Integer>> entries = maps.entrySet().iterator();

		while (entries.hasNext()) {
			Map.Entry<String, Integer> entry = entries.next();

			if (customer.getDestination() == entry.getKey()) {
				desLoc = entry.getValue();
			}
		}
		return desLoc;
	}

	public static List<Driver> letDriverLocConverToInt() {

		HashMap<String, Integer> maps = TaxiManager.getInstance().getLocation();

		for (int i = 0; i < drivers.size(); i++) {
			String driver = drivers.get(i).getLocation();

			Iterator<Map.Entry<String, Integer>> entries = maps.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry<String, Integer> entry = entries.next();

				if (driver == entry.getKey()) {
					// drivers.get(i).setLocation(entry.getValue());
					drivers.get(i).setLoc(entry.getValue());
				}
			}
		}
		return drivers;
	}

	public static Integer letCustomerLocConverToInt(Customer customer) {

		HashMap<String, Integer> maps = TaxiManager.getInstance().getLocation();

		for (int i = 0; i < customers.size(); i++) {
			String cust = customer.getLocation();

			Iterator<Map.Entry<String, Integer>> entries = maps.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry<String, Integer> entry = entries.next();

				if (cust == entry.getKey()) {
					// drivers.get(i).setLocation(entry.getValue());
					return entry.getValue();
				}
			}
		}
		return 0;
	}

	public static Integer letCustomerDesConverToInt(Customer customer) {

		HashMap<String, Integer> maps = TaxiManager.getInstance().getLocation();

		for (int i = 0; i < customers.size(); i++) {
			String des = customer.getDestination();

			Iterator<Map.Entry<String, Integer>> entries = maps.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry<String, Integer> entry = entries.next();

				if (des == entry.getKey()) {
					// drivers.get(i).setLocation(entry.getValue());
					return entry.getValue();
				}
			}
		}
		return 0;
	}

	public static boolean contrastAllowDistance(Customer customer) throws InterruptedException {
		drivers = letDriverLocConverToInt();

		int cusLoc = findCusLocation(customer);
		int desLoc = findCusDestination(customer);

		for (int i = 0; i < drivers.size(); i++) {
			int comeToCus = drivers.get(i).getAllowDistanceToCustomer();
			int moveToDes = drivers.get(i).getAllowDistanceToDestination();

			if ((comeToCus < Math.abs(drivers.get(i).getLoc() - cusLoc))
					&& (moveToDes < Math.abs(drivers.get(i).getLoc() - desLoc))) {

				tmp = i;
				System.out.println("[Customer]");
				System.out.println(drivers.get(tmp).getName() + "님의 택시가 배차되었습니다 평점은 " + drivers.get(tmp).getRate());
				// permitRequestTaxi(drivers.get(tmp));
				System.out.println();
				Thread.sleep(1500);
				return true;
			}
		}
		System.out.println("조건에 부합한 운전자가 없습니다.");
		return false;
	}

	public static boolean permitRequestTaxi(Driver isWho) throws InterruptedException {
		if (isWho.permitRequestion() == true) {
			return true;
		}
		return false;

	}

	public static void finishingMatch(Customer customer) throws InterruptedException {
		if ((contrastAllowDistance(customer) == true) && (drivers.get(0).permitRequestion() == true)) {
			System.out.println();
			System.out.println("[Customer]");
			System.out.println("잠시만 기다려주세요 택시가 출발했습니다");
			System.out.println();
			Thread.sleep(1000);
			System.out.println("기사의 현재 위치는 " + drivers.get(tmp).getLocation() + " 이며, 수학적 위치는 "
					+ drivers.get(tmp).getLoc() + " 입니다.");
			alarmMovingTaxi(customer);
		} else if ((contrastAllowDistance(customer) == true) && (drivers.get(0).permitRequestion() == false)) {
			Thread.sleep(1000);
			System.out.println("택시기사가 승차를 거부했습니다.");
			Thread.sleep(500);
			System.out.println(customer.getRate() + "이 당신의 평점입니다 평점떄문에 그런거 아닌가요?");
		}
		// else {
		// System.out.println("조건에 부합한 기사가 없습니다");
		// }
	}

	public static void alarmMovingTaxi(Customer customer) throws InterruptedException {
		int taxiToCus = Math.abs(letCustomerLocConverToInt(customer) - drivers.get(tmp).getLoc());
		int CusToDes = Math.abs(letCustomerLocConverToInt(customer) - letCustomerDesConverToInt(customer));
		Thread.sleep(1000);
		System.out.println(customer.getLocation() + "의 수학적 위치는 " + letCustomerLocConverToInt(customer));

		Thread.sleep(1000);
		System.out.println(customer.getDestination() + "의 수학적 위치는 " + letCustomerDesConverToInt(customer));
		Thread.sleep(1000);
		System.out.println("택시가 고객님께 도착할 예상 시간은 " + taxiToCus + "입니다.");
		Thread.sleep(1000);
		System.out.println("고객님 위치부터 목적지 도착 예상 시간은 " + CusToDes + "입니다.");

		System.out.println();

		for (int i = taxiToCus; i >= 0; i--) {
			System.out.println(i);
			Thread.sleep(800);
		}
		System.out.println("택시가 도착했습니다. 탑습하십시오");
		System.out.println();
		Thread.sleep(1);
		System.out.println("[" + customer.getName() + " 고객팀 탑승완료]");
		Thread.sleep(1);

		System.out.println("이동중입니다.");

		for (int i = CusToDes; i >= 0; i--) {
			System.out.println(i);
			Thread.sleep(800);
		}
		System.out.println("택시가 목적지에 도착했습니다. 하차하십시오");
		Thread.sleep(700);
		System.out.println("[" + customer.getName() + " 고객팀 하차완료]");

		payMoeny(customer);
	}

	public static boolean payMoeny(Customer customer) {
		int CusToDes = Math.abs(letCustomerLocConverToInt(customer) - letCustomerDesConverToInt(customer));
		int pay = CusToDes * 1000;
		if (customer.getBudget() >= pay) {
			customer.setBudget(pay);
			System.out.println(pay + "원 지불 완료되었습니다.");
			System.out.println("남은 금액은 " + customer.getBudget() + "원 입니다");
			return false;
		} else {
			System.out.println("지불 가능 금액이 부족합니다.");
			System.out.println("경범죄처벌법상의 죄로 10만원 이하의 벌금, 구류 또는 과료에 처해집니다.");
			return true;
		}
	}

	public static void setRateToCustomer(Customer customer) {
		if (payMoeny(customer) == true) {
			System.out.println(customer.getName() + "이 먹튀했습니다. 별점으로 응징해주세요 (0~5)\n 깎을 점수를 입력해주세요");
			Scanner sc = new Scanner(System.in);
			customer.setRate(sc.nextFloat());
			System.out.println(customer.getName() + "님의 평점은 " + customer.getRate() + " 입니다.");
		}
	}

//	public static void compareLocWithDriverLoc() {
//		drivers = letDriverLocConverToInt();
//		
//	}

}
