package com.skyazilim.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skyazilim.entity.Case;

@Repository
public interface ICaseRepository extends JpaRepository<Case, String> {
	@Query("SELECT COUNT(c) FROM Case c")
	public Long getTotalCase();
	
	@Query("SELECT COUNT(c) FROM Case c WHERE c.status = true")
	public Long getActiveCase();
	
	@Query("SELECT COUNT(c) FROM Case c WHERE c.status = true AND c.date <= ?1")
	public Long getUpcomingCase(LocalDate date);
	
	@Query("""
			SELECT c
			FROM Case c
			JOIN c.client cl
			WHERE 
			    c.id LIKE CONCAT('%', :text, '%')
			    OR c.type LIKE CONCAT('%', :text, '%')
			    OR cl.name LIKE CONCAT('%', :text, '%')
			ORDER BY c.date ASC
			""")
	public List<Case> getAllCase(@Param("text") String text);
	
	@Query("""
			SELECT c
			FROM Case c
			JOIN c.client cl
			WHERE 
				c.status = true
			    AND (
			     	c.id LIKE CONCAT('%', :text, '%') 
			     	OR c.type LIKE CONCAT('%', :text, '%') 
			     	OR cl.name LIKE CONCAT('%', :text, '%')
			 	)
			ORDER BY c.date ASC
			""")
	public List<Case> getAllActiveCase(@Param("text") String text);
}
