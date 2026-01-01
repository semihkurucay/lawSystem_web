package com.skyazilim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skyazilim.entity.Writer;

@Repository
public interface IWriterRepository extends JpaRepository<Writer, Long>{

}
