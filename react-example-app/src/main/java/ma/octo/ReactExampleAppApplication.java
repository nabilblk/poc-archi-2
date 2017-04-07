package ma.octo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.bind.annotation.SessionAttributes;

@SpringBootApplication
@EnableOAuth2Sso
@SessionAttributes("authorizationRequest")
public class ReactExampleAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactExampleAppApplication.class, args);
	}
}
