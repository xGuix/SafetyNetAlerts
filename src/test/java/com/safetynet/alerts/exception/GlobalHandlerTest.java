package com.safetynet.alerts.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;



@WebMvcTest(controllers = GlobalHandler.class)
public class GlobalHandlerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ResponseEntity<Object> responseTest;
	
	Exception notFounException;
	Exception alreadyExistsException;
	
	@Test
	void whenReadException_returnNotFound() throws Exception {
		
	}
	
}
