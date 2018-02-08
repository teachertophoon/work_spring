package bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestDrive {
	
	public static void main(String[] args) {
		// MyBean과 TestDrive간의 의존성이 생겼다.
		MyBean bean = new MyBean();
		bean.sayHello();
		
		// 의존성이 없게 만들기 위해 Spring의 DI를 사용해보자.
		// 1. 스프링 설정파일을 불러온다.
		ApplicationContext context = 
				new FileSystemXmlApplicationContext("./src/config/applicationContext.xml");
		
		/*
		 *  2. 애플리케이션 컨텍스트를 통해서
		 *  mybean이라는 이름으로 MyBean 객체를 요청 후
		 *  생성한 객체를 bean02 변수에 저장
		 */
		MyBean bean02 = (MyBean) context.getBean("mybean");
		
		// 3. bean02 객체를 이용하여 메소드 호출
		bean02.sayHello();
	}
}
