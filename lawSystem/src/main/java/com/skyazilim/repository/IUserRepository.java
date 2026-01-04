package com.skyazilim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skyazilim.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
	@Query("FROM User WHERE mail = ?1")
	public Optional<User> getUserByMail(String mail);
}
