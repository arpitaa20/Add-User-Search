package jsp.Spring.Filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter implements Filter {
    
    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        String uri = req.getRequestURI();
        if (!uri.equals("/addUser") && !uri.equals("/queryUser")) {
            chain.doFilter(request, response);
            return;
        }
        
        String authHeader = req.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
            return;
        }
        
        String base64Credentials = authHeader.substring("Basic ".length()).trim();
        String credentials;
        try {
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            credentials = new String(credDecoded, StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Base64 encoding");
            return;
        }
        
        String[] values = credentials.split(":", 2);
        if (values.length != 2) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials format");
            return;
        }
        
        String providedUsername = values[0];
        String providedPassword = values[1];
        
        if (!providedUsername.equals(adminUsername) || !providedPassword.equals(adminPassword)) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials");
            return;
        }
        
        chain.doFilter(request, response);
    }
}
