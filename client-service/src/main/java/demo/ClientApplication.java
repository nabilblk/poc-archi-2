package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link ClientApplication} is a cloud-native Spring Boot application that manages
 * a bounded context for @{link Customer}, @{link Account}, @{link CreditCard}, and @{link Address}
 *
 * @author Nabil Belakbir
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class ClientApplication {

    @RequestMapping(value = "/clients")
    public List<String> allClients(){
        List<String> clients = new ArrayList<>();
        clients.add("Nabil Belakbir");
        clients.add("Aziz Fadil");
        return clients;
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}
