package ooptaxi.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Person {
	private String location;
	private String name;
	private Float rate;

	public String toString() {
		return location + " 에서 출발할 별점 " + rate + "인 " + name + "이 계정을 생성했습니다";
	}

	public void setLocation(int location) {
		this.location = Integer.toString(location);
	}

	public void setRate(Float change) {
		this.rate -= change;
	}

}
