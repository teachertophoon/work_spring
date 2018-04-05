package com.koitt.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Spring3AjaxRestController {
	
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
}
