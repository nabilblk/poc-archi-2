package demo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@PreAuthorize("hasRole('BATMAN')")
public class ProductApi {

    @RequestMapping(value = "/products")
    public List<String> allProducts(Principal user, @RequestHeader Map<String, String> header){
        List<String> products = new ArrayList<>();
        products.add("Product 1");
        products.add("Product 2");
        return products;
    }

}
