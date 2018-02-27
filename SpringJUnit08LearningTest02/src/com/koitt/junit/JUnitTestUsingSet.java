package com.koitt.junit;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * 테스트 컨텍스트가 매번 주입해주는 애플리케이션 컨텍스트는
 * 항상 같은 객체인지 테스트하는 테스트 클래스 작성
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/com/koitt/junit/config.xml")
public class JUnitTestUsingSet {
	
	/*
	 *  테스트 컨텍스트가 매번 주입해주는 애플리케이션 컨택스트는
	 *  항상 같은 객체인지 테스트로 확인해보기.
	 */
	@Autowired
	private ApplicationContext context;
	
	static ApplicationContext contextObject = null;
	
	static Set<ApplicationContext> testObjects = new HashSet<ApplicationContext>();
	
	@Test public void test1() {
		/*
		 * 컬렉션의 사이즈가 0 초과인 것은 적어도
		 * 애플리케이션 컨텍스트 객체가 하나 이상 존재하는 것이므로
		 * 그때부터 객체를 비교해서 같은 것이 있는지 조사를 하는 것이다.
		 */
		if (testObjects.size() > 0) {
			assertThat(testObjects, hasItem(this.context));
		}
		testObjects.add(this.context);
	}
	
	@Test public void test2() {
		if (testObjects.size() > 0) {
			assertThat(testObjects, hasItem(this.context));
		}
		testObjects.add(this.context);
	}
	
	@Test public void test3() {
		if (testObjects.size() > 0) {
			assertThat(testObjects, hasItem(this.context));
		}
		testObjects.add(this.context);
	}
}








