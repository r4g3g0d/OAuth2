package com.packt.example.clientauthorizationcode.oauth;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import com.packt.example.clientauthorizationcode.security.ClientUserDetails;
import com.packt.example.clientauthorizationcode.user.ClientUser;
import com.packt.example.clientauthorizationcode.user.UserRepository;

@Service
public class OAuth2ClientTokenSevices implements ClientTokenServices {

	// @formatter:off
	/*
	 * By implementing ClientTokenServices, the client application is able to
	 * retrieve, save, or remove an access token from its database, allowing you to
	 * choose where to save this data. For example, you might use another kind of
	 * database.
	 */

	@Autowired
	private UserRepository users;

	@Override
	public OAuth2AccessToken getAccessToken(OAuth2ProtectedResourceDetails resource, Authentication authentication) {
		ClientUser clientUser = getClientUser(authentication);
		String accessToken = clientUser.getAccessToken();
		Calendar expirationDate = clientUser.getAccessTokenValidity();
		if (accessToken == null)
			return null;
		DefaultOAuth2AccessToken oAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
		oAuth2AccessToken.setExpiration(expirationDate.getTime());
		oAuth2AccessToken.setRefreshToken(new DefaultOAuth2RefreshToken(clientUser.getRefreshToken()));
		return oAuth2AccessToken;
	}

	@Override
	public void saveAccessToken(OAuth2ProtectedResourceDetails resource, Authentication authentication,
			OAuth2AccessToken accessToken) {
		Calendar expirationDate = Calendar.getInstance();
		expirationDate.setTime(accessToken.getExpiration());
		ClientUser clientUser = getClientUser(authentication);
		clientUser.setAccessToken(accessToken.getValue());
		clientUser.setAccessTokenValidity(expirationDate);
		clientUser.setRefreshToken(accessToken.getRefreshToken().getValue());
		users.save(clientUser);
	}

	@Override
	public void removeAccessToken(OAuth2ProtectedResourceDetails resource, Authentication authentication) {

		ClientUser clientUser = getClientUser(authentication);
		clientUser.setAccessToken(null);
		clientUser.setRefreshToken(null);
		clientUser.setAccessTokenValidity(null);
		users.save(clientUser);
	}

	private ClientUser getClientUser(Authentication authentication) {
		ClientUserDetails loggedUser = (ClientUserDetails) authentication.getPrincipal();
		Long userId = loggedUser.getClientUser().getId();
		ClientUser clientUser = users.findOne(userId);
		return clientUser;
	}

	// @formatter:on
}