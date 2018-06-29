package com.packt.example.jwtasymmetricresource.api;

public class UserProfile {
	private String name;
	private String email;

	// constructor, getters and setters omitted for brevity
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserProfile(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
}
