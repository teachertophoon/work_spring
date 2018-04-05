package com.koitt.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Spring3AjaxRestController {
	
	// 자동차 전체 리스트 응답 메소드 구현
	@ResponseBody	// Ajax 요청에 대한 응답을 위한 메소드라는 표시
	@RequestMapping(value="/car", method=RequestMethod.GET)
	public List<Car> getCars() {
		
		// 클라이언트(웹브라우저)로 전달할 데이터를 리스트에 담는다.
		List<Car> cars = new ArrayList<Car>();
		cars.add(new Car("BMW", "20너1234"));
		cars.add(new Car("모닝", "11가1111"));
		cars.add(new Car("Audi", "22나2222"));
		
		// 전달할 데이터를 리턴(이전에는 페이지였지만 지금은 데이터를 리턴)
		return cars;
	}
	
	// 자동차 한 대의 정보를 응답하는 메소드 구현
	@ResponseBody
	@RequestMapping(value="/car/{name}", method=RequestMethod.GET)
	public Car getCar(@PathVariable("name") String name) {
		System.out.println(name);
		
		// 아래와 같이 차량 정보가 저장되어 있다고 가정(데이터베이스 대신)
		List<Car> cars = new ArrayList<Car>();
		cars.add(new Car("BMW", "20너1234"));
		cars.add(new Car("모닝", "11가1111"));
		cars.add(new Car("Audi", "22나2222"));
		
		// 클라이언트로부터 전달받은 name 정보를 이용하여 검색
		for (int i = 0; i < cars.size(); i++) {
			// 리스트 중에서 name 변수와 일치하는 차량이 존재하는 경우
			if (name.equalsIgnoreCase(cars.get(i).getName())) {
				// 해당 차량을 리턴하여 클라이언트로 전달
				return cars.get(i);
			}
		}
		
		// name과 일치하는 차량이 없을경우 null 값을 리턴
		return null;
	}
	
	// 차량 한 대 추가하는 메소드
	@ResponseBody
	@RequestMapping(value="/car", method=RequestMethod.POST)
	public void addCar(String name, String number) {
		System.out.println(name + " / " + number);
		Car item = new Car(name, number);
		System.out.println("입력받은 차량: " + item.getName() + " / " + item.getNumber());
		
		/*
		 * 입력받은 차량을 데이터베이스에 저장하면 되지만 현재 데이터베이스가 없으므로 
		 * 출력까지만 구현
		 */
	}
}
