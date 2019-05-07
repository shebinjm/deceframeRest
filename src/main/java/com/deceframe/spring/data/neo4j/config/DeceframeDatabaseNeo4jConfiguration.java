package com.deceframe.spring.data.neo4j.config;

import org.neo4j.ogm.config.Configuration.Builder;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@ComponentScan(basePackages = { "com.deceframe.spring.data.neo4j" })
@Configuration
@EnableNeo4jRepositories(basePackages = "com.deceframe.spring.data.neo4j.repository"
,sessionFactoryRef = "getSessionFactory")
public class DeceframeDatabaseNeo4jConfiguration {
	
    public static final String URL = System.getenv("NEO4J_URL") != null ? System.getenv("NEO4J_URL") :
    	"bolt://neo4j:admin123@127.0.0.1:7687";

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
    	org.neo4j.ogm.config.Configuration config = new Builder().uri(URL).build();
        return config;
    }

    @Bean
    public SessionFactory getSessionFactory() {
    	SessionFactory s=new SessionFactory(getConfiguration(), "com.deceframe.spring.data.neo4j.domain");
    	return s; 
        		
        		
    }
}