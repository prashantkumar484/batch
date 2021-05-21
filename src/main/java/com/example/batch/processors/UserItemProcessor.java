//package com.example.batch.processors;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.batch.item.ItemProcessor;
//
//import com.example.batch.domain.User;
//import com.example.batch.models.UserDetail;
//
//
//public class UserItemProcessor implements ItemProcessor<UserDetail, User> {
//    private static final Logger log = LoggerFactory.getLogger(UserItemProcessor.class);
//
//    @Override
//    public User process(UserDetail item) throws Exception {
//
//        log.info("processing user data.....{}", item);
//
//        User transformedUser = new User();
//        transformedUser.setEmail(item.getEmail());
//        transformedUser.setFirstName(item.getFirstName());
//        transformedUser.setLastName(item.getLastName());
//        transformedUser.setMobileNumber(item.getMobileNumber());
//        return transformedUser;
//    }
//
//}