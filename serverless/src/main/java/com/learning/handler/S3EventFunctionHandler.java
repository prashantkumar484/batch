package com.learning.handler;

import java.util.List;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.learning.domain.Data;


public class S3EventFunctionHandler extends SpringBootRequestHandler<S3Event, S3Event> {
}
