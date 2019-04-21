package com.deceframe.spring.data.neo4j.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deceframe.spring.data.neo4j.domain.DecEvent;
import com.deceframe.spring.data.neo4j.repository.DecEventRepository;
import com.google.gson.Gson;

@Service
@Transactional
public class DecEventService {

    @Autowired
    private DecEventRepository decEventRepository;

    private Map<String, Object> toD3Format(Iterator<Map<String, Object>> result) {
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> rels = new ArrayList<>();
        int i = 0;
        while (result.hasNext()) {
            Map<String, Object> row = result.next();
            nodes.add(map("event", row.get("movie"), "label", "movie"));
            int target = i;
            i++;
            for (Object name : (Collection) row.get("cast")) {
                Map<String, Object> actor = map("title", name, "label", "actor");
                int source = nodes.indexOf(actor);
                if (source == -1) {
                    nodes.add(actor);
                    source = i++;
                }
                rels.add(map("source", source, "target", target));
            }
        }
        return map("nodes", nodes, "links", rels);

    }
    private String toD3Format(Collection<DecEvent> result) {   
	Gson gson = new Gson();
	String json="";
	Iterator it=result.iterator();
	while ( it.hasNext() ) {
		DecEvent record = (DecEvent)it.next();
	    json+=gson.toJson(record);
	}
	ObjectMapper mapper = new ObjectMapper();
	try {
		return mapper.defaultPrettyPrintingWriter().writeValueAsString(result);
	} catch (JsonGenerationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JsonMappingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "";
    }

    private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> result = new HashMap<>(2);
        result.put(key1, value1);
        result.put(key2, value2);
        return result;
    }

    
    public String graph1(String ip) {
    	Collection<DecEvent> result= decEventRepository.graph(ip);
        return toD3Format(result);
    }
    
}
