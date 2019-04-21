//package com.test.deceframe.spring.data.neo4j;
//
//import static junit.framework.Assert.assertNull;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.deceframe.spring.data.neo4j.domain.DecEvent;
//import com.deceframe.spring.data.neo4j.domain.Movie;
//import com.deceframe.spring.data.neo4j.domain.Person;
//import com.deceframe.spring.data.neo4j.domain.Role;
//import com.deceframe.spring.data.neo4j.repository.DecEventRepository;
//import com.deceframe.spring.data.neo4j.repository.MachineRepository;
//import com.deceframe.spring.data.neo4j.repository.MovieRepository;
//import com.deceframe.spring.data.neo4j.repository.PersonRepository;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = DeceframeDatabaseNeo4jTestConfiguration.class)
//@ActiveProfiles(profiles = "test")
//public class DecEventRepositoryIntegrationTest {
//
//    @Autowired
//    private DecEventRepository decEventRepository;
//
//    @Autowired
//    private MachineRepository machineRepository;
//
//    public DecEventRepositoryIntegrationTest() {
//    }
//
//    @Before
//    public void initializeDatabase() {
//        System.out.println("seeding embedded database");
//        DecEvent italianJob = new DecEvent();
//        italianJob.setTitle("The Italian Job");
//        italianJob.setReleased(1999);
//        decEventRepository.save(italianJob);
//
//        Person mark = new Person();
//        mark.setName("Mark Wahlberg");
//        machineRepository.save(mark);
//
//        Role charlie = new Role();
//        charlie.setMovie(italianJob);
//        charlie.setPerson(mark);
//        Collection<String> roleNames = new HashSet<>();
//        roleNames.add("Charlie Croker");
//        charlie.setRoles(roleNames);
//        List<Role> roles = new ArrayList<>();
//        roles.add(charlie);
//        italianJob.setRoles(roles);
//        decEventRepository.save(italianJob);
//    }
//
//    @Test
//    @DirtiesContext
//    public void testFindByTitle() {
//        System.out.println("findByTitle");
//        String title = "The Italian Job";
//        Movie result = decEventRepository.findByTitle(title);
//        assertNotNull(result);
//        assertEquals(1999, result.getReleased());
//    }
//
//    @Test
//    @DirtiesContext
//    public void testCount() {
//        System.out.println("count");
//        long movieCount = decEventRepository.count();
//        assertNotNull(movieCount);
//        assertEquals(1, movieCount);
//    }
//
//    @Test
//    @DirtiesContext
//    public void testFindAll() {
//        System.out.println("findAll");
//        Collection<Movie> result = (Collection<Movie>) decEventRepository.findAll();
//        assertNotNull(result);
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    @DirtiesContext
//    public void testFindByTitleContaining() {
//        System.out.println("findByTitleContaining");
//        String title = "Italian";
//        Collection<Movie> result = decEventRepository.findByTitleContaining(title);
//        assertNotNull(result);
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    @DirtiesContext
//    public void testGraph() {
//        System.out.println("graph");
//        List<Map<String, Object>> graph = decEventRepository.graph(5);
//        assertEquals(1, graph.size());
//        Map<String, Object> map = graph.get(0);
//        assertEquals(2, map.size());
//        String[] cast = (String[]) map.get("cast");
//        String movie = (String) map.get("movie");
//        assertEquals("The Italian Job", movie);
//        assertEquals("Mark Wahlberg", cast[0]);
//    }
//
//    @Test
//    @DirtiesContext
//    public void testDeleteMovie() {
//        System.out.println("deleteMovie");
//        decEventRepository.delete(decEventRepository.findByTitle("The Italian Job"));
//        assertNull(decEventRepository.findByTitle("The Italian Job"));
//    }
//
//    @Test
//    @DirtiesContext
//    public void testDeleteAll() {
//        System.out.println("deleteAll");
//        decEventRepository.deleteAll();
//        Collection<Movie> result = (Collection<Movie>) decEventRepository.findAll();
//        assertEquals(0, result.size());
//    }
//}