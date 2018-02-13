package com.koitt.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.koitt.model.Pet;

public class TestDrive {
	
	public static void main(String[] args) {
		ApplicationContext context = 
				new GenericXmlApplicationContext("/com/koitt/config/config.xml");
		SqlSession session = context.getBean(SqlSession.class);
		
		// 1.
		Pet pet = session.selectOne("com.koitt.dao.PetMapper.selectPet", 1);
		System.out.println(pet);
		
		// 2.
		pet = session.selectOne("com.koitt.dao.PetMapper.selectPet2", 1);
		System.out.println(pet);
		
		// 3.
		List<Pet> petList = session.selectList("com.koitt.dao.PetMapper.selectPetAll");
		System.out.println(petList);
	}
}







