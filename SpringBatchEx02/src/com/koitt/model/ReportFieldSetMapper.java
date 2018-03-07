package com.koitt.model;

import java.text.SimpleDateFormat;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class ReportFieldSetMapper implements FieldSetMapper<Report>{

	private SimpleDateFormat dateFormat;
	
	public ReportFieldSetMapper() {
		dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	}

	@Override
	public Report mapFieldSet(FieldSet fieldSet) throws BindException {
		Report item = new Report();
		
		
		
		item.setRefId(fieldSet.readInt(0));
		item.setName(fieldSet.readString(1));
		item.setAge(fieldSet.readInt(2));
		
		String date = fieldSet.readString(3);
		
		try {
			item.setDob(dateFormat.parse(date));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		item.setIncome(fieldSet.readBigDecimal(4));
		
		return item;
	}

}
