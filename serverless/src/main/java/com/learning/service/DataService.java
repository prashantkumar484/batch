package com.learning.service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.learning.domain.Data;

@Service
public class DataService {
    
    public Data getEmployee(Data data) {
    	List<String> modifiedData= new ArrayList<String>();
    	Data d= new Data();
    	data.getPersons().stream().forEach(action->{
    		modifiedData.add(action+"-"+UUID.randomUUID());
    		d.setPersons(modifiedData);
    	});
    	return d;
    }
}
