//package com.learning.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.learning.domain.Data;
//
//import java.util.List;
//import java.util.function.Function;
//
//@Component
//public class DataFunction implements Function<Data, List<String>> {
//	@Autowired
//	private DataService dataService;
//
//	@Override
//	public List<String> apply(Data d) {
//		System.out.println("Input Data---" + d.getPersons());
//		System.out.println("Request After processing Data" + dataService.getEmployee(d).getPersons());
//		return dataService.getEmployee(d).getPersons();
//	}
//}
