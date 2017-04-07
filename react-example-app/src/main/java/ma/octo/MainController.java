package ma.octo;

import org.springframework.security.access.prepost.PreAuthorize;
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


    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal user, @RequestHeader Map<String, String> header) {
        return user;
    }

    @RequestMapping("/")
    public String index(OAuth2Authentication authentication, final Model model) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
        model.addAttribute("jwtToken", details.getTokenValue());
        return "/index";
    }


}
