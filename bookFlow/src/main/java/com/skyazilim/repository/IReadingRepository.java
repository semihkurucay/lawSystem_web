package com.skyazilim.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skyazilim.entity.Reading;

import jakarta.transaction.Transactional;

@Repository
public interface IReadingRepository extends JpaRepository<Reading, Long> {
	@Query("FROM Reading WHERE user.id = ?1")
	public List<Reading> getAllBookByUserId(Long userId);
	
	@Query("FROM Reading WHERE user.id = ?1 AND book.id = ?2")
	public Optional<Reading> isReadBook(Long userId, Long bookId);
	
	@Transactional
	public void deleteByBookId(Long bookId);
}
