package it.plansoft.skills.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class TestController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String getResponse() throws JsonProcessingException {

		String response = null;

		try {
			
				
			response = "Works";
			
				
		} catch (Exception ex) {

		}
		
		return response;
		
	}

	
}