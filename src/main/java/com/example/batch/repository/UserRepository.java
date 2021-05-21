package com.example.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.batch.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
}
