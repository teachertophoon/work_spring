package bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("greeting")	// greeting 이름의 bean 객체를 만든것과 같다.
@Scope("singleton")
public class GreetingService {
	// 필드
	@Autowired				// config.xml bean 객체의 autowire 속성과 같다.
	@Qualifier("korDao")	// autowire의 byName 속성값과 같다. (지정하지 않으면 byType 속성값과 같다.)
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
