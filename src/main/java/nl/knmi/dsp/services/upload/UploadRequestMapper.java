package nl.knmi.dsp.services.upload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import nl.knmi.adaguc.config.ConfigurationItemNotFoundException;
import nl.knmi.adaguc.tools.Debug;
import nl.knmi.adaguc.tools.JSONResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wagenaar on 6/14/17.
 */
@RestController
public class UploadRequestMapper {

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);
        MappingJackson2HttpMessageConverter converter =
                new MappingJackson2HttpMessageConverter(mapper);
        return converter;
    }

    @ResponseBody
    @RequestMapping("upload/file")
    public void uploadFile(HttpServletResponse response, HttpServletRequest request) throws IOException {

        try {
            response.setContentType("text/plain");
            response.getWriter().append("Upload file");
        } catch (AuthenticationException e) {
            Debug.printStackTrace(e);
            new JSONResponse(request).setErrorMessage("Authentication error", 401).print(response);
        } catch (IOException e) {
            new JSONResponse(request).setException("IOException", e).print(response);
        }
    }

    @ResponseBody
    @RequestMapping("upload/preview")
    /**
     * TODO: Is the separator sign necessary to preview the CSV file?
     */
    public void previewFile(HttpServletResponse response, HttpServletRequest request) throws IOException {

        try {
            response.setContentType("text/plain");
            response.getWriter().append("Preview");
        } catch (AuthenticationException e) {
            Debug.printStackTrace(e);
            new JSONResponse(request).setErrorMessage("Authentication error", 401).print(response);
        } catch (IOException e) {
            new JSONResponse(request).setException("IOException", e).print(response);
        }
    }

    @ResponseBody
    @RequestMapping("upload/describe")
    public void describeFile(HttpServletResponse response, HttpServletRequest request) throws IOException {

        try {
            response.setContentType("text/plain");
            response.getWriter().append("Describe");
        } catch (AuthenticationException e) {
            Debug.printStackTrace(e);
            new JSONResponse(request).setErrorMessage("Authentication error", 401).print(response);
        } catch (IOException e) {
            new JSONResponse(request).setException("IOException", e).print(response);
        }
    }
}
