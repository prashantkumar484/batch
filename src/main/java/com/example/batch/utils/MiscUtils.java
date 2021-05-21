package com.example.batch.utils;

import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.example.batch.models.User;
import com.example.batch.repository.UserRepository;

@Component
public class MiscUtils {

	@Autowired
	@Lazy
	private UserRepository userRepository;
	
	public FlatFileItemReader<User> reader() {
		return new FlatFileItemReaderBuilder<User>().name("userItemReader").resource(new ClassPathResource("users.csv"))
				.delimited().delimiter("").names(new String[] { "email", "firstName", "lastName", "mobileNumber" })
				.fieldSetMapper(new BeanWrapperFieldSetMapper<User>() {
					{
						setTargetType(User.class);
					}
				}).build();
	}
	
	public RepositoryItemWriter<User> writer() {
		RepositoryItemWriter<User> writer = new RepositoryItemWriter<>();
		writer.setRepository(userRepository);
		writer.setMethodName("save");
		return writer;
	}
}