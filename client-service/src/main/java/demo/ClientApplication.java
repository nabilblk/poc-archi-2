package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * The {@link ClientApplication} is a cloud-native Spring Boot application that manages
 * a bounded context for @{link Customer}, @{link Account}, @{link CreditCard}, and @{link Address}
 *
 * @author Nabil Belakbir
 */
@SpringBootApplication()
@EnableEurekaClient
@RestController
public class ClientApplication {
    @Inject
    ClientRepo clientRepo;

    @RequestMapping(value = "/clients")
    public List<Client> allClients(){
        return clientRepo.findAll();
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}
