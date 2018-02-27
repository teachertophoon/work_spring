package com.koitt.junit;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/*
 * 매번 새로운 객체를 만드는지를 테스트하는 테스트 클래스
 * JUnitTest 테스트 클래스를 개선
 */
public class JUnitTestUsingSet {
	static Set<JUnitTestUsingSet> testObjects = new HashSet<JUnitTestUsingSet>();
	
	@Test public void test1() {
		/*
		 * not hasItem: 첫번째 파라미터 컬렉션에 해당 객체가 존재하는지 확인
		 * 이 메소드 호출부분을 통과하면 같은 객체가 컬렉션에 존재하지 않는 것이다. 
		 */
		assertThat(testObjects, not(hasItem(this)));
		
		// 존재하지 않는다면 현재 JUnitTestUsingSet 객체를 컬렉션에 저장
		testObjects.add(this);
	}
	
	@Test public void test2() {
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);
	}
	
	@Test public void test3() {
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);
	}

}





