package bean;

public class GreetingService {
	// 필드
	private MessageDao dao;
	
	// 기본 생성자
	public GreetingService() {}
	
	// 생성자
	public GreetingService(MessageDao dao) {
		this.dao = dao;
	}
	
	// setter
	public void setDao(MessageDao dao) {
		this.dao = dao;
	}
	
	// 일반 메소드
	public void sayHello() {
		System.out.println(dao.getMessage());
	}
}
