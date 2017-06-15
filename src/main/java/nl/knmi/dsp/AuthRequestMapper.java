package nl.knmi.dsp;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import nl.knmi.adaguc.config.ConfigurationItemNotFoundException;
import nl.knmi.adaguc.services.oauth2.OAuth2Handler;
import nl.knmi.adaguc.tools.Debug;
import nl.knmi.adaguc.tools.JSONResponse;


@RestController
public class AuthRequestMapper {
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);
		MappingJackson2HttpMessageConverter converter = 
				new MappingJackson2HttpMessageConverter(mapper);
		return converter;
	}
	
	@CrossOrigin
	@ResponseBody
	@RequestMapping(
			path="oauth",
			method = RequestMethod.GET 
		)
	public void doOauth(HttpServletResponse response, HttpServletRequest request) throws JSONException, IOException, ConfigurationItemNotFoundException{
		//JSONResponse jsonResponse = new JSONResponse(request);
//		jsonResponse.setMessage(new JSONObject().put("Test","Test"));
		OAuth2Handler.doGet(request, response);
		//jsonResponse.print(response);

	}
	/**
	 * Small function to check if the Id is unknown.
	 * @param id
	 * @return
	 */
	private boolean isIdUnknown(String id) {
		return id == null || id.length() == 0;
	}
	
	@CrossOrigin
	@RequestMapping(
			path="getid",
			method = RequestMethod.GET 
		)
	public void getId(HttpServletResponse response, HttpServletRequest request) throws JSONException, IOException, ConfigurationItemNotFoundException{
		Debug.println("GetID");
		JSONResponse jsonResponse = new JSONResponse(request);

		String id = (String) request.getSession().getAttribute("user_identifier");
		String servicesAccessToken = (String) request.getSession().getAttribute("services_access_token");
		String emailAddress = (String) request.getSession().getAttribute("emailaddress");

		JSONObject jsonObj = new JSONObject();
		
		if(isIdUnknown(id)){
			jsonObj.put("error","Not signed in");
			Debug.println("GetID not signed in");
		}else{
			jsonObj.put("id",id);
			Debug.println("GetID Signed in");
		}
		
		if(servicesAccessToken == null || servicesAccessToken.length() == 0
				|| isIdUnknown(id)){
			jsonObj.put("error","Not signed in");
			Debug.println("GetID not signed in");
		}else{
			jsonObj.put("services_access_token",servicesAccessToken);
			Debug.println("GetID Signed in");
		}
		
		if(emailAddress == null || emailAddress.length() == 0
				|| isIdUnknown(id)){
			jsonObj.put("error","Not signed in");
			Debug.println("GetID not signed in");
		}else{
			jsonObj.put("email_address",emailAddress);
			Debug.println("GetID Signed in");
		}
		
		jsonResponse.setMessage(jsonObj);
		jsonResponse.print(response);

	}
	
	@CrossOrigin
	@RequestMapping(
			path="logout",
			method = RequestMethod.GET 
		)
	public void doLogout(HttpServletResponse response, HttpServletRequest request) throws JSONException, IOException, ConfigurationItemNotFoundException{
		JSONResponse jsonResponse = new JSONResponse(request);
		
		String id = (String) request.getSession().getAttribute("user_identifier");
		
		if(id !=null){
			request.getSession().setAttribute("user_identifier",null);
			jsonResponse.setMessage(new JSONObject().put("message","ok logged out"));
		}else{
			jsonResponse.setMessage(new JSONObject().put("message","already logged out"));
		}
		
		jsonResponse.print(response);

	}

}
