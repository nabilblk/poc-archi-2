package ma.octo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Map;

@Controller
@PreAuthorize("hasRole('USER')")
public class MainController {

    @Autowired
    private OAuth2RestOperations restTemplate;

    @Value("${security.oauth2.resource.edge-uri}")
    private String resourceURI;



    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal user, @RequestHeader Map<String, String> header) {
        return user;
    }

    @RequestMapping("/")
    public String index(OAuth2Authentication authentication, final Model model) {
        String tokenValue = "";
        String[] productsResponseValue = null;

        try {
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
            tokenValue = details.getTokenValue();
        }catch (Exception e){

        }

        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(resourceURI + "/products/products", Object[].class);
        productsResponseValue = asStrings(responseEntity.getBody());

        model.addAttribute("jwtToken", tokenValue);
        model.addAttribute("productsResult", productsResponseValue);

        return "index";
    }

    public static String[] asStrings(Object... objArray) {
        String[] strArray = new String[objArray.length];
        for (int i = 0; i < objArray.length; i++)
            strArray[i] = String.valueOf(objArray[i]);
        return strArray;
    }


}
