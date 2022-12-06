package ru.netology.springboot5hw01;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBoot5Hw01ApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    private static GenericContainer<?> myappProdapp = new GenericContainer<>("prodapp").withExposedPorts(8081);
    private static GenericContainer<?> myappDeavapp = new GenericContainer<>("devapp").withExposedPorts(8080);

    @BeforeAll
    public static void setUp() {
        myappProdapp.start();
        myappDeavapp.start();
    }

    @Test
    void contextLoads() {
        Integer portProd = myappProdapp.getMappedPort(8081);
        Integer portDev = myappDeavapp.getMappedPort(8080);

        ResponseEntity<String> forEntityProv = restTemplate.getForEntity("http://localhost:" + portProd + "/profile", String.class);
        ResponseEntity<String> forEntityDev = restTemplate.getForEntity("http://localhost:" + portDev + "/profile", String.class);

        assertEquals("Current profile is production", forEntityProv.getBody());
        assertEquals("Current profile is dev", forEntityDev.getBody());
    }

}
