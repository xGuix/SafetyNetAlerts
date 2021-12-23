package com.safetynet.alerts.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class JsonLoaderServiceTest
{
	@Mock
	private ObjectMapper objectMapper;
	
	@Test
	void jsonLoaderExceptionThrow() throws Exception
	{
		/**
		 * JsonNode sendException= objectMapper.readTree(new FileInputStream(Mockito.anySet().toString()));
		 * when(sendException).thenThrow(IOException.class);
		 * assertThrows(IOException.class,()-> {});
		**/
	}
}