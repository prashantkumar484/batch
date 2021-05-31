package com.example.batch.utils;

import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.example.batch.models.User;
import com.example.batch.repository.UserRepository;

@Component
public class UserUtils {

	@Autowired
	@Lazy
	private UserRepository userRepository;

//	public FlatFileItemReader<User> reader() {
//		return new FlatFileItemReaderBuilder<User>().name("userItemReader").resource(new ClassPathResource("users.csv"))
//				.delimited().delimiter("").names(new String[] { "email", "firstName", "lastName", "mobileNumber" })
//				.fieldSetMapper(new BeanWrapperFieldSetMapper<User>() {
//					{
//						setTargetType(User.class);
//					}
//				}).build();
//	}

//	@Bean
//	public FlatFileItemReader<Person> reader() {
//
//	    DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();
//	    DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
//	    lineMapper.setLineTokenizer(delimitedLineTokenizer);
//	    lineMapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {
//	        {
//	            setTargetType(Person.class);
//	        }
//	    });
//
//	    return new FlatFileItemReaderBuilder<Person>()
//	            .name("personItemReader")
//	            .resource(new FileSystemResource(inputFile))
//	            .linesToSkip(1)
//	            .skippedLinesCallback(line -> delimitedLineTokenizer.setNames(line.split(",")))
//	            .lineMapper(lineMapper)
//	            .build();
//	}

	public <T> FlatFileItemReader<T> reader() {

		DefaultLineMapper<T> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter("");
		lineMapper.setLineTokenizer(delimitedLineTokenizer);
		lineMapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<T>() {
			{
				setTargetType((Class<T>) User.class );
			}
		});

		return new FlatFileItemReaderBuilder<T>().name("userItemReaderPK")
				.resource(new ClassPathResource("users.csv"))
				.linesToSkip(1)
				.skippedLinesCallback(line -> delimitedLineTokenizer.setNames(line.split("")))
//	            .delimited().delimiter("")
				.lineMapper(lineMapper).build();

//		return new FlatFileItemReaderBuilder<User>().name("userItemReader")
//				.resource(new ClassPathResource("users.csv"))
//				.delimited().delimiter("").names(new String[] { "email", "firstName", "lastName", "mobileNumber" })
//				.fieldSetMapper(new BeanWrapperFieldSetMapper<User>() {
//					{
//						setTargetType(User.class);
//					}
//				}).build();
	}

	public RepositoryItemWriter<User> writer() {
		RepositoryItemWriter<User> writer = new RepositoryItemWriter<>();
		writer.setRepository(userRepository);
		writer.setMethodName("save");
		return writer;
	}
}