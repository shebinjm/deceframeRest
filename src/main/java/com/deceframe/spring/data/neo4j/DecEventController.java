package com.deceframe.spring.data.neo4j;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deceframe.spring.data.neo4j.services.DecEventService;

@CrossOrigin("*")
@RestController
@RequestMapping(value= "/deceframe")
public class DecEventController {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private DecEventService DecEventService;
    

    @RequestMapping("/decEvents")
    public String generateDecEvent(@RequestParam(value="ip", defaultValue="127.0.0.1") String ip) {
    	
    	
        return  DecEventService.graph1(ip);
    }

}