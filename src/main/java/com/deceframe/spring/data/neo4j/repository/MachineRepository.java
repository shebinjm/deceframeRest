package com.deceframe.spring.data.neo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.deceframe.spring.data.neo4j.domain.Machine;

@Repository
public interface MachineRepository extends Neo4jRepository<Machine, Long> {
}