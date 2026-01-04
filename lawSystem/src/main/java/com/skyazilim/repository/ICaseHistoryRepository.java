package com.skyazilim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skyazilim.entity.CaseHistory;

@Repository
public interface ICaseHistoryRepository extends JpaRepository<CaseHistory, Long> {
	@Query("FROM CaseHistory WHERE cases.id = ?1")
	public List<CaseHistory> getAllCaseHistory(String caseId);
}
