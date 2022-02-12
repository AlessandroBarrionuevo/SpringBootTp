package com.spring.com.tp.repository;

import com.spring.com.tp.model.Sim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SimsRepository extends JpaRepository<Sim, Integer> {

}
