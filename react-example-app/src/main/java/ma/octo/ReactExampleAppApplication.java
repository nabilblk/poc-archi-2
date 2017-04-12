package ma.octo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;

@SpringBootApplication
@EnableOAuth2Sso
@SessionAttributes("authorizationRequest")
public class ReactExampleAppApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(ReactExampleAppApplication.class, args);
	}

	@Value("${security.oauth2.client.access-token-uri}")
	private String accessTokenUri;

	@Value("${security.oauth2.client.user-authorization-uri}")
	private String userAuthorizationUri;

	@Value("${security.oauth2.client.client-id}")
	private String clientID;

	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;


	@Bean
	public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
		return new OAuth2RestTemplate(resource(), oauth2ClientContext);
	}

	private OAuth2ProtectedResourceDetails resource() {
		AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
		resource.setClientId(clientID);
		resource.setClientSecret(clientSecret);
		resource.setAccessTokenUri(accessTokenUri);
		resource.setUserAuthorizationUri(userAuthorizationUri);
		resource.setScope(Arrays.asList("read"));

		return resource;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.logout().and().antMatcher("/**").authorizeRequests()
				.antMatchers("/login","/auth/**").permitAll()
				.anyRequest().authenticated().and().csrf().disable();
	}
}
