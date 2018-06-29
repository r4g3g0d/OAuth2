package com.pakt.example.socialgoogle1;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.social.google")
public class GoogleProperties {
	
	private String appId;
	private String appSecret;
	
	public String getAppId() { 
		return this.appId; 
	}
	
	public void setAppId(String appId) { 
		this.appId = appId;
	}
	
	public String getAppSecret() {
		return this.appSecret; 
	}
	
	public void setAppSecret(String appSecret) { 
		this.appSecret = appSecret;
	}
}
