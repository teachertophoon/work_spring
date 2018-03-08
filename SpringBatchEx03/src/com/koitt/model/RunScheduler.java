package com.koitt.model;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier("mySqlToMailJob")	// job-mysql-to-mail.xml의 mySqlToMailJob id 값을 가지는 빈 객체를 가져온다.
	private Job job;
	
	// 매일 16시마다 실행
	@Scheduled(cron="0 0 16 * * *")
	public void run() {
		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("종료 상태: " + execution.getStatus());
			System.out.println("종료 상태: " + execution.getAllFailureExceptions());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("작업 완료!");
	}
	
}











