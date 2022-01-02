package com.safetynet.alerts.exception;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
class GlobalHandlerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	private GlobalHandlerTest statusController;
	
	Exception notFounException;
	Exception alreadyExistsException;
	
	@BeforeEach
	public void setup() {
	    this.mockMvc = MockMvcBuilders.standaloneSetup(statusController)
	         .setControllerAdvice(new GlobalHandler ())
	        .build();
	}
	
	@Test
	void whenReadException_returnNotFound() throws Exception {

        when(statusController.notFounException).thenThrow(new RuntimeException("Unexpected Exception"));

        mockMvc.perform(get("/api/status"))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("Unexpected Exception"));
    }
}