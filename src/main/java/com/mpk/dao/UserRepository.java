package com.mpk.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mpk.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByMail(String mail);
	User findByUsername(String userName);
}
