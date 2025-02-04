package jsp.Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import jsp.Spring.Filter.AuthenticationFilter;

@SpringBootApplication
public class AddUserSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(AddUserSearchApplication.class, args);
    }
    
    @Bean
    public FilterRegistrationBean<AuthenticationFilter> basicAuthFilterRegistration(AuthenticationFilter filter) {
        FilterRegistrationBean<AuthenticationFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(filter);
        
        registration.addUrlPatterns("/addUser", "/queryUser");
        return registration;
    }
}
