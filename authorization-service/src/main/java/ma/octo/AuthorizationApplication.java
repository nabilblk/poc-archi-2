package ma.octo;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RestController
public class AuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }


    @Configuration
    static class MvcConfig extends WebMvcConfigurerAdapter {
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("login").setViewName("login");
            registry.addViewController("/").setViewName("index");
        }
    }

    @Configuration
    @EnableAuthorizationServer
    @EnableConfigurationProperties({AuthorizationServerProperties.class})
    static class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        AuthorizationServerProperties authorizationServerProperties;

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
                    .withClient("react-example-app")
                    .secret("react-app-secret")
                    .authorizedGrantTypes("client_credentials", "authorization_code", "refresh_token", "implicit")
                    .scopes("read", "write")
                    .accessTokenValiditySeconds((int) TimeUnit.HOURS.toSeconds(1))
                    .autoApprove(true)

                    .and()
                    .withClient("react-example-app-2")
                    .secret("react-app-2-secret")
                    .authorizedGrantTypes("client_credentials", "authorization_code", "refresh_token", "implicit")
                    .scopes("read", "write")
                    .accessTokenValiditySeconds((int) TimeUnit.HOURS.toSeconds(1))
                    .autoApprove(true)

                    .and()

                    .withClient("app-html-1") // No secret!
                    .authorizedGrantTypes("client_credentials","password", "implicit")
                    .scopes("read")
                    .redirectUris("http://app-html-1.local:9090/#/access_token?t=")
                    .accessTokenValiditySeconds((int) TimeUnit.HOURS.toSeconds(1))
                    .autoApprove(true)

                    .and()

                    .withClient("app-html-2") // No secret!
                    .authorizedGrantTypes("client_credentials","password", "implicit")
                    .scopes("read")
                    .redirectUris("http://app-html-2.local:9090/#/access_token?t=")
                    .accessTokenValiditySeconds((int) TimeUnit.HOURS.toSeconds(1))
                    .autoApprove(true)
            ;
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.authenticationManager(authenticationManager).accessTokenConverter(jwtAccessTokenConverter());
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            security.tokenKeyAccess(authorizationServerProperties.getTokenKeyAccess());
        }

        @Bean
        @ConfigurationProperties("jwt")
        JwtAccessTokenConverter jwtAccessTokenConverter() {
            return new JwtAccessTokenConverter();
        }
    }


    @Profile("!cloud")
    @Bean
    RequestDumperFilter requestDumperFilter() {
        return new RequestDumperFilter();
    }
}
