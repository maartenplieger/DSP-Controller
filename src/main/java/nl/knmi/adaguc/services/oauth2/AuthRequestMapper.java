package nl.knmi.adaguc.services.oauth2;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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
	@ResponseBody
	@RequestMapping(
			path="oauth2",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void getUserInfoFromX509Cert(HttpServletResponse response, HttpServletRequest request) throws JSONException, IOException{
		JSONResponse jsonResponse = new JSONResponse(request);
		Debug.println("YESSS");
		jsonResponse.setMessage(new JSONObject().put("test","test"));
		
		jsonResponse.print(response);

	}
	
	
	

}
