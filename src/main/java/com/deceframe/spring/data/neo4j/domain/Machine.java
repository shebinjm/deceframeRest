package com.deceframe.spring.data.neo4j.domain;

import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Machine {
    @Id @GeneratedValue
    private Long id;

    private String ip;
    
    private String sensor;

    @Relationship(type="occured")
    private List<DecEvent> decEvents;

    public Machine(String ip, String sensor) {
        this.ip = ip;
        this.sensor = sensor;
    }

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSensor() {
		return sensor;
	}

	public void setSensor(String sensor) {
		this.sensor = sensor;
	}

	public List<DecEvent> getDecEvents() {
		return decEvents;
	}

	public void setDecEvents(List<DecEvent> decEvents) {
		this.decEvents = decEvents;
	}

	
    
}