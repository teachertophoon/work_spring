package pattern;

import java.util.Date;

public class BuilderPatternTest {
	public static void main(String[] args) {
		StringBuilder builder01 = new StringBuilder();
		builder01
		.append("안녕하세요")
		.append("반갑습니다.")
		.append("오랜만이네요");
		
		System.out.println(builder01);
		
		UserBuilder builder02 = new UserBuilder()
				.setAge(10)
				.setDob(new Date())
				.setEmail("teachertophoon@gmail.com")
				.setName("홍길동");
		
		System.out.println(builder02);
	}
}
