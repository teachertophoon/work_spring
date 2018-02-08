package bean;

public class MyBean {
	// 필드
	private String greeting;
	
	// 생성자
	public MyBean(String greeting) {
		this.greeting = greeting;
	}
	
	// 일반 메소드
	public void sayHello() {
		System.out.println(this.greeting + "~");
	}
	
	// getter
	public String getGreeting() {
		return greeting;
	}
	
	// setter
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
}
