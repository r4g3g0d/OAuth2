package com.packt.example.clientauthorizationcode;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;

@SpringBootApplication
public class ClientAuthorizationCodeApplication implements ServletContextInitializer {
	
	
	/*After logging in you will be redirected to the auth-code-server application where you
	will need to enter the credentials defined at the Authorization Server, which in my case was
	adolfo and 123 for the username and password respectively. Grant the required
	permissions and you will be redirected back to the URI defined by the redirect_uri
	parameter that will be handled by the callback method defined within the
	UserDashboard class. After being redirected back to the client-authorization-code
	application, you should see the dashboard page with the user's profile data.
*/	
    public static void main(String[] args) {
        SpringApplication.run(ClientAuthorizationCodeApplication.class, args);
    }

    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {
        servletContext.getSessionCookieConfig().setName("client-session");
    }

}
