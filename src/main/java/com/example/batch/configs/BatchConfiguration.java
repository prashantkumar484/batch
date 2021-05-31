package com.example.batch.configs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;

import com.example.batch.listeners.JobCompletionNotificationListener;
import com.example.batch.models.User;
//import com.example.batch.models.UserDetail;
//import com.example.batch.processors.UserItemProcessor;
import com.example.batch.repository.UserRepository;
import com.example.batch.utils.UserUtils;

@Configuration
//@EnableBatchProcessing
public class BatchConfiguration {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public UserUtils userUtils;

//	@Bean
//	public Step step1(ItemReader<User> itemReader, ItemWriter<User> itemWriter) throws Exception {
//
////        return this.stepBuilderFactory.get("step1").<UserDetail, User>chunk(5).reader(itemReader)
////                .processor(processor()).writer(itemWriter).build();
//		return this.stepBuilderFactory.get("step1").<User, User>chunk(5).reader(itemReader).writer(itemWriter).build();
//	}
	
	@Bean
	public Step step2() throws Exception {

//        return this.stepBuilderFactory.get("step1").<UserDetail, User>chunk(5).reader(itemReader)
//                .processor(processor()).writer(itemWriter).build();
		return this.stepBuilderFactory.get("step1").<User, User>chunk(5).reader(userUtils.reader()).writer(userUtils.writer()).build();
	}

	@Bean
	public Job userUpdateJob(JobCompletionNotificationListener listener, Step step1) throws Exception {

		return this.jobBuilderFactory.get("userUpdateJob").incrementer(new RunIdIncrementer()).listener(listener)
				.start(step2()).build();
	}
	
//	private <T> DefaultLineMapper<T> getLineMapper() {
//		DefaultLineMapper<T> dlm = new DefaultLineMapper();
//		
//		
//		
//	}
	
	private DelimitedLineTokenizer getDelimitedLineTokenizer(String[] names, String delimiter ) {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		
		tokenizer.setNames(names);
		tokenizer.setDelimiter(delimiter);
		
		return tokenizer;
	}

}