package com.Mohamed.billing_service.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignClientInterceptor();
    }

    private static class FeignClientInterceptor implements RequestInterceptor {

        @Override
        public void apply(RequestTemplate requestTemplate) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("***************");
            System.out.println(authentication);
            System.out.println(getJwtToken(authentication));
            System.out.println("***************");


            if (authentication != null && authentication.isAuthenticated()) {
                String jwtToken = getJwtToken(authentication);
                requestTemplate.header("Authorization", "Bearer " + jwtToken);
            }

        }

        private String getJwtToken(Authentication authentication) {
            if (authentication instanceof JwtAuthenticationToken) {
                JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
                return jwtAuthenticationToken.getToken().getTokenValue();
            }
            return "";
        }
    }
}