package com.Mohamed.front_service.controller;

import com.Mohamed.front_service.models.Bill;
import com.Mohamed.front_service.models.Customer;
import com.Mohamed.front_service.models.Product;
import com.Mohamed.front_service.models.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@EnableMethodSecurity
@RequiredArgsConstructor
@Controller
@Slf4j
public class Productcontroller {

    private final OAuth2AuthorizedClientService oauth2ClientService;
    private final RestTemplate restTemplate;

    @GetMapping("/")
    //@PreAuthorize("hasRole('client-user')")
    public String index() {
        return "index";
    }


    @GetMapping("/products")
    //@PreAuthorize("hasRole('client-admin')")
    public String products(Model model, @AuthenticationPrincipal OidcUser principal) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient oauth2Client = oauth2ClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());
        String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
        System.out.println("jwtAccessToken = " + jwtAccessToken);
        System.out.println("Principal = " + principal);
        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("idTokenValue = " + idTokenValue);

        String url = "http://localhost:8091/products";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtAccessToken);
        HttpEntity<List<Product>> entity = new HttpEntity<>(headers);
        PagedModel<Product> productPagedModel = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<PagedModel<Product>>() {
                }
        ).getBody();
        List<Product> productList = new ArrayList<>(productPagedModel.getContent());
        log.info(productList.toString());
        model.addAttribute("products", productList);
        return "products";
    }


    @GetMapping("/customers")
    //@PreAuthorize("hasRole('client-admin')")
    public String customers(Model model, @AuthenticationPrincipal OidcUser principal) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient oauth2Client = oauth2ClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());
        String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
        System.out.println("jwtAccessToken = " + jwtAccessToken);
        System.out.println("Principal = " + principal);
        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("idTokenValue = " + idTokenValue);

        String url = "http://localhost:8090/customers";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtAccessToken);
        HttpEntity<List<Customer>> entity = new HttpEntity<>(headers);
        PagedModel<Customer> customerPagedModel = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<PagedModel<Customer>>() {
                }
        ).getBody();
        List<Customer> customerList = new ArrayList<>(customerPagedModel.getContent());
        log.info(customerList.toString());
        model.addAttribute("customers", customerList);
        return "customers";
    }

    @GetMapping("/suppliers")
    public String suppliers(Model model, @AuthenticationPrincipal OidcUser principal
    ) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)
                authentication;

        OAuth2AuthorizedClient oauth2Client =
                oauth2ClientService.loadAuthorizedClient(oauthToken.
                        getAuthorizedClientRegistrationId(), oauthToken.getName());

        String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
        System.out.println("jwtAccessToken = " + jwtAccessToken);
        System.out.println("Principal = " + principal);
        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("idTokenValue = " + idTokenValue);

        String url = "http://localhost:8082/all";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtAccessToken);

        HttpEntity<List<Supplier>> entity = new HttpEntity<>(headers);

        ResponseEntity<List<Supplier>> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
                });

        List<Supplier> pageSuppliers = responseEntity.getBody();
        log.info(pageSuppliers.toString());

        model.addAttribute("suppliers", pageSuppliers);
        return "suppliers";
    }

    @GetMapping("/bill")
    public String bill(Model model, @AuthenticationPrincipal OidcUser principal) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)
                authentication;

        OAuth2AuthorizedClient oauth2Client =
                oauth2ClientService.loadAuthorizedClient(oauthToken.
                        getAuthorizedClientRegistrationId(), oauthToken.getName());

        String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
        System.out.println("jwtAccessToken = " + jwtAccessToken);
        System.out.println("Principal = " + principal);
        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("idTokenValue = " + idTokenValue);

        String billServiceUrl = "http://localhost:8092/bills/full/1";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtAccessToken);

        HttpEntity<Bill> entity = new HttpEntity<>(headers);

        ResponseEntity<Bill> responseEntity = restTemplate.exchange(billServiceUrl,
                HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
                });

        Bill pageBill = responseEntity.getBody();
        log.info(pageBill.toString());

        model.addAttribute("bill", pageBill);
        return "bill";


    }


}
