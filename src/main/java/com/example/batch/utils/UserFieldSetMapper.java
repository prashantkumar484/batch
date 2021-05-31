package com.example.batch.utils;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class UserFieldSetMapper<T> implements FieldSetMapper<T> {
	
	public UserFieldSetMapper(String... params) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public T mapFieldSet(FieldSet fieldSet) throws BindException {
		// TODO Auto-generated method stub
		return null;
	}

}
