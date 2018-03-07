package com.koitt.model;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
 *  spring-task-scheduler.xml의 scheduled 앨리먼트의 ref에 해당하는
 *  빈 객체를 생성하기 위해 아래와 같이 @Component 애노테이션을 사용한다.
 *  @Component에 명시적으로 빈 이름을 설정하지 않으면
 *  클래스명 맨 앞 문자만 소문자로 바뀐 빈 이름을 사용하게 된다.
 */
@Component	
public class RunScheduler {
	
	@Scheduled(cron="*/1 * * ? * *")
	public void run01() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("테스트 1 ..." + new Date());
	}
	
	@Scheduled(cron="*/1 * * ? * *")
	public void run02() {
		System.out.println("테스트 2 ..." + new Date());
	}
	
	@Scheduled(cron="*/1 * * ? * *")
	public void run03() {
		System.out.println("테스트 3 ..." + new Date());
	}
	
}
