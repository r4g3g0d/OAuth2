package com.packt.example.authorizationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthorizationServerApplication {

    public static void main(String[] args) {
    	//Implicit flow - http://localhost:8080/oauth/authorize?client_id=clientapp&redirect_uri=http://localhost:9000/callback&response_type=token&scope=read_profile&state=xyz
        //  			- curl --header "Authorization: Bearer 7fac6cbd-379e-4df8-848a-465b69beb7f7" http://localhost:8081/api/profile
    	
    	//Authorization Code flow -req: http://localhost:8080/oauth/authorize?client_id=clientapp&redirect_uri=http://localhost:9000/callback&response_type=code&scope=read_profile
    	//						  -res: http://localhost:9000/callback?code=5sPk8A
    	//						  -req: curl -X GET http://localhost:8081/api/profile -H "authorization: Bearer 7fac6cbd-379e-4df8-848a-465b69beb7f7"
    	
    	
    	//password - flow (investigate missing grant type )
    	
    	SpringApplication.run(AuthorizationServerApplication.class, args);
    }
}
