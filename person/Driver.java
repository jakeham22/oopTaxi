package ooptaxi.person;

import java.util.Scanner;

import lombok.Getter;

@Getter
public class Driver extends Person {
	private int allowDistanceToCustomer;
	private int allowDistanceToDestination;
	private int loc;

	Scanner sc = new Scanner(System.in);

	public Driver(String location, int loc, String name, Float rate, int allowDistanceToCustomer,
			int allowDistanceToDestination) {
		super(location, name, rate);
		this.loc = 0;
		this.allowDistanceToCustomer = allowDistanceToCustomer;
		this.allowDistanceToDestination = allowDistanceToDestination;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}

	public boolean permitRequestion() throws InterruptedException {
		System.out.println("[Driver]");
		System.out.println("탑승을 승낙하면 1, 아니면 아무버튼이나 눌러주세요");
		int n = sc.nextInt();
		if (n == 1) {
			System.out.println("기사가 탑승을 승낙하셨습니다");
			Thread.sleep(1000);
			return true;
		} else {
			System.out.println("기사가 탑승을 거부하셨습니다");
			Thread.sleep(1000);
			return false;
		}
	}

}
