package com.toast.talkit.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toast.talkit.exception.JsonException;

@Service
public class AuthService {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final String GET_USER_STATUS_BY_TOKEN_URL = "http://alpha-id-bo.payco.com:10003/neid_bo/neidProfileApi/getUserStatusByToken";
	private final String CLIENT_ID = "ALPlZgXNG0P2avwfq5ln";
	private final String SERVICE_PROVIDER_CODE = "talkit";
	private final String VERSION = "1.0";
	
	Map<String, String> RequestParams = new HashMap<String, String>(){
		private static final long serialVersionUID = 3076054302963988457L;
		{
			put("serviceProviderCode", SERVICE_PROVIDER_CODE);
			put("version", VERSION);
		}
	};
	
	@Autowired
	private OAuth2RestTemplate restTemplate;
	
    public boolean isValidToken(OAuth2AccessToken accessToken) {
        return (accessToken != null) && (!accessToken.isExpired());
    }
    
    public OAuth2AccessToken getToken() {
    	return restTemplate.getAccessToken();
    }

    public Map<String, String> connectToPayco(Map<String, String> requestParams, String url) {
		String ResponseFromPayco = restTemplate.postForObject(url, requestParams, String.class);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> status;
		
		try {
			status = mapper.readValue(ResponseFromPayco, Map.class);
		} catch (Exception e) {
			throw new JsonException(e.getCause());
		}

		return status;
    }
    
    public Map<String, String> getUserStatus() {
    	OAuth2AccessToken accessToken = getToken();
    	
    	if(!isValidToken(accessToken)){
    		return null;
    	}
    	
    	RequestParams.put("client_id", CLIENT_ID);
    	RequestParams.put("access_token", accessToken.getValue());
    	
    	return connectToPayco(RequestParams, GET_USER_STATUS_BY_TOKEN_URL);
    }
}
