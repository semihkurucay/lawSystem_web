package com.skyazilim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skyazilim.entity.Client;

@Repository
public interface IClientRepository extends JpaRepository<Client, String> {
	@Query("SELECT c FROM Client c WHERE c.id LIKE CONCAT('%', :text, '%') OR c.name LIKE CONCAT('%', :text, '%') OR c.phone LIKE CONCAT('%', :text, '%')")
	public List<Client> getAllClient(@Param("text") String text);
}
