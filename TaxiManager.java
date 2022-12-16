package ooptaxi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import lombok.Getter;
import ooptaxi.person.Customer;
import ooptaxi.person.Driver;
import ooptaxi.place.Location;

@Getter
public class TaxiManager {
	HashMap<String, Integer> location = new HashMap<>();
	private List<Customer> customers = new ArrayList<>();
	private List<Driver> drivers = new ArrayList<>();

	private static TaxiManager application = new TaxiManager();

	public TaxiManager() {
		location.put("선릉", 0);
		location.put("독산", 1);
		location.put("홍대", 2);
		location.put("죽전", 3);
		location.put("판교", 4);
		location.put("정자", 5);
		location.put("잠실", 6);
		location.put("삼성", 7);
		location.put("강남", 8);
		location.put("역삼", 9);
		location.put("교대", 10);
		location.put("당고개", 11);
		location.put("왕십리", 12);
		location.put("아현", 13);
		location.put("신촌", 14);

		Location locationList = new Location(location);

		Random random = new Random();
		List<String> locs = Arrays.asList("선릉", "독산", "홍대", "죽전", "판교", "정자", "잠실", "삼성", "강남", "역삼", "교대", "당고개",
				"왕십리", "아현", "신촌");
		List<String> names = Arrays.asList("김정훈", "김윤식", "함경녕", "조재은", "정사도", "조길환", "김병찬", "임은혜", "염래헌", "김준태", "정준",
				"인찬휘", "김가람", "김효정", "김다함", "왕재준");

		int randomLocIndex = random.nextInt(locs.size());
		int randomDesIndex = random.nextInt(locs.size());
		int randomNameIndex = random.nextInt(names.size());

		Customer customer = new Customer(locs.get(randomLocIndex), locs.get(randomDesIndex), names.get(randomNameIndex),
				(float) Math.round(Math.random() * 5), (int) (Math.random() * 20 + 1) * 1000);
		Customer[] customerList = { customer };
		customers = new ArrayList<>(Arrays.asList(customerList));

		int randomTaxiIndex = random.nextInt(locs.size());
		int randomTNameIndex = random.nextInt(locs.size());
		Driver driver = new Driver(locs.get(randomTaxiIndex), 0, names.get(randomTNameIndex),
				(float) Math.round(Math.random() * 5), (int) Math.random() * 15 + 1, (int) Math.random() * 15 + 1);
		Driver[] driverList = { driver };
		drivers = new ArrayList<>(Arrays.asList(driverList));

		Customer jeongHun = new Customer("선릉", "독산", "김정훈", (float) 5.0, 0);
		Customer gyeongN = new Customer("홍대", "신촌", "함경녕", (float) 4.0, 2000);
		Customer eunSik = new Customer("삼성", "판교", "김은식", (float) 0.5, 10000);
		Customer jeEun = new Customer("교대", "왕십리", "조재은", (float) 4.3, 5000);
		Customer sado = new Customer("신촌", "아현", "정사도", (float) 1.3, 8000);

		// Customer[] customerList = {jeongHun, gyeongN, eunSik, jeEun,sado};
		// customers = new ArrayList<>(Arrays.asList(customerList));

		Driver byeongChan = new Driver("정자", 0, "김병찬", (float) 5.0, 5, 5);
		Driver jeongHo = new Driver("아현", 0, "유정호", (float) 5.0, 2, 10);
		Driver eunHye = new Driver("잠실", 0, "임은혜", (float) 4.0, 3, 7);
		Driver raeHeon = new Driver("강남", 0, "염래헌", (float) 3.0, 10, 10);
		Driver gaRam = new Driver("역삼", 0, "김가람", (float) 1.0, 2, 5);

		// Driver[] driverList = {eunHye, raeHeon, gaRam, byeongChan, jeongHo};
		// drivers = new ArrayList<>(Arrays.asList(driverList));

	}

	public static TaxiManager getInstance() {
		return application;
	}

}
