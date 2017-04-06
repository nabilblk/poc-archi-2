package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link ProductApplication} is a cloud-native Spring Boot application that manages
 * a bounded context for @{link Customer}, @{link Account}, @{link CreditCard}, and @{link Address}
 *
 * @author Kenny Bastani
 * @author Josh Long
 */
@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
@RestController
public class ProductApplication {

    @RequestMapping(value = "/products")
    public List<String> allClients(){
        List<String> products = new ArrayList<>();
        products.add("Product 1");
        products.add("Product 2");
        return products;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}
