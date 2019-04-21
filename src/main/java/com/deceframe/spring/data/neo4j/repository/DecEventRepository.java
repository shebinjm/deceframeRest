package com.deceframe.spring.data.neo4j.repository;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deceframe.spring.data.neo4j.domain.DecEvent;

@Repository
public interface DecEventRepository extends Neo4jRepository<DecEvent, Long> {
    
    @Query("MATCH (m:DecEvent)<-[:occured]-(a:machine) where a.ip={ip} RETURN m ;")
    Collection<DecEvent> graph(@Param("ip") String ip);
}