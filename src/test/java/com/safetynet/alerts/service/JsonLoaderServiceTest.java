package com.safetynet.alerts.service;

import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class JsonLoaderServiceTest
{
	
	@Mock
	private ObjectMapper objectMapper;
	@Mock
	private ResourceLoader resourceLoader;
	  
	@Test
	void jsonLoaderExceptionThrow() throws Exception
	{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(new FileInputStream(Mockito.anyString()));
		when(root).thenThrow(IOException.class);

		// assertThrows(IOException.class,mapper);
		
	}
}