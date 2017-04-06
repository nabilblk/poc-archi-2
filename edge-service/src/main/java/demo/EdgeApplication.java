package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;
import java.util.Map;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableOAuth2Sso
@Controller
@SessionAttributes("authorizationRequest")
public class EdgeApplication {

    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal user, @RequestHeader Map<String, String> header) {
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(EdgeApplication.class, args);
    }
}
