package com.koitt.test;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.koitt.model.Employee;

public class TestDrive {
	
	public static void main(String[] args) {
		String resource = "com/koitt/config/mybatis.xml";
		
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(reader, "development");
			SqlSession session = factory.openSession();
			
			Employee emp = session.selectOne("com.koitt.model.Employee.select", 7698);
			Employee emp2 = session.selectOne("com.koitt.model.Employee.select2", 7698);
			
			session.close();
			
			System.out.println(emp);
			System.out.println(emp2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}






