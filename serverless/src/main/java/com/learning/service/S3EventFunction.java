package com.learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3Entity;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3EventNotificationRecord;
import com.learning.domain.Data;

import java.util.List;
import java.util.function.Function;

@Component
public class S3EventFunction implements Function<S3Event, S3Event> {
	@Autowired
	private DataService dataService;

	@Override
	public S3Event apply(S3Event event) {
		System.out.println("Input Data---" + event.toString());
		
		List<S3EventNotificationRecord> records = event.getRecords();
		
		for(S3EventNotificationRecord record: records) {
			System.out.println("Event Name: "+ record.getEventName().toString());
			
			S3Entity entity = record.getS3();
			System.out.println("Bucket name: "+ entity.getBucket().getName().toString());
			System.out.println("KEY: "+ entity.getObject().getKey().toString());
			System.out.println("ETAG: "+ entity.getObject().geteTag().toString());
			
			System.out.println("\n\n");
		}
		
		return event;
	}
}
