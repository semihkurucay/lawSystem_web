package com.skyazilim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skyazilim.entity.Book;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long>{
	List<Book> findByWriterId(Long writerId);
}
