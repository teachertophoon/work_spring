package com.koitt.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StringTest {
	
	@Test public void substringTest() {
		String object = "한글 부분 스트링 테스트";
		assertThat(object.substring(0, 2), is("한글"));
	}
	
	@Test public void lengthTest() {
		String object = "동해물과 백두산이 마르고 닳도록 ...";
		assertThat(object.length(), is(21));
	}
}
